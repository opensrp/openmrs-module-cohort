<!--Uncomment this and comment the rest to see new UI prototype -->

<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="template/header.jsp" %>
    <title>Add Cohort</title> <!--set page title-->
</head>
<openmrs:htmlInclude file="/moduleResources/cohort/styles/genericPageStyle.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/libraries/jquery-ui.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/pages/genericAddPageStyling.css" />
<%@ include file="template/navbar.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-sm-5">
            <div class="patient-form-heading">
                <h4 class="heading"><span>Enter Cohort Details</span></h4>
                <form id="form1" method="post" class="form-container">
                    <form:errors path="*" cssClass="errorblock" element="div"/>
                    <ul class="list-styled">
                        <li>
                            <fieldset class="form-group">
                                <spring:bind path="cohortmodule.name">
                                    <h4><spring:message code="cohort.cohortname"/> </h4>
                                    <input class="form-control" type="text" name="name" id="name" size="25" value="${status.value}" maxlength="100"/>
                                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                                </spring:bind>
                            </fieldset>
                        </li>
                        
                        <li>
                            <fieldset class="form-group">
                                <h4>Cohort Type</h4>
                                <select class="form-control" name="format" id="format">
                                    <option value=""></option>
                                    <c:forEach var="format" items="${formats}">
                                        <option value="${format}"
                                                <c:if test="${format ==cohortmodule.cohortType}">selected</c:if>>${format}</option>
                                    </c:forEach>
                                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                                </select>
                            </fieldset>
                        </li>

                        <li>
                            <fieldset class="form-group">
                                <spring:bind path="cohortmodule.cohortProgram">
                                    <h4>Cohort Program</h4>
                                    <select name="format1" id="format1" class="form-control">
                                        <option value=""></option>
                                        <c:forEach var="format1" items="${formats1}">
                                            <option value="${format1}"
                                                    <c:if test="${format1 ==cohortmodule.cohortProgram}">selected</c:if>>${format1}</option>
                                        </c:forEach>
                                        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                                    </select>
                                </spring:bind>
                            </fieldset>
                        </li>

                        <li>
                            <fieldset class="form-group">
                                <spring:bind path="cohortmodule.description">
                                    <h4><spring:message code="cohort.cohortdescription"/> </h4>
                                    <textarea class="form-control" rows="4" name="description" id="description" cols="50">${status.value}</textarea>
                                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                                </spring:bind>
                            </fieldset>
                        </li>

                        <li>
                            <fieldset class="form-group">
                                <spring:bind path="cohortmodule.startDate">
                                   <h4> <spring:message code="cohort.startdate"/> </h4>
                                    <input class="form-control" type="text" name="startDate" size="10"
                                           id="startDate" value="${status.value}"/><i style="font-weight: normal; font-size: 0.8em;">(<openmrs:message
                                        code="general.format"/>: <openmrs:datePattern/>)</i>
                                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                                </spring:bind>
                            </fieldset>
                        </li>

                        <li>
                            <fieldset class="form-group">
                                <spring:bind path="cohortmodule.endDate">
                                    <h4><spring:message code="cohort.enddate"/> </h4>
                                    <input class="form-control" type="text" name="endDate" size="10" onFocus="showCalendar(this,60)"
                                           id="endDate" value="${status.value}"/><i style="font-weight: normal; font-size: 0.8em;">(<openmrs:message
                                        code="general.format"/>: <openmrs:datePattern/>)</i>
                                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                                </spring:bind>
                            </fieldset>
                        </li>


                        <li>
                            <fieldset class="form-group">
                                <spring:bind path="cohortmodule.clocation">
                                    <h4><spring:message code="cohort.location"/> </h4>
                                    <select class="form-control" name="location" id="location">
                                        <option value=""></option>
                                        <c:forEach var="location" items="${locations}">
                                            <option value="${location}"
                                                    <c:if test="${location ==cohortmodule.clocation}">selected</c:if>>${location}</option>
                                        </c:forEach>
                                    </select>
                                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                                </spring:bind>
                            </fieldset>
                        </li>
                        
                        <div class="button-container">
                            <input class="btn btn-primary" type="submit" value="Save and Skip" id="submit"/><br/><br/>
                        </div>
                        
                        <div class="button-container">
                            <input class="btn btn-default" type="submit" value="Next" id="next" name="next">
                        </div>
                        
                    </ul>

                    <br/>
                    <br/>
                    
                    
                    
                </form>

            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $('#management-label-nav').addClass('active')
</script>

<!--Script includes for new UI -->
<openmrs:htmlInclude file="/moduleResources/cohort/scripts/pages/addCohort.js" />

<!--jQuery UI -->
<openmrs:htmlInclude file="/moduleResources/cohort/scripts/libraries/jquery-ui.js" />

<!--END-->
