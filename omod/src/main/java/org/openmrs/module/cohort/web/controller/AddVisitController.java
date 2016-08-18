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
import org.openmrs.Location;
import org.openmrs.VisitType;
import org.openmrs.api.LocationService;
import org.openmrs.api.VisitService;
import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortM;
import org.openmrs.module.cohort.CohortVisit;
import org.openmrs.module.cohort.api.CohortService;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
public class AddVisitController {
    protected final Log log = LogFactory.getLog(getClass());

    @RequestMapping(value = "/module/cohort/addvisit", method = RequestMethod.GET)
    public void manage(ModelMap map) {
        map.addAttribute("cohortvisit", new CohortVisit());
        List<String> cohortnames = new ArrayList<String>();
        VisitService enctype = Context.getVisitService();
        List<String> etype = new ArrayList<String>();
        List<VisitType> enctypes = enctype.getAllVisitTypes();
        CohortService cservice = Context.getService(CohortService.class);
        List<CohortM> list1 = cservice.findCohorts();
        for (int i = 0; i < list1.size(); i++) {
            CohortM c = list1.get(i);
            cohortnames.add(c.getName());
        }
        for (int l = 0; l < enctypes.size(); l++) {
            VisitType e = enctypes.get(l);
            etype.add(e.getName());
        }
        map.addAttribute("formats1", cohortnames);
        map.addAttribute("visittypes", etype);
        LocationService service = Context.getLocationService();
        List<Location> formats = service.getAllLocations();
        map.addAttribute("locations", formats);

    }

    @RequestMapping(value = "/module/cohort/addvisit.form", method = RequestMethod.POST)
    public void onSubmit(WebRequest request, HttpSession httpSession, ModelMap model,
                         @RequestParam(required = false, value = "startDate") String startDate,
                         @RequestParam(required = false, value = "endDate") String endDate,
                         @ModelAttribute("cohortvisit") CohortVisit cvisit, BindingResult errors) {
        CohortService departmentService = Context.getService(CohortService.class);
        if (!Context.isAuthenticated()) {
            errors.reject("Required");
        }

        VisitService enctype = Context.getVisitService();
        List<VisitType> enctypes = enctype.getAllVisitTypes();
        VisitType e = new VisitType();
        Location l = new Location();
        LocationService service = Context.getLocationService();
        List<String> cohortnames = new ArrayList<String>();
        List<String> etype = new ArrayList<String>();
        CohortService cservice = Context.getService(CohortService.class);
        CohortM c1 = new CohortM();
        List<CohortM> list1 = cservice.findCohorts();
        for (int i = 0; i < list1.size(); i++) {
            CohortM c = list1.get(i);
            cohortnames.add(c.getName());
        }
        for (int k = 0; k < enctypes.size(); k++) {
            VisitType ec = enctypes.get(k);
            etype.add(e.getName());
        }
        List<Location> formats = service.getAllLocations();
        model.addAttribute("formats1", cohortnames);
        model.addAttribute("locations", formats);
        model.addAttribute("enctypes", etype);

        String visittype = request.getParameter("visittype");
        String location = request.getParameter("location");
        String cohort = request.getParameter("names");
        if (startDate == "" && endDate == "") {
            httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Values cannot be null");
        } else {
            List<CohortM> cohort2 = cservice.findCohorts(cohort);
            for (int i = 0; i < cohort2.size(); i++) {
                c1 = cohort2.get(i);
            }
            List<VisitType> visittypes1 = enctype.getVisitTypes(visittype);
            for (int i = 0; i < visittypes1.size(); i++) {
                e = visittypes1.get(i);
            }
            List<Location> loc = service.getLocations(location);
            for (int j = 0; j < loc.size(); j++) {
                l = loc.get(j);
            }
            cvisit.setCohort(c1);
            cvisit.setVisitType(e);
            cvisit.setLocation(l);
            cservice.saveCohortVisit(cvisit);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "insertion success");
        }
    }

}
