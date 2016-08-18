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
import org.openmrs.module.cohort.CohortProgram;
import org.openmrs.module.cohort.api.CohortService;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The main controller.
 */
@Controller
public class CohortProgramDeleteController {

    protected final Log log = LogFactory.getLog(getClass());

    @RequestMapping(value = "/module/cohort/deletecohortprogram", method = RequestMethod.GET)
    public void manage(HttpSession httpSession, HttpServletRequest request, ModelMap model, @RequestParam(required = false, value = "name") String cohort_name, @ModelAttribute("cohortprogram") CohortProgram cp) {
        CohortService departmentService = Context.getService(CohortService.class);
        List<CohortProgram> list1 = departmentService.findCohortProg();
        for (int i = 0; i < list1.size(); i++) {
            CohortProgram c = (CohortProgram) list1.get(i);
            if ("delete".equals(request.getParameter("delete")) && c.getName().equals(cohort_name)) {
                try {
                    departmentService.purgeCohortProgram(cp);
                    httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "delete success");
                } catch (Exception ex) {
                    httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "delete failure");
                    log.error("Failed to delete cohort", ex);
                }
            }

        }
    }
}
