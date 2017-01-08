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
import org.openmrs.EncounterType;
import org.openmrs.Form;
import org.openmrs.Location;
import org.openmrs.api.EncounterService;
import org.openmrs.api.FormService;
import org.openmrs.api.LocationService;
import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortEncounter;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * The main controller.
 */
@Controller
public class AddEncounter {

    protected final Log log = LogFactory.getLog(getClass());

    @RequestMapping(value = "/module/cohort/addenc", method = RequestMethod.GET)
    public void manage(ModelMap map) {
        map.addAttribute("cohortencounters", new CohortEncounter());
        List<String> cohortnames = new ArrayList<String>();
        CohortService cservice = Context.getService(CohortService.class);
        List<CohortM> list1 = cservice.findCohorts();
        for (int i = 0; i < list1.size(); i++) {
            CohortM c = list1.get(i);
            cohortnames.add(c.getName());
        }
        EncounterService enctype = Context.getEncounterService();
        List<String> etype = new ArrayList<String>();
        List<EncounterType> enctypes = enctype.getAllEncounterTypes();
        for (int l = 0; l < enctypes.size(); l++) {
            EncounterType e = enctypes.get(l);
            etype.add(e.getName());
        }
        FormService fs = Context.getFormService();
        List<String> fo = new ArrayList<String>();
        List<Form> form = fs.getAllForms();
        for (int h = 0; h < form.size(); h++) {
            Form b = form.get(h);
            fo.add(b.getName());
        }
        List<Integer> cvisit = new ArrayList<Integer>();
        List<CohortVisit> cv = cservice.findCohortVisit();
        for (int k = 0; k < cv.size(); k++) {
            CohortVisit e = cv.get(k);
            cvisit.add(e.getId());
        }
        map.addAttribute("forms", fo);
        map.addAttribute("visits", cvisit);
        map.addAttribute("enctypes", etype);
        LocationService service = Context.getLocationService();
        List<Location> formats = service.getAllLocations();
        map.addAttribute("locations", formats);
        map.addAttribute("formats1", cohortnames);
    }

    @RequestMapping(value = "/module/cohort/addenc.form", method = RequestMethod.POST)
    public ModelAndView onSubmit(WebRequest request, HttpSession httpSession, ModelMap model,
                                 @RequestParam(required = false, value = "encounterDateTime") String date,
                                 @RequestParam(required = false, value = "visit") Integer id,
                                 @ModelAttribute("cohortencounters") CohortEncounter cencounters, BindingResult errors) {
        CohortService departmentService = Context.getService(CohortService.class);
        if (!Context.isAuthenticated()) {
            errors.reject("Required");
        }

        EncounterService enctype = Context.getEncounterService();
        List<EncounterType> enctypes = enctype.getAllEncounterTypes();
        EncounterType e = new EncounterType();
        Location l = new Location();
        LocationService service = Context.getLocationService();
        List<String> cohortnames = new ArrayList<String>();
        CohortService cservice = Context.getService(CohortService.class);
        CohortM c1 = new CohortM();
        Form f = new Form();
        CohortVisit cvis = new CohortVisit();
        FormService fs = Context.getFormService();
        List<String> fo = new ArrayList<String>();
        List<Form> form = fs.getAllForms();
        for (int h = 0; h < form.size(); h++) {
            Form b = form.get(h);
            fo.add(b.getName());
        }
        List<Integer> cvisit = new ArrayList<Integer>();
        List<CohortVisit> cv = cservice.findCohortVisit();
        for (int a = 0; a < cv.size(); a++) {
            CohortVisit v = cv.get(a);
            cvisit.add(v.getId());
        }
        model.addAttribute("forms", fo);
        model.addAttribute("visits", cvisit);
        List<CohortM> list1 = cservice.findCohorts();
        for (int d = 0; d < list1.size(); d++) {
            CohortM c = list1.get(d);
            cohortnames.add(c.getName());
        }
        List<String> etype = new ArrayList<String>();
        for (int k = 0; k < enctypes.size(); k++) {
            EncounterType ec = enctypes.get(k);
            etype.add(e.getName());
        }
        List<Location> formats = service.getAllLocations();
        model.addAttribute("locations", formats);
        model.addAttribute("enctypes", etype);

        String encountertype = request.getParameter("enctype");
        String location = request.getParameter("location");
        String cohort = request.getParameter("names");
        String f1 = request.getParameter("form");
        if (date == "") {
            httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Values cannot be null");
        } else {
            List<CohortM> cohort2 = cservice.findCohorts(cohort);
            for (int i = 0; i < cohort2.size(); i++) {
                c1 = cohort2.get(i);
            }
            List<EncounterType> enctypes1 = enctype.findEncounterTypes(encountertype);
            for (int g = 0; g < enctypes1.size(); g++) {
                e = enctypes1.get(g);
            }
            List<Location> loc = service.getLocations(location);
            for (int j = 0; j < loc.size(); j++) {
                l = loc.get(j);
            }
            List<Form> fm = fs.getForms(f1, false, null, false, null, null, null);
            for (int b = 0; b < fm.size(); b++) {
                f = fm.get(b);
            }
            List<CohortVisit> cvi = cservice.findCohortVisit(id);
            for (int c = 0; c < cvi.size(); c++) {
                cvis = cvi.get(c);
            }
            cencounters.setForm(f);
            cencounters.setVisit(cvis);
            cencounters.setCohort(c1);
            cencounters.setEncounterType(e);
            cencounters.setLocation(l);
            departmentService.saveCohortEncounters(cencounters);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "insertion success");
        }
        return null;
    }

}
