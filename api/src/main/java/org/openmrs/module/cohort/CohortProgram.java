package org.openmrs.module.cohort;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.BaseOpenmrsObject;

public class CohortProgram extends BaseOpenmrsData {
	
	@Override
	public String toString() {
		return this.name;
	}
	
	private int cohortProgramId;
	private String name;
	private String description;
	
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public Integer getId() {
		return getCohortProgramId();
	}
	
	@Override
	public void setId(Integer id) {
		setCohortProgramId(id);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCohortProgramId() {
		return cohortProgramId;
	}
	
	public void setCohortProgramId(int cohortProgramId) {
		this.cohortProgramId = cohortProgramId;
	}
}
