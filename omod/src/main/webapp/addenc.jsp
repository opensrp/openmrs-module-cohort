<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="template/localHeader.jsp" %>

<openmrs:htmlInclude file="/scripts/calendar/calendar.js"></openmrs:htmlInclude>
<openmrs:htmlInclude file="/scripts/jquery/jquery-1.3.2.min.js"/>
<h3>Add Encounter</h3>
<form id="form1" method="post">
    <spring:bind path="cohortencounters.cohort">
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
    <spring:bind path="cohortencounters.encounterType">
        Encounter Type<br/>
        <select name="enctype">
            <option value=""></option>
            <c:forEach var="enctype" items="${enctypes}">
                <option value="${enctype}" <c:if test="${enctype == status.value}">selected</c:if>>${enctype}</option>
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
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </select>
    </tr>
    <br/>
    Form <br/>
    <select name="form">
        <option value=""></option>
        <c:forEach var="form" items="${forms}">
            <option value="${form}" <c:if test="${form == status.value}">selected</c:if>>${form}</option>
        </c:forEach>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </select>
    </tr>
    <br/>
    Visit<br/>
    <select name="visit">
        <option value=""></option>
        <c:forEach var="visit" items="${visits}">
            <option value="${visit}" <c:if test="${visit == status.value}">selected</c:if>>${visit}</option>
        </c:forEach>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </select>
    </tr>
    <br/>
    <spring:bind path="cohortencounters.encounterDateTime">
        Date:<br/>
        <input type="text" name="encounterDateTime" size="10" onFocus="showCalendar(this,60)"
               id="encounterDateTime" value="${status.value}"/><i
            style="font-weight: normal; font-size: 0.8em;">(<openmrs:message code="general.format"/>:
        <openmrs:datePattern/></i><br/><br/>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    </tr>
    <input type="submit" value="submit" id="submit"/>
</form>
<%@ include file="/WEB-INF/template/footer.jsp" %>