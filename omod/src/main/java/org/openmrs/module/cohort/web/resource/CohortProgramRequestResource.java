package org.openmrs.module.cohort.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortProgram;
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

@Resource(name = RestConstants.VERSION_1 + CohortRest.COHORT_NAMESPACE + "/testcohortprogram", supportedClass = CohortProgram.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*, 1.10.*, 1.11.*", "1.12.*"})
public class CohortProgramRequestResource extends DataDelegatingCrudResource<CohortProgram> {

    @Override
    public DelegatingResourceDescription getRepresentationDescription(
            Representation rep) {

        DelegatingResourceDescription description = null;

        if (Context.isAuthenticated()) {
            description = new DelegatingResourceDescription();
            if (rep instanceof DefaultRepresentation) {
                description.addProperty("cohortProgramId");
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
    public CohortProgram save(CohortProgram arg0) {
        // TODO Auto-generated method stub
        return Context.getService(CohortService.class).saveCohortProgram(arg0);
    }

    @Override
    protected void delete(CohortProgram arg0, String arg1, RequestContext arg2)
            throws ResponseException {
        Context.getService(CohortService.class).purgeCohortProgram(arg0);
    }

    @Override
    public void purge(CohortProgram arg0, RequestContext arg1)
            throws ResponseException {


    }

    @Override
    public CohortProgram newDelegate() {
        return new CohortProgram();
    }

    @Override
    public CohortProgram getByUniqueId(String uuid) {
        return Context.getService(CohortService.class).getCohortProgramUuid(uuid);
    }

}
