/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.cohort.web.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Form;
import org.openmrs.User;
import org.openmrs.api.APIAuthenticationException;
import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortEncounter;
import org.openmrs.module.cohort.CohortM;
import org.openmrs.module.cohort.api.CohortService;
import org.openmrs.module.cohort.hfe.CohortFormEntrySession;
import org.openmrs.module.htmlformentry.BadFormDesignException;
import org.openmrs.module.htmlformentry.FormEntryContext.Mode;
import org.openmrs.module.htmlformentry.FormSubmissionError;
import org.openmrs.module.htmlformentry.HtmlForm;
import org.openmrs.module.htmlformentry.HtmlFormEntryUtil;
import org.openmrs.module.htmlformentry.ValidationException;
import org.openmrs.util.OpenmrsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * The controller for entering/viewing a form.
 * <p/>
 * Handles {@code htmlFormEntry.form} requests. Renders view {@code htmlFormEntry.jsp}.
 * <p/>
 * TODO: This has a bit too much logic in the onSubmit method. Move that into the FormEntrySession.
 */
@Controller
public class HtmlFormEmbedEntryController {
    protected final Log log = LogFactory.getLog(getClass());
    //    public final static String closeDialogView = "/module/htmlformentry/closeDialog";
    public final static String FORM_IN_PROGRESS_KEY = "HTML_FORM_IN_PROGRESS_KEY";
    public final static String FORM_IN_PROGRESS_VALUE = "HTML_FORM_IN_PROGRESS_VALUE";
    public final static String FORM_PATH = "/module/cohort/htmlFormEntry";
    
 	private static Map<User, Map<String, Object>> volatileUserData = new WeakHashMap<User, Map<String, Object>>();

    @RequestMapping(method = RequestMethod.GET, value = FORM_PATH)
    public void showForm() {
        // Intentionally blank. All work is done in the getFormEntrySession method
    }

