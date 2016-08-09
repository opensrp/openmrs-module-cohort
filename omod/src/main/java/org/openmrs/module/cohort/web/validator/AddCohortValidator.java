package org.openmrs.module.cohort.web.validator;

import org.openmrs.module.cohort.CohortM;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@Qualifier("addCohortValidator")
public class AddCohortValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return arg0.equals(CohortM.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "name", "Cohort Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "description", "Cohort Description Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "startDate", "Cohort Start Date Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "endDate", "Cohort End Date Required");
		CohortM cohort = (CohortM)arg0;
		if (cohort.getStartDate().compareTo(cohort.getEndDate()) > 0) {
			arg1.rejectValue("startDate", "Start date should be less than End date");
		}
	}
}
