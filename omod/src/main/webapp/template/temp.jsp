<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="template/localHeader.jsp" %>

<openmrs:htmlInclude file="/scripts/calendar/calendar.js"/>
<openmrs:htmlInclude file="/scripts/jquery/jquery-1.3.2.min.js"/>

<script type="text/javascript" charset="utf-8"></script>
<form method="post">
    <spring:bind path="cohortatt.cohort">
        Cohort Names:<br/>
        <select name="names">
            <c:forEach var="names" items="${formats1}">
                <option value="${names}" <c:if test="${names ==cohortatt.cohort}">selected</c:if>>${names}</option>
            </c:forEach>
        </select>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    <spring:bind path="cohortatt.cohortAttributeType">
        Cohort Attribute Type:<br/>
        <select name="format">
            <c:forEach var="format" items="${formats}">
                <option value="${format}"
                        <c:if test="${format ==cohortAttributeType}">selected</c:if>>${format}</option>
            </c:forEach>
        </select>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    </td>
    </tr>
    </table>
    <br/>
    <br/>
    <spring:bind path="cohortatt.value">
        <spring:message code="cohort.cohorttypedescription"/> :<br/>
        <input type="text" name="value" id="value" size="25" value="${status.value}"/> <br/> <br/>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    <tr>
        <td>
            <br/>
            <br/>
            <input type="submit" value="Edit Cohort Attributes" id="submit"/>
            <br/><br/>
            Void Cohort Attribute<br/><br/>
            Reason:<input type="text" name="voidReason" id="voidReason" size="25" value="${status.value}"/> <br/> <br/>
            <input type="submit" value="void" id="void" name="void"/><br/><br/>
            Delete Cohort Attribute<br/><br/>
            <input type="submit" value="delete" id="delete" name="delete"/><br/><br/>
</form>

<%@ include file="/WEB-INF/template/footer.jsp" %>