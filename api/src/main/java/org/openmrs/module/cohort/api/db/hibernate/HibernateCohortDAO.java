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
package org.openmrs.module.cohort.api.db.hibernate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Form;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifierType;
import org.openmrs.Person;
import org.openmrs.PersonName;
import org.openmrs.api.db.DAOException;
import org.openmrs.api.db.hibernate.HibernateUtil;
import org.openmrs.api.db.hibernate.PatientSearchCriteria;
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
import org.openmrs.module.cohort.EncounterSearchCriteria;
import org.openmrs.module.cohort.api.db.CohortDAO;

/**
 * It is a default implementation of  {@link cohortDAO}.
 */
public class HibernateCohortDAO implements CohortDAO {
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private SessionFactory sessionFactory;
	
	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	public CohortAttribute saveCohortAttributes(CohortAttribute att) {
		getCurrentSession().saveOrUpdate(att);
		return att;
	}
	
	@Override
	public List<CohortAttribute> findCohortAtt(String name) {
		Criteria criteria = (Criteria) getCurrentSession().createCriteria(CohortAttribute.class);
		criteria.add(Restrictions.ilike("value", name, MatchMode.START));
		return criteria.list();
	}
	
	@Override
	public CohortMember saveCPatient(CohortMember cohort) {
		getCurrentSession().saveOrUpdate(cohort);
		return cohort;
	}
	
	@Override
	public CohortType saveCohortType(CohortType cohorttype) {
		getCurrentSession().saveOrUpdate(cohorttype);
		return cohorttype;
	}
	
	@Override
	public List<CohortType> findCohortType() {
		List<CohortType> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortType");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public List<CohortType> getAllCohortTypes() {
		List<CohortType> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortType");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public List<CohortType> findCohortType(String cohort_name) {
		Criteria criteria = (Criteria) getCurrentSession().createCriteria(CohortType.class);
		criteria.add(Restrictions.ilike("name", cohort_name, MatchMode.START));
		return criteria.list();
	}
	
	@Override
	public void purgeCohortType(CohortType cohort) {
		getCurrentSession().delete(cohort);
	}
	
	@Override
	public void purgeCohortAtt(CohortAttribute att) {
		getCurrentSession().delete(att);
	}
	
	@Override
	public List<CohortAttributeType> findCohortAttributes() {
		List<CohortAttributeType> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortAttributeType");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public List<CohortAttributeType> findCohortAttributes(
			String attribute_type_name) {
		Criteria criteria = (Criteria) getCurrentSession().createCriteria(CohortAttributeType.class);
		criteria.add(Restrictions.ilike("name", attribute_type_name, MatchMode.START));
		return criteria.list();
	}
	
	@Override
	public void purgeCohortAttributes(CohortAttributeType attributes) {
		getCurrentSession().delete(attributes);
	}
	
	@Override
	public void purgeCohortEncounters(CohortEncounter cencounters) {
		getCurrentSession().delete(cencounters);
	}
	
	@Override
	public CohortType getCohortType(Integer id) {
		return (CohortType) getCurrentSession().get(CohortType.class, id);
	}
	
	@Override
	public CohortAttributeType saveCohortAttributes(
			CohortAttributeType attributes) {
		getCurrentSession().saveOrUpdate(attributes);
		return attributes;
	}
	
	@Override
	public CohortAttributeType getCohortAttributes(
			Integer cohort_attribute_type_id) {
		return (CohortAttributeType) getCurrentSession().get(CohortAttributeType.class, cohort_attribute_type_id);
	}
	
	@Override
	public CohortEncounter saveCohortEncounters(CohortEncounter cencounters) {
		getCurrentSession().saveOrUpdate(cencounters);
		return cencounters;
	}
	
	@Override
	public List<CohortEncounter> findCohortEncounters() {
		List<CohortEncounter> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortEncounter");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public CohortM saveCohort(CohortM cohort) {
		getCurrentSession().saveOrUpdate(cohort);
		return cohort;
	}
	
	@Override
	public void purgeCohort(CohortM cohort) {
		getCurrentSession().delete(cohort);
	}
	
	@Override
	public List<CohortM> getCohort(Integer id) {
		List<CohortM> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortM where cohortId='" + id + "'");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public List<CohortM> findCohorts() {
		List<CohortM> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortM");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public List<CohortM> findCohorts(String cohort_module) {
		Criteria criteria = (Criteria) getCurrentSession().createCriteria(CohortM.class);
		criteria.add(Restrictions.ilike("name", cohort_module, MatchMode.START));
		criteria.add(Restrictions.eq("voided", false));
		return criteria.list();
	}
	
