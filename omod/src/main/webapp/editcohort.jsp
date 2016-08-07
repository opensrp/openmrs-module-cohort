<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="template/header.jsp" %>
<title>Edit Cohort Attributes Type</title> <!--set page title-->
</head>
<openmrs:htmlInclude file="/moduleResources/cohort/styles/genericPageStyle.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/pages/genericEditPageStyling.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/libraries/jquery-ui.css" />

<%@ include file="template/navbar.jsp" %>

<body>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h4 class="heading"><span>Edit Cohort</span></h4>
            <form class="form-container" method="post">
                <br>
                <li>
                    <h4>Cohort Program</h4>
                    <select class="form-control generic-label" name="format1">
                        <c:forEach var="format1" items="${formats1}">
                            <option value="${format1}"
                                    <c:if test="${format1==cohortmodule.cohortProgram}">selected</c:if>>${format1}</option>
                        </c:forEach>
                    </select>
                </li>
                <br/>
                <li>
                    <h4>Cohort Type </h4>
                    <select class="form-control generic-label" name="format" >
                        <c:forEach var="format" items="${formats}">
                            <option value="${format}" <c:if test="${format==cohortmodule.cohortType}">selected</c:if>>${format}</option>
                        </c:forEach>
                    </select>
                </li>
                <br/>

                <li>
                    <spring:bind path="cohortmodule.name">
                        <h4><spring:message code="cohort.cohortname"/> </h4>
                        <input class="form-control" type="text" name="name" id="name" size="25" value="${status.value}"/> <br/> 
                        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                    </spring:bind>
                </li>
                
                <li>
                    <spring:bind path="cohortmodule.description">
                        <h4><spring:message code="cohort.cohortdescription"/>  </h4>
                        <textarea class="form-control" rows="4" name="description" id="description" cols="50">${status.value}</textarea><br/>
                        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                    </spring:bind>
                </li>
                
                <li>
                    <spring:bind path="cohortmodule.startDate">
                       <h4> <spring:message code="cohort.startdate"/> </h4>
                        <input class="form-control" type="text" name="startDate" size="10"
                               id="startDate" value="${status.value}"/><i
                            style="font-weight: normal; font-size: 0.8em;">(<openmrs:message code="general.format"/>:
                        <openmrs:datePattern/>)</i><br/><br/>

                        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                    </spring:bind>
                </li>


                <li>
                    <spring:bind path="cohortmodule.endDate">
                        <h4><spring:message code="cohort.enddate"/> </h4>
                        <input class="form-control" type="text" name="endDate" size="10" onFocus="showCalendar(this,60)"
                               id="endDate" value="${status.value}"/><i style="font-weight: normal; font-size: 0.8em;">(<openmrs:message
                            code="general.format"/>: <openmrs:datePattern/>)</i><br/><br/>
                        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                    </spring:bind>
                </li>
                
                <li>
                    <spring:bind path="cohortmodule.clocation">
                        <h4><spring:message code="cohort.location"/> </h4>
                        <select class="form-control" id="location" name="location">
                            <c:forEach var="location" items="${locations}">
                                <option value="${location}"
                                        <c:if test="${location == cohortmodule.clocation}">selected</c:if>>${location}</option>
                            </c:forEach>
                        </select>
                        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                    </spring:bind>
                </li>
                <br><br>
                <div class="button-container">
                    <input class="btn btn-primary" type="submit" value="Edit Cohort" id="Edit Cohort" name="Edit Cohort"/>
                </div>
                <hr/>
                <h4 class="alert-warning">Void Cohort</h4> <br>
                <h4>Reason</h4>:<input class="form-control" type="text" name="voidReason" id="voidReason" size="25" value="${status.value}"/> <br/>
                <input class="btn btn-warning" type="submit" value="void" id="void" name="void"/>
                <hr/>
                <h4 class="alert-danger">Delete Cohort</h4> <br><br/>
                <input class="btn btn-danger" type="submit" value="delete" id="delete" name="delete"/><br/>
                </ul>
            </form>
        </div>
    </div>
</div>
</body>

<!-- Slider JS -->
<openmrs:htmlInclude file="/moduleResources/cohort/scripts/libraries/jquery-ui.js" />

<openmrs:htmlInclude file="/moduleResources/cohort/scripts/pages/cPatients.js" />

