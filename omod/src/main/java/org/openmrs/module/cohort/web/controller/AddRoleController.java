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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.PatientIdentifierType;
import org.openmrs.Person;
import org.openmrs.PersonName;
import org.openmrs.Relationship;
import org.openmrs.api.LocationService;
import org.openmrs.api.PatientService;
import org.openmrs.api.PersonService;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.api.context.UserContext;
import org.openmrs.messagesource.MessageSourceService;
import org.openmrs.module.cohort.CohortM;
import org.openmrs.module.cohort.CohortMember;
import org.openmrs.module.cohort.CohortRole;
import org.openmrs.module.cohort.CohortType;
import org.openmrs.module.cohort.api.CohortService;
import org.openmrs.web.WebConstants;
import org.openmrs.web.taglib.fieldgen.FieldGenHandlerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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
	public String onSubmit(WebRequest request, HttpSession httpSession, HttpServletRequest request1,
			@RequestParam(required = false, value = "name") String cohort_name,
			@ModelAttribute("cohortrole") CohortRole cohortrole, BindingResult errors, ModelMap model) {
		CohortRole cr = new CohortRole();
		CohortType cohort1 = new CohortType();
		String cohort_type_name = request.getParameter("format");
		CohortService departmentService = Context.getService(CohortService.class);
		if (!Context.isAuthenticated()) {
			errors.reject("Required");
		}
		this.validator.validate(cohortrole, errors);
		System.out.println("Before BR");
		if (errors.hasErrors()) {
			System.out.println("BR has errors: " + errors.getErrorCount());
			System.out.println(errors.getAllErrors());
			return "/module/cohort/addRole";
		}
		if (cohort_name.length() > 255) {
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "name cannot be greater than 255");
		} else {
			List<CohortType> cohorttype1 = departmentService.findCohortType(cohort_type_name);
			for (int i = 0; i < cohorttype1.size(); i++) {
				cohort1 = cohorttype1.get(i);
			}
			cohortrole.setCohortType(cohort1);
			departmentService.saveCohortRole(cohortrole);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "insertion success");
			model.addAttribute("formats", cohorttype1);
		}
		//}
				/*catch (ParseException e) {
	 				// TODO Auto-generated catch block
	 				e.printStackTrace();
	 			}  	
	     }*/
		return null;
	}
}
	