<spring:htmlEscape defaultHtmlEscape="true"/>
<ul id="menu">
    <li class="first"><a
            href="${pageContext.request.contextPath}/admin"><spring:message
            code="admin.title.short"/></a></li>

    <li
            <c:if test='<%= request.getRequestURI().contains("/cohortDashboard") %>'>class="active"</c:if>>
        <a
                href="${pageContext.request.contextPath}/module/cohort/cohortDashboard.form">Manage Cohorts</a>
    </li>
    <li
            <c:if test='<%= request.getRequestURI().contains("/configurecohortmetadata") %>'>class="active"</c:if>>
        <a href="${pageContext.request.contextPath}/module/cohort/configurecohortmetadata.form">Configure Cohort
            Metadata</a>
    </li>
    <li
            <c:if test='<%= request.getRequestURI().contains("/cohortenc") %>'>class="active"</c:if>>
        <a href="${pageContext.request.contextPath}/module/cohort/cohortenc.form">Manage Cohort Encounters</a>
    </li>
    <li
            <c:if test='<%= request.getRequestURI().contains("/visitmanage") %>'>class="active"</c:if>>
        <a href="${pageContext.request.contextPath}/module/cohort/visitmanage.form">Manage Cohort Visit</a>
    </li>

    <li
            <c:if test='<%= request.getRequestURI().contains("/cohortobs") %>'>class="active"</c:if>>
        <a href="${pageContext.request.contextPath}/module/cohort/cohortobs.form">Manage Cohort Observations</a>
    </li>

    <!-- Add further links here -->
</ul>
<h2>
    <spring:message code="cohort.title"/>
</h2>
