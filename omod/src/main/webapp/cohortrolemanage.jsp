<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeader.jsp" %>
<h3>Manage Cohort Role</h3>
<a href="arole.form">Add Role </a>
<br/>
<br/>
<div>
    <b class="boxHeader">Find Cohort Roles </b>
    <div class="box">
        <!--   <div class="searchWidgetContainer" id="createCohort">
        </div>
-->
        <form id="login" method="get">
            <spring:bind path="cohortrole.name">
                <spring:message
                        code="cohort.searchcohorttypes"/> :<input type="text" id="name" name="name" value="${status.value}" placeholder="Search..."
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
                <th class="thStyle">Cohort Role Name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ls" items="${CohortTypeList}" varStatus="status">
                <tr class='${status.index % 2 == 0 ? "oddRow" : "evenRow" }'>
                    <td class="tdStyle">
                        <a href="${pageContext.request.contextPath}/module/cohort/editcohortrole.form?croleid=${ls.cohortRoleId}">${ls.name}</a>
                    </td>
                    <td class="tdStyle">
                            ${ls.description}
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@ include file="/WEB-INF/template/footer.jsp" %>