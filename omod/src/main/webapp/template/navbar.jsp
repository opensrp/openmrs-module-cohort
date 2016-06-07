<body>
<div class="navbar navbar-fixed-top">
    <div class="container-fluid top-bar">
        <div class="pull-right">
            <div class="navbar-options-header" style="color: white">
                <ul class="navbar-nav pull-right">
                    <openmrs:authentication>
                        <c:if test="${authenticatedUser != null}">
                            <li class="dropdown">
                                <i  class="fa fa-user" aria-hidden="true"></i >
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#"><c:out value="${authenticatedUser.personName}" />
                                    <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/options.form"><openmrs:message code="Navigation.options"/></a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href='${pageContext.request.contextPath}/logout'><openmrs:message code="header.logout" /></a>
                                <i  class="fa fa-sign-out" aria-hidden="true"></i >
                            </li>
                        </c:if>
                        <c:if test="${authenticatedUser == null}">
                            <li>
                                <openmrs:message code="header.logged.out"/>
                            </li>
                            <li>
                                <a href='${pageContext.request.contextPath}/login.htm'><openmrs:message code="header.login"/></a>
                            </li>
                        </c:if>
                    </openmrs:authentication>
                </ul>
            </div>
        </div>
        <button class="navbar-toggle"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
        <a class="logo" href="index.html">se7en</a>
    </div>
    <div class="container-fluid main-nav clearfix">
        <div class="row">
            <div class="col-sm-2 col-sm-offset-1 nav">
                <ul class="nav admin-back">
                    <li><a href="${pageContext.request.contextPath}/admin/index.htm"> <i class="fa fa-arrow-left fa-lg" aria-hidden="true"> </i ><span class="navbar-label">Adminstration </span></a> </li>
                </ul>
            </div>
            <div class="col-sm-2 nav">
                <ul class="nav admin-back">
                    <div class="module-label">
                        <li> <i  class="fa fa-users fa-lg" aria-hidden="true"> </i ><span class="navbar-label">Cohort Module </span></li>
                    </div>
                </ul>
            </div>
            <div class="col-sm-7">
                <ul class="nav">
                    <li class="item-1"><a class="btn-default" id="cohort-dashboard-link" href="${pageContext.request.contextPath}/module/cohort/cohortDashboard.form"> <i id="cohort-dashboard-icon" class="fa fa-dashboard fa-lg" aria-hidden="true"> </i ><span class="navbar-label">Dashboard </span></a> </li>
                    <li class="item-2">
                        <div class="dropdown">
                            <a id="search-label-nav" class="dropdown-toggle" type="button" data-toggle="dropdown">
                                <i id="cohort-search-icon" class="fa fa-search fa-lg" aria-hidden="true" style="display: block;"> </i>
                                <span class="navbar-label"> Search <span class="caret"></span></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="patientSearch.form"><h4>Patients</h4></a></li>
                                <li><a href="cohortSearch.form"><h4>Cohorts</h4></a></li>
                            </ul>
                        </div>
                        <%--<a class="dropdown-toggle" id="cohort-search-link" href="index.html">--%>
                        <%--<i id="cohort-search-icon" class="fa fa-search fa-lg" aria-hidden="true"> </i>--%>
                            <%--<span class="navbar-label"> Search <span class="caret"></span></span>--%>
                        <%--</a> --%>
                    </li>
                    <li class="item-3"><a class="btn-default" id="cohort-manage-link" href="index.html"> <i id="cohort-manage-icon" class="fa fa-indent fa-lg" aria-hidden="true"> </i><span class="navbar-label"> Management </span></a> </li>
                </ul>
            </div>
        </div>

    </div>
</div>

</body>