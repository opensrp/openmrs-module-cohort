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

import org.hibernate.Criteria;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Form;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.PersonName;
import org.openmrs.Privilege;
import org.openmrs.Provider;
import org.openmrs.User;
import org.openmrs.api.APIException;
import org.openmrs.api.ProviderService;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.cohort.CohortAttribute;
import org.openmrs.module.cohort.CohortAttributeType;
import org.openmrs.module.cohort.CohortEncounter;
import org.openmrs.module.cohort.CohortM;
import org.openmrs.module.cohort.CohortMember;
import org.openmrs.module.cohort.CohortMemberAttribute;
import org.openmrs.module.cohort.CohortMemberAttributeType;
import org.openmrs.module.cohort.CohortObs;
import org.openmrs.module.cohort.CohortProgram;
import org.openmrs.module.cohort.CohortRole;
import org.openmrs.module.cohort.CohortType;
import org.openmrs.module.cohort.CohortVisit;
import org.openmrs.module.cohort.EncounterSearchCriteriaBuilder;
import org.openmrs.module.cohort.api.CohortService;
import org.openmrs.module.cohort.api.db.CohortDAO;
import org.openmrs.util.PrivilegeConstants;

/**
 * It is a default implementation of {@link cohortService}.
 */
public class CohortServiceImpl extends BaseOpenmrsService implements
		CohortService {
	
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
	public CohortType getCohortType(Integer cohort_type_id) {
		return dao.getCohortType(cohort_type_id);
	}
	
	@Override
	public CohortMember saveCPatient(CohortMember cohort) {
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
	public void purgeCohortEncounters(CohortEncounter cencounters) {
		dao.purgeCohortEncounters(cencounters);
	}
	
	@Override
	public List<CohortType> getAllCohortTypes() {
		return dao.getAllCohortTypes();
	}
	
	@Override
	public List<CohortType> findCohortType() {
		return dao.findCohortType();
	}
	
	@Override
	public List<CohortType> findCohortType(String cohort_name) {
		return dao.findCohortType(cohort_name);
	}
	
	@Override
	public List<CohortAttributeType> findCohortAttributes() {
		return dao.findCohortAttributes();
	}
	
	@Override
	public List<CohortAttributeType> findCohortAttributes(
			String attribute_type_name) {
		return dao.findCohortAttributes(attribute_type_name);
	}
	
	@Override
	public List<CohortEncounter> findCohortEncounters() {
		return dao.findCohortEncounters();
	}
	
	@Override
	public CohortEncounter saveCohortEncounters(CohortEncounter cencounters) {
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
	public List<CohortM> findCohorts() {
		return dao.findCohorts();
	}
	
	@Override
	public List<CohortM> findCohorts(String cohort_name) {
		return dao.findCohorts(cohort_name);
	}
	
	@Override
	public List<CohortM> getCohort(Integer id) {
		return dao.getCohort(id);
	}
	
	@Override
	public CohortAttribute saveCohortAttributes(CohortAttribute att) {
		return dao.saveCohortAttributes(att);
	}
	
	@Override
	public List<CohortAttribute> findCohortAtt(String name) {
		return dao.findCohortAtt(name);
	}
	
	@Override
	public void purgeCohortType(CohortType type) {
		dao.purgeCohortType(type);
	}
	
	@Override
	public CohortMemberAttribute saveCohortMemberAttribute(
			CohortMemberAttribute att) {
		return dao.saveCohortMemberAttribute(att);
	}
	
	@Override
	public void purgeCohortMemberAttribute(CohortMemberAttribute att) {
		dao.purgeCohortMemberAttribute(att);
	}
	
	@Override
	public CohortMemberAttributeType saveCohortMemberAttributeType(
			CohortMemberAttributeType at) {
		return dao.saveCohortMemberAttributeType(at);
	}
	
	@Override
	public void purgeCohortMemberAttributeType(CohortMemberAttributeType at) {
		dao.purgeCohortMemberAttributeType(at);
	}
	
	@Override
	public List<CohortMemberAttributeType> findCohortMemberAttributeType() {
		return dao.findCohortMemberAttributeType();
	}
	
	@Override
	public List<CohortMemberAttributeType> findCohortMemberAttributes(
			String attribute_type_name) {
		return dao.findCohortMemberAttributes(attribute_type_name);
		
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
	public List<CohortVisit> findCohortVisit() {
		return dao.findCohortVisit();
	}
	
	@Override
	public CohortM getCohortId(Integer id) {
		return dao.getCohortId(id);
	}
	
	@Override
	public List<CohortMemberAttributeType> findCohortMemberAttributeType(
			String name) {
		return dao.findCohortMemberAttributeType(name);
	}
	
	@Override
	public List<CohortMemberAttribute> findCohortMemberAttribute(String name) {
		return dao.findCohortMemberAttribute(name);
	}
	
	@Override
	public CohortM getCohortUuid(String uuid) {
		return dao.getCohortUuid(uuid);
	}
	
	@Override
	public List<CohortEncounter> findCohortEncounters(String name) {
		return dao.findCohortEncounters(name);
	}
	
	@Override
	public List<CohortVisit> findCohortVisit(String name) {
		return dao.findCohortVisit(name);
	}
	
	@Override
	public CohortAttribute getCohortAttributeUuid(String uuid) {
		return dao.getCohortAttributeUuid(uuid);
	}
	
	@Override
	public CohortEncounter getCohortEncUuid(String uuid) {
		return dao.getCohortEncounterUuid(uuid);
	}
	
	@Override
	public CohortMember getCohortMemUuid(String uuid) {
		return dao.getCohortMemUuid(uuid);
	}
	
	@Override
	public CohortMemberAttribute getCohortMemberAttributeUuid(String uuid) {
		return dao.getCohortMemberAttributeUuid(uuid);
	}
	
	@Override
	public CohortType getCohortTypeUuid(String uuid) {
		return dao.getCohortTypeUuid(uuid);
	}
	
	@Override
	public CohortVisit getCohortVisitUuid(String uuid) {
		return dao.getCohortVisitUuid(uuid);
	}
	
	@Override
	public CohortAttributeType getCohortAttributeTypeUuid(String uuid) {
		return dao.getCohortAttributeTypeUuid(uuid);
	}
	
	@Override
	public CohortMemberAttributeType getCohortMemberAttributeType(String uuid) {
		return dao.getCohortMemberAttributeType(uuid);
	}
	
	@Override
	public List<CohortMember> findCohortMember() {
		return dao.findCohortMember();
	}
	
	@Override
	public List<CohortVisit> findCohortVisit(Integer id) {
		return dao.findCohortVisit(id);
	}
	
	@Override
	public List<CohortM> findCohort(Integer id) {
		// TODO Auto-generated method stub
		return dao.findCohort(id);
	}
	
	@Override
	public List<CohortType> findCohortType(Integer id) {
		// TODO Auto-generated method stub
		return dao.findCohortType(id);
	}
	
	@Override
	public List<CohortAttribute> findCohortAtt(Integer id) {
		// TODO Auto-generated method stub
		return dao.findCohortAtt(id);
	}
	
	@Override
	public List<CohortAttributeType> findCohortAttType(Integer id) {
		// TODO Auto-generated method stub
		return dao.findCohortAttType(id);
	}
	
	@Override
	public List<CohortMemberAttributeType> findCohortMemAttType(Integer id) {
		// TODO Auto-generated method stub
		return dao.findCohortMemAttType(id);
	}
	
	@Override
	public List<CohortMemberAttribute> findCohortMemAtt(Integer id) {
		// TODO Auto-generated method stub
		return dao.findCohortMemAtt(id);
	}
	
	@Override
	public List<CohortEncounter> findCohortEnc(Integer id) {
		// TODO Auto-generated method stub
		return dao.findCohortEnc(id);
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
	public List<CohortProgram> findCohortProg() {
		return dao.findCohortProg();
	}
	
	@Override
	public List<CohortProgram> findCohortProgram(String name) {
		return dao.findCohortProgram(name);
	}
	
	@Override
	public CohortProgram getCohortProgramUuid(String uuid) {
		return dao.getCohortProgramUuid(uuid);
		
	}
	
	@Override
	public List<CohortProgram> findCohortProgram(Integer id) {
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
	public List<CohortObs> findCohortObs() {
		return dao.findCohortObs();
	}
	
	@Override
	public List<CohortObs> findCohortObs(Integer id) {
		return dao.findCohortObs(id);
	}
	
	@Override
	public CohortObs getCohortObsUuid(String uuid) {
		return dao.getCohortObsUuid(uuid);
	}
	
	@Override
	public CohortRole saveCohortRole(CohortRole cohort) {
		return dao.saveCohortRole(cohort);
	}
	
	@Override
	public List<CohortRole> findRoles(String name) {
		return dao.findRoles(name);
	}
	
	@Override
	public List<CohortRole> findCohortRoles(String name) {
		return dao.findCohortRoles(name);
	}
	
	@Override
	public CohortRole getCohortRoleUuid(String uuid) {
		return dao.getCohortRoleUuid(uuid);
	}
	
	@Override
	public CohortEncounter getCohortEncounter(Integer id) {
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
	
	public List<CohortEncounter> getEncounters(org.openmrs.module.cohort.EncounterSearchCriteria encounterSearchCriteria) {
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
	public List<CohortEncounter> getEncountersByCohort(String query,
			boolean includeVoided) {
		if (query == null) {
			throw new IllegalArgumentException("The 'query' parameter is required and cannot be null");
		}
		
		return Context.getService(CohortService.class).filterEncountersByViewPermissions(dao.getEncounters(query, null, null, null, includeVoided), null);
	}
	
	@Override
	public List<CohortEncounter> getEncountersByCohort(CohortM cohort) {
		if (cohort == null) {
			throw new IllegalArgumentException("The 'patient' parameter is requred and cannot be null");
		}
		return Context.getService(CohortService.class).getEncounters(cohort, null, null, null, null, null, null, false);
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
	public CohortEncounter voidEncounter(CohortEncounter encounter,
			String reason) {
		if (reason == null) {
			throw new IllegalArgumentException("The argument 'reason' is required and so cannot be null");
		}
		encounter.setVoided(true);
		encounter.setVoidedBy(Context.getAuthenticatedUser());
		//we expect the dateVoided to be already set by AOP logic at this point unless this method was called within the API,
		//this ensures that original ParentVoidedDate and the dateVoided of associated objects will always match for the
		//unvoid handler to work
		if (encounter.getDateVoided() == null) {
			encounter.setDateVoided(new Date());
		}
		encounter.setVoidReason(reason);
		Context.getService(CohortService.class).saveCohortEncounters(encounter);
		return encounter;
	}
	
	@Override
	public List<CohortRole> findCohortRole(String cohort_name) {
		return dao.findCohortRole(cohort_name);
	}
	
	@Override
	public List<CohortRole> findCohortRole(Integer id) {
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
	public List<CohortMember> getCohortMember(Integer id) {
		return dao.getCohortMember(id);
	}
	
	@Override
	public List<CohortMember> findCohortMember(String name) {
		return dao.findCohortMember(name);
	}
	
	@Override
	public List<CohortEncounter> findCohortEncounter(String cohort, String location) {
		return dao.findCohortEncounter(cohort, location);
	}
	
	@Override
	public List<CohortMember> findCohortMembersByCohortId (Integer cohortId) {
		return dao.findCohortMembersByCohortId(cohortId);
	}
}

	