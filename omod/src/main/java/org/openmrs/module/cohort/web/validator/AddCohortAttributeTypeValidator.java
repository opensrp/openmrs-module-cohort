package org.openmrs.module.cohort.web.validator;

import org.openmrs.module.cohort.CohortRole;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@Qualifier("addCohortAttributeTypeValidator")
public class AddCohortAttributeTypeValidator implements Validator {

    @Override
    public boolean supports(Class<?> arg0) {
        return arg0.equals(CohortRole.class);
    }

    @Override
    public void validate(Object arg0, Errors arg1) {
        ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "name", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "description", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "format", "required");
    }
}
