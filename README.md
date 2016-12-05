# Cohort Module

The purpose of the [OpenMRS Cohort Module](https://wiki.openmrs.org/display/projects/Cohort+Module) is to manage Cohort-related Data, Types, Attributes, Attribute Types and Members while using REST to allow external system integration. Cohort management can be performed by one of two ways:

1. Simple cohorts for single entities.
2. Group cohorts catering to collections like households.

## Introduction

#### What's a cohort?

A cohort consists of a group of patients based on a condition like patients receiving similar kind of treatment which can be monitored over a period of time.

Cohort management system unlike patient management system deals specifically to monitoring  and suggesting possible cures to groups of cohort members under a specific cohort unlike separate diagnosis for a particular patient.

We can analyze trends and patterns and this helps in research and policy making.

## Objective of the Project

* The purpose is to manage cohort related data:
    * cohorts
    * cohort type
    * cohort attributes
    * cohort attribute types
    * cohort members
    * cohort member attribute
    * cohort member attribute type
* Using REST to allow external system integration with cohort module
* Cohort Management can be performed in two ways:
    * Simple Cohorts for single entities
    * Cohorts including groups or collections like households

### This document will cover following information:

1. [Design of the Module](#design-of-the-module)
2. [Cohort REST Web Service Integration](#cohort-web-service-web-integration)
3. [User Manual](#user-manual)
4. [Potential Improvements](#potential-improvements)
5. [Downloads and Resources](#downloads-and-resources)

### Design of the Module

**ER Diagram:**

![](http://i.imgur.com/mxpz1qS.png)

* The module deals with creating cohorts and associating it with cohort attributes which are dependent on cohort attribute types.

* Every cohort member who is a patient is associated to the cohort and also it can have cohort member attributes which are dependent on cohort member attribute types.

* Search operations can be performed based on name under the **Manage Cohorts** page.

* Each of the cohort can have many encounters, visits and observations which are created utilizing the forms from the HTML Form Entry module.

* The search operation would be based on the cohort unlike in OpenMRS which is based on only patient identifier/names.

* It is also created utilizing the REST interface from the REST Module wherein the resources are identified in requests using URIs as resource identifiers to view cohorts, cohort encounters and cohort observations.

## Cohort Web Service Web Integration

* We would be utilizing REST services in our module so that users who are not accessing OpenMRS can view the available data of the module by sending an HTTP GET or POST request.

* GET Request can be sent to view the data related to cohorts, cohort members, cohort encounters and cohort observations.

* POST Request can be sent to add data into the database by sending parameters as a JSON object.

* To know more about REST MODULE click here: [REST Module](https://wiki.openmrs.org/display/docs/REST+Module)

* Modules required:
   1. Cohort Module
   2. REST web services

***

#### Example GET Requests
---
##### list the related cohort

GET: [http://localhost:8080/openmrs/ws/rest/v1/cohortm/cohort/2baddb64-f4f9-47c4-80c6-404c4b016d12](http://localhost:8080/openmrs/ws/rest/v1/cohortm/testcohort/2baddb64-f4f9-47c4-80c6-404c4b016d12)

---
##### list the related cohort member

GET: [http://localhost:8080/openmrs/ws/rest/v1/cohortm/cohortmember/2003394e-daa2-4df8-acba-4894460b0624](http://localhost:8080/openmrs/ws/rest/v1/cohortm/testcohortmember/2003394e-daa2-4df8-acba-4894460b0624)

---
##### list the related cohort encounter

GET: [http://localhost:8080/openmrs/ws/rest/v1/cohortm/cohortenc/47d7bb8c-c4f2-4d16-978a-a4c7cf0d9556](http://localhost:8080/openmrs/ws/rest/v1/cohortm/testcohortenc/47d7bb8c-c4f2-4d16-978a-a4c7cf0d9556)

---
##### list the related cohort observation

GET : [http://localhost:8080/openmrs/ws/rest/v1/cohortm/cohortobs/34839160-5c8a-4c37-bdf6-e17042d46614](http://localhost:8080/openmrs/ws/rest/v1/cohortm/testcohortobs/34839160-5c8a-4c37-bdf6-e17042d46614)
***

#### Example POST Requests

---
POST: [http://localhost:8080/openmrs/ws/rest/v1/cohortm/cohort](http://localhost:8080/openmrs/ws/rest/v1/cohortm/testcohort/2baddb64-f4f9-47c4-80c6-404c4b016d12)

PARAMETERS:
```
{"name":"neurologicalrecoverystudy","cohortType":"\(list the related cohortType uuid\)","cohortProgram":"\(list the related cohortProgram uuid\)"}
```

---
POST :[http://localhost:8080/openmrs/ws/rest/v1/cohortm/](http://localhost:8080/openmrs/ws/rest/v1/cohortm/testcohortmember/2003394e-daa2-4df8-acba-4894460b0624)

[PARAMETERS ](http://localhost:8080/openmrs/ws/rest/v1/cohortm/testcohortmember/2003394e-daa2-4df8-acba-4894460b0624)
```
{"name":"Horatio","person":"\(list the related person uuid\)","cohort":"\(list the related cohort uuid\)"}

```
---
POST: [http://localhost:8080/openmrs/ws/rest/v1/cohortm/](http://localhost:8080/openmrs/ws/rest/v1/cohortm/testcohortenc/47d7bb8c-c4f2-4d16-978a-a4c7cf0d9556)

[PARAMETERS ](http://localhost:8080/openmrs/ws/rest/v1/cohortm/testcohortenc/47d7bb8c-c4f2-4d16-978a-a4c7cf0d9556)
```
{"encounterType":"\(list the related encounterType uuid\)","visit":"\(list the related visit uuid\)","cohort":"\(list the related cohort uuid\)","form":"\(list the related form uuid"\)}
```

---
POST: [http://localhost:8080/openmrs/ws/rest/v1/cohortm/](http://localhost:8080/openmrs/ws/rest/v1/cohortm/testcohortenc/47d7bb8c-c4f2-4d16-978a-a4c7cf0d9556)[cohortobs](http://localhost:8080/openmrs/ws/rest/v1/cohortm/testcohortobs/34839160-5c8a-4c37-bdf6-e17042d46614)

PARAMETERS:
```
{"encounterId":"\(list the related encounter uuid\)","concept":"\(list the related concept uuid\)","cohort":"\(list the related cohort uuid\)"}
```

***

* The min version required for the OpenMRS Core is 1.11.0

* The two modules - **REST Module** of 2.11+ version and **HTML Form Entry** of 2.5+ version are the required modules.

* **HTML FORM ENTRY OMOD FILE**: [https://modules.openmrs.org/\#/show/58/htmlformentry](https://modules.openmrs.org/#/show/58/htmlformentry)

* **REST MODULE OMOD FILE**: [https://modules.openmrs.org/\#/show/153/webservices-rest](https://modules.openmrs.org/#/show/153/webservices-rest)

* These modules should be running on OpenMRS core before installing the Cohort Module.

#### [User Manual](https://wiki.openmrs.org/display/~sharonvarghese/User+Manual)

## Potential Improvements:

* **Integrate it with Reporting Module**

  * Fetch / add members dynamically from reporting query instead of adding one by one.
  * Create reports for cohorts.

* **Integrate Cohort Management with core OpenMRS**

* **Extend the functionality of HTML Form Entry module for cohorts similar to patients which would require refactoring to allow adding custom tags related to cohorts for more dynamic and complex forms**

  * The original HTML Form Entry module supports dynamic functionality is saving forms to manage patient related encounters and observations data. In our module, the HTML Form functionality has been slightly extended to save cohort level encounters and observations based on cohort data to allow dynamic functionality in the module. This feature can be extended to maintain the module for cohort level encounters and observations.

  * Voiding an encounter will void all the observations associated with that encounter.

  * Cohort Module doesn't support Obs Group Component.

  * The user can create a HTML form under the Manage HTML Forms tab and then open the HTML form under the module, the form created is viewed.

  * The parameters date, location and provider needs to be included in the form and enter the rest of the fields in the form, the cohort encounters and observations related to the form are inserted into the database.

  * To know more about HTML Form Entry module click here: [HTML Form Entry module](https://wiki.openmrs.org/display/docs/HTML+Form+Entry+Module)

> The demonstration has been done on the legacy UI of this module. The current UI has been changed. However, the essential process remains the same.

### Downloads and Resources

* [**Source Code**](https://github.com/OpenSRP/openmrs-module-cohort) (OpenSRP openmrs-module-cohort)
* **Project Pages**: [**Cohort Module**](https://wiki.openmrs.org/display/~sharonvarghese/Cohort+Module), [**Cohort Module Enhacements**](https://wiki.openmrs.org/display/projects/Cohort+Module+Enhancements)
* [**OpenMRS Talk Discussions**](https://talk.openmrs.org/search?q=cohort%20module)
* [**Sharon's GSoC '15 Blog posts**](https://sharonvarghese1.wordpress.com/)
* [**Shreyans' GSoC '16 Blog posts**](http://blog.shreyanssheth.com/)
