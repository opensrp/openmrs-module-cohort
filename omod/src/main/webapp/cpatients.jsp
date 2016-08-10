<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="template/header-alt.jsp" %>
    <title>Add Cohort Member</title> <!--set page title-->
</head>
<openmrs:htmlInclude file="/moduleResources/cohort/styles/genericPageStyle.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/pages/cPatients.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/libraries/jquery-ui.css" />
<%--<openmrs:htmlInclude file="/moduleResources/cohort/styles/libraries/jquery.typeahead.css" />--%>
<%--<openmrs:htmlInclude file="/moduleResources/cohort/styles/libraries/jquery-ui.css" />--%>
<%@ include file="template/navbar.jsp" %>

<body>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h4 class="heading"><span>Add Cohort Member</span></h4>
            <form class="form-container" id="form1" method="post">
                <ul>
                    <li>
                        <h4>Cohort Name:</h4> 
                            <a href="/openmrs/module/cohort/cohortDashboard.form?search=search&name=${cohort.name}">
                                <br><span class="attribute-name-header">${cohort.name}</span></a>
                        <br/>
                    </li>
                    <br>
                    <li>
                        <fieldset>
                            <h4>Patient:</h4>
                            <br>
                            <openmrs_tag:patientField formFieldName="patient_id" formFieldId="existingPatientId"/>
                        </fieldset>
                    </li>
                    <br>
                    
                    <li>
                        <fieldset class="form-group">
                            <h4>Role:</h4>
                            <select id="role-dropdown" class="form-control" name="format">
                                <option value=""></option>
                                <c:forEach var="format" items="${formats}">
                                    <option class="form-control" value="${format}" <c:if test="${format ==cpatient.role}">selected</c:if>>${format}</option>
                                </c:forEach>
                            </select>
                        </fieldset>
                    </li>
                    
                    <li>
                        <fieldset class="form-group">
                            <spring:bind path="cpatient.startDate">
                                <h4><spring:message code="cohort.startdate"/> :</h4>
                                <input class="form-control" type="text" name="startDate" size="10"
                                       id="startDate" value="${status.value}"/><i
                                    style="font-weight: normal; font-size: 0.8em;">(<openmrs:message code="general.format"/>:
                                <openmrs:datePattern/>)</i>

                                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                            </spring:bind>
                        </fieldset>
                    </li>

                    <li>
                        <fieldset class="form-group">
                            <spring:bind path="cpatient.endDate">
                                <h4><spring:message code="cohort.enddate"/> :</h4>
                                <input class="form-control" type="text" name="endDate" size="10"
                                       id="endDate" value="${status.value}"/><i style="font-weight: normal; font-size: 0.8em;">(<openmrs:message
                                    code="general.format"/>: <openmrs:datePattern/>)</i>

                                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                            </spring:bind>
                        </fieldset>
                    </li>
                    
                    
                    <br>
                    
                    <input class="btn btn-primary" type="submit" value="Add Cohort Member" id="add" name="add"/><br/><br/>


                    <a class="btn btn-default" href="addCohortMemberAttribute.form?cma=${cpatient.cohortMemberId}">
                        <i class="fa fa-plus fa-2x" aria-hidden="true"></i>
                        <span style="ver">Add Cohort Member Attribute</span>
                    </a>

                </ul>
            </form>
        </div>
    </div>
</div>
</body>

<!--Typeahead JS -->
<openmrs:htmlInclude file="/moduleResources/cohort/scripts/libraries/jquery.typeahead.js" />
<!-- Slider JS -->
<openmrs:htmlInclude file="/moduleResources/cohort/scripts/libraries/jquery-ui.js" />

<openmrs:htmlInclude file="/moduleResources/cohort/scripts/pages/cPatients.js" />

