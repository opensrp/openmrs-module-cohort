package org.openmrs.module.cohort.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortEncounter;
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

@Resource(name = RestConstants.VERSION_1 + CohortRest.COHORT_NAMESPACE + "/cohortenc", supportedClass = CohortEncounter.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*, 1.10.*, 1.11.*", "1.12.*"})
public class CohortEncounterRequestResource extends DataDelegatingCrudResource<CohortEncounter> {

    @Override
    public DelegatingResourceDescription getRepresentationDescription(
            Representation rep) {

        DelegatingResourceDescription description = null;

        if (Context.isAuthenticated()) {
            description = new DelegatingResourceDescription();
            if (rep instanceof DefaultRepresentation) {
                description.addProperty("encounterId");
                description.addProperty("cohort");
                description.addProperty("encounterType", Representation.REF);
                description.addProperty("location", Representation.REF);
                description.addProperty("form", Representation.REF);
                description.addProperty("visit");
                description.addProperty("encounterDateTime");
                description.addProperty("uuid");
            } else if (rep instanceof FullRepresentation) {
                description.addProperty("encounterId");
                description.addProperty("cohort");
                description.addProperty("encounterType");
                description.addProperty("location");
                description.addProperty("form");
                description.addProperty("visit");
                description.addProperty("encounterDateTime");
                description.addProperty("uuid");
            }
        }
        return description;
    }

    @Override
    public DelegatingResourceDescription getCreatableProperties() {
        DelegatingResourceDescription description = new DelegatingResourceDescription();
        description.addRequiredProperty("cohort");
        description.addRequiredProperty("visit");
        return description;
    }

    @Override
    public CohortEncounter newDelegate() {
        return new CohortEncounter();
    }

    @Override
    public CohortEncounter save(CohortEncounter arg0) {
        return Context.getService(CohortService.class).saveCohortEncounters(arg0);
    }

    @Override
    protected void delete(CohortEncounter arg0, String arg1, RequestContext arg2)
            throws ResponseException {

        Context.getService(CohortService.class).purgeCohortEncounters(arg0);
    }

    @Override
    public CohortEncounter getByUniqueId(String uuid) {
        return Context.getService(CohortService.class).getCohortEncUuid(uuid);
    }

    @Override
    public void purge(CohortEncounter arg0, RequestContext arg1)
            throws ResponseException {
        // TODO Auto-generated method stub

    }

    @Override
    public SimpleObject getAll(RequestContext arg0) throws ResponseException {
        List<CohortEncounter> cohort = Context.getService(CohortService.class).findCohortEncounters();
        return new NeedsPaging<CohortEncounter>(cohort, arg0).toSimpleObject(this);
    }


}