	@Override
	public CohortMemberAttribute saveCohortMemberAttribute(
			CohortMemberAttribute att) {
		getCurrentSession().saveOrUpdate(att);
		return att;
	}
	
	@Override
	public void purgeCohortMemberAttribute(CohortMemberAttribute att) {
		getCurrentSession().delete(att);
	}
	
	@Override
	public CohortMemberAttributeType saveCohortMemberAttributeType(
			CohortMemberAttributeType at) {
		getCurrentSession().saveOrUpdate(at);
		return at;
	}
	
	@Override
	public void purgeCohortMemberAttributeType(CohortMemberAttributeType at) {
		getCurrentSession().delete(at);
	}
	
	@Override
	public List<CohortMemberAttributeType> findCohortMemberAttributeType() {
		List<CohortMemberAttributeType> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortMemberAttributeType");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public List<CohortMemberAttributeType> findCohortMemberAttributes(
			String attribute_type_name) {
		List<CohortMemberAttributeType> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortMemberAttributeType where name='" + attribute_type_name + "'");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public CohortVisit saveCohortVisit(CohortVisit cvisit) {
		getCurrentSession().saveOrUpdate(cvisit);
		return cvisit;
	}
	
	@Override
	public void purgeCohortVisit(CohortVisit cvisit) {
		getCurrentSession().delete(cvisit);
	}
	
	@Override
	public List<CohortVisit> findCohortVisit() {
		List<CohortVisit> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortVisit");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public CohortM getCohortId(Integer id) {
		return (CohortM) getCurrentSession().get(CohortM.class, id);
	}
	
	@Override
	public List<CohortMemberAttributeType> findCohortMemberAttributeType(
			String name) {
		Criteria criteria = (Criteria) getCurrentSession().createCriteria(CohortMemberAttributeType.class);
		criteria.add(Restrictions.ilike("name", name, MatchMode.START));
		criteria.add(Restrictions.eq("voided", false));
		return criteria.list();
	}
	
	@Override
	public List<CohortMemberAttribute> findCohortMemberAttribute(String name) {
		Criteria criteria = (Criteria) getCurrentSession().createCriteria(CohortMemberAttribute.class);
		criteria.add(Restrictions.ilike("value", name, MatchMode.START));
		criteria.add(Restrictions.eq("voided", false));
		return criteria.list();
	}
	
	@Override
	public CohortM getCohortUuid(String uuid) {
		return (CohortM) getCurrentSession().createQuery("from CohortM t where t.uuid = :uuid").setString("uuid", uuid).uniqueResult();
	}
	
	@Override
	public List<CohortEncounter> findCohortEncounters(String name) {
		Criteria criteria = (Criteria) getCurrentSession().createCriteria(CohortEncounter.class);
		criteria.add(Restrictions.ilike("encounterType", name, MatchMode.START));
		criteria.add(Restrictions.eq("voided", false));
		return criteria.list();
	}
	
	@Override
	public List<CohortVisit> findCohortVisit(String name) {
		Criteria criteria = (Criteria) getCurrentSession().createCriteria(CohortVisit.class);
		criteria.add(Restrictions.ilike("visitType", name, MatchMode.START));
		criteria.add(Restrictions.eq("voided", false));
		return criteria.list();
	}
	
	@Override
	public CohortAttribute getCohortAttributeUuid(String uuid) {
		return (CohortAttribute) getCurrentSession().createQuery("from CohortAttribute t where t.uuid = :uuid").setString("uuid", uuid).uniqueResult();
	}
	
	@Override
	public CohortEncounter getCohortEncounterUuid(String uuid) {
		return (CohortEncounter) getCurrentSession().createQuery("from CohortEncounter t where t.uuid = :uuid").setString("uuid", uuid).uniqueResult();
	}
	
	@Override
	public CohortMember getCohortMemUuid(String uuid) {
		return (CohortMember) getCurrentSession().createQuery("from CohortMember t where t.uuid = :uuid").setString("uuid", uuid).uniqueResult();
	}
	
	@Override
	public CohortMemberAttribute getCohortMemberAttributeUuid(String uuid) {
		return (CohortMemberAttribute) getCurrentSession().createQuery("from CohortMemberAttribute t where t.uuid = :uuid").setString("uuid", uuid).uniqueResult();
	}
	
	@Override
	public CohortType getCohortTypeUuid(String uuid) {
		return (CohortType) getCurrentSession().createQuery("from CohortType t where t.uuid = :uuid").setString("uuid", uuid).uniqueResult();
	}
	
