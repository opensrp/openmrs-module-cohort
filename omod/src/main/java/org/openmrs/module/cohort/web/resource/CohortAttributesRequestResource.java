package org.openmrs.module.cohort.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortAttribute;
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

@Resource(name = RestConstants.VERSION_1 + CohortRest.COHORT_NAMESPACE + "/testcohortatt", supportedClass = CohortAttribute.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*, 1.10.*, 1.11.*", "1.12.*"})
public class CohortAttributesRequestResource extends DataDelegatingCrudResource<CohortAttribute> {

    @Override
    public DelegatingResourceDescription getRepresentationDescription(
            Representation rep) {

        DelegatingResourceDescription description = null;

        if (Context.isAuthenticated()) {
            description = new DelegatingResourceDescription();
            if (rep instanceof DefaultRepresentation) {
                description.addProperty("cohortAttributeId");
                description.addProperty("cohort");
                description.addProperty("value");
                description.addProperty("cohortAttributeType");
                description.addProperty("uuid");
            } else if (rep instanceof FullRepresentation) {
                description.addProperty("cohortAttributeId");
                description.addProperty("cohort");
                description.addProperty("value");
                description.addProperty("cohortAttributeType");
                description.addProperty("uuid");
            }
        }
        return description;
    }

    @Override
    public DelegatingResourceDescription getCreatableProperties() {
        DelegatingResourceDescription description = new DelegatingResourceDescription();
        description.addRequiredProperty("cohort");
        description.addRequiredProperty("value");
        description.addRequiredProperty("cohortAttributeType");
        return description;
    }

    @Override
    public CohortAttribute save(CohortAttribute arg0) {
        return Context.getService(CohortService.class).saveCohortAttributes(arg0);
    }

    @Override
    protected void delete(CohortAttribute arg0, String arg1, RequestContext arg2)
            throws ResponseException {
        Context.getService(CohortService.class).purgeCohortAtt(arg0);
    }

    @Override
    public void purge(CohortAttribute arg0, RequestContext arg1)
            throws ResponseException {
        // TODO Auto-generated method stub

    }

    @Override
    public CohortAttribute newDelegate() {
        return new CohortAttribute();
    }

    @Override
    public CohortAttribute getByUniqueId(String uuid) {
        return Context.getService(CohortService.class).getCohortAttributeUuid(uuid);

    }
}
