<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="template/localHeader.jsp" %>


<openmrs:htmlInclude file="/scripts/calendar/calendar.js"/>
<style>
    .error {
        color: #ff0000;
    }

    .errorblock {
        color: #000;
        background-color: #ffEEEE;
        border: 3px solid #ff0000;
        padding: 8px;
        margin: 16px;
    }
</style>
<h3>Edit Role</h3>
<form id="form1" method="post">
    <form:errors path="*" cssClass="errorblock" element="div"/>
    Cohort Type:
    <select name="format">
        <option value=""></option>
        <c:forEach var="format" items="${formats}">
            <option value="${format}" <c:if test="${format == cohortrole.cohortType}">selected</c:if>>${format}</option>
        </c:forEach>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </select>
    </td>
    </tr>
    </table>
    <br/>
    <br/>
    <spring:bind path="cohortrole.name">
        <spring:message code="cohort.cohortname"/> :<br/>
        <input type="text" name="name" id="name" size="25" value="${status.value}"/> <br/> <br/>
        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
    </spring:bind>
    <input type="submit" value="Edit Role" id="submit"/><br/><br/>
    Delete Role<br/><br/>
    <input type="submit" value="delete" id="delete" name="delete"/><br/><br/>
</form>
<%@ include file="/WEB-INF/template/footer.jsp" %>