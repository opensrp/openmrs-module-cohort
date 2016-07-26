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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Person;
import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortMember;
import org.openmrs.module.cohort.CohortType;
import org.openmrs.module.cohort.api.CohortService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The main controller.
 */
@Controller
public class PatientSearchController {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@RequestMapping(value = "/module/cohort/patientSearch", method = RequestMethod.GET)
	public void manage(HttpSession httpSession, HttpServletRequest request, ModelMap model) {
		
	}

	@RequestMapping(value = "/module/cohort/patientSearch.form", method = RequestMethod.POST)
	public void getSearchResults(ModelMap model, HttpSession httpSession, HttpServletRequest request,
								 @RequestParam(required = false, value = "patientName") String patientName,
								 @RequestParam(value = "optionsRadios") String patientGender,
								 @RequestParam(required = false, value = "cohortProgram") String cohortProgram,
								 @RequestParam(required = false, value = "location") String location,
								 @RequestParam(required = false, value = "amount") String amount,
								 @ModelAttribute("cohorttype") CohortType cohort) {
		CohortService cohortService = Context.getService(CohortService.class);
		String[] ageLimitStrings = amount.split(" - ");
		int[] ageLimits = new int[2];
		ageLimits[0] = Integer.parseInt(ageLimitStrings[0]);
		ageLimits[1] = Integer.parseInt(ageLimitStrings[1]);
		List<CohortMember> memberList = cohortService.findCohortMember(); //returns all member
		List<CohortMember> resultList = new ArrayList<CohortMember>();
		for (CohortMember member : memberList) {
			Person memberPerson = member.getPerson();
			if((patientName.equals("") || (memberPerson.getPersonName().getFullName().equals(patientName))) && //compare name
					patientGender.equals(memberPerson.getGender()) && //compare gender 
					(cohortProgram.equals("") || (member.getCohort().getCohortProgram().getName().equalsIgnoreCase(cohortProgram))) && //compare cohort program
					member.getPerson().getAge() >= ageLimits[0] && member.getPerson().getAge() <= ageLimits[1]) { //compare age limits
				resultList.add(member);
			}
		}
		System.out.println(resultList);
		if (resultList.size()!= 0) {
			model.addAttribute("personsExist", true);
			model.addAttribute("resultList", resultList);
		} else {
			model.addAttribute("personsExist", false);
		}
	}
	
}
