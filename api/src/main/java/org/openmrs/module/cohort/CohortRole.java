package org.openmrs.module.cohort;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.BaseOpenmrsObject;

public class CohortRole extends BaseOpenmrsData {
	private int cohortRoleId;
	private String name;
	private CohortType cohortType;
	
	@Override
	public Integer getId() {
		return getCohortRoleId();
	}
	
	@Override
	public void setId(Integer id) {
		setCohortRoleId(id);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCohortRoleId() {
		return cohortRoleId;
	}
	
	public void setCohortRoleId(int cohortRoleId) {
		this.cohortRoleId = cohortRoleId;
	}
	
	public CohortType getCohortType() {
		return cohortType;
	}
	
	public void setCohortType(CohortType cohortType) {
		this.cohortType = cohortType;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
