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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.*;
import org.openmrs.api.*;
import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.*;
import org.openmrs.module.cohort.api.CohortService;
import org.openmrs.web.WebConstants;
import org.openmrs.web.taglib.fieldgen.FieldGenHandlerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * The main controller.
 */
@Controller
public class EditCohortController {

    protected final Log log = LogFactory.getLog(getClass());

    @RequestMapping(value = "/module/cohort/editCohort", method = RequestMethod.GET)
    public void manageEditCohort(ModelMap model, HttpServletRequest request, @RequestParam("cid") Integer id, @ModelAttribute("cohortmodule") CohortM cohort) {
        CohortService service1 = Context.getService(CohortService.class);
        List<String> cohorttype = new ArrayList<String>();
        List<String> cohortprg = new ArrayList<String>();
        LocationService service = Context.getLocationService();
        List<Location> formats = service.getAllLocations();
        model.addAttribute("locations", formats);
        List<CohortType> list1 = service1.getAllCohortTypes();
        for (int i = 0; i < list1.size(); i++) {
            CohortType c = list1.get(i);
            cohorttype.add(c.getName());
        }
        List<CohortProgram> list2 = service1.findCohortProg();
        for (int j = 0; j < list2.size(); j++) {
            CohortProgram a = list2.get(j);
            cohortprg.add(a.getName());
        }
        model.addAttribute("formats", cohorttype);
        model.addAttribute("formats1", cohortprg);
        List<CohortM> cohort1 = service1.findCohort(id);
        for (int j = 0; j < cohort1.size(); j++) {
            cohort = cohort1.get(j);
            model.addAttribute("cohortmodule", cohort);
        }
    }

