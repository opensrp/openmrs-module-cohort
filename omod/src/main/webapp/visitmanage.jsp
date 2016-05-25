<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="template/localHeader.jsp" %>
<openmrs:htmlInclude file="/scripts/calendar/calendar.js"/>
<h3>Manage Cohort Visits</h3>
<a href="addvisit.form" id="link">Add Visit</a>
<br/>
<div>
    <b class="boxHeader">Find Cohort Visits</b>
    <div class="box">
        <!--   <div class="searchWidgetContainer" id="createCohort">
        </div>
-->
        <form id="form1" method="get">
            <input type="hidden" name="cid" value="${cohortvisit.cohort.cohortId}"/>
            Search Cohort Visit By Id:<input type="text" id="visitId" name="visitId" value="${status.value}"
                                             placeholder="Search..."
                                             required>
            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            <input type="submit" id="search" name="search" value="search">
        </form>
    </div>
</div>
<div class="box">
    <div>
        <table class="tableStlye">
            <thead>
            <tr>
                <th class="thStyle" id="thStyle">Visit Id</th>
                <th class="thStyle" id="thStyle">Location</th>
                <th class="thStyle" id="thStyle">Start Date</th>
                <th class="thStyle" id="thStyle">End Date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="vs" items="${CohortList}" varStatus="status">
                <tr class='${status.index % 2 == 0 ? "oddRow" : "evenRow" }'>
                    <td class="tdStyle">
                        <a href="${pageContext.request.contextPath}/module/cohort/editvisit.form?visitid=${vs.cohortVisitId}">${ls.cohortVisitId}</a>
                    </td>
                    <td class="tdStyle">
                            ${vs.location}
                    </td>
                    <td class="tdStyle">
                            ${vs.startDate}
                    </td>
                    <td class="tdStyle">
                            ${vs.endDate}
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@ include file="/WEB-INF/template/footer.jsp" %>