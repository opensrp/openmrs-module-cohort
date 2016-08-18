package org.openmrs.module.cohort.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortObs;
import org.openmrs.module.cohort.api.CohortService;
import org.openmrs.module.cohort.rest.v1_0.resource.CohortRest;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + CohortRest.COHORT_NAMESPACE + "/cohortobs", supportedClass = CohortObs.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*, 1.10.*, 1.11.*", "1.12.*"})
public class CohortObsRequestResource extends DataDelegatingCrudResource<CohortObs> {

    @Override
    public DelegatingResourceDescription getRepresentationDescription(
            Representation rep) {

        DelegatingResourceDescription description = null;

        if (Context.isAuthenticated()) {
            description = new DelegatingResourceDescription();
            if (rep instanceof DefaultRepresentation) {
                description.addProperty("obsId");
                description.addProperty("cohort");
                description.addProperty("encounterId");
                description.addProperty("location");
                description.addProperty("obsDateTime");
                description.addProperty("uuid");
            } else if (rep instanceof FullRepresentation) {
                description.addProperty("obsId");
                description.addProperty("cohort");
                description.addProperty("encounterId");
                description.addProperty("location");
                description.addProperty("obsDateTime");
                description.addProperty("uuid");
            }
        }
        return description;
    }

    @Override
    public DelegatingResourceDescription getCreatableProperties() {
        DelegatingResourceDescription description = new DelegatingResourceDescription();
        description.addRequiredProperty("cohort");
        description.addRequiredProperty("encounterId");
        return description;
    }

    @Override
    public CohortObs save(CohortObs arg0) {
        return Context.getService(CohortService.class).saveCohortObs(arg0);
    }

    @Override
    protected void delete(CohortObs arg0, String arg1, RequestContext arg2)
            throws ResponseException {

        Context.getService(CohortService.class).purgeCohortObs(arg0);
    }

    @Override
    public void purge(CohortObs arg0, RequestContext arg1)
            throws ResponseException {
        // TODO Auto-generated method stub

    }

    @Override
    public CohortObs newDelegate() {
        return new CohortObs();
    }

    @Override
    public CohortObs getByUniqueId(String arg0) {
        return Context.getService(CohortService.class).getCohortObsUuid(arg0);
    }

    @Override
    protected PageableResult doGetAll(RequestContext context)
            throws ResponseException {
        return new NeedsPaging<CohortObs>(Context.getService(CohortService.class).findCohortObs(), context);
    }
}
