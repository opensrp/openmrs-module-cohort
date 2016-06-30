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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.PersonName;
import org.openmrs.Privilege;
import org.openmrs.api.PatientService;
import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;
import org.openmrs.messagesource.MessageSourceService;
import org.openmrs.module.cohort.CohortAttribute;
import org.openmrs.module.cohort.CohortAttributeType;
import org.openmrs.module.cohort.CohortM;
import org.openmrs.module.cohort.CohortMember;
import org.openmrs.module.cohort.CohortMemberAttribute;
import org.openmrs.module.cohort.CohortMemberAttributeType;
import org.openmrs.module.cohort.CohortType;
import org.openmrs.module.cohort.api.CohortService;
import org.openmrs.web.WebConstants;
import org.openmrs.web.taglib.fieldgen.FieldGenHandlerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
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
public class AddCohortMemberAttributesController {
	
	protected final Log log = LogFactory.getLog(getClass());
	private SessionStatus status;
	
	@RequestMapping(value = "/module/cohort/addCohortMemberAttribute", method = RequestMethod.GET)
	public void manage(@RequestParam(required = false, value = "cma") Integer id, WebRequest request, ModelMap model, @ModelAttribute("cohortatt") CohortMemberAttribute cohortattribute) {
		int a = 0;
		CohortMember cohort = null;
		CohortMemberAttributeType coat = null;
		CohortService s = Context.getService(CohortService.class);
		List<CohortMemberAttributeType> ls = s.findCohortMemberAttributeType();
		model.addAttribute("attypes", ls);
		String atype = request.getParameter("cohortMemberAttributeTypeId");
		if (StringUtils.hasText(atype)) {
			a = Integer.parseInt(atype);
		}
		List<CohortMemberAttributeType> cat = s.findCohortMemAttType(a);
		if (cat.size() > 0) {
			coat = cat.get(0);
		}
		List<CohortMember> c = s.getCohortMember(id);
		if (c.size() > 0) {
			cohort = c.get(0);
		}
		cohortattribute.setCohortMember(cohort);
		model.addAttribute("cohortmember", cohort);
		cohortattribute.setCohortMemberAttributeType(coat);
		model.addAttribute("cohortatt", cohortattribute);
		model.addAttribute("selectedvalue", request.getParameter("selectedvalue"));
	}
	
	@RequestMapping(value = "/module/cohort/addCohortMemberAttribute.form", method = RequestMethod.POST)
	public ModelAndView onSubmit(WebRequest request, HttpSession httpSession, ModelMap model,
			@RequestParam(required = false, value = "cohortMemberAttributeTypeId") Integer cohort_attribute_type,
			@RequestParam(required = false, value = "selectedvalue") String description,
			@ModelAttribute("cohortatt") CohortMemberAttribute cohortattributes, BindingResult errors) {
		CohortService departmentService = Context.getService(CohortService.class);
		CohortMemberAttributeType a = null;
		Integer id = Integer.parseInt(request.getParameter("cma"));
		List<CohortMember> cohort1 = departmentService.getCohortMember(id);
		if (description == "") {
			httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Values cannot be null");
		} else {
			List<CohortMemberAttributeType> att = departmentService.findCohortMemAttType(cohort_attribute_type);
			for (int i = 0; i < att.size(); i++) {
				a = att.get(i);
			}
			cohortattributes.setCohortMember(cohort1.get(0));
			cohortattributes.setCohortMemberAttributeType(a);
			departmentService.saveCohortMemberAttribute(cohortattributes);
			model.addAttribute("attypes", att);
			model.addAttribute("cohortatt", cohortattributes);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "insertion success");
		}
		return null;
	}
}