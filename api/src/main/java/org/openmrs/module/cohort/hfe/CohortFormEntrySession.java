package org.openmrs.module.cohort.hfe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Encounter;
import org.openmrs.EncounterProvider;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.api.ObsService;
import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortEncounter;
import org.openmrs.module.cohort.CohortM;
import org.openmrs.module.cohort.CohortObs;
import org.openmrs.module.cohort.api.CohortService;
import org.openmrs.module.htmlformentry.BadFormDesignException;
import org.openmrs.module.htmlformentry.CustomFormSubmissionAction;
import org.openmrs.module.htmlformentry.FormEntryContext.Mode;
import org.openmrs.module.htmlformentry.FormEntrySession;
import org.openmrs.module.htmlformentry.HtmlForm;
import org.openmrs.module.htmlformentry.HtmlFormEntryUtil;
import org.openmrs.module.htmlformentry.InvalidActionException;
import org.springframework.util.StringUtils;

public class CohortFormEntrySession extends FormEntrySession {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	public final static String[] COHORT_TAGS = {"cohort"};
	
	private CohortM cohort;
	
	private HtmlForm htmlForm;
	
	private CohortEncounter cohortEncounter;
	
	private CohortObs cohortobs;
	
	private Encounter encounter;
	
	private Obs obs;
	
	private CohortFormSubmissionActions submissionActions;
	
	public CohortFormSubmissionActions getSubmissionActions() {
		return submissionActions;
	}
	
	public void setCohort(CohortM cohort) {
		this.cohort = cohort;
	}
	
	public CohortFormEntrySession(CohortM cohort, HtmlForm htmlForm, HttpSession httpSession) throws Exception {
		this(cohort, htmlForm, Mode.ENTER, httpSession);
	}
	
	
	public CohortFormEntrySession(CohortM cohort, HtmlForm htmlForm, Mode mode, HttpSession httpSession) throws Exception {
		this(cohort, null, htmlForm, mode, null, httpSession, true, false);
	}
	
	public CohortFormEntrySession(CohortM cohort, CohortEncounter encounter, HtmlForm htmlForm, Mode mode,
			Location defaultLocation, HttpSession httpSession,
			boolean automaticClientSideValidation,
			boolean clientSideValidationHints) throws Exception {
		super(null, null/*convertToEncounter(encounter)*/, mode, htmlForm, defaultLocation, httpSession, automaticClientSideValidation, clientSideValidationHints);
		this.htmlForm = htmlForm;
		this.encounter = convertToEncounter(encounter);
		this.cohortEncounter = encounter;
		this.cohort = cohort;
	}
	
	public CohortFormEntrySession(CohortM cohort, CohortEncounter encounter,
			Mode mode, HtmlForm htmlForm, HttpSession session) throws Exception {
		this(cohort, encounter, htmlForm, mode, null, session, true, false);
	}
	
	public static Encounter convertToEncounter(CohortEncounter encounter) {
		if (encounter != null) {
			Encounter e = new Encounter();
			e.setEncounterType(encounter.getEncounterType());
			e.setEncounterDatetime(encounter.getEncounterDateTime());
			e.setLocation(encounter.getLocation());
			e.setForm(encounter.getForm());
			Set<EncounterProvider> eps = new HashSet<EncounterProvider>();
			EncounterProvider ep = new EncounterProvider();
			ep.setEncounter(e);
			ep.setProvider(ep.getProvider());
			eps.add(ep);
			e.setEncounterProviders(eps);
			Set<Obs> obss = null;//TODO
			e.setObs(obss);
			
		}
		return null;
	}
	
	public static Obs convertToObs(CohortObs cobs) {
		if (cobs != null) {
			Obs e = new Obs();
			e.setConcept(cobs.getConcept());
			e.setAccessionNumber(cobs.getAccessionNumber());
		}
		return null;
	}
	
