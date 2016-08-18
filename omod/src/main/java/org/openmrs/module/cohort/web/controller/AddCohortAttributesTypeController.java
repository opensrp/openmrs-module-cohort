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

import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortAttributeType;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * The main controller.
 */
@Controller
public class AddCohortAttributesTypeController {
    @Autowired(required = true)
    @Qualifier("addCohortAttributeTypeValidator")
    private Validator validator;

    @RequestMapping(value = "/module/cohort/addCohortAttributesType", method = RequestMethod.GET)
    public void manage(ModelMap model) {
        model.addAttribute("cohortattributes", new CohortAttributeType());
        List<String> formats = new ArrayList<String>(FieldGenHandlerFactory.getSingletonInstance().getHandlers().keySet());
        formats.add("java.lang.Character");
        formats.add("java.lang.Integer");
        formats.add("java.lang.Float");
        formats.add("java.lang.Boolean");
        model.addAttribute("formats", formats);
    }

    @RequestMapping(value = "/module/cohort/addCohortAttributesType.form", method = RequestMethod.POST)
    public void onSubmit(WebRequest request, HttpSession httpSession, ModelMap model,
                         @RequestParam(required = false, value = "name") String attribute_type,
                         @RequestParam(required = false, value = "description") String description,
                         @ModelAttribute("cohortattributes") CohortAttributeType cohortattributes, BindingResult errors) {
        CohortService departmentService = Context.getService(CohortService.class);
        String voided = request.getParameter("voided");
        String format = request.getParameter("format");
        model.addAttribute("cohortattributes", new CohortAttributeType());
        List<String> formats = new ArrayList<String>(FieldGenHandlerFactory.getSingletonInstance().getHandlers().keySet());
        formats.add("java.lang.Character");
        formats.add("java.lang.Integer");
        formats.add("java.lang.Float");
        formats.add("java.lang.Boolean");
        model.addAttribute("formats", formats);
        cohortattributes.setName(attribute_type);
        cohortattributes.setDescription(description);
        this.validator.validate(cohortattributes, errors);
        cohortattributes.setFormat(format);
        System.out.println("Before BR");
        if (errors.hasErrors()) {
            System.out.println("BR has errors: " + errors.getErrorCount());
            System.out.println(errors.getAllErrors());
        } else {
            departmentService.saveCohort(cohortattributes);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "insertion success");
        }
    }
}