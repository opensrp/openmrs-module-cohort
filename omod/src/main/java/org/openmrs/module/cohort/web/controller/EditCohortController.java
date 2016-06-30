/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.cohort.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.EncounterType;
import org.openmrs.Form;
import org.openmrs.Location;
import org.openmrs.Person;
import org.openmrs.VisitType;
import org.openmrs.api.ConceptService;
import org.openmrs.api.EncounterService;
import org.openmrs.api.FormService;
import org.openmrs.api.LocationService;
import org.openmrs.api.VisitService;
import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortAttribute;
import org.openmrs.module.cohort.CohortAttributeType;
import org.openmrs.module.cohort.CohortEncounter;
import org.openmrs.module.cohort.CohortM;
import org.openmrs.module.cohort.CohortMember;
import org.openmrs.module.cohort.CohortMemberAttribute;
import org.openmrs.module.cohort.CohortMemberAttributeType;
import org.openmrs.module.cohort.CohortObs;
import org.openmrs.module.cohort.CohortProgram;
import org.openmrs.module.cohort.CohortRole;
import org.openmrs.module.cohort.CohortType;
import org.openmrs.module.cohort.CohortVisit;
import org.openmrs.module.cohort.api.CohortService;
import org.openmrs.web.WebConstants;
import org.openmrs.web.taglib.fieldgen.FieldGenHandlerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * The main controller.
 */
@Controller
public class EditCohortController {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@RequestMapping(value = "/module/cohort/editcohort", method = RequestMethod.GET)
	public void manageEditCohort(ModelMap model, HttpServletRequest request, @RequestParam("cid") Integer id, @ModelAttribute("cohortmodule") CohortM cohort) {
		CohortService service1 = Context.getService(CohortService.class);
		List<String> cohorttype = new ArrayList<String>();
		List<String> cohortprg = new ArrayList<String>();
		LocationService service = Context.getLocationService();
		List<Location> formats = service.getAllLocations();
		model.addAttribute("locations", formats);
		List<CohortType> list1 = service1.getAllCohortTypes();
		for (int i = 0; i < list1.size(); i++) {
			CohortType c = list1.get(i);
			cohorttype.add(c.getName());
		}
		List<CohortProgram> list2 = service1.findCohortProg();
		for (int j = 0; j < list2.size(); j++) {
			CohortProgram a = list2.get(j);
			cohortprg.add(a.getName());
		}
		model.addAttribute("formats", cohorttype);
		model.addAttribute("formats1", cohortprg);
		List<CohortM> cohort1 = service1.findCohort(id);
		for (int j = 0; j < cohort1.size(); j++) {
			cohort = cohort1.get(j);
			model.addAttribute("cohortmodule", cohort);
		}
	}
	
