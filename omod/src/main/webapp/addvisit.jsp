<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="template/localHeader.jsp" %>
<openmrs:htmlInclude file="/scripts/calendar/calendar.js"/>
<h3>Add Cohort Visit</h3>
<form id="form1" method="post">
    <spring:bind path="cohortvisit.cohort">
        Cohort Names:<br/>
        <select name="names">
            <option value=""></option>
            <c:forEach var="names" items="${formats1}">
                <option value="${names}" <c:if test="${names == status.value}">selected</c:if>>${names}</option>
            </c:forEach>
        </select>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    <br/>
    <br/>
    Visit Type<br/>
    <spring:bind path="cohortvisit.visitType">
        <select name="visittype">
            <option value=""></option>
            <c:forEach var="visittype" items="${visittypes}">
                <option value="${visittype}"
                        <c:if test="${visittype == status.value}">selected</c:if>>${visittype}</option>
            </c:forEach>
        </select>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    </tr>
    <br/>
    Location <br/>
    <select name="location">
        <option value=""></option>
        <c:forEach var="location" items="${locations}">
            <option value="${location}" <c:if test="${location == status.value}">selected</c:if>>${location}</option>
        </c:forEach>
    </select>
    </tr>
    <br/>
    <spring:bind path="cohortvisit.startDate">
        <spring:message code="cohort.startdate"/> :<br/>
        <input type="text" name="startDate" size="10" onFocus="showCalendar(this,60)"
               id="startDate" value="${status.value}"/><i
            style="font-weight: normal; font-size: 0.8em;">(<openmrs:message code="general.format"/>:
        <openmrs:datePattern/>)</i><br/><br/>

        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    <spring:bind path="cohortvisit.endDate">
        <spring:message code="cohort.enddate"/> :<br/>
        <input type="text" name="endDate" size="10" onFocus="showCalendar(this,60)"
               id="endDate" value="${status.value}"/><i style="font-weight: normal; font-size: 0.8em;">(<openmrs:message
            code="general.format"/>: <openmrs:datePattern/>)</i><br/><br/>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    <input type="submit" value="Add Cohort Visit" id="submit"/><br/><br/>
</form>
<%@ include file="/WEB-INF/template/footer.jsp" %>