	@Override
	public CohortVisit getCohortVisitUuid(String uuid) {
		return (CohortVisit) getCurrentSession().createQuery("from CohortVisit t where t.uuid = :uuid").setString("uuid", uuid).uniqueResult();
	}
	
	@Override
	public CohortAttributeType getCohortAttributeTypeUuid(String uuid) {
		return (CohortAttributeType) getCurrentSession().createQuery("from CohortAttributeType t where t.uuid = :uuid").setString("uuid", uuid).uniqueResult();
	}
	
	@Override
	public CohortMemberAttributeType getCohortMemberAttributeType(String uuid) {
		return (CohortMemberAttributeType) getCurrentSession().createQuery("from CohortMemberAttributeType t where t.uuid = :uuid").setString("uuid", uuid).uniqueResult();
	}
	
	@Override
	public List<CohortMember> findCohortMember() {
		List<CohortMember> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortMember");
		cohort = queryResult.list();
		return cohort;
		
	}
	
	@Override
	public List<CohortVisit> findCohortVisit(Integer id) {
		List<CohortVisit> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortVisit where cohortVisitId='" + id + "'");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public List<CohortM> findCohort(Integer id) {
		List<CohortM> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortM where cohortId='" + id + "'");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public List<CohortType> findCohortType(Integer id) {
		List<CohortType> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortType where cohortTypeId='" + id + "'");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public List<CohortAttribute> findCohortAtt(Integer id) {
		List<CohortAttribute> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortAttribute where cohortAttributeId='" + id + "'");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public List<CohortAttributeType> findCohortAttType(Integer id) {
		List<CohortAttributeType> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortAttributeType where cohortAttributeTypeId='" + id + "'");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public List<CohortMemberAttributeType> findCohortMemAttType(Integer id) {
		List<CohortMemberAttributeType> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortMemberAttributeType where cohortMemberAttributeTypeId='" + id + "'");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public List<CohortMemberAttribute> findCohortMemAtt(Integer id) {
		List<CohortMemberAttribute> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortMemberAttribute where cohortMemberAttributeId='" + id + "'");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public List<CohortEncounter> findCohortEnc(Integer id) {
		List<CohortEncounter> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortEncounter where encounterId='" + id + "'");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public CohortProgram saveCohortProgram(CohortProgram cohort) {
		getCurrentSession().saveOrUpdate(cohort);
		return cohort;
	}
	
	@Override
	public void purgeCohortProgram(CohortProgram cvisit) {
		getCurrentSession().delete(cvisit);
	}
	
	@Override
	public List<CohortProgram> findCohortProg() {
		List<CohortProgram> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortProgram");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public List<CohortProgram> findCohortProgram(String name) {
		Criteria criteria = (Criteria) getCurrentSession().createCriteria(CohortProgram.class);
		criteria.add(Restrictions.ilike("name", name, MatchMode.START));
		criteria.add(Restrictions.eq("voided", false));
		return criteria.list();
	}
	
	@Override
	public CohortProgram getCohortProgramUuid(String uuid) {
		return (CohortProgram) getCurrentSession().createQuery("from CohortProgram t where t.uuid = :uuid").setString("uuid", uuid).uniqueResult();
	}
	
	@Override
	public List<CohortProgram> findCohortProgram(Integer id) {
		List<CohortProgram> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortProgram where cohortProgramId='" + id + "'");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public CohortObs saveCohortObs(CohortObs cobs) {
		getCurrentSession().saveOrUpdate(cobs);
		return cobs;
	}
	
	@Override
	public void purgeCohortObs(CohortObs cobs) {
		getCurrentSession().delete(cobs);
	}
	
	@Override
	public List<CohortObs> findCohortObs() {
		List<CohortObs> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortObs");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public List<CohortObs> findCohortObs(Integer id) {
		List<CohortObs> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortObs where obsId='" + id + "'");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public CohortObs getCohortObsUuid(String uuid) {
		return (CohortObs) getCurrentSession().createQuery("from CohortObs t where t.uuid = :uuid").setString("uuid", uuid).uniqueResult();
	}
	
	@Override
	public CohortRole saveCohortRole(CohortRole cohort) {
		getCurrentSession().saveOrUpdate(cohort);
		return cohort;
	}
	
	@Override
	public List<CohortRole> findRoles(String name) {
		List<CohortRole> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortRole r where r.cohortType=(select m.cohortType from CohortM m where m.name='" + name + "')");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public List<CohortRole> findCohortRoles(String name) {
		List<CohortRole> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortRole where name='" + name + "'");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public CohortRole getCohortRoleUuid(String uuid) {
		return (CohortRole) getCurrentSession().createQuery("from CohortRole t where t.uuid = :uuid").setString("uuid", uuid).uniqueResult();
	}
	
