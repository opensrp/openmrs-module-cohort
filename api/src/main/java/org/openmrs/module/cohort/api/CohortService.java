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
	CohortM getCohortByName(String name);
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
	List<CohortMember> getAllHeadCohortMembers();
	CohortMember saveCohortMember(CohortMember cohortmember);

	CohortAttributeType getCohortAttributeType(Integer id);
	List<CohortAttributeType> getAllCohortAttributeTypes();
	CohortAttributeType getCohortAttributeTypeByName(String attribute_type_name);
	CohortAttributeType getCohortAttributeTypeByUuid(String uuid);
	CohortAttributeType saveCohort(CohortAttributeType a);
	void purgeCohortAttributes(CohortAttributeType attributes);

	CohortAttribute getCohortAttributeByUuid(String uuid);
	CohortAttribute getCohortAttributeById(Integer id);
	CohortAttribute saveCohortAttribute(CohortAttribute att);
	void purgeCohortAtt(CohortAttribute att);
	List<CohortAttribute> getCohortAttributesByCohortId(Integer id);
	
	CohortRole getCohortRoleByUuid(String uuid);
	CohortRole getCohortRoleByName(String name);
	CohortRole getCohortRoleById(Integer id);
	List<CohortRole> getAllCohortRoles();
	CohortRole saveCohortRole(CohortRole cohort);
	void purgeCohortRole(CohortRole crole);
	
	CohortType getCohortTypeById(Integer id);
	CohortType getCohortTypeByUuid(String uuid);
	CohortType getCohortTypeByName(String name);
	List<CohortType> getAllCohortTypes();
	CohortType saveCohort(CohortType cohort);
	void purgeCohortType(CohortType type);
	
	CohortProgram getCohortProgramByUuid(String uuid);
	CohortProgram getCohortProgramById(Integer id);
	CohortProgram getCohortProgramByName(String name);
	List<CohortProgram> getAllCohortPrograms();
	CohortProgram saveCohortProgram(CohortProgram cohort);
	void purgeCohortProgram(CohortProgram cvisit);
	
	CohortVisit getCohortVisitById(Integer id);
	List<CohortVisit> getCohortVisitByType(Integer visitType);
	CohortVisit getCohortVisitByUuid(String uuid);
	CohortVisit saveCohortVisit(CohortVisit cvisit);
	CohortVisit getCohortVisitByLocation(Integer id);
	List<CohortVisit> getCohortVisitsByDate(Date startDate, Date endDate);
	void purgeCohortVisit(CohortVisit cvisit);

	CohortEncounter getCohortEncounterByUuid(String uuid);
	CohortEncounter getCohortEncounterById(Integer id);
	List<CohortEncounter> filterEncountersByViewPermissions(List<CohortEncounter> encounters, User user);
	List<CohortEncounter> findCohortEncounter(String cohort, String location);
	List<CohortEncounter> getEncounters(CohortM who, Location loc,
			Date fromDate, Date toDate, Collection<Form> enteredViaForms,
			Collection<EncounterType> encounterTypes, boolean includeVoided);
	List<CohortEncounter> getEncounters(CohortM who, Location loc, Date fromDate, Date toDate,
			Collection<Form> enteredViaForms, Collection<EncounterType> encounterTypes, Collection<User> providers,
			boolean includeVoided);
	List<CohortEncounter> getEncountersByCohort(String query, Integer cohortId, boolean includeVoided);
	CohortEncounter saveCohortEncounter(CohortEncounter cencounters);
	void purgeCohortEncounter(CohortEncounter cencounters);
	
	CohortObs getCohortObsByUuid(String uuid);
	CohortObs getCohortObsById(Integer id);
	List<CohortObs> getObservations(List<CohortM> whom,
			List<CohortEncounter> encounters, List<Concept> questions,
			List<Concept> answers, List<Location> locations, List<String> sort,
			Integer mostRecentN, Integer obsGroupId, Date fromDate,
			Date toDate, boolean includeVoidedObs);
	List<CohortObs> getObservationsByCohortAndConcept(CohortM who, Concept question);
	CohortObs saveCohortObs(CohortObs cobs);
	CohortObs voidObs(CohortObs obs, String reason);
	List<CohortObs> getCohortObsByEncounterId(Integer id);
	void purgeCohortObs(CohortObs cobs);
	
	Long getCount(String name);
}