    @RequestMapping(value = "/module/cohort/editCohort.form", method = RequestMethod.POST)
    public void manageEditCohort1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("cid") Integer id,
                                  @RequestParam(required = false, value = "voidReason") String voidReason, @ModelAttribute("cohortmodule") CohortM cohort) throws Exception {
        CohortService service1 = Context.getService(CohortService.class);
        List<String> cohorttype = new ArrayList<String>();
        LocationService service = Context.getLocationService();
        List<Location> formats = service.getAllLocations();
        model.addAttribute("locations", formats);
        List<CohortType> list1 = service1.getAllCohortTypes();
        for (int i = 0; i < list1.size(); i++) {
            CohortType c = list1.get(i);
            cohorttype.add(c.getName());
        }
        model.addAttribute("formats", cohorttype);
        List<String> cohortprg = new ArrayList<String>();
        List<CohortProgram> list2 = service1.findCohortProg();
        for (int j = 0; j < list2.size(); j++) {
            CohortProgram a = list2.get(j);
            cohortprg.add(a.getName());
        }
        model.addAttribute("formats1", cohortprg);
        List<CohortM> cohort1 = service1.findCohort(id);
        for (int j = 0; j < cohort1.size(); j++) {
            cohort = cohort1.get(j);
        }
        if ("Edit Cohort".equals(request.getParameter("Edit Cohort"))) {
            String givenName = request.getParameter("name");
            String givenDescription = request.getParameter("description");
            String givenStartDate = request.getParameter("startDate");
            String givenEndDate = request.getParameter("endDate");
            String givenLocation = request.getParameter("location");
            if (!givenName.equals("") && !givenDescription.equals("") && !givenStartDate.equals("") && !givenEndDate.equals("") && !givenLocation.equals("")) {
                java.util.Date parsedStartDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(givenStartDate);
                java.util.Date parsedEndDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(givenEndDate);
                if (parsedEndDate.before(parsedStartDate)) {
                    httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "End date must be after Start date");
                } else {
                    cohort.setName(givenName);
                    cohort.setDescription(givenDescription);
                    cohort.setStartDate(parsedStartDate);
                    cohort.setEndDate(parsedEndDate);
                    for (Location locations : service.getAllLocations()) {
                        if (locations.getName().equals(givenLocation)) {
                            cohort.setClocation(locations);
                            break;
                        }
                    }
                    service1.saveCohort(cohort);
                    httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Cohort Edit Success");
                }
            } else {
                httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Fields cannot be empty");
            }

        }
        if ("void".equalsIgnoreCase(request.getParameter("void"))) {
            if (voidReason == "") {
                httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "void Reason cannot be null");
            }
            cohort.setVoided(true);
            cohort.setVoidReason(voidReason);
            service1.saveCohort(cohort);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "cohort voided success");
        }
        if ("delete".equals(request.getParameter("delete"))) {
            service1.purgeCohort(cohort);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "delete success");
        }
    }

    @RequestMapping(value = "/module/cohort/editCohortType", method = RequestMethod.GET)
    public void manageEditCohortType(ModelMap model, HttpServletRequest request, @RequestParam(required = false, value = "voidReason") String voidReason, @RequestParam("ctypeid") Integer id, @ModelAttribute("cohorttype") CohortType cohort) {
        CohortService service1 = Context.getService(CohortService.class);
        List<CohortType> cohort1 = service1.findCohortType(id);
        for (int j = 0; j < cohort1.size(); j++) {
            cohort = cohort1.get(j);
            model.addAttribute("cohorttype", cohort);
        }
    }

    @RequestMapping(value = "/module/cohort/editCohortType.form", method = RequestMethod.POST)
    public void manageEditCohortType1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("ctypeid") Integer id, @RequestParam(required = false, value = "voidReason") String voidReason, @ModelAttribute("cohorttype") CohortType cohortType) {
        CohortService service1 = Context.getService(CohortService.class);
        List<CohortType> cohort1 = service1.findCohortType(id);
        for (int j = 0; j < cohort1.size(); j++) {
            cohortType = cohort1.get(j);
        }
        if ("edit".equals(request.getParameter("edit"))) {
            String givenTypeName = request.getParameter("name");
            String givenTypeDescription = request.getParameter("description");
            if (!givenTypeName.equals("") && !givenTypeDescription.equals("")) {
                System.out.println("FIRST: " + cohortType.getName());
                cohortType.setName(givenTypeName);
                cohortType.setDescription(givenTypeDescription);
                service1.saveCohort(cohortType);
                httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Edit Success");
            } else {
                httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Fields cannot be blank");
            }
        }
        if ("void".equalsIgnoreCase(request.getParameter("void"))) {
            if (voidReason == "") {
                httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "void Reason cannot be null");
            }
            cohortType.setVoided(true);
            cohortType.setVoidReason(voidReason);
            service1.saveCohort(cohortType);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "voided success");
        }
        if ("delete".equals(request.getParameter("delete"))) {
            service1.purgeCohortType(cohortType);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "delete success");
        }
    }

    @RequestMapping(value = "/module/cohort/editCohortProgram", method = RequestMethod.GET)
    public void manageEditCohortProgram(ModelMap model, HttpServletRequest request, @RequestParam("cpid") Integer id, @ModelAttribute("cohortprogram") CohortProgram cp) {
        CohortService service1 = Context.getService(CohortService.class);
        List<CohortProgram> cohort1 = service1.findCohortProgram(id);
        for (int j = 0; j < cohort1.size(); j++) {
            cp = cohort1.get(j);
            model.addAttribute("cohortprogram", cp);
        }
    }

    @RequestMapping(value = "/module/cohort/editCohortProgram.form", method = RequestMethod.POST)
    public void manageEditCohortProgram1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("cpid") Integer id, @RequestParam(required = false, value = "voidReason") String voidReason, @ModelAttribute("cohortprogram") CohortProgram cohortProgram) {
        CohortService service1 = Context.getService(CohortService.class);
        List<CohortProgram> cohort1 = service1.findCohortProgram(id);
        for (int j = 0; j < cohort1.size(); j++) {
            cohortProgram = cohort1.get(j);
        }
        if ("edit".equals(request.getParameter("edit"))) {
            String givenName = request.getParameter("name");
            String givenDescription = request.getParameter("description");
            if (!givenName.equals("") && !givenDescription.equals("")) {
                cohortProgram.setName(givenName);
                cohortProgram.setDescription(givenDescription);
                service1.saveCohortProgram(cohortProgram);
                httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Edit Success");
            } else {
                httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Fields cannot be left empty");
            }
        }
        if ("void".equalsIgnoreCase(request.getParameter("void"))) {
            if (voidReason == "") {
                httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "void Reason cannot be null");
            }
            cohortProgram.setVoided(true);
            cohortProgram.setVoidReason(voidReason);
            service1.saveCohortProgram(cohortProgram);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "cohort voided success");
        }
        if ("delete".equals(request.getParameter("delete"))) {
            service1.purgeCohortProgram(cohortProgram);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "delete success");
        }
    }

    @RequestMapping(value = "/module/cohort/editCohortAttributesType", method = RequestMethod.GET)
    public void manageEditCohortAttributeType(ModelMap model, HttpServletRequest request, @RequestParam("cat") Integer id, @ModelAttribute("cohortattributes") CohortAttributeType cohort) {
        CohortService service1 = Context.getService(CohortService.class);
        List<CohortAttributeType> cohort1 = service1.findCohortAttType(id);
        List<String> formats = new ArrayList<String>(FieldGenHandlerFactory.getSingletonInstance().getHandlers().keySet());
        formats.add("java.lang.Character");
        formats.add("java.lang.Integer");
        formats.add("java.lang.Float");
        formats.add("java.lang.Boolean");
        model.addAttribute("formats", formats);
        for (int j = 0; j < cohort1.size(); j++) {
            cohort = cohort1.get(j);
            model.addAttribute("cohortattributes", cohort);
        }
    }

    @RequestMapping(value = "/module/cohort/editCohortAttributesType.form", method = RequestMethod.POST)
    public void manageEditCohortAttributeType1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("cat") Integer id, @RequestParam(required = false, value = "voidReason") String voidReason, @ModelAttribute("cohortattributes") CohortAttributeType cohortAttributeType) {
        CohortService service1 = Context.getService(CohortService.class);
        List<CohortAttributeType> cohort1 = service1.findCohortAttType(id);
        List<String> formats = new ArrayList<String>(FieldGenHandlerFactory.getSingletonInstance().getHandlers().keySet());
        formats.add("java.lang.Character");
        formats.add("java.lang.Integer");
        formats.add("java.lang.Float");
        formats.add("java.lang.Boolean");
        model.addAttribute("formats", formats);
        for (int j = 0; j < cohort1.size(); j++) {
            cohortAttributeType = cohort1.get(j);
        }
        if ("edit".equals(request.getParameter("edit"))) {
            String givenName = request.getParameter("name");
            String givenDescription = request.getParameter("description");
            String givenFormat = request.getParameter("format");
            if (!givenName.equals("") || !givenDescription.equals("") || !givenFormat.equals("")) {
                cohortAttributeType.setName(givenName);
                cohortAttributeType.setDescription(givenDescription);
                cohortAttributeType.setFormat(givenFormat);
                service1.saveCohort(cohortAttributeType);
                httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Edit Success");
            } else {
                httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Fields Cannot be blank");
            }
        }
        if ("void".equalsIgnoreCase(request.getParameter("void"))) {
            if (voidReason == "") {
                httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Void Reason cannot be null");
            }
            cohortAttributeType.setVoided(true);
            cohortAttributeType.setVoidReason(voidReason);
            service1.saveCohort(cohortAttributeType);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Cohort Attribute Type Voided success");
        }
        if ("delete".equals(request.getParameter("delete"))) {
            service1.purgeCohortAttributes(cohortAttributeType);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Delete success");
        }
    }


    @RequestMapping(value = "/module/cohort/editCohortMemberAttributeType", method = RequestMethod.GET)
    public void manageEditCohortMemberAttributeType(ModelMap model, HttpServletRequest request, @RequestParam("cmat") Integer id, @ModelAttribute("cohortattributes") CohortMemberAttributeType cohort) {
        CohortService service1 = Context.getService(CohortService.class);
        List<CohortMemberAttributeType> cohort1 = service1.findCohortMemAttType(id);
        List<String> formats = new ArrayList<String>(FieldGenHandlerFactory.getSingletonInstance().getHandlers().keySet());
        formats.add("java.lang.Character");
        formats.add("java.lang.Integer");
        formats.add("java.lang.Float");
        formats.add("java.lang.Boolean");
        model.addAttribute("formats", formats);
        for (int j = 0; j < cohort1.size(); j++) {
            cohort = cohort1.get(j);
            model.addAttribute("cohortattributes", cohort);
        }
    }

    @RequestMapping(value = "/module/cohort/editCohortMemberAttributeType.form", method = RequestMethod.POST)
    public void manageEditCohortMemberAttributeType1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("cmat") Integer id, @RequestParam(required = false, value = "voidReason") String voidReason, @ModelAttribute("cohortattributes") CohortMemberAttributeType cohortMemberAttributeType) {
        CohortService service1 = Context.getService(CohortService.class);
        List<CohortMemberAttributeType> cohort1 = service1.findCohortMemAttType(id);
        List<String> formats = new ArrayList<String>(FieldGenHandlerFactory.getSingletonInstance().getHandlers().keySet());
        formats.add("java.lang.Character");
        formats.add("java.lang.Integer");
        formats.add("java.lang.Float");
        formats.add("java.lang.Boolean");
        model.addAttribute("formats", formats);
        for (int j = 0; j < cohort1.size(); j++) {
            cohortMemberAttributeType = cohort1.get(j);
        }
        if ("edit".equals(request.getParameter("edit"))) {
            String givenName = request.getParameter("name");
            String givenDescription = request.getParameter("description");
            String givenFormat = request.getParameter("format");
            if (!givenName.equals("") && !givenDescription.equals("") && !givenFormat.equals("")) {
                cohortMemberAttributeType.setName(givenName);
                cohortMemberAttributeType.setDescription(givenDescription);
                cohortMemberAttributeType.setFormat(givenFormat);
                service1.saveCohortMemberAttributeType(cohortMemberAttributeType);
                httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Edit Success");
            } else {
                httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Fields cannot be left blank");
            }
        }
        if ("void".equalsIgnoreCase(request.getParameter("void"))) {
            if (voidReason == "") {
                httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "void Reason cannot be null");
            }
            cohortMemberAttributeType.setVoided(true);
            cohortMemberAttributeType.setVoidReason(voidReason);
            service1.saveCohortMemberAttributeType(cohortMemberAttributeType);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "cohort voided success");
        }
        if ("delete".equals(request.getParameter("delete"))) {
            service1.purgeCohortMemberAttributeType(cohortMemberAttributeType);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "delete success");
        }
    }

    @RequestMapping(value = "/module/cohort/editCohortAttributes", method = RequestMethod.GET)
    public void manageEditCohortAttribute(ModelMap model, HttpServletRequest request, @RequestParam("ca") Integer id, @ModelAttribute("cohortatt") CohortAttribute cohort) {
        CohortService s = Context.getService(CohortService.class);
        List<String> cohorta = new ArrayList<String>();
        List<String> cohortm = new ArrayList<String>();
        List<CohortM> m = s.findCohorts();
        for (int j = 0; j < m.size(); j++) {
            CohortM c = m.get(j);
            cohortm.add(c.getName());
        }
        List<CohortAttributeType> att = s.findCohortAttributes();
        for (int i = 0; i < att.size(); i++) {
            CohortAttributeType a = att.get(i);
            cohorta.add(a.getName());
        }
        model.addAttribute("formats", cohorta);
        model.addAttribute("formats1", cohortm);
        List<CohortAttribute> cohort1 = s.findCohortAtt(id);
        for (int j = 0; j < cohort1.size(); j++) {
            cohort = cohort1.get(j);
            model.addAttribute("cohortatt", cohort);
        }
    }

    @RequestMapping(value = "/module/cohort/editCohortAttributes.form", method = RequestMethod.POST)
    public void manageEditCohortAttribute1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("ca") Integer id, @RequestParam(required = false, value = "voidReason") String voidReason, @ModelAttribute("cohortatt") CohortAttribute cohort) {
        CohortService service1 = Context.getService(CohortService.class);
        List<String> cohorta = new ArrayList<String>();
        List<String> cohortm = new ArrayList<String>();
        List<CohortM> m = service1.findCohorts();
        for (int j = 0; j < m.size(); j++) {
            CohortM c = m.get(j);
            cohortm.add(c.getName());
        }
        List<CohortAttributeType> att = service1.findCohortAttributes();
        for (int i = 0; i < att.size(); i++) {
            CohortAttributeType a = att.get(i);
            cohorta.add(a.getName());
        }
        model.addAttribute("formats", cohorta);
        model.addAttribute("formats1", cohortm);
        List<CohortAttribute> cohort1 = service1.findCohortAtt(id);
        for (int j = 0; j < cohort1.size(); j++) {
            cohort = cohort1.get(j);
        }
        if ("void".equalsIgnoreCase(request.getParameter("void"))) {
            if (voidReason == "") {
                httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "void Reason cannot be null");
            }
            cohort.setVoided(true);
            cohort.setVoidReason(voidReason);
            service1.saveCohortAttributes(cohort);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "cohort voided success");
        }
        if ("delete".equals(request.getParameter("delete"))) {
            service1.purgeCohortAtt(cohort);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "delete success");
        }
    }

    @RequestMapping(value = "/module/cohort/editCohortMemberAttribute", method = RequestMethod.GET)
    public void manageEditCohortMemberAttribute(ModelMap model, HttpServletRequest request, @RequestParam("cma") Integer id, @ModelAttribute("cohortatt") CohortMemberAttribute cohort) {
        CohortService s = Context.getService(CohortService.class);
        List<CohortMember> cm = s.findCohortMember();
        List<String> p = new ArrayList<String>();
        List<String> cohorta = new ArrayList<String>();
        List<CohortMemberAttributeType> att = s.findCohortMemberAttributeType();
        for (int i = 0; i < att.size(); i++) {
            CohortMemberAttributeType a = att.get(i);
            cohorta.add(a.getName());
        }
        for (int b = 0; b < cm.size(); b++) {
            CohortMember cmm = cm.get(b);
            Person pp = cmm.getPerson();
            p.add(pp.getGivenName());
        }
        model.addAttribute("formats", cohorta);
        model.addAttribute("formats1", p);
        List<CohortMemberAttribute> cohort1 = s.findCohortMemAtt(id);
        for (int j = 0; j < cohort1.size(); j++) {
            cohort = cohort1.get(j);
            model.addAttribute("cohortatt", cohort);
        }
    }

    @RequestMapping(value = "/module/cohort/editCohortMemberAttribute.form", method = RequestMethod.POST)
    public void manageEditCohortMemberAttribute1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("cma") Integer id, @RequestParam(required = false, value = "voidReason") String voidReason, @ModelAttribute("cohortatt") CohortMemberAttribute cohort) {
        CohortService s = Context.getService(CohortService.class);
        List<CohortMember> cm = s.findCohortMember();
        List<String> p = new ArrayList<String>();
        List<String> cohorta = new ArrayList<String>();
        List<CohortMemberAttributeType> att = s.findCohortMemberAttributeType();
        for (int i = 0; i < att.size(); i++) {
            CohortMemberAttributeType a = att.get(i);
            cohorta.add(a.getName());
        }
        for (int b = 0; b < cm.size(); b++) {
            CohortMember cmm = cm.get(b);
            Person pp = cmm.getPerson();
            p.add(pp.getGivenName());
        }
        model.addAttribute("formats", cohorta);
        model.addAttribute("formats1", p);
        List<CohortMemberAttribute> cohort1 = s.findCohortMemAtt(id);
        for (int j = 0; j < cohort1.size(); j++) {
            cohort = cohort1.get(j);
        }
        if ("void".equalsIgnoreCase(request.getParameter("void"))) {
            if (voidReason == "") {
                httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "void Reason cannot be null");
            }
            cohort.setVoided(true);
            cohort.setVoidReason(voidReason);
            s.saveCohortMemberAttribute(cohort);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "cohort voided success");
        }
        if ("delete".equals(request.getParameter("delete"))) {
            s.purgeCohortMemberAttribute(cohort);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "delete success");
        }
    }

    @RequestMapping(value = "/module/cohort/editenc", method = RequestMethod.GET)
    public void manageEditEncounter(ModelMap model, HttpServletRequest request, @RequestParam("encid") Integer id, @ModelAttribute("cohortencounters") CohortEncounter cohort) {
        List<String> cohortnames = new ArrayList<String>();
        CohortService cservice = Context.getService(CohortService.class);
        List<CohortM> list1 = cservice.findCohorts();
        for (int i = 0; i < list1.size(); i++) {
            CohortM c = list1.get(i);
            cohortnames.add(c.getName());
        }
        EncounterService enctype = Context.getEncounterService();
        List<String> etype = new ArrayList<String>();
        List<EncounterType> enctypes = enctype.getAllEncounterTypes();
        for (int l = 0; l < enctypes.size(); l++) {
            EncounterType e = enctypes.get(l);
            etype.add(e.getName());
        }
        FormService fs = Context.getFormService();
        List<String> fo = new ArrayList<String>();
        List<Form> form = fs.getAllForms();
        for (int h = 0; h < form.size(); h++) {
            Form b = form.get(h);
            fo.add(b.getName());
        }
        List<String> cvisit = new ArrayList<String>();
        List<CohortVisit> cv = cservice.findCohortVisit();
        for (int k = 0; k < cv.size(); k++) {
            CohortVisit e = cv.get(k);
            cvisit.add(e.getCohortVisitId().toString());
        }
        LocationService service = Context.getLocationService();
        List<Location> formats = service.getAllLocations();
        model.addAttribute("locations", formats);
        model.addAttribute("formats1", cohortnames);
        model.addAttribute("forms", fo);
        model.addAttribute("visits", cvisit);
        model.addAttribute("enctypes", etype);
        List<CohortEncounter> enc = cservice.findCohortEnc(id);
        for (int k = 0; k < enc.size(); k++) {
            cohort = enc.get(k);
            model.addAttribute("cohortencounters", cohort);
        }

    }

    @RequestMapping(value = "/module/cohort/editenc.form", method = RequestMethod.POST)
    public void manageEditEncounter1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("encid") Integer id, @RequestParam(required = false, value = "voidReason") String voidReason, @ModelAttribute("cohortencounters") CohortEncounter cohort) {
        List<String> cohortnames = new ArrayList<String>();
        CohortService cservice = Context.getService(CohortService.class);
        List<CohortM> list1 = cservice.findCohorts();
        for (int i = 0; i < list1.size(); i++) {
            CohortM c = list1.get(i);
            cohortnames.add(c.getName());
        }
        EncounterService enctype = Context.getEncounterService();
        List<String> etype = new ArrayList<String>();
        List<EncounterType> enctypes = enctype.getAllEncounterTypes();
        for (int l = 0; l < enctypes.size(); l++) {
            EncounterType e = enctypes.get(l);
            etype.add(e.getName());
        }
        FormService fs = Context.getFormService();
        List<String> fo = new ArrayList<String>();
        List<Form> form = fs.getAllForms();
        for (int h = 0; h < form.size(); h++) {
            Form b = form.get(h);
            fo.add(b.getName());
        }
        List<String> cvisit = new ArrayList<String>();
        List<CohortVisit> cv = cservice.findCohortVisit();
        for (int k = 0; k < cv.size(); k++) {
            CohortVisit e = cv.get(k);
            cvisit.add(e.getCohortVisitId().toString());
        }
        LocationService service = Context.getLocationService();
        List<Location> formats = service.getAllLocations();
        model.addAttribute("locations", formats);
        model.addAttribute("formats1", cohortnames);
        model.addAttribute("forms", fo);
        model.addAttribute("visits", cvisit);
        model.addAttribute("enctypes", etype);
        List<CohortEncounter> enc = cservice.findCohortEnc(id);
        for (int k = 0; k < enc.size(); k++) {
            cohort = enc.get(k);
        }
        if ("void".equalsIgnoreCase(request.getParameter("void"))) {
            if (voidReason == "") {
                httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "void Reason cannot be null");
            }
            cohort.setVoided(true);
            cohort.setVoidReason(voidReason);
            cservice.saveCohortEncounters(cohort);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "cohort voided success");
        }
        if ("delete".equals(request.getParameter("delete"))) {
            cservice.purgeCohortEncounters(cohort);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "delete success");
        }
    }

    @RequestMapping(value = "/module/cohort/editvisit", method = RequestMethod.GET)
    public void manageEditVisit(ModelMap model, HttpServletRequest request, @RequestParam("visitid") Integer id, @ModelAttribute("cohortvisit") CohortVisit cohort) {
        List<String> cohortnames = new ArrayList<String>();
        VisitService enctype = Context.getVisitService();
        List<String> etype = new ArrayList<String>();
        List<VisitType> enctypes = enctype.getAllVisitTypes();
        CohortService cservice = Context.getService(CohortService.class);
        List<CohortM> list1 = cservice.findCohorts();
        for (int i = 0; i < list1.size(); i++) {
            CohortM c = list1.get(i);
            cohortnames.add(c.getName());
        }
        for (int l = 0; l < enctypes.size(); l++) {
            VisitType e = enctypes.get(l);
            etype.add(e.getName());
        }
        model.addAttribute("visittypes", etype);
        LocationService service = Context.getLocationService();
        List<Location> formats = service.getAllLocations();
        model.addAttribute("locations", formats);
        model.addAttribute("formats1", cohortnames);
        List<CohortVisit> visits = cservice.findCohortVisit(id);
        for (int a = 0; a < visits.size(); a++) {
            cohort = visits.get(a);
            model.addAttribute("cohortvisit", cohort);
        }
    }

    @RequestMapping(value = "/module/cohort/editvisit.form", method = RequestMethod.POST)
    public void manageEditVisit1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("visitid") Integer id, @RequestParam(required = false, value = "voidReason") String voidReason, @ModelAttribute("cohortvisit") CohortVisit cohort) {
        List<String> cohortnames = new ArrayList<String>();
        VisitService enctype = Context.getVisitService();
        List<String> etype = new ArrayList<String>();
        List<VisitType> enctypes = enctype.getAllVisitTypes();
        CohortService cservice = Context.getService(CohortService.class);
        List<CohortM> list1 = cservice.findCohorts();
        for (int i = 0; i < list1.size(); i++) {
            CohortM c = list1.get(i);
            cohortnames.add(c.getName());
        }
        for (int l = 0; l < enctypes.size(); l++) {
            VisitType e = enctypes.get(l);
            etype.add(e.getName());
        }
        model.addAttribute("visittypes", etype);
        LocationService service = Context.getLocationService();
        List<Location> formats = service.getAllLocations();
        model.addAttribute("locations", formats);
        model.addAttribute("formats1", cohortnames);
        List<CohortVisit> visits = cservice.findCohortVisit(id);
        for (int a = 0; a < visits.size(); a++) {
            cohort = visits.get(a);
        }
        if ("void".equalsIgnoreCase(request.getParameter("void"))) {
            if (voidReason == "") {
                httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "void Reason cannot be null");
            }
            cohort.setVoided(true);
            cohort.setVoidReason(voidReason);
            cservice.saveCohortVisit(cohort);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "cohort voided success");
        }
        if ("delete".equals(request.getParameter("delete"))) {
            cservice.purgeCohortVisit(cohort);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "delete success");
        }
    }

    @RequestMapping(value = "/module/cohort/editobs", method = RequestMethod.GET)
    public void manageEditObs(ModelMap model, HttpServletRequest request, @RequestParam("obid") Integer id, @ModelAttribute("cohortobs") CohortObs cobs) {
        List<String> cohortnames = new ArrayList<String>();
        CohortService cservice = Context.getService(CohortService.class);
        List<CohortM> list1 = cservice.findCohorts();
        for (int i = 0; i < list1.size(); i++) {
            CohortM c = list1.get(i);
            cohortnames.add(c.getName());
        }
        List<Integer> etype = new ArrayList<Integer>();
        List<CohortEncounter> enctypes = cservice.findCohortEncounters();
        for (int l = 0; l < enctypes.size(); l++) {
            CohortEncounter e = enctypes.get(l);
            etype.add(e.getId());
        }
        model.addAttribute("encids", etype);
        LocationService service = Context.getLocationService();
        List<Location> formats = service.getAllLocations();
        ConceptService cs = Context.getConceptService();
        List<Concept> concept = cs.getAllConcepts();

        model.addAttribute("concepts", concept);
        model.addAttribute("locations", formats);
        model.addAttribute("formats1", cohortnames);

        List<CohortObs> visits = cservice.findCohortObs(id);
        for (int a = 0; a < visits.size(); a++) {
            cobs = visits.get(a);
            model.addAttribute("cohortvisit", cobs);
        }
    }

    @RequestMapping(value = "/module/cohort/editobs.form", method = RequestMethod.POST)
    public void manageEditObs1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("obid") Integer id, @RequestParam(required = false, value = "voidReason") String voidReason, @ModelAttribute("cohortobs") CohortObs cohort) {
        List<String> cohortnames = new ArrayList<String>();
        CohortService cservice = Context.getService(CohortService.class);
        List<CohortM> list1 = cservice.findCohorts();
        for (int i = 0; i < list1.size(); i++) {
            CohortM c = list1.get(i);
            cohortnames.add(c.getName());
        }
        List<Integer> etype = new ArrayList<Integer>();
        List<CohortEncounter> enctypes = cservice.findCohortEncounters();
        for (int l = 0; l < enctypes.size(); l++) {
            CohortEncounter e = enctypes.get(l);
            etype.add(e.getId());
        }
        model.addAttribute("encids", etype);
        LocationService service = Context.getLocationService();
        List<Location> formats = service.getAllLocations();
        ConceptService cs = Context.getConceptService();
        List<Concept> concept = cs.getAllConcepts();

        model.addAttribute("concepts", concept);
        model.addAttribute("locations", formats);
        model.addAttribute("formats1", cohortnames);

        List<CohortObs> visits = cservice.findCohortObs(id);
        for (int a = 0; a < visits.size(); a++) {
            cohort = visits.get(a);
        }
        if ("void".equalsIgnoreCase(request.getParameter("void"))) {
            if (voidReason == "") {
                httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "void Reason cannot be null");
            }
            cohort.setVoided(true);
            cohort.setVoidReason(voidReason);
            cservice.saveCohortObs(cohort);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "cohort voided success");
        }
        if ("delete".equals(request.getParameter("delete"))) {
            cservice.purgeCohortObs(cohort);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "delete success");
        }
    }

    @RequestMapping(value = "/module/cohort/editCohortRole", method = RequestMethod.GET)
    public void manageEditRoles(ModelMap model, HttpServletRequest request, @RequestParam("croleid") Integer id, @ModelAttribute("cohortrole") CohortRole crole) {

        model.addAttribute("cohortrole", new CohortRole());
        List<String> cohorttype = new ArrayList<String>();
        CohortService service1 = Context.getService(CohortService.class);
        List<CohortType> list1 = service1.getAllCohortTypes();
        for (int i = 0; i < list1.size(); i++) {
            CohortType c = list1.get(i);
            cohorttype.add(c.getName());
        }
        model.addAttribute("formats", cohorttype);
        List<CohortRole> visits = service1.findCohortRole(id);
        for (int a = 0; a < visits.size(); a++) {
            crole = visits.get(a);
            model.addAttribute("cohortrole", crole);
        }
    }

    @RequestMapping(value = "/module/cohort/editCohortRole.form", method = RequestMethod.POST)
    public void manageEditRoles1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("croleid") Integer id, @ModelAttribute("cohortrole") CohortRole cohort, BindingResult errors) {
        if (!Context.isAuthenticated()) {
            errors.reject("Required");
        } else if (request.getParameter("name").equals("") || request.getParameter("format").equals("")) {
            errors.reject("Fields required");
        } else {
            CohortService service1 = Context.getService(CohortService.class);
            List<CohortRole> cohortRolesPresent = service1.findCohortRole(id);
            for (int a = 0; a < cohortRolesPresent.size(); a++) {
                cohort = cohortRolesPresent.get(a);
            }
            if ("Edit Role".equals(request.getParameter("Edit Role"))) {
                List<CohortRole> roles = service1.findCohortRole(id);
                List<String> cohortTypeToSetInView = new ArrayList<String>();
                //Someone coded everything as a list. The list is gonna return only 1 element. Why would anyone do that -.-
                for (CohortRole role : roles) {
                    role.setName(request.getParameter("name"));
                    for (CohortType cohortType : service1.findCohortType(request.getParameter("format"))) {
                        role.setCohortType(cohortType);
                        //set the correct type
                        cohortTypeToSetInView.add(cohortType.getName());
                    }
                }
                //Find all the other types to choose from
                List<CohortType> list1 = service1.getAllCohortTypes();
                for (int i = 0; i < list1.size(); i++) {
                    CohortType c = list1.get(i);
                    if (!cohortTypeToSetInView.contains(c.getName())) {
                        cohortTypeToSetInView.add(c.getName());
                    }
                }
                //Set this in the dropdown
                model.addAttribute("formats", cohortTypeToSetInView);
                httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Edit Success");
            }

            if ("delete".equals(request.getParameter("delete"))) {
                service1.purgeCohortRole(cohort);
                httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "delete success");
            }
        }
    }
}
