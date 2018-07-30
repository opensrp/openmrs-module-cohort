package org.openmrs.module.cohort;

import org.openmrs.BaseOpenmrsData;

public class CohortAttributeType extends BaseOpenmrsData {
	private static final long serialVersionUID = 1L;
	
	private Integer cohortAttributeTypeId;
	private String name;
	private String description;
	private String format;
	
	public Integer getCohortAttributeTypeId() {
		return cohortAttributeTypeId;
	}

	public void setCohortAttributeTypeId(Integer cohortAttributeTypeId) {
		this.cohortAttributeTypeId = cohortAttributeTypeId;
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

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	public Integer getId() {
		return getCohortAttributeTypeId();
	}
	
	@Override
	public void setId(Integer id) {
		setCohortAttributeTypeId(id);
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	
}
