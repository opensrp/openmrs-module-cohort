<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeader.jsp" %>
<h3>Manage Cohort Program</h3>
<a href="addcohortprogram.form">Add Program</a>
<br/>
<div>
    <b class="boxHeader"><spring:message code="cohort.findcohorttypes"/> </b>
    <div class="box">
        <!--   <div class="searchWidgetContainer" id="createCohort">
        </div>
-->
        <form id="login" method="get">
            <spring:bind path="cohortprogram.name">
                Cohort Program Name:<input type="text" id="name" name="name" value="${status.value}" placeholder="Search..."
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
                <th class="thStyle">CohortProgramName</th>
                <th class="thStyle">Description</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ls" items="${CohortTypeList}" varStatus="status">
                <tr class='${status.index % 2 == 0 ? "oddRow" : "evenRow" }'>
                    <td class="tdStyle">
                        <a href="${pageContext.request.contextPath}/module/cohort/editcohortprogram.form?cpid=${ls.cohortProgramId}">${ls.name}</a>
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