package org.openmrs.module.cohort;

import org.openmrs.BaseOpenmrsData;

public class CohortType extends BaseOpenmrsData {
	
	private static final long serialVersionUID = 1L;

	private int cohortTypeId;
	private String name;
	private String description;
	
	
	public int getCohortTypeId() {
		return cohortTypeId;
	}

	public void setCohortTypeId(int cohortTypeId) {
		this.cohortTypeId = cohortTypeId;
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
		return getCohortTypeId();
	}
	
	@Override
	public void setId(Integer id) {
		setCohortTypeId(id);
	}
}
