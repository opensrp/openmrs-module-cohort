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

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.PatientIdentifierType;
import org.openmrs.Person;
import org.openmrs.PersonName;
import org.openmrs.Relationship;
import org.openmrs.api.LocationService;
import org.openmrs.api.PatientService;
import org.openmrs.api.PersonService;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.api.context.UserContext;
import org.openmrs.messagesource.MessageSourceService;
import org.openmrs.module.cohort.CohortM;
import org.openmrs.module.cohort.CohortMember;
import org.openmrs.module.cohort.CohortProgram;
import org.openmrs.module.cohort.CohortRole;
import org.openmrs.module.cohort.CohortType;
import org.openmrs.module.cohort.api.CohortService;
import org.openmrs.web.WebConstants;
import org.openmrs.web.taglib.fieldgen.FieldGenHandlerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * The main controller.
 */
@Controller
public class AddCohortController {

    @Autowired(required = true)
    @Qualifier("addCohortValidator")
    private Validator validator;
    List<Patient> list1 = new ArrayList();
    Set set1 = new HashSet();

    @ModelAttribute
    private void addReferenceData(ModelMap model) {
        List<String> cohorttype = new ArrayList<String>();
        List<String> cohortprg = new ArrayList<String>();
        LocationService service = Context.getLocationService();
        List<Location> formats = service.getAllLocations();
        model.addAttribute("locations", formats);
        CohortService service1 = Context.getService(CohortService.class);
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
    }

    @RequestMapping(value = "/module/cohort/addCohort", method = RequestMethod.GET)
    public void manage(ModelMap model) {
        model.addAttribute("cohortmodule", new CohortM());
        List<String> cohorttype = new ArrayList<String>();
        List<String> cohortprg = new ArrayList<String>();
        LocationService service = Context.getLocationService();
        List<Location> formats = service.getAllLocations();
        model.addAttribute("locations", formats);
        CohortService service1 = Context.getService(CohortService.class);
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
    }

