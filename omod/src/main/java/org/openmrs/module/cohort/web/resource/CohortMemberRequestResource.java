package org.openmrs.module.cohort.web.resource;

import org.openmrs.Person;
import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortMember;
import org.openmrs.module.cohort.api.CohortService;
import org.openmrs.module.cohort.rest.v1_0.resource.CohortRest;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.ConversionUtil;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.PropertyGetter;
import org.openmrs.module.webservices.rest.web.annotation.PropertySetter;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ConversionException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;
import org.openmrs.module.webservices.validation.ValidateUtil;

import java.util.List;
import java.util.Map;

@Resource(name = RestConstants.VERSION_1 + CohortRest.COHORT_NAMESPACE + "/cohortmember", supportedClass = CohortMember.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*, 1.10.*, 1.11.*", "1.12.*"})
public class CohortMemberRequestResource extends DataDelegatingCrudResource<CohortMember> {


    @PropertySetter("person")
    public static void setPerson(CohortMember instance, String uuid) {
    }

    @PropertyGetter("person")
    public static Person getPerson(CohortMember instance) {
        return instance.getPerson();
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(
            Representation rep) {

        DelegatingResourceDescription description = null;

        if (Context.isAuthenticated()) {
            description = new DelegatingResourceDescription();
            if (rep instanceof DefaultRepresentation) {
                description.addProperty("cohortMemberId");
                description.addProperty("person", Representation.DEFAULT);
                description.addProperty("cohort");
                description.addProperty("role");
                description.addProperty("startDate");
                description.addProperty("uuid");
            } else if (rep instanceof FullRepresentation) {
                description.addProperty("cohortMemberId");
                description.addProperty("person", Representation.FULL);
                description.addProperty("cohort");
                description.addProperty("role");
                description.addProperty("startDate");
                description.addProperty("uuid");
            }
        }
        return description;
    }

    @Override
    public DelegatingResourceDescription getCreatableProperties() {
        DelegatingResourceDescription description = new DelegatingResourceDescription();
        description.addRequiredProperty("person");
        description.addRequiredProperty("cohort");
        return description;
    }

    @Override
    public Object create(SimpleObject propertiesToCreate, RequestContext context) throws ResponseException {
        CohortMember delegate = getCohortMember(propertiesToCreate);
        ValidateUtil.validate(delegate);
        delegate = save(delegate);
        return ConversionUtil.convertToRepresentation(delegate, Representation.DEFAULT);
    }

    public CohortMember getCohortMember(SimpleObject propertiesToCreate) {
        Object personProperty = propertiesToCreate.get("person");
        Person person = null;
        if (personProperty == null) {
            throw new ConversionException("The person property is missing");
        } else if (personProperty instanceof String) {
            person = Context.getPersonService().getPersonByUuid((String) personProperty);
            Context.evictFromSession(person);
        } else if (personProperty instanceof Map) {
            person = (Person) ConversionUtil.convert(personProperty, Person.class);
            propertiesToCreate.put("person", "");
        }

        CohortMember delegate = new CohortMember(person);
        setConvertedProperties(delegate, propertiesToCreate, getCreatableProperties(), true);
        return delegate;
    }


    @Override
    public CohortMember newDelegate() {
        return new CohortMember();
    }

    @Override
    public CohortMember save(CohortMember arg0) {
        return Context.getService(CohortService.class).saveCPatient(arg0);
    }

    @Override
    protected void delete(CohortMember arg0, String arg1, RequestContext arg2)
            throws ResponseException {

        Context.getService(CohortService.class);
    }

    @Override
    public CohortMember getByUniqueId(String uuid) {
        return Context.getService(CohortService.class).getCohortMemUuid(uuid);
    }

    @Override
    public void purge(CohortMember arg0, RequestContext arg1)
            throws ResponseException {
        // TODO Auto-generated method stub

    }

    @Override
    protected PageableResult doGetAll(RequestContext context) {
        return new NeedsPaging<CohortMember>(Context.getService(CohortService.class).findCohortMember(), context);
    }

    @Override
    public SimpleObject search(RequestContext context) throws ResponseException {
        List<CohortMember> cohort = Context.getService(CohortService.class).findCohortMember(context.getRequest().getParameter("h"));
        return new NeedsPaging<CohortMember>(cohort, context).toSimpleObject(this);
    }


}
