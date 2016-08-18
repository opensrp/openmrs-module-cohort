<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="template/header.jsp" %>
<title>Manage Cohort Member Role</title> <!--set page title-->
</head>
<openmrs:htmlInclude file="/moduleResources/cohort/styles/genericPageStyle.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/pages/genericManagePageStyling.css" />
<%@ include file="template/navbar.jsp" %>

<body>
<div class="container">
    <div class="row">
        <div class="col-sm-10">
            <h4 class="heading"><span>Manage Cohort Member Role</span></h4>
            <div class="main-container">

                <div class="row">
                    <div class="col-sm-7">
                        <form id="login" method="get">
                            <div class="input-group">
                                <spring:bind path="cohortrole.name">
                                    <span class="input-group-btn">
                                        <input class="btn btn-default" type="submit" id="search" name="search" value="search">
                                    </span>
                                    <input class="search form-control" type="text" id="name" name="name" value="${status.value}" placeholder="Search Cohort Member Role"
                                           required>
                                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                                </spring:bind>
                            </div>
                        </form>
                    </div>

                    <div class="col-sm-3 ">
                        <a class="btn btn-primary" href="addRole.form" style="padding-top: 0; padding-bottom: 0">
                            <h4>
                                <i class="fa fa-plus-square-o fa-lg"></i> Add Cohort Member Role
                            </h4>
                        </a>
                    </div>

                </div>
                <br/>

                <div class="row">
                    <div class="col-sm-11">
                        <table class="table table-hover table-bordered results">

                            <c:if test="${first == false}">
                                <c:if test="${fn:length(CohortRoleList) eq 0}">
                                    <tr class='${status.index % 2 == 0 ? "oddRow" : "evenRow" }'>
                                            <p class="not-found-record">No record(s) found
                                            </p>
                                    </tr>
                                </c:if>
                            </c:if>

                            <c:if test="${fn:length(CohortRoleList) gt 0}">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th class="col-md-5 col-xs-5">Cohort Member Role Name<span class="text-muted"> (click entry to edit)</span></th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="ls" items="${CohortRoleList}" varStatus="status">
                                <tr class='${status.index % 2 == 0 ? "oddRow" : "evenRow" }'>
                                    <th scope="row">${status.index+1}</th>
                                    <td class="tdStyle">
                                        <a href="${pageContext.request.contextPath}/module/cohort/editCohortRole.form?croleid=${ls.cohortRoleId}">${ls.name}</a>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                            </c:if>
                        </table>

                    </div>
                    <br/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">
    $('#management-label-nav').addClass('active')
</script>

<!--Code Body-->


<!--Script includes for new UI -->
<%--<openmrs:htmlInclude file="/moduleResources/cohort/scripts/pages/patientSearch.js" />--%>
<!--END-->