<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="template/localHeader.jsp" %>

<openmrs:htmlInclude file="/scripts/calendar/calendar.js"></openmrs:htmlInclude>
<openmrs:htmlInclude file="/scripts/jquery/jquery-1.3.2.min.js"/>
<h3>Add Observations</h3>
<form id="form1" method="post">
    <spring:bind path="cohortobs.cohort">
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
    <spring:bind path="cohortobs.concept">
        Concept<br/>
        <select name="concept">
            <option value=""></option>
            <c:forEach var="concept" items="${concepts}">
                <option value="${concept}" <c:if test="${concept == status.value}">selected</c:if>>${concept}</option>
            </c:forEach>
        </select>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    <br/>
    <br/>
    Encounter Id<br/>
    <select name="encid">
        <option value=""></option>
        <c:forEach var="encid" items="${encids}">
            <option value="${encid}" <c:if test="${encid == status.value}">selected</c:if>>${encid}</option>
        </c:forEach>
    </select>
    </tr>
    <br/>
    Location <br/>
    <select name="location">
        <option value=""></option>
        <c:forEach var="location" items="${locations}">
            <option value="${location}" <c:if test="${location == status.value}">selected</c:if>>${location}</option>
        </c:forEach>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </select>
    </tr>
    <br/>
    <spring:bind path="cohortobs.obsDateTime">
        Date:<br/>
        <input type="text" name="obsDateTime" size="10" onFocus="showCalendar(this,60)"
               id="obsDateTime" value="${status.value}"/><i
            style="font-weight: normal; font-size: 0.8em;">(<openmrs:message code="general.format"/>:
        <openmrs:datePattern/></i><br/><br/>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    </tr>
    <input type="submit" value="submit" id="submit"/>
</form>
<%@ include file="/WEB-INF/template/footer.jsp" %>