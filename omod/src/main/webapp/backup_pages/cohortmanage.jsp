<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<!--

-->

<%@ include file="template/localHeader.jsp" %>
<h3>Manage Cohort </h3>
<a href="addCohort.form"><spring:message code="cohort.createcohort"/></a>
<a href="groupcohort.form">Create Group Cohort</a>
<br/>
<br/>
<div>
    <b class="boxHeader"><spring:message code="cohort.findcohort"/></b>
    <div class="box">
        <!--   <div class="searchWidgetContainer" id="createCohort">
        </div>
-->
        <form id="login" method="get">
            <spring:bind path="cohortmodule.name">
                <spring:message
                        code="cohort.searchcohorts"/> :<input type="text" id="name" name="name" value="${status.value}" placeholder="Search..."
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
                <th class="thStyle" id="thStyle">Cohort Name</th>
                <th class="thStyle" id="thStyle">Description</th>
                <th class="thStyle" id="thStyle">Cohort Type</th>
                <th class="thStyle" id="thStyle">Cohort Program</th>
                <th class="thStyle" id="thStyle">Location</th>
                <th class="thStyle" id="thStyle">Start Date</th>
                <th class="thStyle" id="thStyle">End Date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ls" items="${CohortList}" varStatus="status">
                <tr class='${status.index % 2 == 0 ? "oddRow" : "evenRow" }'>
                    <td class="tdStyle">
                        <a href="${pageContext.request.contextPath}/module/cohort/editCohort.form?cid=${ls.cohortId}">${ls.name}</a>
                    </td>
                    <td class="tdStyle">
                            ${ls.description}
                    </td>
                    <td class="tdStyle">
                            ${ls.cohortType.name}
                    </td>
                    <td class="tdStyle">
                            ${ls.cohortProgram.name}
                    </td>
                    <td class="tdStyle">
                            ${ls.clocation}
                    </td>
                    <td class="tdStyle">
                            ${ls.startDate}
                    </td>
                    <td class="tdStyle">
                            ${ls.endDate}
                    </td>
                    <td class="tdStyle">
                        <a href="${pageContext.request.contextPath}/module/cohort/addCohortAttributes.form?ca=${ls.cohortId}">Add
                            Attribute</a>
                    </td>
                    <td class="tdStyle">
                        <a href="${pageContext.request.contextPath}/module/cohort/cPatients.form?cpid=${ls.cohortId}">Add
                            Cohort Member</a>
                    </td>
                    <td class="tdStyle">
                        <a href="${pageContext.request.contextPath}/module/cohort/htmlFormEntry.form?coh=${ls.cohortId}&htmlformId=${htmlformId}">Add
                            Encounter</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@ include file="/WEB-INF/template/footer.jsp" %>