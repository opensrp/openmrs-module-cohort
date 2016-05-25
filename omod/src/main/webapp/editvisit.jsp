<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="template/localHeader.jsp" %>
<openmrs:htmlInclude file="/scripts/calendar/calendar.js"/>
<form id="form1" method="post">
    <spring:bind path="cohortvisit.cohort">
        Cohort Names:<br/>
        <select name="names">
            <c:forEach var="names" items="${formats1}">
                <option value="${names}" <c:if test="${names ==cohortvisit.cohort}">selected</c:if>>${names}</option>
            </c:forEach>
        </select>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    <br/>
    <br/>
    <spring:bind path="cohortvisit.visitType">
        Visit Type<br/>
        <select name="visittype">
            <c:forEach var="visittype" items="${visittypes}">
                <option value="${visittype}"
                        <c:if test="${visittype ==cohortvisit.visitType}">selected</c:if>>${visittype}</option>
            </c:forEach>
        </select>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    </tr>
    <br/>
    <spring:bind path="cohortvisit.location">
        Location <br/>
        <select name="location">
            <c:forEach var="location" items="${locations}">
                <option value="${location}"
                        <c:if test="${location ==cohortvisit.location}">selected</c:if>>${location}</option>
            </c:forEach>
        </select>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
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
    Void Cohort Visit<br/><br/>
    Reason:<input type="text" name="voidReason" id="voidReason" size="25" value="${status.value}"/> <br/> <br/>
    <input type="submit" value="void" id="void" name="void"/><br/><br/>
    Delete Cohort Visit<br/><br/>
    <input type="submit" value="delete" id="delete" name="delete"/><br/><br/>
</form>
<%@ include file="/WEB-INF/template/footer.jsp" %>