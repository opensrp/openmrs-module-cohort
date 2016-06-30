<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="template/localHeader.jsp" %>
<openmrs:htmlInclude file="/scripts/calendar/calendar.js"/>
<h3>Add Cohort Member</h3>
<form id="form1" method="post">
    Cohort Name: ${cohort.name}<br/>
    Role<br/>
    <select name="format">
        <option value=""></option>
        <c:forEach var="format" items="${formats}">
            <option value="${format}" <c:if test="${format ==cpatient.role}">selected</c:if>>${format}</option>
        </c:forEach>
    </select>
    <br/>
    <br/>
    <spring:bind path="cpatient.startDate">
        <spring:message code="cohort.startdate"/> :<br/>
        <input type="text" name="startDate" size="10"
               id="startDate" value="${status.value}"/><i
            style="font-weight: normal; font-size: 0.8em;">(<openmrs:message code="general.format"/>:
        <openmrs:datePattern/>)</i><br/><br/>

        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    <br/>
    <br/>
    <spring:bind path="cpatient.endDate">
        <spring:message code="cohort.enddate"/> :<br/>
        <input type="text" name="endDate" size="10"
               id="endDate" value="${status.value}"/><i style="font-weight: normal; font-size: 0.8em;">(<openmrs:message
            code="general.format"/>: <openmrs:datePattern/>)</i><br/><br/>

        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    <br/>
    <br/>
    Cohort Member Name:<br/>
    <openmrs_tag:patientField formFieldName="patient_id" formFieldId="existingPatientId"/>
    <br/>
    <br/>
    <input type="submit" value="Add Cohort Member" id="add" name="add"/><br/><br/>
    <a href="addCohortMemberAttribute.form?cma=${cpatient.cohortMemberId}">Add Cohort Member Attribute</a>
</form>
<%@ include file="/WEB-INF/template/footer.jsp" %>

