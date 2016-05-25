package org.openmrs.module.cohort;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.BaseOpenmrsObject;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.Person;

public class CohortM extends BaseOpenmrsData {
	
	
	private Integer cohortId;
	private String name;
	private String description;
	private Location clocation;
	private Date startDate;
	private Date endDate;
	private static final long serialVersionUID = 1L;
	private CohortType cohortType;
	private CohortProgram cohortProgram;
	//private Long members;
	private boolean groupCohort;
	
	public boolean isGroupCohort() {
		return groupCohort;
	}
	
	public void setGroupCohort(boolean groupCohort) {
		this.groupCohort = groupCohort;
	}
	
	@Override
	public Integer getId() {
		return getCohortId();
	}
	
	@Override
	public void setId(Integer id) {
		setCohortId(id);
	}
	
	public Integer getCohortId() {
		return cohortId;
	}
	
	public void setCohortId(Integer cohortId) {
		this.cohortId = cohortId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
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
	
	public CohortType getCohortType() {
		return cohortType;
	}
	
	public void setCohortType(CohortType cohortType) {
		this.cohortType = cohortType;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Location getClocation() {
		return clocation;
	}
	
	public void setClocation(Location clocation) {
		this.clocation = clocation;
	}
	
	public CohortProgram getCohortProgram() {
		return cohortProgram;
	}
	
	public void setCohortProgram(CohortProgram cohortProgram) {
		this.cohortProgram = cohortProgram;
	}
	/*public Long getMembers() {
		return members;
	}
	public void setMembers(Long members) {
		this.members = members;
	}*/
	
	@Override
	public String toString() {
		return this.name;
	}
}
	
	
	
