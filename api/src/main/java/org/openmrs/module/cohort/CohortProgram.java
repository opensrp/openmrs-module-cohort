package org.openmrs.module.cohort;

import org.openmrs.BaseOpenmrsData;

public class CohortProgram extends BaseOpenmrsData {
	
	private static final long serialVersionUID = 1L;

	private int cohortProgramId;
	private String name;
	private String description;
	
	
	public int getCohortProgramId() {
		return cohortProgramId;
	}

	public void setCohortProgramId(int cohortProgramId) {
		this.cohortProgramId = cohortProgramId;
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

	@Override
	public Integer getId() {
		return getCohortProgramId();
	}
	
	@Override
	public void setId(Integer id) {
		setCohortProgramId(id);
	}
}
