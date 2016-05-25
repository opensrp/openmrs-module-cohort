<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeader.jsp" %>
<h3>Manage Cohort Type</h3>
<a href="addcohorttype.form"><spring:message code="cohort.createcohorttypes"/> </a>
<br/>
<br/>
<div>
    <b class="boxHeader"><spring:message code="cohort.findcohorttypes"/> </b>
    <div class="box">
        <!--   <div class="searchWidgetContainer" id="createCohort">
        </div>
-->
        <form id="login" method="get">
            <spring:bind path="cohorttype.name">
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
                <th class="thStyle">CohortTypeName</th>
                <th class="thStyle">Description</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ls" items="${CohortTypeList}" varStatus="status">
                <tr class='${status.index % 2 == 0 ? "oddRow" : "evenRow" }'>
                    <td class="tdStyle">
                        <a href="${pageContext.request.contextPath}/module/cohort/editcohorttype.form?ctypeid=${ls.cohortTypeId}">${ls.name}</a>
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