<!--Uncomment this and comment the rest to see new UI prototype -->

<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="template/header.jsp" %>
<openmrs:htmlInclude file="/moduleResources/cohort/styles/manageCohorts.css" />
<title><openmrs:message code="openmrs.title"/></title> <!--set page title-->
</head>
<%@ include file="template/navbar.jsp" %>

<!--Code body here -->

<section class="top-options">
    <div class="container">
        <div class="row">
            <div class="col-xs-4">
                <form class="form-inline" id="login" method="get">
                    <input class="form-control" type="submit" id="search" name="search" value="search">
                    <spring:bind path="cohortmodule.name">
                        <input class="form-control cohort-search" type="text" id="name" name="name" value="${status.value}" placeholder="Cohort Search"
                               required>
                        <c:if test="${status.errorMessage != ''}">
                            <span class="error">${status.errorMessage}</span>
                        </c:if>
                    </spring:bind>
                </form>
            </div>
            <div class="col-xs-4">
                <div class="add-cohorts-button cohort-box-container btn-default">
                    <a href="addcohort.form"><i class="fa fa-plus-square-o fa-2x" aria-hidden="true"></i>
                        <span><spring:message code="cohort.createcohort"/> </a>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="add-cohorts-button group-cohort-box-container btn-default">
                    <a href="groupcohort.form"><i class="fa fa-plus-square-o fa-2x" aria-hidden="true"></i>
                        <span>Create Group Cohort </span></a>
                </div>
            </div>
        </div>
    </div>
</section>

<%--<c:if test="${cohortExists == true}">--%>

<c:forEach var="ls" items="${CohortList}" varStatus="status">
    <section class="successful-search-body">
        <div class="container" >
            <div class="row">
                <div class="col-xs-4">
                    <div class="table-container details-container">
                        <table class="table">
                            <thead class="thead-inverse">
                            <tr>
                                <th><h4>Details</h4></th>
                                <th ></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <th scope="row">Cohort Name</th>
                                <td><a href="${pageContext.request.contextPath}/module/cohort/editcohort.form?cid=${ls.cohortId}">${ls.name}</a></td>
                            </tr>
                            <tr>
                                <th scope="row">Cohort Program</th>
                                <td>${ls.cohortProgram.name}</td>
                            </tr>
                            <tr>
                                <th scope="row">Location</th>
                                <td>${ls.clocation}</td>
                            </tr>
                            <tr>
                                <th scope="row">Start Date</th>
                                <td>${ls.startDate}</td>
                            </tr>
                            <tr>
                                <th scope="row">End Date</th>
                                <td>${ls.endDate}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-xs-3">
                    <div class="cohort-name">
                        <h2>
                            <span>${ls.cohortType.name}</span>
                        </h2>
                    </div>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="cohort-description-heading">
                                <h4>Description</h4>
                            </div>
                            <div class="cohort-description-content">
                                    ${ls.description}
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-3">

                </div>
                <div class="col-xs-2">
                    <div class="box-1-right">
                        <div class="add-cohort-attributes-and-encounters-button add-cohort-attributes-and-encounters-box-container btn-default">
                            <a href="${pageContext.request.contextPath}/module/cohort/addcohortattributes.form?ca=${ls.cohortId}"><i class="fa fa-dot-circle-o fa-2x" aria-hidden="true"></i>
                                <span>Add Attribute</span></a>
                        </div>
                    </div>
                    <div class="box-2-right">
                        <div class="add-cohort-attributes-and-encounters-button add-cohort-attributes-and-encounters-box-container btn-default">
                            <a  href="${pageContext.request.contextPath}/module/cohort/htmlFormEntry.form?coh=${ls.cohortId}&htmlformId=${htmlformId}"><i class="fa fa-check-square-o fa-2x" aria-hidden="true"></i>
                                <span>Add Encounter</span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</c:forEach>
<%--</c:if>--%>



<!--Script includes for new UI -->
<openmrs:htmlInclude file="/moduleResources/cohort/scripts/jquery.js" />
<openmrs:htmlInclude file="/moduleResources/cohort/bootstrap/js/bootstrap.js" />
<!--END-->


