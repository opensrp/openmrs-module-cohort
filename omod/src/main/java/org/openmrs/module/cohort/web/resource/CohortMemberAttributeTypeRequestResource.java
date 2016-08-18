package org.openmrs.module.cohort.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortMemberAttributeType;
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

@Resource(name = RestConstants.VERSION_1 + CohortRest.COHORT_NAMESPACE + "/testcohortmattributetype", supportedClass = CohortMemberAttributeType.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*, 1.10.*, 1.11.*", "1.12.*"})
public class CohortMemberAttributeTypeRequestResource extends DataDelegatingCrudResource<CohortMemberAttributeType> {

    @Override
    public DelegatingResourceDescription getRepresentationDescription(
            Representation rep) {

        DelegatingResourceDescription description = null;

        if (Context.isAuthenticated()) {
            description = new DelegatingResourceDescription();
            if (rep instanceof DefaultRepresentation) {
                description.addProperty("cohortMemberAttributeTypeId");
                description.addProperty("name");
                description.addProperty("description");
                description.addProperty("format");
                description.addProperty("uuid");
            } else if (rep instanceof FullRepresentation) {
                description.addProperty("cohortMemberAttributeTypeId");
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
        return description;
    }

    @Override
    public CohortMemberAttributeType save(CohortMemberAttributeType arg0) {
        return Context.getService(CohortService.class).saveCohortMemberAttributeType(arg0);
    }

    @Override
    protected void delete(CohortMemberAttributeType arg0, String arg1,
                          RequestContext arg2) throws ResponseException {
        Context.getService(CohortService.class).purgeCohortMemberAttributeType(arg0);
    }

    @Override
    public void purge(CohortMemberAttributeType arg0, RequestContext arg1)
            throws ResponseException {
        // TODO Auto-generated method stub

    }


    @Override
    public CohortMemberAttributeType newDelegate() {
        return new CohortMemberAttributeType();
    }


    @Override
    public CohortMemberAttributeType getByUniqueId(String arg0) {
        return Context.getService(CohortService.class).getCohortMemberAttributeType(arg0);
    }
}