	@RequestMapping(value = "/module/cohort/editcohort.form", method = RequestMethod.POST)
	public void manageEditCohort1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("cid") Integer id,
			@RequestParam(required = false, value = "voidReason") String voidReason, @ModelAttribute("cohortmodule") CohortM cohort) {
		CohortService service1 = Context.getService(CohortService.class);
		List<String> cohorttype = new ArrayList<String>();
		LocationService service = Context.getLocationService();
		List<Location> formats = service.getAllLocations();
		model.addAttribute("locations", formats);
		List<CohortType> list1 = service1.getAllCohortTypes();
		for (int i = 0; i < list1.size(); i++) {
			CohortType c = list1.get(i);
			cohorttype.add(c.getName());
		}
		List<CohortM> cohort1 = service1.findCohort(id);
		for (int j = 0; j < cohort1.size(); j++) {
			cohort = cohort1.get(j);
		}
		if ("void".equalsIgnoreCase(request.getParameter("void"))) {
			if (voidReason == "") {
				httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "void Reason cannot be null");
			}
			cohort.setVoided(true);
			cohort.setVoidReason(voidReason);
			service1.saveCohort(cohort);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "cohort voided success");
		}
		if ("delete".equals(request.getParameter("delete"))) {
			service1.purgeCohort(cohort);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "delete success");
		}
	}
	
	@RequestMapping(value = "/module/cohort/editcohorttype", method = RequestMethod.GET)
	public void manageEditCohortType(ModelMap model, HttpServletRequest request, @RequestParam(required = false, value = "voidReason") String voidReason, @RequestParam("ctypeid") Integer id, @ModelAttribute("cohorttype") CohortType cohort) {
		CohortService service1 = Context.getService(CohortService.class);
		List<CohortType> cohort1 = service1.findCohortType(id);
		for (int j = 0; j < cohort1.size(); j++) {
			cohort = cohort1.get(j);
			model.addAttribute("cohorttype", cohort);
		}
	}
	
	@RequestMapping(value = "/module/cohort/editcohorttype.form", method = RequestMethod.POST)
	public void manageEditCohortType1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("ctypeid") Integer id, @RequestParam(required = false, value = "voidReason") String voidReason, @ModelAttribute("cohorttype") CohortType cohort) {
		CohortService service1 = Context.getService(CohortService.class);
		List<CohortType> cohort1 = service1.findCohortType(id);
		for (int j = 0; j < cohort1.size(); j++) {
			cohort = cohort1.get(j);
		}
		if ("void".equalsIgnoreCase(request.getParameter("void"))) {
			if (voidReason == "") {
				httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "void Reason cannot be null");
			}
			cohort.setVoided(true);
			cohort.setVoidReason(voidReason);
			service1.saveCohort(cohort);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "cohort voided success");
		}
		if ("delete".equals(request.getParameter("delete"))) {
			service1.purgeCohortType(cohort);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "delete success");
		}
	}
	
	@RequestMapping(value = "/module/cohort/editcohortprogram", method = RequestMethod.GET)
	public void manageEditCohortProgram(ModelMap model, HttpServletRequest request, @RequestParam("cpid") Integer id, @ModelAttribute("cohortprogram") CohortProgram cp) {
		CohortService service1 = Context.getService(CohortService.class);
		List<CohortProgram> cohort1 = service1.findCohortProgram(id);
		for (int j = 0; j < cohort1.size(); j++) {
			cp = cohort1.get(j);
			model.addAttribute("cohortprogram", cp);
		}
	}
	
	@RequestMapping(value = "/module/cohort/editcohortprogram.form", method = RequestMethod.POST)
	public void manageEditCohortProgram1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("cpid") Integer id, @RequestParam(required = false, value = "voidReason") String voidReason, @ModelAttribute("cohortprogram") CohortProgram cohort) {
		CohortService service1 = Context.getService(CohortService.class);
		List<CohortProgram> cohort1 = service1.findCohortProgram(id);
		for (int j = 0; j < cohort1.size(); j++) {
			cohort = cohort1.get(j);
		}
		if ("void".equalsIgnoreCase(request.getParameter("void"))) {
			if (voidReason == "") {
				httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "void Reason cannot be null");
			}
			cohort.setVoided(true);
			cohort.setVoidReason(voidReason);
			service1.saveCohortProgram(cohort);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "cohort voided success");
		}
		if ("delete".equals(request.getParameter("delete"))) {
			service1.purgeCohortProgram(cohort);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "delete success");
		}
	}
	
	@RequestMapping(value = "/module/cohort/editcohortattributestype", method = RequestMethod.GET)
	public void manageEditCohortAttributeType(ModelMap model, HttpServletRequest request, @RequestParam("cat") Integer id, @ModelAttribute("cohortattributes") CohortAttributeType cohort) {
		CohortService service1 = Context.getService(CohortService.class);
		List<CohortAttributeType> cohort1 = service1.findCohortAttType(id);
		List<String> formats = new ArrayList<String>(FieldGenHandlerFactory.getSingletonInstance().getHandlers().keySet());
		formats.add("java.lang.Character");
		formats.add("java.lang.Integer");
		formats.add("java.lang.Float");
		formats.add("java.lang.Boolean");
		model.addAttribute("formats", formats);
		for (int j = 0; j < cohort1.size(); j++) {
			cohort = cohort1.get(j);
			model.addAttribute("cohortattributes", cohort);
		}
	}
	
	@RequestMapping(value = "/module/cohort/editcohortattributestype.form", method = RequestMethod.POST)
	public void manageEditCohortAttributeType1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("cat") Integer id, @RequestParam(required = false, value = "voidReason") String voidReason, @ModelAttribute("cohortattributes") CohortAttributeType cohort) {
		CohortService service1 = Context.getService(CohortService.class);
		List<CohortAttributeType> cohort1 = service1.findCohortAttType(id);
		List<String> formats = new ArrayList<String>(FieldGenHandlerFactory.getSingletonInstance().getHandlers().keySet());
		formats.add("java.lang.Character");
		formats.add("java.lang.Integer");
		formats.add("java.lang.Float");
		formats.add("java.lang.Boolean");
		model.addAttribute("formats", formats);
		for (int j = 0; j < cohort1.size(); j++) {
			cohort = cohort1.get(j);
		}
		if ("void".equalsIgnoreCase(request.getParameter("void"))) {
			if (voidReason == "") {
				httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "void Reason cannot be null");
			}
			cohort.setVoided(true);
			cohort.setVoidReason(voidReason);
			service1.saveCohort(cohort);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "cohort voided success");
		}
		if ("delete".equals(request.getParameter("delete"))) {
			service1.purgeCohortAttributes(cohort);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "delete success");
		}
	}
	
	
	@RequestMapping(value = "/module/cohort/editcohortmemberattributetype", method = RequestMethod.GET)
	public void manageEditCohortMemberAttributeType(ModelMap model, HttpServletRequest request, @RequestParam("cmat") Integer id, @ModelAttribute("cohortattributes") CohortMemberAttributeType cohort) {
		CohortService service1 = Context.getService(CohortService.class);
		List<CohortMemberAttributeType> cohort1 = service1.findCohortMemAttType(id);
		List<String> formats = new ArrayList<String>(FieldGenHandlerFactory.getSingletonInstance().getHandlers().keySet());
		formats.add("java.lang.Character");
		formats.add("java.lang.Integer");
		formats.add("java.lang.Float");
		formats.add("java.lang.Boolean");
		model.addAttribute("formats", formats);
		for (int j = 0; j < cohort1.size(); j++) {
			cohort = cohort1.get(j);
			model.addAttribute("cohortattributes", cohort);
		}
	}
	
	@RequestMapping(value = "/module/cohort/editcohortmemberattributetype.form", method = RequestMethod.POST)
	public void manageEditCohortMemberAttributeType1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("cmat") Integer id, @RequestParam(required = false, value = "voidReason") String voidReason, @ModelAttribute("cohortattributes") CohortMemberAttributeType cohort) {
		CohortService service1 = Context.getService(CohortService.class);
		List<CohortMemberAttributeType> cohort1 = service1.findCohortMemAttType(id);
		List<String> formats = new ArrayList<String>(FieldGenHandlerFactory.getSingletonInstance().getHandlers().keySet());
		formats.add("java.lang.Character");
		formats.add("java.lang.Integer");
		formats.add("java.lang.Float");
		formats.add("java.lang.Boolean");
		model.addAttribute("formats", formats);
		for (int j = 0; j < cohort1.size(); j++) {
			cohort = cohort1.get(j);
		}
		if ("void".equalsIgnoreCase(request.getParameter("void"))) {
			if (voidReason == "") {
				httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "void Reason cannot be null");
			}
			cohort.setVoided(true);
			cohort.setVoidReason(voidReason);
			service1.saveCohortMemberAttributeType(cohort);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "cohort voided success");
		}
		if ("delete".equals(request.getParameter("delete"))) {
			service1.purgeCohortMemberAttributeType(cohort);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "delete success");
		}
	}
	
	@RequestMapping(value = "/module/cohort/editCohortAttributes", method = RequestMethod.GET)
	public void manageEditCohortAttribute(ModelMap model, HttpServletRequest request, @RequestParam("ca") Integer id, @ModelAttribute("cohortatt") CohortAttribute cohort) {
		CohortService s = Context.getService(CohortService.class);
		List<String> cohorta = new ArrayList<String>();
		List<String> cohortm = new ArrayList<String>();
		List<CohortM> m = s.findCohorts();
		for (int j = 0; j < m.size(); j++) {
			CohortM c = m.get(j);
			cohortm.add(c.getName());
		}
		List<CohortAttributeType> att = s.findCohortAttributes();
		for (int i = 0; i < att.size(); i++) {
			CohortAttributeType a = att.get(i);
			cohorta.add(a.getName());
		}
		model.addAttribute("formats", cohorta);
		model.addAttribute("formats1", cohortm);
		List<CohortAttribute> cohort1 = s.findCohortAtt(id);
		for (int j = 0; j < cohort1.size(); j++) {
			cohort = cohort1.get(j);
			model.addAttribute("cohortatt", cohort);
		}
	}
	
	@RequestMapping(value = "/module/cohort/editCohortAttributes.form", method = RequestMethod.POST)
	public void manageEditCohortAttribute1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("ca") Integer id, @RequestParam(required = false, value = "voidReason") String voidReason, @ModelAttribute("cohortatt") CohortAttribute cohort) {
		CohortService service1 = Context.getService(CohortService.class);
		List<String> cohorta = new ArrayList<String>();
		List<String> cohortm = new ArrayList<String>();
		List<CohortM> m = service1.findCohorts();
		for (int j = 0; j < m.size(); j++) {
			CohortM c = m.get(j);
			cohortm.add(c.getName());
		}
		List<CohortAttributeType> att = service1.findCohortAttributes();
		for (int i = 0; i < att.size(); i++) {
			CohortAttributeType a = att.get(i);
			cohorta.add(a.getName());
		}
		model.addAttribute("formats", cohorta);
		model.addAttribute("formats1", cohortm);
		List<CohortAttribute> cohort1 = service1.findCohortAtt(id);
		for (int j = 0; j < cohort1.size(); j++) {
			cohort = cohort1.get(j);
		}
		if ("void".equalsIgnoreCase(request.getParameter("void"))) {
			if (voidReason == "") {
				httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "void Reason cannot be null");
			}
			cohort.setVoided(true);
			cohort.setVoidReason(voidReason);
			service1.saveCohortAttributes(cohort);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "cohort voided success");
		}
		if ("delete".equals(request.getParameter("delete"))) {
			service1.purgeCohortAtt(cohort);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "delete success");
		}
	}
	
	@RequestMapping(value = "/module/cohort/editcohortmemberattribute", method = RequestMethod.GET)
	public void manageEditCohortMemberAttribute(ModelMap model, HttpServletRequest request, @RequestParam("cma") Integer id, @ModelAttribute("cohortatt") CohortMemberAttribute cohort) {
		CohortService s = Context.getService(CohortService.class);
		List<CohortMember> cm = s.findCohortMember();
		List<String> p = new ArrayList<String>();
		List<String> cohorta = new ArrayList<String>();
		List<CohortMemberAttributeType> att = s.findCohortMemberAttributeType();
		for (int i = 0; i < att.size(); i++) {
			CohortMemberAttributeType a = att.get(i);
			cohorta.add(a.getName());
		}
		for (int b = 0; b < cm.size(); b++) {
			CohortMember cmm = cm.get(b);
			Person pp = cmm.getPerson();
			p.add(pp.getGivenName());
		}
		model.addAttribute("formats", cohorta);
		model.addAttribute("formats1", p);
		List<CohortMemberAttribute> cohort1 = s.findCohortMemAtt(id);
		for (int j = 0; j < cohort1.size(); j++) {
			cohort = cohort1.get(j);
			model.addAttribute("cohortatt", cohort);
		}
	}
	
	@RequestMapping(value = "/module/cohort/editcohortmemberattribute.form", method = RequestMethod.POST)
	public void manageEditCohortMemberAttribute1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("cma") Integer id, @RequestParam(required = false, value = "voidReason") String voidReason, @ModelAttribute("cohortatt") CohortMemberAttribute cohort) {
		CohortService s = Context.getService(CohortService.class);
		List<CohortMember> cm = s.findCohortMember();
		List<String> p = new ArrayList<String>();
		List<String> cohorta = new ArrayList<String>();
		List<CohortMemberAttributeType> att = s.findCohortMemberAttributeType();
		for (int i = 0; i < att.size(); i++) {
			CohortMemberAttributeType a = att.get(i);
			cohorta.add(a.getName());
		}
		for (int b = 0; b < cm.size(); b++) {
			CohortMember cmm = cm.get(b);
			Person pp = cmm.getPerson();
			p.add(pp.getGivenName());
		}
		model.addAttribute("formats", cohorta);
		model.addAttribute("formats1", p);
		List<CohortMemberAttribute> cohort1 = s.findCohortMemAtt(id);
		for (int j = 0; j < cohort1.size(); j++) {
			cohort = cohort1.get(j);
		}
		if ("void".equalsIgnoreCase(request.getParameter("void"))) {
			if (voidReason == "") {
				httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "void Reason cannot be null");
			}
			cohort.setVoided(true);
			cohort.setVoidReason(voidReason);
			s.saveCohortMemberAttribute(cohort);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "cohort voided success");
		}
		if ("delete".equals(request.getParameter("delete"))) {
			s.purgeCohortMemberAttribute(cohort);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "delete success");
		}
	}
	
	@RequestMapping(value = "/module/cohort/editenc", method = RequestMethod.GET)
	public void manageEditEncounter(ModelMap model, HttpServletRequest request, @RequestParam("encid") Integer id, @ModelAttribute("cohortencounters") CohortEncounter cohort) {
		List<String> cohortnames = new ArrayList<String>();
		CohortService cservice = Context.getService(CohortService.class);
		List<CohortM> list1 = cservice.findCohorts();
		for (int i = 0; i < list1.size(); i++) {
			CohortM c = list1.get(i);
			cohortnames.add(c.getName());
		}
		EncounterService enctype = Context.getEncounterService();
		List<String> etype = new ArrayList<String>();
		List<EncounterType> enctypes = enctype.getAllEncounterTypes();
		for (int l = 0; l < enctypes.size(); l++) {
			EncounterType e = enctypes.get(l);
			etype.add(e.getName());
		}
		FormService fs = Context.getFormService();
		List<String> fo = new ArrayList<String>();
		List<Form> form = fs.getAllForms();
		for (int h = 0; h < form.size(); h++) {
			Form b = form.get(h);
			fo.add(b.getName());
		}
		List<String> cvisit = new ArrayList<String>();
		List<CohortVisit> cv = cservice.findCohortVisit();
		for (int k = 0; k < cv.size(); k++) {
			CohortVisit e = cv.get(k);
			cvisit.add(e.getCohortVisitId().toString());
		}
		LocationService service = Context.getLocationService();
		List<Location> formats = service.getAllLocations();
		model.addAttribute("locations", formats);
		model.addAttribute("formats1", cohortnames);
		model.addAttribute("forms", fo);
		model.addAttribute("visits", cvisit);
		model.addAttribute("enctypes", etype);
		List<CohortEncounter> enc = cservice.findCohortEnc(id);
		for (int k = 0; k < enc.size(); k++) {
			cohort = enc.get(k);
			model.addAttribute("cohortencounters", cohort);
		}
		
	}
	
	@RequestMapping(value = "/module/cohort/editenc.form", method = RequestMethod.POST)
	public void manageEditEncounter1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("encid") Integer id, @RequestParam(required = false, value = "voidReason") String voidReason, @ModelAttribute("cohortencounters") CohortEncounter cohort) {
		List<String> cohortnames = new ArrayList<String>();
		CohortService cservice = Context.getService(CohortService.class);
		List<CohortM> list1 = cservice.findCohorts();
		for (int i = 0; i < list1.size(); i++) {
			CohortM c = list1.get(i);
			cohortnames.add(c.getName());
		}
		EncounterService enctype = Context.getEncounterService();
		List<String> etype = new ArrayList<String>();
		List<EncounterType> enctypes = enctype.getAllEncounterTypes();
		for (int l = 0; l < enctypes.size(); l++) {
			EncounterType e = enctypes.get(l);
			etype.add(e.getName());
		}
		FormService fs = Context.getFormService();
		List<String> fo = new ArrayList<String>();
		List<Form> form = fs.getAllForms();
		for (int h = 0; h < form.size(); h++) {
			Form b = form.get(h);
			fo.add(b.getName());
		}
		List<String> cvisit = new ArrayList<String>();
		List<CohortVisit> cv = cservice.findCohortVisit();
		for (int k = 0; k < cv.size(); k++) {
			CohortVisit e = cv.get(k);
			cvisit.add(e.getCohortVisitId().toString());
		}
		LocationService service = Context.getLocationService();
		List<Location> formats = service.getAllLocations();
		model.addAttribute("locations", formats);
		model.addAttribute("formats1", cohortnames);
		model.addAttribute("forms", fo);
		model.addAttribute("visits", cvisit);
		model.addAttribute("enctypes", etype);
		List<CohortEncounter> enc = cservice.findCohortEnc(id);
		for (int k = 0; k < enc.size(); k++) {
			cohort = enc.get(k);
		}
		if ("void".equalsIgnoreCase(request.getParameter("void"))) {
			if (voidReason == "") {
				httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "void Reason cannot be null");
			}
			cohort.setVoided(true);
			cohort.setVoidReason(voidReason);
			cservice.saveCohortEncounters(cohort);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "cohort voided success");
		}
		if ("delete".equals(request.getParameter("delete"))) {
			cservice.purgeCohortEncounters(cohort);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "delete success");
		}
	}
	
	@RequestMapping(value = "/module/cohort/editvisit", method = RequestMethod.GET)
	public void manageEditVisit(ModelMap model, HttpServletRequest request, @RequestParam("visitid") Integer id, @ModelAttribute("cohortvisit") CohortVisit cohort) {
		List<String> cohortnames = new ArrayList<String>();
		VisitService enctype = Context.getVisitService();
		List<String> etype = new ArrayList<String>();
		List<VisitType> enctypes = enctype.getAllVisitTypes();
		CohortService cservice = Context.getService(CohortService.class);
		List<CohortM> list1 = cservice.findCohorts();
		for (int i = 0; i < list1.size(); i++) {
			CohortM c = list1.get(i);
			cohortnames.add(c.getName());
		}
		for (int l = 0; l < enctypes.size(); l++) {
			VisitType e = enctypes.get(l);
			etype.add(e.getName());
		}
		model.addAttribute("visittypes", etype);
		LocationService service = Context.getLocationService();
		List<Location> formats = service.getAllLocations();
		model.addAttribute("locations", formats);
		model.addAttribute("formats1", cohortnames);
		List<CohortVisit> visits = cservice.findCohortVisit(id);
		for (int a = 0; a < visits.size(); a++) {
			cohort = visits.get(a);
			model.addAttribute("cohortvisit", cohort);
		}
	}
	
	@RequestMapping(value = "/module/cohort/editvisit.form", method = RequestMethod.POST)
	public void manageEditVisit1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("visitid") Integer id, @RequestParam(required = false, value = "voidReason") String voidReason, @ModelAttribute("cohortvisit") CohortVisit cohort) {
		List<String> cohortnames = new ArrayList<String>();
		VisitService enctype = Context.getVisitService();
		List<String> etype = new ArrayList<String>();
		List<VisitType> enctypes = enctype.getAllVisitTypes();
		CohortService cservice = Context.getService(CohortService.class);
		List<CohortM> list1 = cservice.findCohorts();
		for (int i = 0; i < list1.size(); i++) {
			CohortM c = list1.get(i);
			cohortnames.add(c.getName());
		}
		for (int l = 0; l < enctypes.size(); l++) {
			VisitType e = enctypes.get(l);
			etype.add(e.getName());
		}
		model.addAttribute("visittypes", etype);
		LocationService service = Context.getLocationService();
		List<Location> formats = service.getAllLocations();
		model.addAttribute("locations", formats);
		model.addAttribute("formats1", cohortnames);
		List<CohortVisit> visits = cservice.findCohortVisit(id);
		for (int a = 0; a < visits.size(); a++) {
			cohort = visits.get(a);
		}
		if ("void".equalsIgnoreCase(request.getParameter("void"))) {
			if (voidReason == "") {
				httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "void Reason cannot be null");
			}
			cohort.setVoided(true);
			cohort.setVoidReason(voidReason);
			cservice.saveCohortVisit(cohort);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "cohort voided success");
		}
		if ("delete".equals(request.getParameter("delete"))) {
			cservice.purgeCohortVisit(cohort);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "delete success");
		}
	}
	
	@RequestMapping(value = "/module/cohort/editobs", method = RequestMethod.GET)
	public void manageEditObs(ModelMap model, HttpServletRequest request, @RequestParam("obid") Integer id, @ModelAttribute("cohortobs") CohortObs cobs) {
		List<String> cohortnames = new ArrayList<String>();
		CohortService cservice = Context.getService(CohortService.class);
		List<CohortM> list1 = cservice.findCohorts();
		for (int i = 0; i < list1.size(); i++) {
			CohortM c = list1.get(i);
			cohortnames.add(c.getName());
		}
		List<Integer> etype = new ArrayList<Integer>();
		List<CohortEncounter> enctypes = cservice.findCohortEncounters();
		for (int l = 0; l < enctypes.size(); l++) {
			CohortEncounter e = enctypes.get(l);
			etype.add(e.getId());
		}
		model.addAttribute("encids", etype);
		LocationService service = Context.getLocationService();
		List<Location> formats = service.getAllLocations();
		ConceptService cs = Context.getConceptService();
		List<Concept> concept = cs.getAllConcepts();
		
		model.addAttribute("concepts", concept);
		model.addAttribute("locations", formats);
		model.addAttribute("formats1", cohortnames);
		
		List<CohortObs> visits = cservice.findCohortObs(id);
		for (int a = 0; a < visits.size(); a++) {
			cobs = visits.get(a);
			model.addAttribute("cohortvisit", cobs);
		}
	}
	
	@RequestMapping(value = "/module/cohort/editobs.form", method = RequestMethod.POST)
	public void manageEditObs1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("obid") Integer id, @RequestParam(required = false, value = "voidReason") String voidReason, @ModelAttribute("cohortobs") CohortObs cohort) {
		List<String> cohortnames = new ArrayList<String>();
		CohortService cservice = Context.getService(CohortService.class);
		List<CohortM> list1 = cservice.findCohorts();
		for (int i = 0; i < list1.size(); i++) {
			CohortM c = list1.get(i);
			cohortnames.add(c.getName());
		}
		List<Integer> etype = new ArrayList<Integer>();
		List<CohortEncounter> enctypes = cservice.findCohortEncounters();
		for (int l = 0; l < enctypes.size(); l++) {
			CohortEncounter e = enctypes.get(l);
			etype.add(e.getId());
		}
		model.addAttribute("encids", etype);
		LocationService service = Context.getLocationService();
		List<Location> formats = service.getAllLocations();
		ConceptService cs = Context.getConceptService();
		List<Concept> concept = cs.getAllConcepts();
		
		model.addAttribute("concepts", concept);
		model.addAttribute("locations", formats);
		model.addAttribute("formats1", cohortnames);
		
		List<CohortObs> visits = cservice.findCohortObs(id);
		for (int a = 0; a < visits.size(); a++) {
			cohort = visits.get(a);
		}
		if ("void".equalsIgnoreCase(request.getParameter("void"))) {
			if (voidReason == "") {
				httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "void Reason cannot be null");
			}
			cohort.setVoided(true);
			cohort.setVoidReason(voidReason);
			cservice.saveCohortObs(cohort);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "cohort voided success");
		}
		if ("delete".equals(request.getParameter("delete"))) {
			cservice.purgeCohortObs(cohort);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "delete success");
		}
	}
	
	@RequestMapping(value = "/module/cohort/editcohortrole", method = RequestMethod.GET)
	public void manageEditRoles(ModelMap model, HttpServletRequest request, @RequestParam("croleid") Integer id, @ModelAttribute("cohortrole") CohortRole crole) {
		
		model.addAttribute("cohortrole", new CohortRole());
		List<String> cohorttype = new ArrayList<String>();
		CohortService service1 = Context.getService(CohortService.class);
		List<CohortType> list1 = service1.getAllCohortTypes();
		for (int i = 0; i < list1.size(); i++) {
			CohortType c = list1.get(i);
			cohorttype.add(c.getName());
		}
		model.addAttribute("formats", cohorttype);
		List<CohortRole> visits = service1.findCohortRole(id);
		for (int a = 0; a < visits.size(); a++) {
			crole = visits.get(a);
			model.addAttribute("cohortrole", crole);
		}
	}
	
	@RequestMapping(value = "/module/cohort/editcohortrole.form", method = RequestMethod.POST)
	public void manageEditRoles1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("croleid") Integer id, @ModelAttribute("cohortrole") CohortRole cohort) {
		List<String> cohorttype = new ArrayList<String>();
		CohortService service1 = Context.getService(CohortService.class);
		List<CohortType> list1 = service1.getAllCohortTypes();
		for (int i = 0; i < list1.size(); i++) {
			CohortType c = list1.get(i);
			cohorttype.add(c.getName());
		}
		model.addAttribute("formats", cohorttype);
		List<CohortRole> visits = service1.findCohortRole(id);
		for (int a = 0; a < visits.size(); a++) {
			cohort = visits.get(a);
		}
		if ("delete".equals(request.getParameter("delete"))) {
			service1.purgeCohortRole(cohort);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "delete success");
		}
	}
}
