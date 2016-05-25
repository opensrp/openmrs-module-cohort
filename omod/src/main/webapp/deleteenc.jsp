<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="template/localHeader.jsp" %>
<script type="text/javascript" charset="utf-8"></script>
<h3>Delete Cohort Encounter</h3>
<form method="get">
    <spring:bind path="cohortencounter.encounterId">
        Encounter Id: <br/>
        <input type="text" name="encounterId" id="encounterId" size="25" value="${status.value}"/> <br/> <br/>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    <input type="submit" value="delete" id="delete" name="delete"/>
</form>
<%@ include file="/WEB-INF/template/footer.jsp" %>