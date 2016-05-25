package org.openmrs.module.cohort;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.BaseOpenmrsObject;

public class CohortMemberAttributeType extends BaseOpenmrsData {
	@Override
	public String toString() {
		return this.name;
	}
	
	private Integer cohortMemberAttributeTypeId;
	private String name;
	private String description;
	private String format;
	
	@Override
	public Integer getId() {
		return getCohortMemberAttributeTypeId();
	}
	
	@Override
	public void setId(Integer id) {
		setCohortMemberAttributeTypeId(id);
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getCohortMemberAttributeTypeId() {
		return cohortMemberAttributeTypeId;
	}
	
	public void setCohortMemberAttributeTypeId(Integer cohortMemberAttributeTypeId) {
		this.cohortMemberAttributeTypeId = cohortMemberAttributeTypeId;
	}
}