    @RequestMapping(value = "module/cohort/addCohort.form", method = RequestMethod.POST)
    public String onSubmit(WebRequest request, HttpSession httpSession, HttpServletRequest request1,
                           @RequestParam(required = false, value = "name") String cohort_name,
                           @RequestParam(required = false, value = "description") String description,
                           @RequestParam(required = false, value = "startDate") String start_date,
                           @RequestParam(required = false, value = "endDate") String end_date,
                           @ModelAttribute("cohortmodule") CohortM cohortmodule, BindingResult errors, ModelMap model) {
        CohortType cohort1 = new CohortType();
        CohortProgram prg = new CohortProgram();
        Location loc = new Location();
        String cohort_program = request.getParameter("format1");
        String cohort_type_name = request.getParameter("format");
        String location = request.getParameter("location");
        CohortService departmentService = Context.getService(CohortService.class);
        if (!Context.isAuthenticated()) {
            errors.reject("Required");
        }
        this.validator.validate(cohortmodule, errors);
        System.out.println("Before BR");
        if (errors.hasErrors()) {
            System.out.println("BR has errors: " + errors.getErrorCount());
            System.out.println(errors.getAllErrors());
            return "/module/cohort/addCohort";
        } else {
				 /*try {
	 				java.util.Date start = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ENGLISH).parse(start_date);
	 				 java.util.Date end = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ENGLISH).parse(end_date);
	 				 if (start.compareTo(end) < 0 || start.compareTo(end)==0) {*/
            //cohortmodule.setLocation(location);
            List<CohortProgram> list2 = departmentService.findCohortProgram(cohort_program);
            List<CohortType> cohorttype1 = departmentService.findCohortType(cohort_type_name);
            LocationService service = Context.getLocationService();
            List<Location> formats = service.getLocations(location);
            for (int j = 0; j < formats.size(); j++) {
                loc = formats.get(j);
            }
            for (int i = 0; i < cohorttype1.size(); i++) {
                cohort1 = cohorttype1.get(i);
            }
            for (int a = 0; a < list2.size(); a++) {
                prg = list2.get(a);
            }
            cohortmodule.setCohortProgram(prg);
            cohortmodule.setClocation(loc);
            cohortmodule.setCohortType(cohort1);
            departmentService.saveCohort(cohortmodule);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "insertion success");
            model.addAttribute("locations", formats);
            model.addAttribute("formats", cohorttype1);
            model.addAttribute("formats1", list2);
            model.addAttribute("cohortmodule", cohortmodule);
            if ("Next".equalsIgnoreCase(request.getParameter("next"))) {
                departmentService.saveCohort(cohortmodule);
                httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "insertion success");
                String redirectUrl = "/module/cohort/addCohortAttributes.form?ca=" + cohortmodule.getCohortId();
                return "redirect:" + redirectUrl;
            }

        }
        //}
	        	/*catch (ParseException e) {
	 				// TODO Auto-generated catch block
	 				e.printStackTrace();
	 			}  	
	     }*/
        return null;
    }

    @RequestMapping(value = "/module/cohort/cPatients.form", method = RequestMethod.GET)
    public void manage1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("cpid") Integer id, @ModelAttribute("cpatient") CohortMember cohort) {
        //model.addAttribute("cpatient",new CohortMember());
        List<String> type = new ArrayList<String>();
        List<String> names = new ArrayList<String>();
        CohortService departmentService = Context.getService(CohortService.class);
			/*PatientService ps=Context.getPatientService();
			List<Patient> pn=ps.getAllPatients();*/
        List<CohortM> cohort1 = departmentService.findCohort(id);
        for (int i = 0; i < cohort1.size(); i++) {
            CohortM cohort2 = cohort1.get(i);
            String cname = cohort2.getName();
            List<CohortRole> cr = departmentService.findRoles(cname);
            for (int k = 0; k < cr.size(); k++) {
                CohortRole c3 = cr.get(k);
                type.add(c3.getName());
            }
        }
			/*for(int j=0;j<pn.size();j++)
			{
			  Patient p=pn.get(j);
		      names.add(p.getGivenName());
			}
			model.addAttribute("pnames",names);*/
        model.addAttribute("formats", type);
        model.addAttribute("cohort", cohort1.get(0));

    }

    @RequestMapping(value = "module/cohort/cPatients.form", method = RequestMethod.POST)
    public void onClick(WebRequest request, HttpSession httpSession, ModelMap model,
                        @RequestParam(required = false, value = "type") String type,
                        @RequestParam(required = false, value = "startDate") String startDate,
                        @RequestParam("cpid") Integer id, @RequestParam("patient_id") Integer pid, @ModelAttribute("cpatient") CohortMember cpatient, @ModelAttribute("patient") Patient patient, BindingResult errors) {
        CohortM cohort = new CohortM();
        CohortRole c2 = new CohortRole();
        List<String> names = new ArrayList<String>();
        List<String> type1 = new ArrayList<String>();
        CohortService departmentService = Context.getService(CohortService.class);
        PatientService ps = Context.getPatientService();
		 /* List<Patient> pn=ps.getAllPatients();*/
        String cname;
        List<CohortM> cohort1 = departmentService.findCohort(id);
        for (int i = 0; i < cohort1.size(); i++) {
            cohort = cohort1.get(i);
            cname = cohort.getName();
            List<CohortRole> cr = departmentService.findRoles(cname);
            for (int k = 0; k < cr.size(); k++) {
                CohortRole c3 = cr.get(k);
                type1.add(c3.getName());
            }
        }
        //List<CohortRole> cr=departmentService.findRoles(cname);
	      /*for(int k=0;k<cr.size();k++)
		  {
			CohortRole c3=cr.get(k);
			type1.add(c3.getName());
		  }*/
		  /*for(int b=0;b<pn.size();b++)
		  {
		    	 Patient p1 = pn.get(b);
		    	 names.add(p1.getGivenName());
		   }*/
        //model.addAttribute("pnames",names);
        model.addAttribute("formats", type1);
        patient = ps.getPatient(pid);
        String rolname = request.getParameter("format");
        List<CohortRole> crole = departmentService.findCohortRoles(rolname);
        for (int g = 0; g < crole.size(); g++) {
            c2 = crole.get(g);
        }
        cpatient.setPerson(patient);
        cpatient.setCohort(cohort);
        cpatient.setRole(c2);
        departmentService.saveCPatient(cpatient);
        model.addAttribute("formats", crole);
        model.addAttribute("cpatient", cpatient);
        httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Insertion success");

    }

    @RequestMapping(value = "/module/cohort/groupcohort.form", method = RequestMethod.GET)
    public void manage2(ModelMap model, HttpSession httpSession, HttpServletRequest request, @ModelAttribute("cpatient") CohortMember cohortmem, @ModelAttribute("cohortmodule") CohortM cohort) {
        model.addAttribute("cohortmodule", new CohortM());
        List<String> cohortm = new ArrayList<String>();
        List<String> type = new ArrayList<String>();
        LocationService service = Context.getLocationService();
        List<Location> formats = service.getAllLocations();
        model.addAttribute("locations", formats);
        CohortService service1 = Context.getService(CohortService.class);
        List<CohortM> cp = service1.findCohorts();
        for (int i = 0; i < cp.size(); i++) {
            CohortM c = cp.get(i);
            cohortm.add(c.getName());
            List<CohortRole> cr = service1.findRoles(c.getName());
            for (int k = 0; k < cr.size(); k++) {
                CohortRole c3 = cr.get(k);
                type.add(c3.getName());
            }
        }
        model.addAttribute("formats", cohortm);
        model.addAttribute("formats1", type);
    }

    @RequestMapping(value = "module/cohort/groupcohort.form", method = RequestMethod.POST)
    public String onSubmit1(WebRequest request, HttpSession httpSession, HttpServletRequest request1,
                            @RequestParam(required = false, value = "name") String cohort_name,
                            @RequestParam(required = false, value = "description") String description,
                            @RequestParam(required = false, value = "startDate") String start_date,
                            @RequestParam(required = false, value = "endDate") String end_date,
                            @RequestParam(required = false, value = "patient_id") Integer pid,
                            @ModelAttribute("cohortmodule") CohortM cohortmodule, @ModelAttribute("cpatient") CohortMember cohortmember, @ModelAttribute("patient") Patient patient, BindingResult errors, ModelMap model) {
        CohortProgram cp1 = new CohortProgram();
        CohortRole c2 = new CohortRole();
        CohortType ct1 = new CohortType();
        List<String> cohortm = new ArrayList<String>();
        List<String> type = new ArrayList<String>();
        LocationService service = Context.getLocationService();
        List<Location> formats = service.getAllLocations();
        Location loc = new Location();
        model.addAttribute("locations", formats);
        CohortService service1 = Context.getService(CohortService.class);
        List<CohortM> cp = service1.findCohorts();
        for (int i = 0; i < cp.size(); i++) {
            CohortM c = cp.get(i);
            cohortm.add(c.getName());
            List<CohortRole> cr = service1.findRoles(c.getName());
            for (int k = 0; k < cr.size(); k++) {
                CohortRole c3 = cr.get(k);
                type.add(c3.getName());
            }
        }
        model.addAttribute("formats", cohortm);
        model.addAttribute("formats1", type);
        String cohort_name1 = request.getParameter("format");
        List<CohortM> cc = service1.findCohorts(cohort_name1);
        for (int j = 0; j < cc.size(); j++) {
            CohortM c1 = cc.get(j);
            cp1 = c1.getCohortProgram();
            ct1 = c1.getCohortType();

        }
        String location = request.getParameter("location");
        List<Location> loc1 = service.getLocations(location);
        for (int k = 0; k < loc1.size(); k++) {
            loc = loc1.get(k);
        }
        PatientService ps = Context.getPatientService();

        patient = ps.getPatient(pid);
        String rolname = request.getParameter("format");
        List<CohortRole> crole = service1.findCohortRoles(rolname);
        for (int g = 0; g < crole.size(); g++) {
            c2 = crole.get(g);
        }
        cohortmodule.setCohortProgram(cp1);
        cohortmodule.setCohortType(ct1);
        cohortmodule.setClocation(loc);
        cohortmodule.setGroupCohort(true);
        cohortmember.setCohort(cohortmodule);
        cohortmember.setRole(c2);
        cohortmember.setPerson(patient);
        cohortmember.setHead(true);
        service1.saveCohort(cohortmodule);
        service1.saveCPatient(cohortmember);
        httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "insertion success");
        if ("Next".equalsIgnoreCase(request.getParameter("next"))) {
            service1.saveCohort(cohortmodule);
            service1.saveCPatient(cohortmember);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "insertion success");
            String redirectUrl = "/module/cohort/addCohortAttributes.form?cpid=" + cohortmodule.getCohortId();
            return "redirect:" + redirectUrl;
        }
        return null;
    }

}


