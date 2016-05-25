<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="template/localHeader.jsp" %>
<openmrs:htmlInclude file="/scripts/calendar/calendar.js"/>
<h3>Delete Cohort Visit</h3>
<form id="form1" method="post">
    <spring:bind path="cohortvisit.visitType">
        Visit Id: <br/>
        <input type="text" name="visitId" id="visitId" size="25" value="${status.value}"/> <br/> <br/>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    <input type="submit" value="delete" id="delete" name="delete"/>
</form>
</form>
<%@ include file="/WEB-INF/template/footer.jsp" %>