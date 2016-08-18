package org.openmrs.module.cohort.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortM;
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

@Resource(name = RestConstants.VERSION_1 + CohortRest.COHORT_NAMESPACE + "/cohort", supportedClass = CohortM.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*, 1.10.*, 1.11.*", "1.12.*"})
public class CohortRequestResource extends DataDelegatingCrudResource<CohortM> {

    @Override
    public DelegatingResourceDescription getRepresentationDescription(
            Representation rep) {

        DelegatingResourceDescription description = null;

        if (Context.isAuthenticated()) {
            description = new DelegatingResourceDescription();
            if (rep instanceof DefaultRepresentation) {
                description.addProperty("cohortId");
                description.addProperty("name");
                description.addProperty("description");
                description.addProperty("startDate");
                description.addProperty("endDate");
                description.addProperty("uuid");
                description.addProperty("cohortType");
                description.addProperty("cohortProgram");
                description.addSelfLink();
            } else if (rep instanceof FullRepresentation) {
                description.addProperty("cohortId");
                description.addProperty("name");
                description.addProperty("description");
                description.addProperty("startDate");
                description.addProperty("endDate");
                description.addProperty("uuid");
                description.addProperty("cohortType");
                description.addProperty("cohortProgram");
                description.addSelfLink();
            }
        }
        return description;
    }

    @Override
    public DelegatingResourceDescription getCreatableProperties() {
        DelegatingResourceDescription description = new DelegatingResourceDescription();
        description.addRequiredProperty("name");
        description.addProperty("description");
        description.addProperty("startDate");
        description.addProperty("endDate");
        description.addRequiredProperty("cohortType");
        description.addRequiredProperty("cohortProgram");
        return description;
    }

    @Override
    public CohortM save(CohortM arg0) {
        return Context.getService(CohortService.class).saveCohort(arg0);
    }

    @Override
    protected void delete(CohortM arg0, String arg1, RequestContext arg2)
            throws ResponseException {
        Context.getService(CohortService.class).purgeCohort(arg0);

    }

    @Override
    public void purge(CohortM arg0, RequestContext arg1)
            throws ResponseException {
        // TODO Auto-generated method stub

    }

    @Override
    public CohortM newDelegate() {
        return new CohortM();
    }

    @Override
    public CohortM getByUniqueId(String uuid) {
        return Context.getService(CohortService.class).getCohortUuid(uuid);
    }


    @Override
    public SimpleObject getAll(RequestContext arg0) throws ResponseException {
        List<CohortM> cohort = Context.getService(CohortService.class).findCohorts();
        return new NeedsPaging<CohortM>(cohort, arg0).toSimpleObject(this);
    }

    @Override
    public SimpleObject search(RequestContext context) throws ResponseException {
        List<CohortM> cohort = Context.getService(CohortService.class).findCohorts(context.getParameter("t"));
        return new NeedsPaging<CohortM>(cohort, context).toSimpleObject(this);
    }
}
