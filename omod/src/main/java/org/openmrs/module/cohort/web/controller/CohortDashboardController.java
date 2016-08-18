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
import org.openmrs.module.cohort.CohortM;
import org.openmrs.module.cohort.CohortMember;
import org.openmrs.module.cohort.api.CohortService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * The main controller.
 */
@Controller
public class CohortDashboardController {

    protected final Log log = LogFactory.getLog(getClass());

    @RequestMapping(value = "/module/cohort/cohortDashboard", method = RequestMethod.GET)
    public void manage(HttpSession httpSession, HttpServletRequest request, ModelMap model, @RequestParam(required = false, value = "name") String cohort_name, @ModelAttribute("cohortmodule") CohortM cohort) {
        //Long a=0L;
        CohortService service = Context.getService(CohortService.class);
        if ("search".equals(request.getParameter("search"))) {
            List<CohortM> cohortsFound = service.findCohorts(cohort_name);
            if (cohortsFound.size() != 0) {
                model.addAttribute("cohortExists", "true");
            } else {
                model.addAttribute("cohortExists", "false");
            }
            List<List<CohortMember>> cohortMembers = new ArrayList<List<CohortMember>>();
//			List<List<CohortEncounter>> cohortEncounters = new ArrayList<List<CohortEncounter>>();
            model.addAttribute("cohortList", cohortsFound);
            for (int i = 0; i < cohortsFound.size(); i++) {
                CohortM currentCohort = cohortsFound.get(i);
                cohortMembers.add(service.findCohortMembersByCohortId(currentCohort.getCohortId()));
//				cohortEncounters.add(service.getEncountersByCohort(currentCohort));
            }
            model.addAttribute("memberList", cohortMembers);
//			model.addAttribute("encounterList", cohortEncounters);
//			 for(int i=0;i<list1.size();i++)
//			 {
//			 CohortM c=list1.get(i);
//			 a=service.getCount(c.getName());
//			 }
//			
//			model.addAttribute("cohortmodule",a);
            for (int i = 0; i < cohortsFound.size(); i++) {
                cohort = cohortsFound.get(i);
                if (cohort.isGroupCohort()) {
                    model.addAttribute("htmlformId", 2);
                } else {
                    model.addAttribute("htmlformId", 1);
                }
            }
        }
    }

    @RequestMapping(value = "/module/cohort/configurecohortmetadata", method = RequestMethod.GET)
    public void manage1(HttpSession httpSession, HttpServletRequest request, ModelMap model, @RequestParam(required = false, value = "name") String cohort_name, @ModelAttribute("cohortmodule") CohortM cohort) {
        CohortM cohort1 = new CohortM();
        model.addAttribute("cohort", cohort1);
    }
}
