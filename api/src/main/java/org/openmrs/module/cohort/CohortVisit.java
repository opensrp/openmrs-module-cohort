package org.openmrs.module.cohort;


import java.util.Date;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Location;
import org.openmrs.VisitType;

public class CohortVisit extends BaseOpenmrsData {
	private static final long serialVersionUID = 1L;
	
	private Integer cohortVisitId;
	private CohortM cohort;
	private VisitType visitType;
	private Location location;
	private Date startDate;
	private Date endDate;
	
	public Integer getCohortVisitId() {
		return cohortVisitId;
	}

	public void setCohortVisitId(Integer cohortVisitId) {
		this.cohortVisitId = cohortVisitId;
	}

	public CohortM getCohort() {
		return cohort;
	}

	public void setCohort(CohortM cohort) {
		this.cohort = cohort;
	}

	public VisitType getVisitType() {
		return visitType;
	}

	public void setVisitType(VisitType visitType) {
		this.visitType = visitType;
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

	@Override
	public Integer getId() {
		return getCohortVisitId();
	}
	
	@Override
	public void setId(Integer id) {
		setCohortVisitId(id);
	}
}
	
	
	
