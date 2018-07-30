package org.openmrs.module.cohort;

import org.openmrs.BaseOpenmrsData;

public class CohortRole extends BaseOpenmrsData {
	private static final long serialVersionUID = 1L;
	
	private int cohortRoleId;
	private String name;
	private CohortType cohortType;
	
	public int getCohortRoleId() {
		return cohortRoleId;
	}

	public void setCohortRoleId(int cohortRoleId) {
		this.cohortRoleId = cohortRoleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CohortType getCohortType() {
		return cohortType;
	}

	public void setCohortType(CohortType cohortType) {
		this.cohortType = cohortType;
	}

	@Override
	public Integer getId() {
		return getCohortRoleId();
	}
	
	@Override
	public void setId(Integer id) {
		setCohortRoleId(id);
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
