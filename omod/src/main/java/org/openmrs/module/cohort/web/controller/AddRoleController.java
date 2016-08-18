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

import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortRole;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The main controller.
 */
@Controller
public class AddRoleController {

    @Autowired(required = true)
    @Qualifier("addRoleValidator")
    private Validator validator;
    List<Patient> list1 = new ArrayList();
    Set set1 = new HashSet();

    @RequestMapping(value = "/module/cohort/addRole", method = RequestMethod.GET)
    public void manage(ModelMap model) {
        model.addAttribute("cohortrole", new CohortRole());
        List<String> cohorttype = new ArrayList<String>();
        CohortService service1 = Context.getService(CohortService.class);
        List<CohortType> list1 = service1.getAllCohortTypes();
        for (int i = 0; i < list1.size(); i++) {
            CohortType c = list1.get(i);
            cohorttype.add(c.getName());
        }
        model.addAttribute("formats", cohorttype);
    }

    @RequestMapping(value = "module/cohort/addRole.form", method = RequestMethod.POST)
    public void onSubmit(WebRequest request, HttpSession httpSession, HttpServletRequest request1,
                         @RequestParam(required = false, value = "name") String cohort_name,
                         @ModelAttribute("cohortrole") CohortRole cohortrole, BindingResult errors, ModelMap model) {
        CohortRole cr = new CohortRole();
        CohortType cohort1 = new CohortType();
        String cohort_type_name = request.getParameter("format");
        CohortService departmentService = Context.getService(CohortService.class);
        if (!Context.isAuthenticated()) {
            errors.reject("Required");
        }
        List<CohortType> cohorttype1 = departmentService.findCohortType(cohort_type_name);
        for (int i = 0; i < cohorttype1.size(); i++) {
            cohort1 = cohorttype1.get(i);
        }
        cohortrole.setCohortType(cohort1);
        departmentService.saveCohortRole(cohortrole);
        model.addAttribute("formats", cohorttype1);
        this.validator.validate(cohortrole, errors);
        System.out.println("Before BR");
        if (errors.hasErrors()) {
            System.out.println("BR has errors: " + errors.getErrorCount());
            System.out.println(errors.getAllErrors());
        } else {
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "insertion success");
        }
    }
}
    