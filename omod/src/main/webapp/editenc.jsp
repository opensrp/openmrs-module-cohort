<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="template/localHeader.jsp" %>

<openmrs:htmlInclude file="/scripts/calendar/calendar.js"></openmrs:htmlInclude>
<openmrs:htmlInclude file="/scripts/jquery/jquery-1.3.2.min.js"/>
<form id="form1" method="post">
    <spring:bind path="cohortencounters.cohort">
        Cohort Names:<br/>
        <select name="names">
            <c:forEach var="names" items="${formats1}">
                <option value="${names}"
                        <c:if test="${names ==cohortencounters.cohort}">selected</c:if>>${names}</option>
            </c:forEach>
        </select>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    <br/>
    <br/>
    <spring:bind path="cohortencounters.encounterType">
        Encounter Type<br/>
        <select name="enctype">
            <c:forEach var="enctype" items="${enctypes}">
                <option value="${enctype}"
                        <c:if test="${enctype ==cohortencounters.encounterType}">selected</c:if>>${enctype}</option>
            </c:forEach>
        </select>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    </tr>
    <br/>
    <spring:bind path="cohortencounters.location">
        Location <br/>
        <select name="location">
            <c:forEach var="location" items="${locations}">
                <option value="${location}"
                        <c:if test="${location ==cohortencounters.location}">selected</c:if>>${location}</option>
            </c:forEach>
        </select>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    <br/>
    <br/>
    Form <br/>
    <select name="form">
        <c:forEach var="form" items="${forms}">
            <option value="${form}" <c:if test="${form ==cohortencounters.form}">selected</c:if>>${form}</option>
        </c:forEach>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </select>
    </tr>
    <br/>
    Visit<br/>
    <select name="visit">
        <c:forEach var="visit" items="${visits}">
            <option value="${visit}" <c:if test="${visit ==cohortencounters.visit}">selected</c:if>>${visit}</option>
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
    <input type="submit" value="edit" id="submit"/>
    <br/>
    <br/>
    Void Cohort Encounter<br/><br/>
    Reason:<input type="text" name="voidReason" id="voidReason" size="25" value="${status.value}"/> <br/> <br/>
    <input type="submit" value="void" id="void" name="void"/><br/><br/>
    Delete Cohort Encounter<br/><br/>
    <input type="submit" value="delete" id="delete" name="delete"/><br/><br/>
</form>
<%@ include file="/WEB-INF/template/footer.jsp" %>