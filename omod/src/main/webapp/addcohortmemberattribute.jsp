<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="template/localHeader.jsp" %>

<openmrs:htmlInclude file="/scripts/calendar/calendar.js"/>
<openmrs:htmlInclude file="/scripts/jquery/jquery-1.3.2.min.js"/>

<script type="text/javascript" charset="utf-8"></script>
<h3>Add Cohort Member Attribute</h3>
<form method="post" id="form2">
    Cohort Member Name: ${cohortmember.person.names}<br/>
    Cohort Member Attribute Type:<br/>
    <select name="cohortMemberAttributeTypeId"
            onchange="document.getElementById('form2').method='GET'; document.getElementById('form2').submit()">
        <option value=""></option>
        <c:forEach var="attype" items="${attypes}">
            <option value="${attype.cohortMemberAttributeTypeId}"
                    <c:if test="${attype ==cohortatt.cohortMemberAttributeType}">selected</c:if>>${attype}</option>
        </c:forEach>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </select>
    </td>
    </tr>
    </table>
    <br/>
    <br/>
    <input type="hidden" name="cma" value="${cohortatt.cohortMember.cohortMemberId}"/>
    <spring:bind path="cohortatt.value">
        Value:<br/>
        <openmrs:fieldGen
                type="${cohortatt.cohortMemberAttributeType.format}"
                formFieldName="selectedvalue"
                val="${selectedvalue}"
                parameters="optionHeader=[blank]|showAnswers=${attrType.foreignKey}|isNullable=false"/> <%-- isNullable=false so booleans don't have 'unknown' radiobox --%>
    </spring:bind>
    <tr>
        <td>
            <br/>
            <br/>
            <input type="submit" value="Add Cohort Member Attribute" id="add"/>
</form>

<%@ include file="/WEB-INF/template/footer.jsp" %>