package org.openmrs.module.cohort.web.validator;

import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortProgram;
import org.openmrs.module.cohort.CohortRole;
import org.openmrs.module.cohort.api.CohortService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@Qualifier("addCohortProgramValidator")
public class AddCohortProgramValidator implements Validator {

    @Override
    public boolean supports(Class<?> arg0) {
        return arg0.equals(CohortRole.class);
    }

    @Override
    public void validate(Object arg0, Errors arg1) {
        ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "name", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "description", "required");
        CohortProgram program = (CohortProgram) arg0;
        CohortService departmentService = Context.getService(CohortService.class);
        for (CohortProgram programs : departmentService.findCohortProg()) {
            if (program.getName().equals(programs.getName())) {
                arg1.rejectValue("name", "An entry with this name already exists");
            }
        }
    }
}