    @ModelAttribute("command")
    public CohortFormEntrySession getFormEntrySession(HttpServletRequest request,
                                                      // @RequestParam doesn't pick up query parameters (in the url) in a POST, so I'm handling encounterId, modeParam, and which specially
                                                /*@RequestParam(value="mode", required=false) String modeParam,*/
                                                /*@RequestParam(value="encounterId", required=false) Integer encounterId,*/
                                                /*@RequestParam(value="which", required=false) String which,*/
                                                      @RequestParam(value = "cid", required = false) Integer cohortId,
                                                /*@RequestParam(value="personId", required=false) Integer personId,*/
                                                      @RequestParam(value = "formId", required = false) Integer formId,
                                                      @RequestParam(value = "htmlformId", required = false) Integer htmlFormId,
                                                      @RequestParam(value = "returnUrl", required = false) String returnUrl,
                                                      @RequestParam(value = "formModifiedTimestamp", required = false) Long formModifiedTimestamp,
                                                      @RequestParam(value = "encounterModifiedTimestamp", required = false) Long encounterModifiedTimestamp,
                                                      @RequestParam(value = "hasChangedInd", required = false) String hasChangedInd) throws Exception {
        long ts = System.currentTimeMillis();
        Mode mode = Mode.VIEW;
        cohortId = Integer.parseInt(request.getParameter("coh"));
        formId = 3; // <-----------------------this is where you need to mention the FORM ID or HTML Form id in below variable
        htmlFormId = Integer.parseInt(request.getParameter("htmlformId"));
//<--------------------|
        //Integer.parseInt(request.getParameter("formId"));
//        if (StringUtils.hasText(request.getParameter("personId"))) {
//            personId = Integer.valueOf(request.getParameter("personId"));
//        }
        String modeParam = request.getParameter("mode");
        if ("enter".equalsIgnoreCase(modeParam)) {
            mode = Mode.ENTER;
        } else if ("edit".equalsIgnoreCase(modeParam)) {
            mode = Mode.EDIT;
        }
        CohortM cohort = null;
        CohortEncounter encounter = null;
        Form form = null;
        HtmlForm htmlForm = null;
        if (StringUtils.hasText(request.getParameter("encounterId"))) {
            Integer encounterId = Integer.valueOf(request.getParameter("encounterId"));
            encounter = Context.getService(CohortService.class).getCohortEncounter(encounterId);
            if (encounter == null) {
                throw new IllegalArgumentException("No encounter with id=" + encounterId);
            }
            cohort = encounter.getCohort();
            cohortId = cohort.getCohortId();
            if (formId != null) { // I think formId is allowed to differ from encounter.form.id because of HtmlFormFlowsheet
                form = Context.getFormService().getForm(formId);
                htmlForm = HtmlFormEntryUtil.getService().getHtmlFormByForm(form);
                if (htmlForm == null) {
                    throw new IllegalArgumentException("No HtmlForm associated with formId " + formId);
                }
            } else {
                form = encounter.getForm();
                htmlForm = HtmlFormEntryUtil.getService().getHtmlFormByForm(encounter.getForm());
                if (htmlForm == null) {
                    throw new IllegalArgumentException("The form for the specified encounter (" + encounter.getForm() + ") does not have an HtmlForm associated with it");
                }
            }
        } else { // no encounter specified
            // get person from patientId/personId (register module uses patientId, htmlformentry uses personId)
            if (cohortId != null) {
                cohort = Context.getService(CohortService.class).getCohortId(cohortId);
            }
            // determine form
            if (htmlFormId != null) {
                htmlForm = HtmlFormEntryUtil.getService().getHtmlForm(htmlFormId);
            } else if (formId != null) {
                form = Context.getFormService().getForm(formId);
                htmlForm = HtmlFormEntryUtil.getService().getHtmlFormByForm(form);
            }
            if (htmlForm == null) {
                throw new IllegalArgumentException("You must specify either an htmlFormId or a formId for a valid html form");
            }
            String which = request.getParameter("which");
            if (StringUtils.hasText(which)) {
                if (cohort == null) {
                    throw new IllegalArgumentException("Cannot specify 'which' without specifying a person/patient");
                }
                List<CohortEncounter> encs = Context.getService(CohortService.class).getEncounters(cohort, null, null, null, Collections.singleton(form), null, false);
                if (which.equals("first")) {
                    encounter = encs.get(0);
                } else if (which.equals("last")) {
                    encounter = encs.get(encs.size() - 1);
                } else {
                    throw new IllegalArgumentException("which must be 'first' or 'last'");
                }
            }
        }
        if (mode != Mode.ENTER && cohort == null) {
            throw new IllegalArgumentException("No patient with id of patientId=" + cohortId);
        }
        CohortFormEntrySession session = null;
        if (mode == Mode.ENTER && cohort == null) {
            cohort = new CohortM();
        }
        if (encounter != null) {
            session = new CohortFormEntrySession(cohort, encounter, mode, htmlForm, request.getSession());
        } else {
            session = new CohortFormEntrySession(cohort, htmlForm, request.getSession());
        }
        if (StringUtils.hasText(returnUrl)) {
            session.setReturnUrl(returnUrl);
        }
        // Since we're not using a sessionForm, we need to check for the case where the underlying form was modified while a user was filling a form out
        if (formModifiedTimestamp != null) {
            if (!OpenmrsUtil.nullSafeEquals(formModifiedTimestamp, session.getFormModifiedTimestamp())) {
                throw new RuntimeException(Context.getMessageSourceService().getMessage("htmlformentry.error.formModifiedBeforeSubmission"));
            }
        }
        // Since we're not using a sessionForm, we need to make sure this encounter hasn't been modified since the user opened it
        if (encounter != null) {
            if (encounterModifiedTimestamp != null && !OpenmrsUtil.nullSafeEquals(encounterModifiedTimestamp, session.getEncounterModifiedTimestamp())) {
                throw new RuntimeException(Context.getMessageSourceService().getMessage("htmlformentry.error.encounterModifiedBeforeSubmission"));
            }
        }
        if (hasChangedInd != null) {
            session.setHasChangedInd(hasChangedInd);
        }
        setVolatileUserData(FORM_IN_PROGRESS_KEY, session);
        log.info("Took " + (System.currentTimeMillis() - ts) + " ms");
        session.getHtmlToDisplay();
        return session;
    }

