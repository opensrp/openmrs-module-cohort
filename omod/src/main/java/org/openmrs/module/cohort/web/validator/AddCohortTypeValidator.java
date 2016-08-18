package org.openmrs.module.cohort.web.validator;

import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortType;
import org.openmrs.module.cohort.api.CohortService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

@Component
@Qualifier("addCohortTypeValidator")
public class AddCohortTypeValidator implements Validator {

    @Override
    public boolean supports(Class<?> arg0) {
        return arg0.equals(CohortType.class);
    }

    @Override
    public void validate(Object arg0, Errors arg1) {
        ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "name", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "description", "required");
        CohortType currentType = (CohortType) arg0;
        CohortService departmentService = Context.getService(CohortService.class);
        List<CohortType> cohortTypes = departmentService.findCohortType();
        for (CohortType type : cohortTypes) {
            if (type.getName().equals(currentType.getName())) {
                arg1.rejectValue("name", "a cohort type with the same name already exists");
            }
        }
    }
}
