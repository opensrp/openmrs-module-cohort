/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.cohort.extension.html;

import org.openmrs.module.Extension;
import org.openmrs.module.web.extension.AdministrationSectionExt;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class defines the links that will appear on the administration page under the
 * "cohort.title" heading.
 */
public class AdminList extends AdministrationSectionExt {

    /**
     * @see AdministrationSectionExt#getMediaType()
     */
    public Extension.MEDIA_TYPE getMediaType() {
        return Extension.MEDIA_TYPE.html;
    }

    /**
     * @see AdministrationSectionExt#getTitle()
     */
    public String getTitle() {
        return "cohort.title";
    }

    /**
     * @see AdministrationSectionExt#getLinks()
     */
    public Map<String, String> getLinks() {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("/module/cohort/cohortDashboard.form", "Cohort Dashboard");
        map.put("/module/cohort/patientSearch.form", "Cohort Patient Search");
        map.put("/module/cohort/cohortSearch.form", "Cohort Search");
        return map;
    }

}
