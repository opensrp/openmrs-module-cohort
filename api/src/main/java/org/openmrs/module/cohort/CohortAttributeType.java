package org.openmrs.module.cohort;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.BaseOpenmrsObject;

public class CohortAttributeType extends BaseOpenmrsData {
	private Integer cohortAttributeTypeId;
	private String name;
	private String description;
	private String format;
	
	@Override
	public Integer getId() {
		return getCohortAttributeTypeId();
	}
	
	@Override
	public void setId(Integer id) {
		setCohortAttributeTypeId(id);
	}
	
	
	public String getFormat() {
		return format;
	}
	
	public void setFormat(String format) {
		this.format = format;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
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
	
	@Override
	public String toString() {
		return this.name;
	}
	
	
}
