<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="template/localHeader.jsp" %>

<openmrs:htmlInclude file="/scripts/calendar/calendar.js"/>
<openmrs:htmlInclude file="/scripts/jquery/jquery-1.3.2.min.js"/>

<script type="text/javascript" charset="utf-8"></script>
<h3>Add Cohort Attribute</h3>
<form method="post" id="form1">
    Cohort Name: ${cohortmodule.name}<br/>
    Cohort Attribute Type:<br/>
    <select name="cohortAttributeTypeId"
            onchange="document.getElementById('form1').method='GET'; document.getElementById('form1').submit()">
        <option value=""></option>
        <c:forEach var="attype" items="${attypes}">
            <option value="${attype.cohortAttributeTypeId}"
                    <c:if test="${attype ==cohortatt.cohortAttributeType}">selected</c:if> >${attype}</option>
        </c:forEach>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </select>
    <br/>
    <input type="hidden" name="ca" value="${cohortatt.cohort.cohortId}"/>
    Value:<br/>
    <spring:bind path="cohortatt.value">
        <openmrs:fieldGen
                type="${cohortatt.cohortAttributeType.format}"
                formFieldName="selectedvalue"
                val="${selectedvalue}"
                parameters="optionHeader=[blank]|showAnswers=${attrType.foreignKey}|isNullable=false"/> <%-- isNullable=false so booleans don't have 'unknown' radiobox --%>
    </spring:bind>
    <tr>
        <td>
            <br/>
            <br/>
            <input type="submit" value="Add Cohort Attribute" id="add" name="add"/>
            <input type="submit" value="Next" id="next" name="next"/>
</form>

<%@ include file="/WEB-INF/template/footer.jsp" %>