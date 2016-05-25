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

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.messagesource.MessageSourceService;
import org.openmrs.module.cohort.CohortType;
import org.openmrs.module.cohort.api.CohortService;
import org.openmrs.web.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * The main controller.
 */
@Controller
public class AddCohortTypeController {
	
	@Autowired(required = true)
	@Qualifier("addCohortTypeValidator")
	private Validator validator;
	
	@RequestMapping(value = "/module/cohort/addcohorttype", method = RequestMethod.GET)
	public void manage(ModelMap model) {
		model.addAttribute("cohorttype", new CohortType());
	}
	
	@RequestMapping(value = "/module/cohort/addcohorttype.form", method = RequestMethod.POST)
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
			return "/module/cohort/addcohorttype";
		}
		if (cohort_name.length() > 20) {
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "name cannot be greater than 20");
		} else {
			departmentService.saveCohort(cohorttype);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "insertion success");
		}
		return null;
	}
}