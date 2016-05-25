package org.openmrs.module.cohort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.BaseOpenmrsObject;
import org.openmrs.EncounterProvider;
import org.openmrs.EncounterRole;
import org.openmrs.EncounterType;
import org.openmrs.Form;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.Provider;
import org.openmrs.User;
import org.openmrs.api.context.Context;

public class CohortEncounter extends BaseOpenmrsData {
	
	private Integer encounterId;
	private CohortM cohort;
	private EncounterType encounterType;
	private Location location;
	private Date encounterDatetime;
	private Form form;
	private CohortVisit visit;
	
	private Set<CohortObs> obs;
	
	private Set<EncounterProvider> encounterProviders = new LinkedHashSet<EncounterProvider>();
	
	public Form getForm() {
		return form;
	}
	
	public void setForm(Form form) {
		this.form = form;
	}
	
	public CohortVisit getVisit() {
		return visit;
	}
	
	public void setVisit(CohortVisit visit) {
		this.visit = visit;
	}
	
	private Date encounterDateTime;
	
	@Override
	public Integer getId() {
		return getEncounterId();
	}
	
	@Override
	public void setId(Integer id) {
		setEncounterId(id);
	}
	
	public Integer getEncounterId() {
		return encounterId;
	}
	
	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Date getEncounterDateTime() {
		return encounterDateTime;
	}
	
	public void setEncounterDateTime(Date encounterDateTime) {
		this.encounterDateTime = encounterDateTime;
	}
	
	public EncounterType getEncounterType() {
		return encounterType;
	}
	
	public void setEncounterType(EncounterType encounterType) {
		this.encounterType = encounterType;
	}
	
	public CohortM getCohort() {
		return cohort;
	}
	
	public void setCohort(CohortM cohort) {
		this.cohort = cohort;
	}
	
	public void setProvider(EncounterRole role, Provider provider) {
		boolean hasProvider = false;
		for (Iterator<EncounterProvider> it = encounterProviders.iterator(); it.hasNext(); ) {
			EncounterProvider encounterProvider = it.next();
			if (encounterProvider.getEncounterRole().equals(role)) {
				if (!encounterProvider.getProvider().equals(provider)) {
					encounterProvider.setVoided(true);
					encounterProvider.setDateVoided(new Date());
					encounterProvider.setVoidedBy(Context.getAuthenticatedUser());
				} else if (!encounterProvider.isVoided()) {
					hasProvider = true;
				}
			}
		}
		
		if (!hasProvider) {
			addProvider(role, provider);
		}
	}
	
	public void addProvider(EncounterRole role, Provider provider) {
		// first, make sure the provider isn't already there
		for (EncounterProvider ep : encounterProviders) {
			if (ep.getEncounterRole().equals(role) && ep.getProvider().equals(provider) && !ep.isVoided()) {
				return;
			}
		}
		EncounterProvider encounterProvider = new EncounterProvider();
		//encounterProvider.setEncounter(this);
		encounterProvider.setEncounterRole(role);
		encounterProvider.setProvider(provider);
		encounterProvider.setDateCreated(new Date());
		encounterProvider.setCreator(Context.getAuthenticatedUser());
		encounterProviders.add(encounterProvider);
	}
	
	
	public Set<CohortObs> getObs() {
		Set<CohortObs> ret = new HashSet<CohortObs>();
		
		if (this.obs != null) {
			for (CohortObs o : this.obs) {
				ret.addAll(getObsLeaves(o));
			}
		}
		return ret;
	}
	
	private List<CohortObs> getObsLeaves(CohortObs obsParent) {
		List<CohortObs> leaves = new ArrayList<CohortObs>();
		
		if (obsParent.hasGroupMembers()) {
			for (CohortObs child : obsParent.getGroupMembers()) {
				if (!child.isVoided()) {
					if (!child.isObsGrouping()) {
						leaves.add(child);
					} else {
						// recurse if this is a grouping obs
						leaves.addAll(getObsLeaves(child));
					}
				}
			}
		} else if (!obsParent.isVoided()) {
			leaves.add(obsParent);
		}
		
		return leaves;
	}
	
	public Set<CohortObs> getAllObs(boolean includeVoided) {
		if (includeVoided && obs != null) {
			return obs;
		}
		
		Set<CohortObs> ret = new HashSet<CohortObs>();
		
		if (this.obs != null) {
			for (CohortObs o : this.obs) {
				if (includeVoided) {
					ret.add(o);
				} else if (!o.isVoided()) {
					ret.add(o);
				}
			}
		}
		return ret;
	}
	
