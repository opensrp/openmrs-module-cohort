<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="template/header.jsp" %>
<openmrs:htmlInclude file="/moduleResources/cohort/styles/libraries/jquery-ui.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/pages/cohortDashboard.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/genericPageStyle.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/pages/groupCohortStyling.css" />


<title>Cohort Dashboard</title> <!--set page title-->
</head>
<%@ include file="template/navbar.jsp" %>

<body>
<section class="top-options">
    <div class="container">
        <div class="row" >
            <div class="col-sm-4">
                <h4 class="heading"><span>Add Group Cohort</span></h4>
                <form id="form1" method="post" class="form-container">
                    <form:errors path="*" cssClass="errorblock" element="div"/>
                    <h4> Cohort Name:</h4>
                    <br/>
                    <select id = "format" name="format" class="form-control">
                        <option value=""></option>
                        <c:forEach var="format" items="${formats}">
                            <option value="${format}" <c:if test="${format == status.value}">selected</c:if>>${format}</option>
                        </c:forEach>
                        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                    </select>
                    </td>
                    </tr>
                    </table>
                    <br/>
                    <spring:bind path="cohortmodule.name">
                        <h4> Group Cohort Name</h4><br/>
                        <input class="form-control" type="text" name="name" id="name" size="25" value="${status.value}"/> <br/> <br/>
                        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                    </spring:bind>
                    <spring:bind path="cohortmodule.description">
                        <h4 ><spring:message code="cohort.cohortdescription"/></h4><br/>
                        <textarea class="form-control" rows="4" name="description" id="description" cols="50">${status.value}</textarea><br/><br/>
                        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                    </spring:bind>
                    <spring:bind path="cohortmodule.clocation">
                        <h4><spring:message code="cohort.location"/> </h4><br/>
                        <select class="form-control" name="location" id="location">
                            <option value=""></option>
                            <c:forEach var="location" items="${locations}">
                                <option value="${location}"
                                        <c:if test="${location == status.value}">selected</c:if>>${location}</option>
                            </c:forEach>
                        </select>
                        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                    </spring:bind>
                    </td>
                    </tr>
                    <br/>
                    <spring:bind path="cohortmodule.startDate">
                        <h4><spring:message code="cohort.startdate"/></h4><br>
                        <input class="form-control" type="text" name="startDate" size="10" onFocus="showCalendar(this,60)"
                               id="startDate" value="${status.value}"/><i
                            style="font-weight: normal; font-size: 0.8em;">(<openmrs:message code="general.format"/>:
                        <openmrs:datePattern/>)</i><br/><br/>

                        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                    </spring:bind>
                    <spring:bind path="cohortmodule.endDate">
                        <h4><spring:message code="cohort.enddate"/> </h4><br/>
                        <input class="form-control" type="text" name="endDate" size="10" onFocus="showCalendar(this,60)"
                               id="endDate" value="${status.value}"/><i style="font-weight: normal; font-size: 0.8em;">(<openmrs:message
                            code="general.format"/>: <openmrs:datePattern/>)</i><br/><br/>
                        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                    </spring:bind>
                    <h4>Head of Group</h4><br>
                    <openmrs_tag:patientField formFieldName="patient_id" formFieldId="existingPatientId"/>
                    <br/>
                    <h4>Role</h4><br/>
                    <select name="format1" id="format1" class="form-control">
                        <option value=""></option>
                        <c:forEach var="format1" items="${formats1}">
                            <option value="${format1}" <c:if test="${format1 == status.value}">selected</c:if>>${format1}</option>
                        </c:forEach>
                    </select>
                    <br/>
                    <input class="btn btn-primary" type="submit" value="Add Group Cohort" id="submit"/><br/><br/>
                    <input class="btn btn-default" type="submit" value="Next" id="next" name="next"/>
                </form>
            </div>
        </div>
    </div>
</section>

</body>

<script>
    $('#existingPatientId_selection').addClass('form-control');
    $('#existingPatientId_selection').addClass('eighty-five-width');
</script>

<!--END-->