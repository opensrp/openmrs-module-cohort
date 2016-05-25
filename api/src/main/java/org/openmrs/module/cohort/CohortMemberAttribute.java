package org.openmrs.module.cohort;

import org.openmrs.BaseOpenmrsData;

public class CohortMemberAttribute extends BaseOpenmrsData {
	
	private Integer cohortMemberAttributeId;
	private CohortMember cohortMember;
	private CohortMemberAttributeType cohortMemberAttributeType;
	private String value;
	
	@Override
	public Integer getId() {
		return getCohortMemberAttributeId();
	}
	
	@Override
	public void setId(Integer id) {
		setCohortMemberAttributeId(id);
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public Integer getCohortMemberAttributeId() {
		return cohortMemberAttributeId;
	}
	
	public void setCohortMemberAttributeId(Integer cohortMemberAttributeId) {
		this.cohortMemberAttributeId = cohortMemberAttributeId;
	}
	
	public CohortMemberAttributeType getCohortMemberAttributeType() {
		return cohortMemberAttributeType;
	}
	
	public void setCohortMemberAttributeType(CohortMemberAttributeType cohortMemberAttributeType) {
		this.cohortMemberAttributeType = cohortMemberAttributeType;
	}
	
	public CohortMember getCohortMember() {
		return cohortMember;
	}
	
	public void setCohortMember(CohortMember cohortMember) {
		this.cohortMember = cohortMember;
	}
	
}
