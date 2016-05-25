<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="template/localHeader.jsp" %>

<h3>Configure Cohort MetaData</h3>
<div class="container">
    <ul>
        <li><a href="cohorttypemanage.form">Manage Cohort Types</a></li>
        <li><a href="cohortattributestypemanage.form">Manage Cohort Attribute Types</a></li>
        <li><a href="cohortmemberattributetypemanage.form">Manage Cohort Member Attribute Type</a>
        <li><a href="cohortprogrammanage.form">Manage Cohort Programs</a></li>
        <li><a href="cohortrolemanage.form">Manage Cohort Role</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/visits/visitType.list">Visit Types</a></li>
    </ul>
</div>
<%@ include file="/WEB-INF/template/footer.jsp" %>