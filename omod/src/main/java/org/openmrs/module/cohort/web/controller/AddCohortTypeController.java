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
import org.openmrs.module.cohort.CohortType;
import org.openmrs.module.cohort.api.CohortService;
import org.openmrs.web.WebConstants;
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

/**
 * The main controller.
 */
@Controller
public class AddCohortTypeController {

    @Autowired(required = true)
    @Qualifier("addCohortTypeValidator")
    private Validator validator;

    @RequestMapping(value = "/module/cohort/addCohortType", method = RequestMethod.GET)
    public void manage(ModelMap model) {
        model.addAttribute("cohorttype", new CohortType());
    }

    @RequestMapping(value = "/module/cohort/addCohortType.form", method = RequestMethod.POST)
    public String onSearch(WebRequest request, HttpSession httpSession, ModelMap model,
                           @RequestParam(required = false, value = "name") String cohort_name,
                           @RequestParam(required = false, value = "description") String description,
                           @ModelAttribute("cohorttype") CohortType cohorttype, BindingResult errors) {
        CohortService departmentService = Context.getService(CohortService.class);
        if (!Context.isAuthenticated()) {
            errors.reject("Required");
        }
        this.validator.validate(cohorttype, errors);
        System.out.println("Before BR");
        if (errors.hasErrors()) {
            System.out.println("BR has errors: " + errors.getErrorCount());
            System.out.println(errors.getAllErrors());
            return "/module/cohort/addCohortType";
        }
        if (cohort_name.length() > 255) {
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "name cannot be greater than 255");
        } else {
            departmentService.saveCohort(cohorttype);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "insertion success");
        }
        return null;
    }
}