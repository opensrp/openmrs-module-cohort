<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="template/header.jsp" %>
<openmrs:htmlInclude file="/moduleResources/cohort/styles/pages/cohortDashboard.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/genericPageStyle.css" />
<title>Cohort Dashboard</title> <!--set page title-->
</head>
<%@ include file="template/navbar.jsp" %>

<!--Code body here -->

<body>
<section class="top-options">
    <div class="container">
        <div class="row" >
            <div class="col-sm-6" style="padding-left: 0!important;">
                <form class="form-inline" id="login" method="get">
                    <%--<label for="search">Search</label>--%>
                    <input class="btn btn-default form-control" type="submit" id="search" name="search" value="search">
                    <spring:bind path="cohortmodule.name">
                        <input class="form-control cohort-search" type="text" id="name" name="name" value="${status.value}" placeholder="Cohort Search"
                               required>
                        <c:if test="${status.errorMessage != ''}">
                            <span class="error">${status.errorMessage}</span>
                        </c:if>
                    </spring:bind>
                </form>
            </div>
            <div class="col-sm-2 create-cohort-button">
                <a class="btn btn-primary" href="addCohort.form">
                    <i class="fa fa-plus fa-2x" aria-hidden="true"></i>
                    <span>Create Cohort</span>
                </a>
            </div>
            <div class="col-sm-3 create-group-cohort-button">
                <a class="btn btn-primary" href="groupcohort.form">
                    <i class="fa fa-plus fa-2x" aria-hidden="true"></i>
                    <span>Create Cohort Group</span>
                </a>
            </div>
        </div>
    </div>
</section>

<c:if test="${cohortExists == 'true'}">
    <c:forEach var="cohort" items="${cohortList}" varStatus="status">
        <section class="successful-search-body">
            <div class="container">
                <div class="row">
                    <h4 class="cohort-name">
                        <b style="font-size: 130%">${cohort.name}</b>
                    </h4>
                    <div class="col-sm-11" style="background: white; border: 1px solid #737373; padding: 30px 30px 30px 30px">
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="table-container details-container" style="border: 1px solid #ddd;">
                                    <table class="table">
                                        <thead class="thead-inverse">
                                        <tr>
                                            <th><h4>Details</h4></th>
                                            <th ></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <th scope="row">Program</th>
                                            <td>${cohort.cohortProgram.name}</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Location</th>
                                            <td>${cohort.clocation}</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Start Date</th>
                                            <%--<td>${fn:substring(cohort.startDate, 0, 10)}</td>--%>
                                            <td><openmrs:formatDate date="${cohort.startDate}"/></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">End Date</th>
                                            <td><openmrs:formatDate date="${cohort.endDate}"/></td>
                                        </tr>
                                        <tr>
                                            <th scope="row"> Description</th>
                                            <td>
                                                <div style="max-height: 100px; overflow-y: scroll">
                                                        ${cohort.description}
                                                </div>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>


                            <div class="col-sm-4" style="margin-left: 30px; margin-right: 20px; padding-right: 10px">
                                <div style="height: 325px; overflow-y: scroll; border: 1px solid #ccc">
                                    <table class="table">
                                        <thead class="thead-inverse">
                                        <tr>
                                            <th><h4>Members</h4></th>
                                            <th ></th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                        <c:forEach var="member" items="${memberList[status.index]}" varStatus="memberStatus">
                                            <tr>
                                                <td>
                                                    <strong><span>${memberStatus.index+1}.</span>
                                                        <a href="/openmrs/patientDashboard.form?patientId=${member.person.personId}" target="_blank">${member.person.givenName} ${member.person.familyName}</a>
                                                    </strong>
                                                </td>
                                                
                                                <td>
                                                    <div class="dropdown">
                                                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                                            Details
                                                            <span class="caret"></span>
                                                        </button>
                                                        <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu1">
                                                            <li>
                                                                <span class="person-attribute-label"><strong>Member Id</strong>:</span>
                                                                <span class="person-detail-label">${member.cohortMemberId}</span>
                                                            </li>

                                                            <li role="separator" class="divider"></li>
                                                            

                                                            <li>
                                                                <span class="person-attribute-label"><strong>Start Date</strong>:</span>
                                                                <span class="person-detail-label"><openmrs:formatDate date="${member.startDate}"/></span>
                                                            </li>

                                                            <li role="separator" class="divider"></li>

                                                            <li>
                                                                <span class="person-attribute-label"><strong>End Date</strong>:</span>
                                                                <span class="person-detail-label"><openmrs:formatDate date="${member.endDate}"/></span>
                                                            </li>

                                                            <li role="separator" class="divider"></li>
                                                            
                                                            <li>
                                                                <span class="person-attribute-label"><strong>Role</strong>:</span>
                                                                <span class="person-detail-label">${member.role}</span>
                                                            </li>
                                                            
                                                        </ul>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <div class="col-sm-2">
                                <div class="box-right">
                                    <a class="btn btn-default" href="${pageContext.request.contextPath}/module/cohort/addCohortAttributes.form?ca=${cohort.cohortId}"><i class="fa fa-dot-circle-o fa-2x" aria-hidden="true"></i>
                                        <span class="desc-1">Add Attribute</span></a>
                                </div>
                                <div class="box-right">
                                    <a class="btn btn-default" href="${pageContext.request.contextPath}/module/cohort/htmlFormEntry.form?coh=${cohort.cohortId}&htmlformId=${htmlformId}"><i class="fa fa-check-square-o fa-2x" aria-hidden="true"></i>
                                        <span class="desc-2">Add Encounter</span></a>
                                </div>
                                <div class="box-right">
                                    <a class="btn btn-default" href="${pageContext.request.contextPath}/module/cohort/cPatients.form?cpid=${cohort.cohortId}">
                                        <i class="fa fa-user-plus fa-2x" aria-hidden="true"></i><span class="desc-3"> Add Member</span></a>
                                </div>

                                <div class="box-right">
                                    <a class="btn btn-default" href="${pageContext.request.contextPath}/module/cohort/editCohort.form?cid=${cohort.cohortId}">
                                        <i class="fa fa-edit fa-2x" aria-hidden="true"></i><span class="desc-3">&nbsp;

Edit Cohort &nbsp;

</span></a>
                                </div>
                            </div>
                        </div>
                            <%--<c:forEach var="member" items="${memberList[status.index]}" varStatus="memberStatus">--%>
                            <%--<div class="item">--%>
                            <%--<div class="col-sm-3">--%>
                    </div>
                </div>
            </div>
            <br>
        </section>
    </c:forEach>
</c:if>

<c:if test="${cohortExists == 'false'}">
    <div class="container">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1 not-found-container">
                <h4 class="not-found">No cohorts currently exist with this name!</h4>
            </div>
        </div>
    </div>
</c:if>

</body>

<openmrs:htmlInclude file="/moduleResources/cohort/scripts/pages/cohortDashboard.js" />


<script type="text/javascript">
    $('#cohort-dashboard-link').addClass('active')
</script>
<!--END-->