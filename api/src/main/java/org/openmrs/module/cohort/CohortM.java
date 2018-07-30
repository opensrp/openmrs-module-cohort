package org.openmrs.module.cohort;

import java.util.Date;
import org.openmrs.BaseOpenmrsData;
import org.openmrs.Location;

public class CohortM extends BaseOpenmrsData {
	
	private static final long serialVersionUID = 1L;
	
	private Integer cohortId;
	private String name;
	private String description;
	private Location location;
	private Date startDate;
	private Date endDate;
	private CohortType cohortType;
	private CohortProgram cohortProgram;
	private boolean groupCohort;
	
	public Integer getCohortId() {
		return cohortId;
	}

	public void setCohortId(Integer cohortId) {
		this.cohortId = cohortId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
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

	public CohortProgram getCohortProgram() {
		return cohortProgram;
	}

	public void setCohortProgram(CohortProgram cohortProgram) {
		this.cohortProgram = cohortProgram;
	}

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
	
	@Override
	public String toString() {
		return this.name;
	}
}
	
	
	
