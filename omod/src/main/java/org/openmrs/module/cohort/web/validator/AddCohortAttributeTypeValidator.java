package org.openmrs.module.cohort.web.validator;

import org.openmrs.module.cohort.CohortAttributeType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@Qualifier("addCohortAttributeTypeValidator")
public class AddCohortAttributeTypeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(CohortAttributeType.class);
    }

    @Override
    public void validate(Object command, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "format", "required");
        
        //TODO reject if duplicate is created
    }
}