<%--<!--LEGACY CODE -->--%>


<%--<%@ include file="/WEB-INF/template/include.jsp" %>--%>
<%--<%@ include file="/WEB-INF/template/header.jsp" %>--%>

<%--<%@ include file="template/localHeader.jsp" %>--%>
<%--<h3>Manage Cohort </h3>--%>
<%--<a href="addcohort.form"><spring:message code="cohort.createcohort"/></a>--%>
<%--<a href="groupcohort.form">Create Group Cohort</a>--%>
<%--<br/>--%>
<%--<br/>--%>
<%--<div>--%>
<%--<b class="boxHeader"><spring:message code="cohort.findcohort"/></b>--%>
<%--<div class="box">--%>
<%--<!--   <div class="searchWidgetContainer" id="createCohort">--%>
<%--</div>--%>
<%---->--%>
<%--<form id="login" method="get">--%>
<%--<spring:bind path="cohortmodule.name">--%>
<%--<spring:message--%>
<%--code="cohort.searchcohorts"/> :<input type="text" id="name" name="name" value="${status.value}" placeholder="Search..."--%>
<%--required>--%>
<%--<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>--%>
<%--</spring:bind>--%>
<%--<input type="submit" id="search" name="search" value="search">--%>
<%--</form>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="box">--%>
<%--<div>--%>
<%--<table class="tableStlye">--%>
<%--<thead>--%>
<%--<tr>--%>
<%--<th class="thStyle" id="thStyle">Cohort Name</th>--%>
<%--<th class="thStyle" id="thStyle">Description</th>--%>
<%--<th class="thStyle" id="thStyle">Cohort Type</th>--%>
<%--<th class="thStyle" id="thStyle">Cohort Program</th>--%>
<%--<th class="thStyle" id="thStyle">Location</th>--%>
<%--<th class="thStyle" id="thStyle">Start Date</th>--%>
<%--<th class="thStyle" id="thStyle">End Date</th>--%>
<%--</tr>--%>
<%--</thead>--%>
<%--<tbody>--%>
<%--<c:forEach var="ls" items="${CohortList}" varStatus="status">--%>
<%--<tr class='${status.index % 2 == 0 ? "oddRow" : "evenRow" }'>--%>
<%--<td class="tdStyle">--%>
<%--<a href="${pageContext.request.contextPath}/module/cohort/editcohort.form?cid=${ls.cohortId}">${ls.name}</a>--%>
<%--</td>--%>
<%--<td class="tdStyle">--%>
<%--${ls.description}--%>
<%--</td>--%>
<%--<td class="tdStyle">--%>
<%--${ls.cohortType.name}--%>
<%--</td>--%>
<%--<td class="tdStyle">--%>
<%--${ls.cohortProgram.name}--%>
<%--</td>--%>
<%--<td class="tdStyle">--%>
<%--${ls.clocation}--%>
<%--</td>--%>
<%--<td class="tdStyle">--%>
<%--${ls.startDate}--%>
<%--</td>--%>
<%--<td class="tdStyle">--%>
<%--${ls.endDate}--%>
<%--</td>--%>
<%--<td class="tdStyle">--%>
<%--<a href="${pageContext.request.contextPath}/module/cohort/addcohortattributes.form?ca=${ls.cohortId}">Add--%>
<%--Attribute</a>--%>
<%--</td>--%>
<%--<td class="tdStyle">--%>
<%--<a href="${pageContext.request.contextPath}/module/cohort/cpatients.form?cpid=${ls.cohortId}">Add--%>
<%--Cohort Member</a>--%>
<%--</td>--%>
<%--<td class="tdStyle">--%>
<%--<a href="${pageContext.request.contextPath}/module/cohort/htmlFormEntry.form?coh=${ls.cohortId}&htmlformId=${htmlformId}">Add--%>
<%--Encounter</a>--%>
<%--</td>--%>
<%--</tr>--%>
<%--</c:forEach>--%>
<%--</tbody>--%>
<%--</table>--%>
<%--</div>--%>
<%--</div>--%>
<%--<%@ include file="/WEB-INF/template/footer.jsp" %>--%>