	public void setObs(Set<CohortObs> obs) {
		this.obs = obs;
	}
	
	public Set<CohortObs> getAllObs() {
		return getAllObs(false);
	}
	
	public Set<CohortObs> getObsAtTopLevel(boolean includeVoided) {
		Set<CohortObs> ret = new HashSet<CohortObs>();
		for (CohortObs o : getAllObs(includeVoided)) {
			if (o.getObsGroup() == null) {
				ret.add(o);
			}
		}
		return ret;
	}
	
	
	public void addObs(CohortObs observation) {
		if (obs == null) {
			obs = new HashSet<CohortObs>();
		}
		
		if (observation != null) {
			obs.add(observation);
			
			//Propagate some attributes to the obs and any groupMembers
			
			// a Deque is a two-ended queue, that lets us add to the end, and fetch from the beginning
			Deque<CohortObs> obsToUpdate = new ArrayDeque<CohortObs>();
			obsToUpdate.add(observation);
			
			//prevent infinite recursion if an obs is its own group member
			Set<CohortObs> seenIt = new HashSet<CohortObs>();
			
			while (!obsToUpdate.isEmpty()) {
				CohortObs o = obsToUpdate.removeFirst();
				
				//has this obs already been processed?
				if (o == null || seenIt.contains(o)) {
					continue;
				}
				seenIt.add(o);
				
				o.setEncounterId(this);
				
				//if the attribute was already set, preserve it
				//if not, inherit the values sfrom the encounter
				if (o.getObsDateTime() == null) {
					o.setObsDateTime(getEncounterDateTime());
				}
				if (o.getCohort() == null) {
					o.setCohort(getCohort());
				}
				if (o.getLocation() == null) {
					o.setLocation(getLocation());
				}
				
				//propagate attributes to  all group members as well
				if (o.getGroupMembers(true) != null) {
					obsToUpdate.addAll(o.getGroupMembers());
				}
			}
			
		}
	}
	
	/**
	 * Remove the given observation from the list of obs for this Encounter
	 *
	 * @param observation
	 * @should remove obs successfully
	 * @should not throw error when removing null obs from empty set
	 * @should not throw error when removing null obs from non empty set
	 */
	public void removeObs(CohortObs observation) {
		if (obs != null) {
			obs.remove(observation);
		}
	}
	
	public Date getEncounterDatetime() {
		return encounterDatetime;
	}
	
	public void setEncounterDatetime(Date encounterDatetime) {
		this.encounterDatetime = encounterDatetime;
	}
	
	public Set<EncounterProvider> getEncounterProviders() {
		return encounterProviders;
	}
	
	public void setEncounterProviders(Set<EncounterProvider> encounterProviders) {
		this.encounterProviders = encounterProviders;
	}
	
	@Deprecated
	public Person getProvider() {
		if (encounterProviders == null || encounterProviders.isEmpty()) {
			return null;
		} else {
			for (EncounterProvider encounterProvider : encounterProviders) {
				// Return the first non-voided provider associated with a person in the list
				if (!encounterProvider.isVoided() && encounterProvider.getProvider().getPerson() != null) {
					return encounterProvider.getProvider().getPerson();
				}
			}
		}
		return null;
	}
	
	/**
	 * @param provider The provider to set.
	 * @deprecated use {@link #setProvider(Person)}
	 */
	@Deprecated
	public void setProvider(User provider) {
		setProvider(provider.getPerson());
	}
	
	/**
	 * @param provider The provider to set.
	 * @should set existing provider for unknown role
	 * @deprecated since 1.9, use {@link #setProvider(EncounterRole, Provider)}
	 */
	@Deprecated
	public void setProvider(Person provider) {
		EncounterRole unknownRole = Context.getEncounterService().getEncounterRoleByUuid(
				EncounterRole.UNKNOWN_ENCOUNTER_ROLE_UUID);
		if (unknownRole == null) {
			throw new IllegalStateException("No 'Unknown' encounter role with uuid "
					+ EncounterRole.UNKNOWN_ENCOUNTER_ROLE_UUID + ".");
		}
		Collection<Provider> providers = Context.getProviderService().getProvidersByPerson(provider);
		if (providers == null || providers.isEmpty()) {
			throw new IllegalArgumentException("No provider with personId " + provider.getPersonId());
		}
		setProvider(unknownRole, providers.iterator().next());
	}
}
	
	
	
	
	
