/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.cohort.api.db;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Form;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.PersonName;
import org.openmrs.api.db.DAOException;

import org.openmrs.module.cohort.CohortAttribute;
import org.openmrs.module.cohort.CohortAttributeType;
import org.openmrs.module.cohort.CohortM;
import org.openmrs.module.cohort.CohortMember;
import org.openmrs.module.cohort.CohortMemberAttribute;
import org.openmrs.module.cohort.CohortMemberAttributeType;
import org.openmrs.module.cohort.CohortObs;
import org.openmrs.module.cohort.CohortProgram;
import org.openmrs.module.cohort.CohortRole;
import org.openmrs.module.cohort.CohortType;
import org.openmrs.module.cohort.CohortEncounter;
import org.openmrs.module.cohort.CohortVisit;
import org.openmrs.module.cohort.api.CohortService;
import org.openmrs.module.cohort.EncounterSearchCriteria;

/**
 * Database methods for {@link cohortService}.
 */
public interface CohortDAO {
	
	/*
	 * Add DAO methods here
	 */
	public CohortM saveCohort(CohortM cohort);
	
	public List<CohortEncounter> findCohortEncounter(String cohort, String location);
	
	public List<CohortMember> findCohortMember(String name);
	
	public List<CohortMember> getCohortMember(Integer id);
	
	public CohortObs saveObs(CohortObs obs);
	
	public Long getCount(String name);
	
	public void purgeCohortRole(CohortRole crole);
	
	public List<CohortRole> findCohortRole(String cohort_name);
	
	public CohortEncounter getCohortEncounter(Integer id);
	
	public CohortRole getCohortRoleUuid(String uuid);
	
	public List<CohortRole> findRoles(String name);
	
	public List<CohortRole> findCohortRoles(String name);
	
	public CohortRole saveCohortRole(CohortRole cohort);
	
	public List<CohortProgram> findCohortProgram(Integer id);
	
	public CohortProgram getCohortProgramUuid(String uuid);
	
	public List<CohortProgram> findCohortProgram(String name);
	
	public List<CohortProgram> findCohortProg();
	
	public CohortProgram saveCohortProgram(CohortProgram cohort);
	
	public void purgeCohortProgram(CohortProgram cvisit);
	
	public CohortVisit saveCohortVisit(CohortVisit cvisit);
	
	public void purgeCohortVisit(CohortVisit cvisit);
	
	public List<CohortVisit> findCohortVisit();
	
	public CohortMemberAttribute saveCohortMemberAttribute(CohortMemberAttribute att);
	
	public List<CohortMemberAttributeType> findCohortMemberAttributes(String attribute_type_name);
	
	public CohortMemberAttributeType saveCohortMemberAttributeType(CohortMemberAttributeType at);
	
	public List<CohortMemberAttributeType> findCohortMemberAttributeType();
	
	public void purgeCohortMemberAttribute(CohortMemberAttribute att);
	
	public void purgeCohortMemberAttributeType(CohortMemberAttributeType at);
	
	public CohortAttribute saveCohortAttributes(CohortAttribute att);
	
	public List<CohortAttribute> findCohortAtt(String name);
	
	public List<CohortM> getCohort(Integer id);
	
	public CohortMember saveCPatient(CohortMember cohort);
	
	public List<CohortM> findCohorts();
	
	public List<CohortM> findCohorts(String cohort_module);
	
	public void purgeCohort(CohortM cohort);
	
	public CohortType saveCohortType(CohortType cohorttype);
	
	public List<CohortType> findCohortType();
	
	public List<CohortType> getAllCohortTypes();
	
	public List<CohortType> findCohortType(String cohort_name);
	
	public void purgeCohortType(CohortType cohort);
	
	public void purgeCohortAtt(CohortAttribute att);
	
	public List<CohortAttributeType> findCohortAttributes();
	
	public List<CohortAttributeType> findCohortAttributes(String attribute_type_name);
	
	public void purgeCohortAttributes(CohortAttributeType attributes);
	
	public void purgeCohortEncounters(CohortEncounter cencounters);
	
	public CohortType getCohortType(Integer id);
	
	public CohortAttributeType saveCohortAttributes(CohortAttributeType attributes);
	
	public CohortAttributeType getCohortAttributes(Integer cohort_attribute_type_id);
	
	public CohortEncounter saveCohortEncounters(CohortEncounter cencounters);
	
	public List<CohortEncounter> findCohortEncounters();
	
	public CohortM getCohortId(Integer id);
	
	public List<CohortMemberAttributeType> findCohortMemberAttributeType(String name);
	
	public List<CohortMemberAttribute> findCohortMemberAttribute(String name);
	
	public CohortM getCohortUuid(String uuid);
	
	public CohortEncounter getCohortEncounterUuid(String uuid);
	
	public CohortAttribute getCohortAttributeUuid(String uuid);
	
	public List<CohortEncounter> findCohortEncounters(String name);
	
	public List<CohortVisit> findCohortVisit(String name);
	
	public CohortMember getCohortMemUuid(String uuid);
	
	public CohortMemberAttribute getCohortMemberAttributeUuid(String uuid);
	
	public CohortType getCohortTypeUuid(String uuid);
	
	public CohortVisit getCohortVisitUuid(String uuid);
	
	public List<CohortM> findCohort(Integer id);
	
	public List<CohortType> findCohortType(Integer id);
	
	public List<CohortAttribute> findCohortAtt(Integer id);
	
	public List<CohortAttributeType> findCohortAttType(Integer id);
	
	public List<CohortMemberAttributeType> findCohortMemAttType(Integer id);
	
	public List<CohortMemberAttribute> findCohortMemAtt(Integer id);
	
	public List<CohortEncounter> findCohortEnc(Integer id);
	
	public List<CohortVisit> findCohortVisit(Integer id);
	
	public CohortAttributeType getCohortAttributeTypeUuid(String uuid);
	
	public CohortMemberAttributeType getCohortMemberAttributeType(String uuid);
	
	public List<CohortMember> findCohortMember();
	
	public CohortObs saveCohortObs(CohortObs cobs);
	
	public void purgeCohortObs(CohortObs cobs);
	
	public List<CohortObs> findCohortObs();
	
	public List<CohortObs> findCohortObs(Integer id);
	
	public List<CohortRole> findCohortRole(Integer id);
	
	public CohortObs getCohortObsUuid(String uuid);
	
	public List<CohortEncounter> getEncounters(EncounterSearchCriteria searchCriteria);
	
	public List<CohortEncounter> getEncounters(String query, Integer cohortId, Integer start, Integer length,
			boolean includeVoided);
	
	public List<CohortMember> findCohortMembersByCohortId (Integer cohortId);
}
