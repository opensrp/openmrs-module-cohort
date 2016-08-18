package org.openmrs.module.cohort.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortAttributeType;
import org.openmrs.module.cohort.api.CohortService;
import org.openmrs.module.cohort.rest.v1_0.resource.CohortRest;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + CohortRest.COHORT_NAMESPACE + "/testcohortatttype", supportedClass = CohortAttributeType.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*, 1.10.*, 1.11.*", "1.12.*"})
public class CohortAttributesTypeRequestResource extends DataDelegatingCrudResource<CohortAttributeType> {

    @Override
    public DelegatingResourceDescription getRepresentationDescription(
            Representation rep) {

        DelegatingResourceDescription description = null;

        if (Context.isAuthenticated()) {
            description = new DelegatingResourceDescription();
            if (rep instanceof DefaultRepresentation) {
                description.addProperty("cohortAttributeTypeId");
                description.addProperty("name");
                description.addProperty("description");
                description.addProperty("format");
                description.addProperty("uuid");
            } else if (rep instanceof FullRepresentation) {
                description.addProperty("cohortAttributeTypeId");
                description.addProperty("name");
                description.addProperty("description");
                description.addProperty("format");
                description.addProperty("uuid");
            }
        }
        return description;
    }

    @Override
    public DelegatingResourceDescription getCreatableProperties() {
        DelegatingResourceDescription description = new DelegatingResourceDescription();
        description.addRequiredProperty("name");
        description.addRequiredProperty("format");
        return description;
    }

    @Override
    public CohortAttributeType save(CohortAttributeType arg0) {
        return Context.getService(CohortService.class).saveCohort(arg0);
    }

    @Override
    protected void delete(CohortAttributeType arg0, String arg1,
                          RequestContext arg2) throws ResponseException {
        Context.getService(CohortService.class).purgeCohortAttributes(arg0);

    }

    @Override
    public void purge(CohortAttributeType arg0, RequestContext arg1)
            throws ResponseException {
        // TODO Auto-generated method stub

    }

    @Override
    public CohortAttributeType newDelegate() {
        return new CohortAttributeType();
    }

    @Override
    public CohortAttributeType getByUniqueId(String arg0) {
        return Context.getService(CohortService.class).getCohortAttributeTypeUuid(arg0);
    }
}
