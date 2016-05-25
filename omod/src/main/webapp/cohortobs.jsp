<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="template/localHeader.jsp" %>

<openmrs:htmlInclude file="/scripts/calendar/calendar.js"/>
<h3>Manage Cohort Observations</h3>
<a href="addobs.form" id="link">Add Observations</a>
<br/>
<div>
    <b class="boxHeader">Find Cohort Observations</b>
    <div class="box">
        <!--   <div class="searchWidgetContainer" id="createCohort">
        </div>
-->
        <form id="form1" method="get">
            <spring:bind path="cohortobs.obsId">
                Search Cohort Observations by Id:<input type="text" id="obsId" name="obsId" value="${status.value}" placeholder="Search..."
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
                <th class="thStyle" id="thStyle">Observation Id</th>
                <th class="thStyle" id="thStyle">Date</th>
                <th class="thStyle" id="thStyle">Location</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ls" items="${CohortList}" varStatus="status">
                <tr class='${status.index % 2 == 0 ? "oddRow" : "evenRow" }'>
                    <td class="tdStyle">
                        <a href="${pageContext.request.contextPath}/module/cohort/editobs.form?obid=${ls.obsId}">${ls.obsId}</a>
                    </td>
                    <td class="tdStyle">
                            ${ls.obsDateTime}
                    </td>
                    <td class="tdStyle">
                            ${ls.location}
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@ include file="/WEB-INF/template/footer.jsp" %>