///**
// * This Source Code Form is subject to the terms of the Mozilla Public License,
// * v. 2.0. If a copy of the MPL was not distributed with this file, You can
// * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
// * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
// *
// * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
// * graphic logo is a trademark of OpenMRS Inc.
// */
//package org.openmrs.module.cohort.web.controller;
//
//import java.sql.Date;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//import java.util.Set;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.openmrs.Location;
//import org.openmrs.Patient;
//import org.openmrs.PatientIdentifier;
//import org.openmrs.PatientIdentifierType;
//import org.openmrs.Person;
//import org.openmrs.PersonName;
//import org.openmrs.Relationship;
//import org.openmrs.api.LocationService;
//import org.openmrs.api.PatientService;
//import org.openmrs.api.PersonService;
//import org.openmrs.api.UserService;
//import org.openmrs.api.context.Context;
//import org.openmrs.api.context.UserContext;
//import org.openmrs.messagesource.MessageSourceService;
//import org.openmrs.module.cohort.CohortM;
//import org.openmrs.module.cohort.CohortMember;
//import org.openmrs.module.cohort.CohortProgram;
//import org.openmrs.module.cohort.CohortRole;
//import org.openmrs.module.cohort.CohortType;
//import org.openmrs.module.cohort.api.CohortService;
//import org.openmrs.web.WebConstants;
//import org.openmrs.web.taglib.fieldgen.FieldGenHandlerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindException;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
//import org.springframework.validation.Validator;
//import org.springframework.web.bind.ServletRequestUtils;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.support.SessionStatus;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.view.RedirectView;
//
///**
// * The main controller.
// */
//@Controller
//public class AddCohortController {
//
//	@Autowired(required = true)
//	@Qualifier("addCohortValidator")
//	private Validator validator;
//	List<Patient> list1 = new ArrayList();
//	Set set1 = new HashSet();
//
//	@ModelAttribute
//	private void addReferenceData(ModelMap model) {
//		List<String> cohorttype = new ArrayList<String>();
//		List<String> cohortprg = new ArrayList<String>();
//		LocationService service = Context.getLocationService();
//		List<Location> formats = service.getAllLocations();
//		model.addAttribute("locations", formats);
//		CohortService service1 = Context.getService(CohortService.class);
//		List<CohortType> list1 = service1.getAllCohortTypes();
//		for (int i = 0; i < list1.size(); i++) {
//			CohortType c = list1.get(i);
//			cohorttype.add(c.getName());
//		}
//		List<CohortProgram> list2 = service1.findCohortProg();
//		for (int j = 0; j < list2.size(); j++) {
//			CohortProgram a = list2.get(j);
//			cohortprg.add(a.getName());
//		}
//		model.addAttribute("formats", cohorttype);
//		model.addAttribute("formats1", cohortprg);
//	}
//
//	@RequestMapping(value = "/module/cohort/addCohort", method = RequestMethod.GET)
//	public void manage(ModelMap model) {
//		model.addAttribute("cohortmodule", new CohortM());
//		List<String> cohorttype = new ArrayList<String>();
//		List<String> cohortprg = new ArrayList<String>();
//		LocationService service = Context.getLocationService();
//		List<Location> formats = service.getAllLocations();
//		model.addAttribute("locations", formats);
//		CohortService service1 = Context.getService(CohortService.class);
//		List<CohortType> list1 = service1.getAllCohortTypes();
//		for (int i = 0; i < list1.size(); i++) {
//			CohortType c = list1.get(i);
//			cohorttype.add(c.getName());
//		}
//		List<CohortProgram> list2 = service1.findCohortProg();
//		for (int j = 0; j < list2.size(); j++) {
//			CohortProgram a = list2.get(j);
//			cohortprg.add(a.getName());
//		}
//		model.addAttribute("formats", cohorttype);
//		model.addAttribute("formats1", cohortprg);
//	}
//
//	@RequestMapping(value = "module/cohort/addCohort.form", method = RequestMethod.POST)
//	public String onSubmit(WebRequest request, HttpSession httpSession, HttpServletRequest request1,
//						   @RequestParam(required = false, value = "name") String cohort_name,
//						   @RequestParam(required = false, value = "description") String description,
//						   @RequestParam(required = false, value = "startDate") String start_date,
//						   @RequestParam(required = false, value = "endDate") String end_date,
//						   @ModelAttribute("cohortmodule") CohortM cohortmodule, BindingResult errors, ModelMap model) throws Exception {
//		CohortType cohort1 = new CohortType();
//		CohortProgram prg = new CohortProgram();
//		Location loc = new Location();
//		String cohort_program = request.getParameter("format1");
//		String cohort_type_name = request.getParameter("format");
//		String location = request.getParameter("location");
//		java.util.Date startDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(start_date);
//		java.util.Date endDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(end_date);
//		System.out.println(startDate+" "+endDate);
//		CohortService departmentService = Context.getService(CohortService.class);
//		if (!Context.isAuthenticated()) {
//			errors.reject("Required");
//		}
//		this.validator.validate(cohortmodule, errors);
//		System.out.println("Before BR");
//		if (errors.hasErrors()) {
//			System.out.println("BR has errors: " + errors.getErrorCount());
//			System.out.println(errors.getAllErrors());
//			return "/module/cohort/addCohort";
//		} else if (start_date.compareTo(end_date) > 0) {
//			errors.reject("Start Date larger than end date");
//		} else {
//			List<CohortProgram> list2 = departmentService.findCohortProgram(cohort_program);
//			List<CohortType> cohorttype1 = departmentService.findCohortType(cohort_type_name);
//			LocationService service = Context.getLocationService();
//			List<Location> formats = service.getLocations(location);
//			for (int j = 0; j < formats.size(); j++) {
//				loc = formats.get(j);
//			}
//			for (int i = 0; i < cohorttype1.size(); i++) {
//				cohort1 = cohorttype1.get(i);
//			}
//			for (int a = 0; a < list2.size(); a++) {
//				prg = list2.get(a);
//			}
//			cohortmodule.setCohortProgram(prg);
//			cohortmodule.setClocation(loc);
//			cohortmodule.setCohortType(cohort1);
//			cohortmodule.setStartDate(startDate);
//			cohortmodule.setEndDate(endDate);
//			departmentService.saveCohort(cohortmodule);
//			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "insertion success");
//			model.addAttribute("locations", formats);
//			model.addAttribute("formats", cohorttype1);
//			model.addAttribute("formats1", list2);
//			model.addAttribute("cohortmodule", cohortmodule);
//			model.addAttribute("startDate", startDate);
//			model.addAttribute("endDate", endDate);
//			if ("Next".equalsIgnoreCase(request.getParameter("next"))) {
//				departmentService.saveCohort(cohortmodule);
//				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "insertion success");
//				String redirectUrl = "/module/cohort/addCohortAttributes.form?ca=" + cohortmodule.getCohortId();
//				return "redirect:" + redirectUrl;
//			}
//
//		}
//		//}
//	        	/*catch (ParseException e) {
//	 				// TODO Auto-generated catch block
//	 				e.printStackTrace();
//	 			}  	
//	     }*/
//		return null;
//	}
//
//	@RequestMapping(value = "/module/cohort/cPatients.form", method = RequestMethod.GET)
//	public void manage1(ModelMap model, HttpSession httpSession, HttpServletRequest request, @RequestParam("cpid") Integer id, @ModelAttribute("cpatient") CohortMember cohort) {
//		//model.addAttribute("cpatient",new CohortMember());
//		List<String> type = new ArrayList<String>();
//		List<String> names = new ArrayList<String>();
//		CohortService departmentService = Context.getService(CohortService.class);
//			/*PatientService ps=Context.getPatientService();
//			List<Patient> pn=ps.getAllPatients();*/
//		List<CohortM> cohort1 = departmentService.findCohort(id);
//		for (int i = 0; i < cohort1.size(); i++) {
//			CohortM cohort2 = cohort1.get(i);
//			String cname = cohort2.getName();
//			List<CohortRole> cr = departmentService.findRoles(cname);
//			for (int k = 0; k < cr.size(); k++) {
//				CohortRole c3 = cr.get(k);
//				type.add(c3.getName());
//			}
//		}
//			/*for(int j=0;j<pn.size();j++)
//			{
//			  Patient p=pn.get(j);
//		      names.add(p.getGivenName());
//			}
//			model.addAttribute("pnames",names);*/
//		model.addAttribute("formats", type);
//		model.addAttribute("cohort", cohort1.get(0));
//
//	}
//
//	@RequestMapping(value = "module/cohort/cPatients.form", method = RequestMethod.POST)
//	public void onClick(WebRequest request, HttpSession httpSession, ModelMap model,
//						@RequestParam(required = false, value = "type") String type,
//						@RequestParam(required = false, value = "startDate") String startDate,
//						@RequestParam("cpid") Integer id, @RequestParam("patient_id") Integer pid, @ModelAttribute("cpatient") CohortMember cpatient, @ModelAttribute("patient") Patient patient, BindingResult errors) {
//		CohortM cohort = new CohortM();
//		CohortRole c2 = new CohortRole();
//		List<String> names = new ArrayList<String>();
//		List<String> type1 = new ArrayList<String>();
//		CohortService departmentService = Context.getService(CohortService.class);
//		PatientService ps = Context.getPatientService();
//		 /* List<Patient> pn=ps.getAllPatients();*/
//		String cname;
//		List<CohortM> cohort1 = departmentService.findCohort(id);
//		for (int i = 0; i < cohort1.size(); i++) {
//			cohort = cohort1.get(i);
//			cname = cohort.getName();
//			List<CohortRole> cr = departmentService.findRoles(cname);
//			for (int k = 0; k < cr.size(); k++) {
//				CohortRole c3 = cr.get(k);
//				type1.add(c3.getName());
//			}
//		}
//		//List<CohortRole> cr=departmentService.findRoles(cname);
//	      /*for(int k=0;k<cr.size();k++)
//		  {
//			CohortRole c3=cr.get(k);
//			type1.add(c3.getName());
//		  }*/
//		  /*for(int b=0;b<pn.size();b++)
//		  {
//		    	 Patient p1 = pn.get(b);
//		    	 names.add(p1.getGivenName());
//		   }*/
//		//model.addAttribute("pnames",names);
//		model.addAttribute("formats", type1);
//		patient = ps.getPatient(pid);
//		String rolname = request.getParameter("format");
//		List<CohortRole> crole = departmentService.findCohortRoles(rolname);
//		for (int g = 0; g < crole.size(); g++) {
//			c2 = crole.get(g);
//		}
//		cpatient.setPerson(patient);
//		cpatient.setCohort(cohort);
//		cpatient.setRole(c2);
//		departmentService.saveCPatient(cpatient);
//		model.addAttribute("formats", crole);
//		model.addAttribute("cpatient", cpatient);
//		httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Insertion success");
//
//	}
//
//	@RequestMapping(value = "/module/cohort/groupcohort.form", method = RequestMethod.GET)
//	public void manage2(ModelMap model, HttpSession httpSession, HttpServletRequest request, @ModelAttribute("cpatient") CohortMember cohortmem, @ModelAttribute("cohortmodule") CohortM cohort) {
//		model.addAttribute("cohortmodule", new CohortM());
//		List<String> cohortm = new ArrayList<String>();
//		List<String> type = new ArrayList<String>();
//		LocationService service = Context.getLocationService();
//		List<Location> formats = service.getAllLocations();
//		model.addAttribute("locations", formats);
//		CohortService service1 = Context.getService(CohortService.class);
//		List<CohortM> cp = service1.findCohorts();
//		for (int i = 0; i < cp.size(); i++) {
//			CohortM c = cp.get(i);
//			cohortm.add(c.getName());
//			List<CohortRole> cr = service1.findRoles(c.getName());
//			for (int k = 0; k < cr.size(); k++) {
//				CohortRole c3 = cr.get(k);
//				type.add(c3.getName());
//			}
//		}
//		model.addAttribute("formats", cohortm);
//		model.addAttribute("formats1", type);
//	}
//
//	@RequestMapping(value = "module/cohort/groupcohort.form", method = RequestMethod.POST)
//	public String onSubmit1(WebRequest request, HttpSession httpSession, HttpServletRequest request1,
//							@RequestParam(required = false, value = "name") String cohort_name,
//							@RequestParam(required = false, value = "description") String description,
//							@RequestParam(required = false, value = "startDate") String start_date,
//							@RequestParam(required = false, value = "endDate") String end_date,
//							@RequestParam(required = false, value = "patient_id") Integer pid,
//							@ModelAttribute("cohortmodule") CohortM cohortmodule, @ModelAttribute("cpatient") CohortMember cohortmember, @ModelAttribute("patient") Patient patient, BindingResult errors, ModelMap model) {
//		CohortProgram cp1 = new CohortProgram();
//		CohortRole c2 = new CohortRole();
//		CohortType ct1 = new CohortType();
//		List<String> cohortm = new ArrayList<String>();
//		List<String> type = new ArrayList<String>();
//		LocationService service = Context.getLocationService();
//		List<Location> formats = service.getAllLocations();
//		Location loc = new Location();
//		model.addAttribute("locations", formats);
//		CohortService service1 = Context.getService(CohortService.class);
//		List<CohortM> cp = service1.findCohorts();
//		for (int i = 0; i < cp.size(); i++) {
//			CohortM c = cp.get(i);
//			cohortm.add(c.getName());
//			List<CohortRole> cr = service1.findRoles(c.getName());
//			for (int k = 0; k < cr.size(); k++) {
//				CohortRole c3 = cr.get(k);
//				type.add(c3.getName());
//			}
//		}
//		model.addAttribute("formats", cohortm);
//		model.addAttribute("formats1", type);
//		String cohort_name1 = request.getParameter("format");
//		List<CohortM> cc = service1.findCohorts(cohort_name1);
//		for (int j = 0; j < cc.size(); j++) {
//			CohortM c1 = cc.get(j);
//			cp1 = c1.getCohortProgram();
//			ct1 = c1.getCohortType();
//
//		}
//		String location = request.getParameter("location");
//		List<Location> loc1 = service.getLocations(location);
//		for (int k = 0; k < loc1.size(); k++) {
//			loc = loc1.get(k);
//		}
//		PatientService ps = Context.getPatientService();
//
//		patient = ps.getPatient(pid);
//		String rolname = request.getParameter("format");
//		List<CohortRole> crole = service1.findCohortRoles(rolname);
//		for (int g = 0; g < crole.size(); g++) {
//			c2 = crole.get(g);
//		}
//		cohortmodule.setCohortProgram(cp1);
//		cohortmodule.setCohortType(ct1);
//		cohortmodule.setClocation(loc);
//		cohortmodule.setGroupCohort(true);
//		cohortmember.setCohort(cohortmodule);
//		cohortmember.setRole(c2);
//		cohortmember.setPerson(patient);
//		cohortmember.setHead(true);
//		service1.saveCohort(cohortmodule);
//		service1.saveCPatient(cohortmember);
//		httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "insertion success");
//		if ("Next".equalsIgnoreCase(request.getParameter("next"))) {
//			service1.saveCohort(cohortmodule);
//			service1.saveCPatient(cohortmember);
//			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "insertion success");
//			String redirectUrl = "/module/cohort/addCohortAttributes.form?cpid=" + cohortmodule.getCohortId();
//			return "redirect:" + redirectUrl;
//		}
//		return null;
//	}
//
//}