	@Override
	public CohortEncounter getCohortEncounter(Integer id) {
		
		return (CohortEncounter) getCurrentSession().get(CohortEncounter.class, id);
		
	}
	
	@Override
	public List<CohortEncounter> getEncounters(EncounterSearchCriteria searchCriteria) {
		Criteria crit = getCurrentSession().createCriteria(CohortEncounter.class);
		
		if (searchCriteria.getCohort() != null && searchCriteria.getCohort().getCohortId() != null) {
			crit.add(Restrictions.eq("cohort", searchCriteria.getCohort()));
		}
		if (searchCriteria.getLocation() != null && searchCriteria.getLocation().getLocationId() != null) {
			crit.add(Restrictions.eq("location", searchCriteria.getLocation()));
		}
		if (searchCriteria.getFromDate() != null) {
			crit.add(Restrictions.ge("encounterDatetime", searchCriteria.getFromDate()));
		}
		if (searchCriteria.getToDate() != null) {
			crit.add(Restrictions.le("encounterDatetime", searchCriteria.getToDate()));
		}
		if (searchCriteria.getEnteredViaForms() != null && searchCriteria.getEnteredViaForms().size() > 0) {
			crit.add(Restrictions.in("form", searchCriteria.getEnteredViaForms()));
		}
		if (searchCriteria.getEncounterTypes() != null && searchCriteria.getEncounterTypes().size() > 0) {
			crit.add(Restrictions.in("encounterType", searchCriteria.getEncounterTypes()));
		}
		if (searchCriteria.getVisits() != null && searchCriteria.getVisits().size() > 0) {
			crit.add(Restrictions.in("visit", searchCriteria.getVisits()));
		}
		crit.addOrder(Order.asc("encounterDatetime"));
		return crit.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<CohortEncounter> getEncounters(String query, Integer cohortId,
			Integer start, Integer length, boolean includeVoided) {
		if (StringUtils.isBlank(query) && cohortId == null) {
			return Collections.emptyList();
		}
		
		Criteria criteria = createEncounterByQueryCriteria(query, cohortId, includeVoided, true);
		
		if (start != null) {
			criteria.setFirstResult(start);
		}
		if (length != null && length > 0) {
			criteria.setMaxResults(length);
		}
		
		return criteria.list();
	}
	
	private Criteria createEncounterByQueryCriteria(String query, Integer cohortId, boolean includeVoided,
			boolean orderByNames) {
		Criteria criteria = getCurrentSession().createCriteria(CohortEncounter.class, "enc");
		if (!includeVoided) {
			criteria.add(Restrictions.eq("enc.voided", false));
		}
		
		criteria = criteria.createCriteria("cohort", "coh");
		if (cohortId != null) {
			criteria.add(Restrictions.eq("coh.cohortId", cohortId));
			if (StringUtils.isNotBlank(query)) {
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				//match on location.name, encounterType.name, form.name
				//provider.name, provider.identifier, provider.person.names
				MatchMode mode = MatchMode.ANYWHERE;
				criteria.createAlias("enc.location", "loc");
				criteria.createAlias("enc.encounterType", "encType");
				criteria.createAlias("enc.form", "form");
				criteria.createAlias("enc.encounterProviders", "enc_prov");
				criteria.createAlias("enc_prov.provider", "prov");
				criteria.createAlias("prov.person", "person", Criteria.LEFT_JOIN);
				criteria.createAlias("person.names", "personName", Criteria.LEFT_JOIN);
				
				Disjunction or = Restrictions.disjunction();
				or.add(Restrictions.ilike("loc.name", query, mode));
				or.add(Restrictions.ilike("encType.name", query, mode));
				or.add(Restrictions.ilike("form.name", query, mode));
				or.add(Restrictions.ilike("prov.name", query, mode));
				or.add(Restrictions.ilike("prov.identifier", query, mode));
				
				String[] splitNames = query.split(" ");
				Disjunction nameOr = Restrictions.disjunction();
				for (String splitName : splitNames) {
					nameOr.add(Restrictions.ilike("personName.givenName", splitName, mode));
					nameOr.add(Restrictions.ilike("personName.middleName", splitName, mode));
					nameOr.add(Restrictions.ilike("personName.familyName", splitName, mode));
					nameOr.add(Restrictions.ilike("personName.familyName2", splitName, mode));
				}
				//OUTPUT for provider criteria: 
				//prov.name like '%query%' OR prov.identifier like '%query%'
				//OR ( personName.voided = false 
				//		 AND (  personName.givenName like '%query%' 
				//			OR personName.middleName like '%query%' 
				//			OR personName.familyName like '%query%'
				//			OR personName.familyName2 like '%query%'
				//			)
				//	 )
				Conjunction personNameConjuction = Restrictions.conjunction();
				personNameConjuction.add(Restrictions.eq("personName.voided", false));
				personNameConjuction.add(nameOr);
				
				or.add(personNameConjuction);
				
				criteria.add(or);
			}
		} else {
			String name = null;
			String identifier = null;
			if (query.matches(".*\\d+.*")) {
				identifier = query;
			} else {
				// there is no number in the string, search on name
				name = query;
			}
			criteria = new PatientSearchCriteria(sessionFactory, criteria).prepareCriteria(name, identifier,
					new ArrayList<PatientIdentifierType>(), false, orderByNames, false);
		}
		
		return criteria;
	}
	
	@Override
	public List<CohortRole> findCohortRole(String cohort_name) {
		Criteria criteria = (Criteria) getCurrentSession().createCriteria(CohortRole.class);
		criteria.add(Restrictions.ilike("name", cohort_name, MatchMode.START));
		criteria.add(Restrictions.eq("voided", false));
		return criteria.list();
	}
	
	@Override
	public List<CohortRole> findCohortRole(Integer id) {
		List<CohortRole> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortRole where cohortRoleId='" + id + "'");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public void purgeCohortRole(CohortRole crole) {
		getCurrentSession().delete(crole);
	}
	
	@Override
	public Long getCount(String name) {
		return ((Long) getCurrentSession().createQuery("select count(*) from CohortMember c left outer join c.cohort m where m.name='" + name + "'").iterate().next()).longValue();
	}
	
	@Override
	public CohortObs saveObs(CohortObs obs) {
		if (obs.hasGroupMembers() && obs.getObsId() != null) {
			// hibernate has a problem updating child collections
			// if the parent object was already saved so we do it
			// explicitly here
			for (CohortObs member : obs.getGroupMembers()) {
				if (member.getObsId() == null) {
					saveObs(member);
				}
			}
		}
		
		getCurrentSession().saveOrUpdate(obs);
		
		return obs;
	}
	
	@Override
	public List<CohortMember> getCohortMember(Integer id) {
		List<CohortMember> cohort = null;
		Session session = getCurrentSession();
		Query queryResult = session.createQuery("from CohortMember where cohortMemberId='" + id + "'");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public List<CohortMember> findCohortMember(String name) {
		List<CohortMember> cohort = null;
		Session session = getCurrentSession();
		/*Criteria criteria=(Criteria) getCurrentSession().createCriteria(Person.class);
    	criteria.add(Restrictions.ilike("names",name, MatchMode.START));*/
		Query queryResult = session.createQuery("from CohortMember where person=(select personId from Person where names='" + name + "'");
		cohort = queryResult.list();
		return cohort;
	}
	
	@Override
	public List<CohortEncounter> findCohortEncounter(String cohort, String location) {
		Criteria criteria = (Criteria) getCurrentSession().createCriteria(CohortEncounter.class);
		criteria.add(Restrictions.ilike("cohort", cohort, MatchMode.START));
		criteria.add(Restrictions.ilike("location", location, MatchMode.START));
		return criteria.list();
	}
	
	@Override
	public List<CohortMember> findCohortMembersByCohortId (Integer cohortId) {
		Session session = getCurrentSession();
		return session.createQuery("from CohortMember where cohort ='" +cohortId+ "'").list();
//		List<CohortMember> cohortMembers = null;
//		Criteria criteria = (Criteria) getCurrentSession().createCriteria(CohortMember.class);
//		criteria.add(Restrictions.ilike("cohort", cohortId.toString(), MatchMode.START));
//		return criteria.list();
	}
	
	/**
	 * Gets the current hibernate session while taking care of the hibernate 3 and 4 differences.
	 * 
	 * @return the current hibernate session.
	 */
	private org.hibernate.Session getCurrentSession() {
		try {
			return sessionFactory.getCurrentSession();
		}
		catch (NoSuchMethodError ex) {
			try {
				Method method = sessionFactory.getClass().getMethod("getCurrentSession", null);
				return (org.hibernate.Session)method.invoke(sessionFactory, null);
			}
			catch (Exception e) {
				throw new RuntimeException("Failed to get the current hibernate session", e);
			}
		}
	}
}