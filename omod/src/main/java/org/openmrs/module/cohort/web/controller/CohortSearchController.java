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
import org.openmrs.module.cohort.CohortType;
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
public class CohortSearchController {

    protected final Log log = LogFactory.getLog(getClass());

    @RequestMapping(value = "/module/cohort/cohortSearch", method = RequestMethod.GET)
    public void manage(HttpSession httpSession, HttpServletRequest request, ModelMap model) {

    }

    @RequestMapping(value = "/module/cohort/cohortSearch.form", method = RequestMethod.POST)
    public void getSearchResults(ModelMap model, HttpSession httpSession, HttpServletRequest request,
                                 @RequestParam(required = false, value = "cohortName") String cohortName,
                                 @RequestParam(required = false, value = "cohortProgram") String cohortProgram,
                                 @RequestParam(required = false, value = "cohortHead") String cohortHead,
                                 @RequestParam(required = false, value = "location") String location,
                                 @RequestParam(required = false, value = "startDate") String startDate,
                                 @ModelAttribute("cohorttype") CohortType cohort) throws Exception {
        CohortService cohortService = Context.getService(CohortService.class);
        List<CohortM> cohortList = cohortService.findCohorts(); //returns all cohorts
        List<CohortM> resultList = new ArrayList<CohortM>();
        for (CohortM cohorts : cohortList) {
            System.out.println(getParsedDate(cohorts.getStartDate().toString()));
            if ((cohortName.equals("") || cohortName.equals(cohorts.getName())) &&
                    (cohortProgram.equals("") || cohortProgram.equals(cohorts.getCohortProgram().getName())) &&
                    (cohortHead.equals("") || containsCohortHeadEntered(cohortHead)) &&
                    (location.equals("") || cohorts.getClocation().toString().equals(location)) &&
                    (startDate.equals("") || (cohorts.getStartDate() != null && getParsedDate(cohorts.getStartDate().toString()).equals(startDate)))) {
                resultList.add(cohorts);
            }
        }
        if (resultList.size() != 0) {
            model.addAttribute("cohortsExist", true);
            model.addAttribute("resultList", resultList);
        } else {
            model.addAttribute("cohortsExist", false);
        }
    }

    private boolean containsCohortHeadEntered(String headEntered) {
        CohortService cohortService = Context.getService(CohortService.class);
        List<CohortMember> members = cohortService.findCohortMember();
        for (CohortMember member : members) {
            if (member.getPerson().getPersonName().getFullName().equals(headEntered)) {
                return true;
            }
        }
        return false;
    }

    private String getParsedDate(String startDate) {
        String[] parts = startDate.split(" ")[0].split("-");
        String finalDate = parts[2] + "/" + parts[1] + "/" + parts[0];
        return finalDate;
    }
}
