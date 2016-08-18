package org.openmrs.module.cohort.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortType;
import org.openmrs.module.cohort.api.CohortService;
import org.openmrs.module.cohort.rest.v1_0.resource.CohortRest;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

import java.util.List;

@Resource(name = RestConstants.VERSION_1 + CohortRest.COHORT_NAMESPACE + "/testcohorttype", supportedClass = CohortType.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*, 1.10.*, 1.11.*", "1.12.*"})
public class CohortTypeRequestResource extends DataDelegatingCrudResource<CohortType> {

    @Override
    public DelegatingResourceDescription getRepresentationDescription(
            Representation rep) {

        DelegatingResourceDescription description = null;

        if (Context.isAuthenticated()) {
            description = new DelegatingResourceDescription();
            if (rep instanceof DefaultRepresentation) {
                description.addProperty("cohortTypeId");
                description.addProperty("name");
                description.addProperty("description");
                description.addProperty("uuid");
            } else if (rep instanceof FullRepresentation) {
                description.addProperty("cohortId");
                description.addProperty("name");
                description.addProperty("description");
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
    public CohortType save(CohortType arg0) {
        return Context.getService(CohortService.class).saveCohort(arg0);
    }

    @Override
    protected void delete(CohortType arg0, String arg1, RequestContext arg2)
            throws ResponseException {
        Context.getService(CohortService.class).purgeCohortType(arg0);

    }

    @Override
    public void purge(CohortType arg0, RequestContext arg1)
            throws ResponseException {
        // TODO Auto-generated method stub

    }

    @Override
    public CohortType newDelegate() {
        return new CohortType();
    }

    @Override
    public CohortType getByUniqueId(String uuid) {
        return Context.getService(CohortService.class).getCohortTypeUuid(uuid);
    }

    public SimpleObject getAll(RequestContext arg0) throws ResponseException {
        List<CohortType> cohort = Context.getService(CohortService.class).findCohortType();
        return new NeedsPaging<CohortType>(cohort, arg0).toSimpleObject(this);
    }

}
