<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeader.jsp" %>
<h3>Manage Cohort Member Attributes</h3>
<a href="addcohortmemberattribute.form"><spring:message code="cohort.createcohortatt"/></a>
<br/>
<div>
    <b class="boxHeader"><spring:message code="cohort.findcohortatt"/></b>
    <div class="box">
        <!--   <div class="searchWidgetContainer" id="createCohort">
        </div>
-->
        <form id="login" method="get">
            <spring:bind path="cohortatt.value">
                <spring:message
                        code="cohort.searchcohortattributes"/> :<input type="text" id="value" name="value" value="${status.value}" placeholder="Search..."
                required>
                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            </spring:bind>
            <input type="submit" id="search" name="search" value="search">
        </form>
    </div>
</div>
<div class="box">
    <div>
        <table class="tableStlye">
            <thead>
            <tr>
                <th class="thStyle" id="thStyle">Value</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ls" items="${CohortAttributesList}" varStatus="status">
                <tr class='${status.index % 2 == 0 ? "oddRow" : "evenRow" }'>
                    <td class="tdStyle">
                        <a href="${pageContext.request.contextPath}/module/cohort/editcohortmemberattribute.form?cma=${ls.cohortMemberAttributeId}">${ls.value}</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@ include file="/WEB-INF/template/footer.jsp" %>