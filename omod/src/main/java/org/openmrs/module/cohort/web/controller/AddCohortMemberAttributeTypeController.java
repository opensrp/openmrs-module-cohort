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
import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortMemberAttributeType;
import org.openmrs.module.cohort.api.CohortService;
import org.openmrs.web.WebConstants;
import org.openmrs.web.taglib.fieldgen.FieldGenHandlerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * The main controller.
 */
@Controller
public class AddCohortMemberAttributeTypeController {

    @Autowired(required = true)
    @Qualifier("addCohortMemberAttributeTypeValidator")
    private Validator validator;

    protected final Log log = LogFactory.getLog(getClass());
    private SessionStatus status;

    @RequestMapping(value = "/module/cohort/addCohortMemberAttributeType", method = RequestMethod.GET)
    public void manage(ModelMap model) {
        model.addAttribute("cohortattributes", new CohortMemberAttributeType());
        List<String> formats = new ArrayList<String>(FieldGenHandlerFactory.getSingletonInstance().getHandlers().keySet());
        formats.add("java.lang.Character");
        formats.add("java.lang.Integer");
        formats.add("java.lang.Float");
        formats.add("java.lang.Boolean");
        model.addAttribute("formats", formats);
    }

    @RequestMapping(value = "/module/cohort/addCohortMemberAttributeType.form", method = RequestMethod.POST)
    public String onSubmit(WebRequest request, HttpSession httpSession, ModelMap model, @ModelAttribute("cohortattributes") CohortMemberAttributeType cohortMemberAttributeType, BindingResult errors) {
        CohortService departmentService = Context.getService(CohortService.class);
        String voided = request.getParameter("voided");
        String format = request.getParameter("format");
        this.validator.validate(cohortMemberAttributeType, errors);
        if (errors.hasErrors()) {
            System.out.println("BR has errors: " + errors.getErrorCount());
            System.out.println(errors.getAllErrors());
            return "/module/cohort/addCohortMemberAttributeType";
        } else {
            cohortMemberAttributeType.setFormat(format);
            departmentService.saveCohortMemberAttributeType(cohortMemberAttributeType);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "insertion success");
        }
        return null;
    }
}