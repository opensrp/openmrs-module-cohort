package org.openmrs.module.cohort.web.validator;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortType;
import org.openmrs.module.cohort.api.CohortService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@Qualifier("addCohortTypeValidator")
public class AddCohortTypeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(CohortType.class);
    }

    @Override
    public void validate(Object command, Errors errors) {
    	CohortService cohortService = Context.getService(CohortService.class);

    	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required");
        
        CohortType currentType = (CohortType) command;
        List<CohortType> cohortTypes = cohortService.findCohortType();
        
        // TODO change it to find by name and then reject
        for (CohortType type : cohortTypes) {
            if (type.getName().equals(currentType.getName())) {
            	errors.rejectValue("name", "a cohort type with the same name already exists");
            }
        }
    }
}