	@Override
	public void applyActions() throws BadFormDesignException {
		// if any encounter to be created by this form is missing a required field, throw an error
		// (If there's a widget but it was left blank, that would have been caught earlier--this
		// is for when there was no widget in the first place.)
		
		{
			for (Encounter e : getSubmissionActions().getEncountersToCreate()) {
				if (!HtmlFormEntryUtil.hasProvider(e) || e.getEncounterDatetime() == null || e.getLocation() == null) {
					throw new BadFormDesignException("Please check the design of your form to make sure it has all three tags: <b>&lt;encounterDate/&gt</b>;, <b>&lt;encounterLocation/&gt</b>;, and <b>&lt;encounterProvider/&gt;</b>");
				}
			}
		}
		
		// remove any obs groups that don't contain children
		HtmlFormEntryUtil.removeEmptyObs(getSubmissionActions().getObsToCreate());
		
		// propagate encounterDatetime to Obs where necessary
		if (getSubmissionActions().getObsToCreate() != null) {
			List<Obs> toCheck = new ArrayList<Obs>();
			toCheck.addAll(getSubmissionActions().getObsToCreate());
			while (toCheck.size() > 0) {
				Obs o = toCheck.remove(toCheck.size() - 1);
				if (o.getObsDatetime() == null && o.getEncounter() != null) {
					o.setObsDatetime(o.getEncounter().getEncounterDatetime());
					if (log.isDebugEnabled()) {
						log.debug("Set obsDatetime to " + o.getObsDatetime() + " for "
								+ o.getConcept().getName(Context.getLocale(), false));
					}
				}
				if (o.getLocation() == null && o.getEncounter() != null) {
					o.setLocation(o.getEncounter().getLocation());
				}
				if (o.hasGroupMembers()) {
					toCheck.addAll(o.getGroupMembers());
				}
			}
		}
		
		// TODO get cohorts to create
		if (submissionActions.getCohortsToCreate() != null) {
			for (CohortM p : submissionActions.getCohortsToCreate()) {
				if (p instanceof CohortM) {
					CohortM cohort = p;
					if (!StringUtils.hasText(cohort.getName()) || !StringUtils.hasText(cohort.getDescription())
							|| cohort.getStartDate() == null || cohort.getEndDate() == null) {
						throw new BadFormDesignException(
								"Please check the design of your form to make sure the following fields are mandatory to create a patient: <br/><b>&lt;personName/&gt;</b>, <b>&lt;birthDateOrAge/&gt;</b>, <b>&lt;gender/&gt;</b>, <b>&lt;identifierType/&gt;</b>, <b>&lt;identifier/&gt;</b>, and <b>&lt;identifierLocation/&gt;</b>");
					}
				}
				Context.getService(CohortService.class).saveCohort(cohort);
			}
		}
		if (getSubmissionActions().getEncountersToCreate() != null) {
			for (Encounter e : getSubmissionActions().getEncountersToCreate()) {
				if (htmlForm != null) {
					e.setForm(htmlForm.getForm());
					if (htmlForm.getForm().getEncounterType() != null) {
						e.setEncounterType(htmlForm.getForm().getEncounterType());
					}
				}
				cohortEncounter = new CohortEncounter();
				cohortEncounter.setCohort(cohort);
				cohortEncounter.setEncounterType(e.getEncounterType());
				cohortEncounter.setForm(e.getForm());
				cohortEncounter.setEncounterDateTime(e.getEncounterDatetime());
				cohortEncounter.setLocation(e.getLocation());
				cohortEncounter.setEncounterProviders(e.getEncounterProviders());
				//TODO add visit somehow
				// cohortEncounter.setVisit(cvisit.get(0));
				//TODO SAVE COHORT ENCOUNTER
				System.out.println("SAVE COHORT ENC");
				Context.getService(CohortService.class).saveCohortEncounter(cohortEncounter);
			}
		}
		
		ObsService obsService = Context.getObsService();
		
		if (getSubmissionActions().getObsToVoid() != null) {
			for (Obs o : getSubmissionActions().getObsToVoid()) {
				if (log.isDebugEnabled()) {
					log.debug("voiding obs: " + o.getObsId());
				}
				obsService.voidObs(o, "htmlformentry");
				// if o was in a group and it has no obs left, void the group
				//TODO IMPLEMENT FOR COHORT OBS
				cohortobs.setObsGroup(o.getObsGroup());
				voidObsGroupIfAllChildObsVoided(cohortobs);
			}
		}
		
		if (submissionActions.getObsToCreate() != null) {
			for (Obs o : submissionActions.getObsToCreate()) {
				cohortobs = new CohortObs();
				cohortobs.setAccessionNumber(o.getAccessionNumber());
				cohortobs.setObsDateTime(o.getObsDatetime());
				cohortobs.setEncounterId(cohortEncounter);
				cohortobs.setChangedBy(o.getChangedBy());
				cohortobs.setComment(o.getComment());
				cohortobs.setConcept(o.getConcept());
				cohortobs.setComplexData(o.getComplexData());
				cohortobs.setCreator(o.getCreator());
				cohortobs.setDateVoided(o.getDateVoided());
				Context.getService(CohortService.class).saveCohortObs(cohortobs);
			}
			
			//Context.getObsService().saveObs(o, null);
		}
//TODO Add Obs Creation line from FormENtrySession 
		// If we're in EDIT mode, we have to save the encounter so that any new obs are created.
		// This feels a bit like a hack, but actually it's a good thing to update the encounter's dateChanged in this case. (PS- turns out there's no dateChanged on encounter up to 1.5.)
		// If there is no encounter (impossible at the time of writing this comment) we save the obs manually
		if (getContext().getMode() == Mode.EDIT) {
			if (cohortEncounter != null) {
				System.out.println("SAVE COHORT ENCOUNTER");
				Context.getService(CohortService.class).saveCohortEncounter(cohortEncounter);
			} else if (getSubmissionActions().getObsToCreate() != null) {
				// this may not work right due to savehandlers (similar error to HTML-135) but this branch is
				// unreachable until html forms are allowed to edit data without an encounter
				for (Obs o : getSubmissionActions().getObsToCreate()) {
					System.out.println("SAVE COHORT OBS");
					
					cohortobs.setAccessionNumber(o.getAccessionNumber());
					cohortobs.setObsDateTime(o.getObsDatetime());
					cohortobs.setEncounterId(cohortEncounter);
					//TODO obsService.saveObs(o, null);
					Context.getService(CohortService.class).saveCohortObs(cohortobs);
				}
			}
		}
		
		// handle any custom actions (for an example of a custom action, see: https://github.com/PIH/openmrs-module-appointmentschedulingui/commit/e2cda8de1caa8a45d319ae4fbf7714c90c9adb8b)
		if (getSubmissionActions().getCustomFormSubmissionActions() != null) {
			for (CustomFormSubmissionAction customFormSubmissionAction : getSubmissionActions().getCustomFormSubmissionActions()) {
				customFormSubmissionAction.applyAction(this);
			}
		}
		
	}
	
