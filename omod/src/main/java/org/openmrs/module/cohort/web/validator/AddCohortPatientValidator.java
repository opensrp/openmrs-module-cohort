package org.openmrs.module.cohort.web.validator;

import org.openmrs.module.cohort.CohortM;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@Qualifier("addCohortPatientValidator")
public class AddCohortPatientValidator implements Validator {

    @Override
    public boolean supports(Class<?> arg0) {
        return arg0.equals(CohortM.class);
    }

    @Override
    public void validate(Object arg0, Errors arg1) {
        
    }
}
