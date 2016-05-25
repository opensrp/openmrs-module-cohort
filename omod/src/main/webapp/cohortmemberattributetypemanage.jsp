<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<!--
<%@ include file="resources/module_style.css" %>
-->

<%@ include file="template/localHeader.jsp" %>
<h3>Manage Cohort Member Attributes Type</h3>
<a href="addcohortmemberattributetype.form">Create Cohort Member Attribute Type</a>
<br/>
<div>
    <b class="boxHeader">Find Cohort Member Attribute Type</b>
    <div class="box">
        <!--   <div class="searchWidgetContainer" id="createCohort">
        </div>
-->
        <form id="login" method="get">
            <spring:bind path="cohortattributes.name">
                <spring:message
                        code="cohort.searchcohortattributes"/> :<input type="text" id="name" name="name" value="${status.value}" placeholder="Search..."
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
                <th class="thStyle" id="thStyle">Attribute Type Name</th>
                <th class="thStyle" id="thStyle">Format</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ls" items="${CohortAttributesList}" varStatus="status">
                <tr class='${status.index % 2 == 0 ? "oddRow" : "evenRow" }'>
                    <td class="tdStyle">
                        <a href="${pageContext.request.contextPath}/module/cohort/editcohortmemberattributetype.form?cmat=${ls.cohortMemberAttributeTypeId}">${ls.name}</a>
                    </td>
                    <td class="tdStyle">
                            ${ls.format}
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@ include file="/WEB-INF/template/footer.jsp" %>