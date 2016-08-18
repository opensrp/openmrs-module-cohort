package org.openmrs.module.cohort.web.validator;

import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortM;
import org.openmrs.module.cohort.CohortMemberAttributeType;
import org.openmrs.module.cohort.api.CohortService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

@Component
@Qualifier("addCohortMemberAttributeTypeValidator")
public class AddCohortMemberAttributeTypeValidator implements Validator {

    @Override
    public boolean supports(Class<?> arg0) {
        return arg0.equals(CohortM.class);
    }

    @Override
    public void validate(Object arg0, Errors arg1) {
        CohortService service = Context.getService(CohortService.class);
        CohortMemberAttributeType current = (CohortMemberAttributeType) arg0;
        ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "name", "Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "description", "Description Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "format", "Format Required");
        List<CohortMemberAttributeType> allCohortMemberAttributeTypes = service.findCohortMemberAttributeType();
        for (CohortMemberAttributeType values : allCohortMemberAttributeTypes) {
            if (values.getName().equals(current.getName())) {
                arg1.rejectValue("name", "A value with this name already exists");
            }
        }
    }
}
