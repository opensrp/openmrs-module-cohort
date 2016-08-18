package org.openmrs.module.cohort.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortVisit;
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

@Resource(name = RestConstants.VERSION_1 + CohortRest.COHORT_NAMESPACE + "/testcohortvisit", supportedClass = CohortVisit.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*, 1.10.*, 1.11.*", "1.12.*"})
public class CohortVisitRequestResource extends DataDelegatingCrudResource<CohortVisit> {

    @Override
    public DelegatingResourceDescription getRepresentationDescription(
            Representation rep) {

        DelegatingResourceDescription description = null;

        if (Context.isAuthenticated()) {
            description = new DelegatingResourceDescription();
            if (rep instanceof DefaultRepresentation) {
                description.addProperty("cohortVisitId");
                description.addProperty("name");
                description.addProperty("cohort");
                description.addProperty("visitType");
                description.addProperty("location");
                description.addProperty("startDate");
                description.addProperty("endDate");
                description.addProperty("uuid");
            } else if (rep instanceof FullRepresentation) {
                description.addProperty("cohortVisitId");
                description.addProperty("name");
                description.addProperty("cohort");
                description.addProperty("visitType");
                description.addProperty("location");
                description.addProperty("startDate");
                description.addProperty("endDate");
                description.addProperty("uuid");
            }
        }
        return description;
    }

    @Override
    public DelegatingResourceDescription getCreatableProperties() {
        DelegatingResourceDescription description = new DelegatingResourceDescription();
        description.addRequiredProperty("cohortVisitId");
        description.addProperty("cohort");
        description.addProperty("visitType");
        return description;
    }

    @Override
    public CohortVisit save(CohortVisit arg0) {
        return Context.getService(CohortService.class).saveCohortVisit(arg0);
    }

    @Override
    protected void delete(CohortVisit arg0, String arg1, RequestContext arg2)
            throws ResponseException {
        Context.getService(CohortService.class).purgeCohortVisit(arg0);

    }

    @Override
    public void purge(CohortVisit arg0, RequestContext arg1)
            throws ResponseException {
        // TODO Auto-generated method stub

    }

    @Override
    public CohortVisit newDelegate() {
        return new CohortVisit();
    }

    @Override
    public CohortVisit getByUniqueId(String uuid) {
        return Context.getService(CohortService.class).getCohortVisitUuid(uuid);
    }


}
