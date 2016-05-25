package org.openmrs.module.cohort;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.BaseOpenmrsObject;

public class CohortType extends BaseOpenmrsData {
	
	@Override
	public String toString() {
		return this.name;
	}
	
	private int cohortTypeId;
	private String name;
	private String description;
	
	public int getCohortTypeId() {
		return cohortTypeId;
	}
	
	public void setCohortTypeId(int cohortTypeId) {
		this.cohortTypeId = cohortTypeId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public Integer getId() {
		return getCohortTypeId();
	}
	
	@Override
	public void setId(Integer id) {
		setCohortTypeId(id);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
