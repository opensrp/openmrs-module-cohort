<%@ include file="/WEB-INF/template/include.jsp" %>

<meta charset="utf-8">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
    $(function () {
        $("#tabs").tabs();
    });
</script>
<div>
    <c:set var="cohortName" value="${cohort.name} (${cohort.cohortId})"/>
    <openmrs:message var="pageTitle" text="patientDashboard.title" scope="page"/>
</div>
<div id="tabs">
    <ul>
        <li><a href="#tabs-1">Manage Visits</a></li>
        <li><a href="#tabs-2">View Cohort</a></li>
        <li><a href="#tabs-3">Form Entry</a>
    </ul>
    <div id="tabs-1">
        <%@ include file="visitmanage.jsp" %>
    </div>
    <div id="tabs-2">
        <div>
            <table class="tableStlye">
                <thead>
                <tr>
                    <th class="thStyle" id="thStyle">Cohort Id</th>
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
                <c:forEach var="ls" items="${CohortL}" varStatus="status">
                    <tr class='${status.index % 2 == 0 ? "oddRow" : "evenRow" }'>
                        <td class="tdStyle">
                                ${ls.cohortId}
                        </td>
                        <td class="tdStyle">
                            <a href="${pageContext.request.contextPath}/module/cohort/editcohort.form?cid=${ls.cohortId}">${ls.name}</a>
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
                        <br/>
                        <td class="tdStyle">
                                ${ls.endDate}
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div id="tabs-3">
        <a href="htmlFormEntry.form?coh=${cohort.cohortId}&htmlformId=1">Neurological recovery study</a>
        <br/>
        <a href="htmlFormEntry.form?coh=${cohort.cohortId}&htmlformId=2">Family Study</a>
        <br/>
    </div>
</div>