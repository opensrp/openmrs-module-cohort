<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="template/localHeader.jsp" %>

<openmrs:htmlInclude file="/scripts/calendar/calendar.js"/>
<openmrs:htmlInclude file="/scripts/jquery/jquery-1.3.2.min.js"/>

<script type="text/javascript" charset="utf-8"></script>
<form method="post">
    <table>
        <spring:bind path="cohortattributes.name">
            <spring:message code="cohort.cohorttypename"/> :<br/>
            <input type="text" name="name" id="name" size="25" value="${status.value}"/> <br/> <br/>
            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
        </spring:bind>

        <spring:bind path="cohortattributes.description">
            <spring:message code="cohort.cohorttypedescription"/> :<br/>
            <textarea rows="4" name="description" id="description" cols="50"
                      value="${status.value}"></textarea><br/><br/>
            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
        </spring:bind>
        <tr>
            <td>
                <spring:bind path="cohortattributes.format">
                    <spring:message code="cohort.format"/> :<br/>
                    <select name="format">
                        <c:forEach var="format" items="${formats}">
                            <option value="${format}"
                                    <c:if test="${format ==cohortattributes.format}">selected</c:if>>${format}</option>
                        </c:forEach>
                        <c:if test="${status.errorMessage != ''}"><span
                                class="error">${status.errorMessage}</span></c:if>
                    </select>
                </spring:bind>
            </td>
        </tr>
    </table>
    <input type="submit" value="submit" id="submit"/>
</form>

<%@ include file="/WEB-INF/template/footer.jsp" %>