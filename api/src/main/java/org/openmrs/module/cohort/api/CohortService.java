/**
 * The contents of this file are subject to the OpenMRS License
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
package org.openmrs.module.cohort.api;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.openmrs.Concept;
import org.openmrs.EncounterType;
import org.openmrs.Form;
import org.openmrs.Location;
import org.openmrs.User;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.cohort.CohortAttribute;
import org.openmrs.module.cohort.CohortAttributeType;
import org.openmrs.module.cohort.CohortEncounter;
import org.openmrs.module.cohort.CohortM;
import org.openmrs.module.cohort.CohortMember;
import org.openmrs.module.cohort.CohortObs;
import org.openmrs.module.cohort.CohortProgram;
import org.openmrs.module.cohort.CohortRole;
import org.openmrs.module.cohort.CohortType;
import org.openmrs.module.cohort.CohortVisit;
import org.springframework.transaction.annotation.Transactional;

/**
 * This service exposes module's core functionality. It is a Spring managed bean which is configured in moduleApplicationContext.xml.
 *
 * It can be accessed only via Context:<br>
 * <code>
 * Context.getService(cohortService.class).someMethod();
 * </code>
 *
 * @see org.openmrs.api.context.Context
 */
@Transactional
public interface CohortService extends OpenmrsService {
	
	CohortM getCohortById(Integer id);
	CohortM getCohortByUuid(String uuid);
	List<CohortM> getAllCohorts();
	List<CohortM> findCohortsMatching(String nameMatching);
	CohortM saveCohort(CohortM cohort);
	void purgeCohort(CohortM cohort);
	CohortM getCohort(Integer loationId, Integer programId, Integer typeId);
	
	CohortMember getCohortMemberByUuid(String uuid);
	List<CohortMember> findCohortMemberByName(String name);
	List<CohortMember> findCohortMembersByCohort (Integer cohortId);
	CohortMember getCohortMemberById(Integer id);
	List<CohortMember> getCohortMembersByCohortId(Integer id);
	List<CohortMember> getCohortMembersByCohortRoleId(Integer id);
	CohortMember saveCohortMember(CohortMember cohortmember);

	CohortAttributeType findCohortAttributeType(Integer id);
	List<CohortAttributeType> getAllCohortAttributeTypes();
	CohortAttributeType findCohortAttributeTypeByName(String attribute_type_name);
	CohortAttributeType getCohortAttributeTypeUuid(String uuid);
	CohortAttributeType saveCohort(CohortAttributeType a);
	void purgeCohortAttributes(CohortAttributeType attributes);

	CohortAttribute getCohortAttributeUuid(String uuid);
	List<CohortAttribute> findCohortAtt(String name);
	List<CohortAttribute> findCohortAttribute(Integer id);
	CohortAttribute saveCohortAttributes(CohortAttribute att);
	void purgeCohortAtt(CohortAttribute att);
	
	CohortRole getCohortRoleUuid(String uuid);
	List<CohortRole> findCohortRole(Integer id);
	List<CohortRole> findCohortRole(String cohort_name);
	List<CohortRole> findCohortRoles(String name);
	List<CohortRole> findRoles(String name);
	CohortRole saveCohortRole(CohortRole cohort);
	void purgeCohortRole(CohortRole crole);
	List<CohortRole> getAllCohortRoles();
	CohortRole getCohortRoleByName(String name);
	void deleteCohortRoleById(Integer id);
	
	CohortType getCohortType(Integer id);
	CohortType getCohortTypeUuid(String uuid);
	List<CohortType> findCohortType();
	List<CohortType> findCohortType(Integer id);
	List<CohortType> findCohortType(String cohort_name);
	List<CohortType> getAllCohortTypes();
	CohortType saveCohort(CohortType cohort);
	void purgeCohortType(CohortType type);
	
	CohortProgram getCohortProgramUuid(String uuid);
	CohortProgram saveCohortProgram(CohortProgram cohort);
	List<CohortProgram> findCohortProg();
	List<CohortProgram> findCohortProgram(Integer id);
	List<CohortProgram> findCohortProgram(String name);
	void purgeCohortProgram(CohortProgram cvisit);
	
//	List<CohortVisit> findCohortVisit();
	List<CohortVisit> findCohortVisit(Integer id);
	List<CohortVisit> findCohortVisit(String name);
	CohortVisit getCohortVisitUuid(String uuid);
	CohortVisit saveCohortVisit(CohortVisit cvisit);
	void purgeCohortVisit(CohortVisit cvisit);

	CohortEncounter getCohortEncUuid(String uuid);
	CohortEncounter getCohortEncounter(Integer id);
	List<CohortEncounter> filterEncountersByViewPermissions(List<CohortEncounter> encounters, User user);
	//List<CohortEncounter> findCohortEnc(Integer id);
	List<CohortEncounter> findCohortEncounter(String cohort, String location);
//	List<CohortEncounter> findCohortEncounters();
	List<CohortEncounter> findCohortEncounters(String name);
	List<CohortEncounter> getEncounters(CohortM who, Location loc,
			Date fromDate, Date toDate, Collection<Form> enteredViaForms,
			Collection<EncounterType> encounterTypes, boolean includeVoided);
	List<CohortEncounter> getEncounters(CohortM who, Location loc, Date fromDate, Date toDate,
			Collection<Form> enteredViaForms, Collection<EncounterType> encounterTypes, Collection<User> providers,
			boolean includeVoided);
	List<CohortEncounter> getEncountersByCohort(CohortM cohort);
	List<CohortEncounter> getEncountersByCohort(String query, boolean includeVoided);
	CohortEncounter saveCohortEncounters(CohortEncounter cencounters);
	CohortEncounter voidEncounter(CohortEncounter encounter, String reason);
	void purgeCohortEncounters(CohortEncounter cencounters);
	
	CohortObs getCohortObsUuid(String uuid);
//	List<CohortObs> findCohortObs();
	List<CohortObs> findCohortObs(Integer id);
	List<CohortObs> getObservations(List<CohortM> whom,
			List<CohortEncounter> encounters, List<Concept> questions,
			List<Concept> answers, List<Location> locations, List<String> sort,
			Integer mostRecentN, Integer obsGroupId, Date fromDate,
			Date toDate, boolean includeVoidedObs);
	List<CohortObs> getObservationsByCohortAndConcept(CohortM who, Concept question);
	CohortObs saveCohortObs(CohortObs cobs);
	CohortObs voidObs(CohortObs obs, String reason);
	void purgeCohortObs(CohortObs cobs);
	
	Long getCount(String name);
}