	@Override
	public void prepareForSubmit() {
		
		submissionActions = new CohortFormSubmissionActions();
		
		if (hasCohortTag() && !hasEncouterTag()) {
			try {
				submissionActions.beginCohort(cohort);
			} catch (InvalidActionException e) {
				log.error("Programming error: should be no errors starting a patient", e);
			}
		} else {
			if (getContext().getMode() == Mode.EDIT) {
				if (getEncounter() == null) {
					throw new RuntimeException("Programming exception: encounter shouldn't be null in EDIT mode");
				}
			} else {
				encounter = new Encounter();
			}
			try {
				// TODO cohort specific submissionActions.beginPerson(patient);
				submissionActions.beginCohort(cohort);
				submissionActions.beginEncounter(encounter);
			} catch (InvalidActionException e) {
				log.error("Programming error: should be no errors starting a patient and encounter", e);
			}
		}
		
	}
	
	public CohortM getCohort() {
		return cohort;
	}
	
	public String getReturnUrlWithParameters() {
		if (!StringUtils.hasText(getReturnUrl())) {
			return null;
		}
		String ret = getReturnUrl();
		if (!ret.contains("?")) {
			ret += "?";
		}
		if (!ret.endsWith("?") && !ret.endsWith("&")) {
			ret += "&";
		}
		ret += "cohortId=" + getCohort().getCohortId();
		return ret;
	}
	
	public boolean hasCohortTag() {
		for (String tag : CohortFormEntrySession.COHORT_TAGS) {
			tag = "<" + tag;
			if (htmlForm.getXmlData().contains(tag)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Encounter getEncounter() {
		return encounter;
	}
	
	public CohortEncounter getCohortEncounter() {
		return cohortEncounter;
	}
	
	private void voidObsGroupIfAllChildObsVoided(CohortObs group) {
		if (group != null) {
			
			// probably should be able to just tet if group.getGroupMembers() == 0 since
			// getGroupMembers only returns non-voided members?
			boolean allObsVoided = true;
			for (CohortObs member : group.getGroupMembers()) {
				allObsVoided = allObsVoided && member.isVoided();
			}
			if (allObsVoided) {
				Context.getService(CohortService.class).voidObs(group, "htmlformentry");
			}
			voidObsGroupIfAllChildObsVoided(group);
		}
		voidObsGroupIfAllChildObsVoided(group);
	}
}