    /*
     * I'm using a return type of ModelAndView so I can use RedirectView rather than "redirect:" and preserve the fact that
     * returnUrl values from the pre-annotated-controller days will have the context path already
     */
    @RequestMapping(method = RequestMethod.POST, value = FORM_PATH)
    public ModelAndView handleSubmit(@ModelAttribute("command") CohortFormEntrySession session,
                                     Errors errors,
                                     HttpServletRequest request,
                                     Model model) throws Exception {
        try {
            List<FormSubmissionError> validationErrors = session.getSubmissionController().validateSubmission(session.getContext(), request);
            if (validationErrors != null && validationErrors.size() > 0) {
                errors.reject("Fix errors");
            }
        } catch (Exception ex) {
            log.error("Exception during form validation", ex);
            errors.reject("Exception during form validation, see log for more details: " + ex);
        }
        if (errors.hasErrors()) {
            return new ModelAndView(FORM_PATH, "command", session);
        }
        // no form validation errors, proceed with submission
        session.prepareForSubmit();
        if (session.getContext().getMode() == Mode.ENTER && session.hasCohortTag() && session.getCohort() == null
                && (session.getSubmissionActions().getPersonsToCreate() == null || session.getSubmissionActions().getPersonsToCreate().size() == 0)) {
            throw new IllegalArgumentException("This form is not going to create an Cohort");
        }
        if (session.getContext().getMode() == Mode.ENTER && session.hasEncouterTag() && (session.getSubmissionActions().getEncountersToCreate() == null || session.getSubmissionActions().getEncountersToCreate().size() == 0)) {
            throw new IllegalArgumentException("This form is not going to create an encounter");
        }
        try {
            session.getSubmissionController().handleFormSubmission(session, request);
            session.applyActions();
            String successView = session.getReturnUrlWithParameters();
            if (successView == null) {
                successView = request.getContextPath() + "/module/cohort/dashboard.form" + getQueryPrameters(request, session);
            }
            if (StringUtils.hasText(request.getParameter("closeAfterSubmission"))) {
//                return new ModelAndView(closeDialogView, "dialogToClose", request.getParameter("closeAfterSubmission"));
            } else {
                return new ModelAndView(new RedirectView(successView));
            }
        } catch (ValidationException ex) {
            log.error("Invalid input:", ex);
            errors.reject(ex.getMessage());
        } catch (BadFormDesignException ex) {
            log.error("Bad Form Design:", ex);
            errors.reject(ex.getMessage());
        } catch (Exception ex) {
            log.error("Exception trying to submit form", ex);
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            errors.reject("Exception! " + ex.getMessage() + "<br/>" + sw.toString());
        }
        // if we get here it's because we caught an error trying to submit/apply
        return new ModelAndView(FORM_PATH, "command", session);
    }

    protected String getQueryPrameters(HttpServletRequest request, CohortFormEntrySession formEntrySession) {
        return "?cid=" + formEntrySession.getCohort().getCohortId();
    }
    
    /**
	 * Set a piece of information for the currently authenticated user. This information is stored
	 * only temporarily. When a new module is loaded or the server is restarted, this information
	 * will disappear
	 *
	 * @param key identifying string for this information
	 * @param value information to be stored
	 */
	public static void setVolatileUserData(String key, Object value) {
		User u = Context.getAuthenticatedUser();
		if (u == null) {
			throw new APIAuthenticationException();
		}
		Map<String, Object> myData = volatileUserData.get(u);
		if (myData == null) {
			myData = new HashMap<String, Object>();
			volatileUserData.put(u, myData);
		}
		myData.put(key, value);
	}
}