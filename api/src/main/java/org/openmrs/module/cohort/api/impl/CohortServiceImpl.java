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
package org.openmrs.module.cohort.api.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.EncounterType;
import org.openmrs.Form;
import org.openmrs.Location;
import org.openmrs.Provider;
import org.openmrs.User;
import org.openmrs.api.ProviderService;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
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
import org.openmrs.module.cohort.api.CohortService;
import org.openmrs.module.cohort.api.db.CohortDAO;
import org.openmrs.module.cohort.api.db.EncounterSearchCriteriaBuilder;

/**
 * It is a default implementation of {@link cohortService}.
 */
public class CohortServiceImpl extends BaseOpenmrsService implements CohortService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private CohortDAO dao;
	
	/**
	 * @param dao the dao to set
	 */
	
	public CohortDAO getDao() {
		return dao;
	}
	
	public void setDao(CohortDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public CohortType getCohortTypeById(Integer cohort_type_id) {
		return dao.getCohortType(cohort_type_id);
	}
	
	@Override
	public CohortMember saveCohortMember(CohortMember cohort) {
		return dao.saveCPatient(cohort);
	}
	
	@Override
	public CohortAttributeType saveCohort(CohortAttributeType a) {
		return dao.saveCohortAttributes(a);
	}
	
	@Override
	public CohortType saveCohort(CohortType cohort) {
		return dao.saveCohortType(cohort);
	}
	
	@Override
	public void purgeCohortAttributes(CohortAttributeType attributes) {
		dao.purgeCohortAttributes(attributes);
	}
	
	@Override
	public void purgeCohortAtt(CohortAttribute att) {
		dao.purgeCohortAtt(att);
	}
	
	@Override
	public void purgeCohortEncounter(CohortEncounter cencounters) {
		dao.purgeCohortEncounters(cencounters);
	}
	
	@Override
	public List<CohortType> getAllCohortTypes() {
		return dao.getAllCohortTypes();
	}
	
	@Override
	public List<CohortAttributeType> getAllCohortAttributeTypes() {
		return dao.getAllCohortAttributes();
	}
	
	@Override
	public CohortAttributeType getCohortAttributeTypeByName(String attribute_type_name) {
		return dao.findCohortAttributes(attribute_type_name);
	}
	
	/*@Override
	public List<CohortEncounter> findCohortEncounters() {
		return dao.findCohortEncounters();
	}
	*/
	@Override
	public CohortEncounter saveCohortEncounter(CohortEncounter cencounters) {
		return dao.saveCohortEncounters(cencounters);
	}
	
	@Override
	public CohortM saveCohort(CohortM cohort) {
		return dao.saveCohort(cohort);
	}
	
	@Override
	public void purgeCohort(CohortM cohort) {
		dao.purgeCohort(cohort);
	}
	
	@Override
	public List<CohortM> getAllCohorts() {
		return dao.findCohorts();
	}
	
	@Override
	public List<CohortM> findCohortsMatching(String nameMatching) {
		return dao.findCohorts(nameMatching);
	}
	
	@Override
	public CohortAttribute saveCohortAttribute(CohortAttribute att) {
		return dao.saveCohortAttributes(att);
	}
	
	@Override
	public void purgeCohortType(CohortType type) {
		dao.purgeCohortType(type);
	}
	
	@Override
	public CohortVisit saveCohortVisit(CohortVisit cvisit) {
		return dao.saveCohortVisit(cvisit);
	}
	
	@Override
	public void purgeCohortVisit(CohortVisit cvisit) {
		dao.purgeCohortVisit(cvisit);
	}
	
	@Override
	public CohortM getCohortById(Integer id) {
		return dao.getCohortMById(id);
	}
	
	@Override
	public CohortM getCohortByUuid(String uuid) {
		return dao.getCohortUuid(uuid);
	}
	
	@Override
	public List<CohortVisit> getCohortVisitByType(Integer visitType) {
		return dao.findCohortVisitByVisitType(visitType);
	}
	
	@Override
	public CohortAttribute getCohortAttributeByUuid(String uuid) {
		return dao.getCohortAttributeByUuid(uuid);
	}
	
	@Override
	public CohortEncounter getCohortEncounterByUuid(String uuid) {
		return dao.getCohortEncounterUuid(uuid);
	}
	
	@Override
	public CohortMember getCohortMemberByUuid(String uuid) {
		return dao.getCohortMemUuid(uuid);
	}
	
	@Override
	public CohortType getCohortTypeByUuid(String uuid) {
		return dao.getCohortTypeByUuid(uuid);
	}
	
	@Override
	public CohortVisit getCohortVisitByUuid(String uuid) {
		return dao.getCohortVisitUuid(uuid);
	}
	
	@Override
	public CohortAttributeType getCohortAttributeTypeByUuid(String uuid) {
		return dao.getCohortAttributeTypeUuid(uuid);
	}
	
	@Override
	public CohortVisit getCohortVisitById(Integer id) {
		return dao.findCohortVisit(id);
	}
	
	@Override
	public CohortAttribute getCohortAttributeById(Integer id) {
		return dao.findCohortAtt(id);
	}
	
	@Override
	public CohortAttributeType getCohortAttributeType(Integer id) {
		return dao.findCohortAttributeType(id);
	}
	
	@Override
	public CohortProgram saveCohortProgram(CohortProgram cohort) {
		return dao.saveCohortProgram(cohort);
	}
	
	@Override
	public void purgeCohortProgram(CohortProgram cvisit) {
		dao.purgeCohortProgram(cvisit);
	}
	
	@Override
	public List<CohortProgram> getAllCohortPrograms() {
		return dao.findCohortProg();
	}
	
	@Override
	public CohortProgram getCohortProgramByName(String name) {
		return dao.findCohortProgram(name);
	}
	
	@Override
	public CohortProgram getCohortProgramByUuid(String uuid) {
		return dao.getCohortProgramUuid(uuid);
		
	}
	
	@Override
	public CohortProgram getCohortProgramById(Integer id) {
		return dao.findCohortProgram(id);
	}
	
	@Override
	public CohortObs saveCohortObs(CohortObs cobs) {
		return dao.saveCohortObs(cobs);
	}
	
	@Override
	public void purgeCohortObs(CohortObs cobs) {
		dao.purgeCohortObs(cobs);
	}

	@Override
	public CohortObs getCohortObsById(Integer id) {
		return dao.findCohortObs(id);
	}
	
	@Override
	public CohortObs getCohortObsByUuid(String uuid) {
		return dao.getCohortObsUuid(uuid);
	}
	
	@Override
	public CohortRole saveCohortRole(CohortRole cohort) {
		return dao.saveCohortRole(cohort);
	}
	
	@Override
	public CohortRole getCohortRoleByUuid(String uuid) {
		return dao.getCohortRoleUuid(uuid);
	}
	
	@Override
	public CohortEncounter getCohortEncounterById(Integer id) {
		return dao.getCohortEncounter(id);
	}
	
	@Override
	public List<CohortEncounter> getEncounters(CohortM who, Location loc,
			Date fromDate, Date toDate, Collection<Form> enteredViaForms,
			Collection<EncounterType> encounterTypes, boolean includeVoided) {
		return Context.getService(CohortService.class).getEncounters(who, loc, fromDate, toDate, enteredViaForms, encounterTypes, null, includeVoided);
	}
	
	public List<CohortEncounter> getEncounters(CohortM who, Location loc, Date fromDate, Date toDate,
			Collection<Form> enteredViaForms, Collection<EncounterType> encounterTypes, Collection<User> providers,
			boolean includeVoided) {
		EncounterSearchCriteriaBuilder encounterSearchCriteriaBuilder = new EncounterSearchCriteriaBuilder()
				.setCohort(who)
				.setLocation(loc)
				.setFromDate(fromDate)
				.setToDate(toDate)
				.setEnteredViaForms(enteredViaForms)
				.setEncounterTypes(encounterTypes)
				.setProviders(usersToProviders(providers))
				.setIncludeVoided(includeVoided);
		return getEncounters(encounterSearchCriteriaBuilder.createEncounterSearchCriteria());
	}
	
	private Collection<Provider> usersToProviders(Collection<User> users) {
		if (users == null) {
			return null;
		}
		ProviderService providerService = Context.getProviderService();
		Collection<Provider> ret = new HashSet<Provider>();
		for (User u : users) {
			ret.addAll(providerService.getProvidersByPerson(u.getPerson()));
		}
		return ret;
	}
	
	public List<CohortEncounter> getEncounters(org.openmrs.module.cohort.api.db.EncounterSearchCriteria encounterSearchCriteria) {
		// the second search parameter is null as it defaults to authenticated user from context
		return Context.getService(CohortService.class).filterEncountersByViewPermissions(dao.getEncounters(encounterSearchCriteria),
				null);
	}
	
	public List<CohortEncounter> filterEncountersByViewPermissions(List<CohortEncounter> encounters, User user) {
		if (encounters != null) {
			// if user is not specified then use authenticated user from context by default
			if (user == null) {
				user = Context.getAuthenticatedUser();
			}
			for (Iterator<CohortEncounter> iterator = encounters.iterator(); iterator.hasNext(); ) {
				CohortEncounter encounter = iterator.next();
				// determine whether it's need to include this encounter into result or not
				// as it can be not accessed by current user due to permissions lack
				EncounterType et = encounter.getEncounterType();
				if (et != null) {
					// exclude this encounter from result
					iterator.remove();
				}
			}
		}
		return encounters;
	}
	
	@Override
	public List<CohortEncounter> getEncountersByCohort(String query, Integer cohortId, boolean includeVoided) {
		return dao.getEncounters(query, cohortId, null, null, includeVoided);
	}
	
	@Override
	public List<CohortObs> getObservationsByCohortAndConcept(CohortM who,
			Concept question) {
		List<CohortM> whom = new Vector<CohortM>();
		if (who != null && who.getCohortId() != null) {
			whom.add(who);
		}
		List<Concept> questions = new Vector<Concept>();
		questions.add(question);
		
		return Context.getService(CohortService.class).getObservations(whom, null, questions, null, null, null, null, null, null, null, false);
	}
	
	@Override
	public List<CohortObs> getObservations(List<CohortM> whom,
			List<CohortEncounter> encounters, List<Concept> questions,
			List<Concept> answers, List<Location> locations, List<String> sort,
			Integer mostRecentN, Integer obsGroupId, Date fromDate,
			Date toDate, boolean includeVoidedObs) {
		return null;
	}
	
	@Override
	public CohortRole getCohortRoleById(Integer id) {
		return dao.findCohortRole(id);
	}
	
	@Override
	public void purgeCohortRole(CohortRole crole) {
		dao.purgeCohortRole(crole);
	}
	
	@Override
	public Long getCount(String name) {
		return dao.getCount(name);
	}
	
	@Override
	public CohortObs voidObs(CohortObs obs, String reason) {
		return dao.saveObs(obs);
	}
	
	@Override
	public CohortMember getCohortMemberById(Integer id) {
		return dao.getCohortMember(id);
	}
	
	@Override
	public List<CohortMember> findCohortMemberByName(String name) {
		return dao.findCohortMember(name);
	}
	
	@Override
	public List<CohortEncounter> findCohortEncounter(String cohort, String location) {
		return dao.findCohortEncounter(cohort, location);
	}
	
	@Override
	public List<CohortMember> findCohortMembersByCohort (Integer cohortId) {
		return dao.findCohortMembersByCohortId(cohortId);
	}

	/*@Override
	public List<CohortM> getCohortsByLocationId(Integer id) {
		return dao.getCohortsByLocationId(id);
	}

	@Override
	public List<CohortM> getCohortByCohortTypeId(Integer id) {
		return dao.getCohortByCohortTypeId(id);
	}

	@Override
	public List<CohortM> getCohortByCohortProgramId(Integer id) {
		return dao.getCohortByCohortProgramId(id);
	}*/

	@Override
	public List<CohortMember> getCohortMembersByCohortRoleId(Integer id) {
		return dao.getCohortMembersByCohortRoleId(id);
	}

	@Override
	public CohortM getCohort(Integer locationId, Integer programId,Integer typeId) {
		return dao.getCohort(locationId, programId, typeId);
	}

	@Override
	public List<CohortMember> getCohortMembersByCohortId(Integer id) {
		return dao.getCohortMembersByCohortId(id);
	}

	@Override
	public List<CohortRole> getAllCohortRoles() {
		return dao.getAllCohortRoles();
	}

	@Override
	public CohortRole getCohortRoleByName(String name) {
		return dao.getCohortRoleByName(name);
	}

	@Override
	public List<CohortAttribute> getCohortAttributesByCohortId(Integer id) {
		return dao.getCohortAttributesByCohortId(id);
	}

	@Override
	public CohortType getCohortTypeByName(String name) {
		return dao.getCohortTypeByName(name);
	}

	@Override
	public CohortM getCohortByName(String name) {
		return dao.getCohortByName(name);
	}

	@Override
	public CohortVisit getCohortVisitByLocation(Integer id) {
		return dao.getCohortVisitByLocationId(id);
	}

	@Override
	public List<CohortMember> getAllHeadCohortMembers() {
		return dao.getAllHeadCohortMembers();
	}

	@Override
	public List<CohortObs> getCohortObsByEncounterId(Integer id) {
		return dao.getCohortObsByEncounterId(id);
	}

	@Override
	public List<CohortVisit> getCohortVisitsByDate(Date startDate, Date endDate) {
		return dao.getCohortVisitsByDate(startDate, endDate);
	}
}

	