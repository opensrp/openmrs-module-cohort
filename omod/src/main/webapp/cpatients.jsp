<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="template/header.jsp" %>
    <title>Add Cohort Member</title> <!--set page title-->
</head>
<openmrs:htmlInclude file="/moduleResources/cohort/styles/genericPageStyle.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/pages/cPatients.css" />
<%@ include file="template/navbar.jsp" %>

<body>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h4 class="heading"><span>Add Cohort Member</span></h4>
            <form class="form-container" id="form1" method="post">
                <ul>
                    <li>
                        <h4>Cohort Name:</h4> ${cohort.name}
                        <br/>
                    </li>
                    
                    <li>
                        Cohort Member Name:<br/>
                        <openmrs_tag:patientField formFieldName="patient_id" formFieldId="existingPatientId"/>
                        <br/>
                        <br/>
                    </li>

                    <li>
                        <fieldset class="form-group">
                            Cohort Member Name:<br/>
                            <div class="for">
                                <openmrs_tag:patientField formFieldName="patient_id" formFieldId="existingPatientId"/>
                            </div>
                        </fieldset>
                    </li>
                    
                    
                    <li>
                        <fieldset class="form-group">
                            <h4>Role:</h4>
                            <select class="form-control" name="format">
                                <option value=""></option>
                                <c:forEach var="format" items="${formats}">
                                    <option value="${format}" <c:if test="${format ==cpatient.role}">selected</c:if>>${format}</option>
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
                    
                    <a href="addcohortmemberattribute.form?cma=${cpatient.cohortMemberId}">Add Cohort Member Attribute</a>

                </ul>
            </form>
        </div>
    </div>
</div>
</body>