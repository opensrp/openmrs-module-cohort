package org.openmrs.module.cohort;

import java.util.Date;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Person;


public class CohortMember extends BaseOpenmrsData {
	private Integer cohortMemberId;
	private Person person;
	private Integer personId;
	private CohortM cohort;
	private CohortRole role;
	private Date startDate;
	private boolean head;
	
	public boolean isHead() {
		return head;
	}
	
	public void setHead(boolean head) {
		this.head = head;
	}
	
	public CohortMember() {
		
	}
	
	public CohortMember(Person person) {
		person = new Person();
		if (person != null) {
			this.personId = person.getPersonId();
			if (person.getUuid() != null) {
				this.setUuid(person.getUuid());
			}
		}
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	private Date endDate;
	
	@Override
	public Integer getId() {
		return getCohortMemberId();
	}
	
	@Override
	public void setId(Integer arg0) {
		setCohortMemberId(arg0);
	}
	
	public Integer getCohortMemberId() {
		return cohortMemberId;
	}
	
	public void setCohortMemberId(Integer cohortMemberId) {
		this.cohortMemberId = cohortMemberId;
	}
	
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public CohortM getCohort() {
		return cohort;
	}
	
	public void setCohort(CohortM cohort) {
		this.cohort = cohort;
	}
	
	public CohortRole getRole() {
		return role;
	}
	
	public void setRole(CohortRole role) {
		this.role = role;
	}
}
