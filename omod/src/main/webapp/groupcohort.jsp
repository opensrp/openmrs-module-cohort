<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="template/localHeader.jsp" %>
<openmrs:htmlInclude file="/scripts/calendar/calendar.js"/>
<style>
    .error {
        color: #ff0000;
    }

    .errorblock {
        color: #000;
        background-color: #ffEEEE;
        border: 3px solid #ff0000;
        margin: 16px;
    }
</style>
<h3>Add Group Cohort</h3>
<form id="form1" method="post">
    <form:errors path="*" cssClass="errorblock" element="div"/>
    Cohort Name:<br/>
    <select name="format">
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
        Group Cohort Name:<br/>
        <input type="text" name="name" id="name" size="25" value="${status.value}"/> <br/> <br/>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    <spring:bind path="cohortmodule.description">
        <spring:message code="cohort.cohortdescription"/> :<br/>
        <textarea rows="4" name="description" id="description" cols="50">${status.value}</textarea><br/><br/>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    <spring:bind path="cohortmodule.clocation">
        <spring:message code="cohort.location"/> :<br/>
        <select name="location">
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
        <spring:message code="cohort.startdate"/> :<br/>
        <input type="text" name="startDate" size="10" onFocus="showCalendar(this,60)"
               id="startDate" value="${status.value}"/><i
            style="font-weight: normal; font-size: 0.8em;">(<openmrs:message code="general.format"/>:
        <openmrs:datePattern/>)</i><br/><br/>

        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    <spring:bind path="cohortmodule.endDate">
        <spring:message code="cohort.enddate"/> :<br/>
        <input type="text" name="endDate" size="10" onFocus="showCalendar(this,60)"
               id="endDate" value="${status.value}"/><i style="font-weight: normal; font-size: 0.8em;">(<openmrs:message
            code="general.format"/>: <openmrs:datePattern/>)</i><br/><br/>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    Head of Group:<br/>
    <openmrs_tag:patientField formFieldName="patient_id" formFieldId="existingPatientId"/>
    <br/>
    Role:<br/>
    <select name="format1">
        <option value=""></option>
        <c:forEach var="format1" items="${formats1}">
            <option value="${format1}" <c:if test="${format1 == status.value}">selected</c:if>>${format1}</option>
        </c:forEach>
    </select>
    <br/>
    <input type="submit" value="Add Group Cohort" id="submit"/><br/><br/>
    <input type="submit" value="Next" id="next" name="next"/>
</form>
<%@ include file="/WEB-INF/template/footer.jsp" %>