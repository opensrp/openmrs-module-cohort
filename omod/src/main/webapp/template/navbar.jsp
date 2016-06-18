<div class="navbar navbar-fixed-top">
    <div class="container-fluid top-bar">
        <div class="pull-right">
            <div class="navbar-options-header" style="color: white">
                <ul class="navbar-nav pull-right">
                    <openmrs:authentication>
                        <c:if test="${authenticatedUser != null}">
                            <li class="dropdown">
                                <i class="fa fa-user" aria-hidden="true"></i>
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#"><c:out
                                        value="${authenticatedUser.personName}"/>
                                    <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/options.form"><openmrs:message
                                                code="Navigation.options"/></a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href='${pageContext.request.contextPath}/logout'><openmrs:message
                                        code="header.logout"/></a>
                                <i class="fa fa-sign-out" aria-hidden="true"></i>
                            </li>
                        </c:if>
                        <c:if test="${authenticatedUser == null}">
                            <li>
                                <openmrs:message code="header.logged.out"/>
                            </li>
                            <li>
                                <a href='${pageContext.request.contextPath}/login.htm'><openmrs:message
                                        code="header.login"/></a>
                            </li>
                        </c:if>
                    </openmrs:authentication>
                </ul>
            </div>
        </div>
        <button class="navbar-toggle"><span class="icon-bar"></span><span class="icon-bar"></span><span
                class="icon-bar"></span></button>
        <a class="logo" href="index.html">se7en</a>
    </div>
    <div class="container-fluid main-nav clearfix">
        <div class="row">
            <div class="col-sm-2 col-sm-offset-1 nav">
                <ul class="nav admin-back">
                    <li><a href="${pageContext.request.contextPath}/admin/index.htm"> <i class="fa fa-arrow-left fa-lg"
                                                                                         aria-hidden="true"> </i><span
                            class="navbar-label">Adminstration </span></a></li>
                </ul>
            </div>
            <div class="col-sm-2 nav">
                <ul class="nav admin-back">
                    <div class="module-label">
                        <li><i class="fa fa-users fa-lg" aria-hidden="true"> </i><span class="navbar-label">Cohort Module </span>
                        </li>
                    </div>
                </ul>
            </div>
            <div class="col-sm-7">
                <ul class="nav">
                    <li class="item-1"><a class="btn-default" id="cohort-dashboard-link"
                                          href="${pageContext.request.contextPath}/module/cohort/cohortDashboard.form">
                        <i id="cohort-dashboard-icon" class="fa fa-dashboard fa-lg" aria-hidden="true"> </i><span
                            class="navbar-label">Dashboard </span></a></li>
                    <li class="item-2">
                        <div class="dropdown">
                            <a id="search-label-nav" class="dropdown-toggle" type="button" data-toggle="dropdown">
                                <i id="cohort-search-icon" class="fa fa-search fa-lg" aria-hidden="true"
                                   style="display: block;"> </i>
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
                    <li class="item-3">
                        <div class="dropdown">
                            <a id="management-label-nav" class="dropdown-toggle" type="button" data-toggle="dropdown">
                                <i id="cohort-manage-icon" class="fa fa-indent fa-lg" aria-hidden="true"
                                   style="display: block;"> </i>
                                <span class="navbar-label"> Management <span class="caret"></span></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="addCohort.form"><h4>Add Cohort</h4></a></li>
                                <li><a href="cohortSearch.form"><h4>Add Group Cohort</h4></a></li>

                                <li role="separator" class="divider"></li>

                                <li><a href="patientSearch.form"><h4>Edit Cohort</h4></a></li>

                                <li role="separator" class="divider"></li>

                                <li><a href="patientSearch.form"><h4>Cohort Metadata</h4></a></li>

                                <li role="separator" class="divider"></li>

                                <li><a href="patientSearch.form"><h4>Cohort Encounters</h4></a></li>
                                <li><a href="patientSearch.form"><h4>Cohort Observations</h4></a></li>


                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
        </div>

    </div>
</div>

<openmrs:forEachAlert>
<c:if test="${varStatus.first}">
<div id="alertOuterBox"></c:if>
    <c:if test="${varStatus.last}">
        <div id="alertBar">
            <img src="${pageContext.request.contextPath}/images/alert.gif" align="center"
                 alt='<openmrs:message htmlEscape="false" code="Alert.unreadAlert"/>'
                 title='<openmrs:message htmlEscape="false" code="Alert.unreadAlert"/>'/>
            <c:if test="${varStatus.count == 1}"><openmrs:message htmlEscape="false"
                                                                  code="Alert.unreadAlert"/></c:if>
            <c:if test="${varStatus.count != 1}"><openmrs:message htmlEscape="false"
                                                                  code="Alert.unreadAlerts"
                                                                  arguments="${varStatus.count}"/></c:if>
            <c:if test="${alert.satisfiedByAny}"><i class="smallMessage">(<openmrs:message
                    code="Alert.mark.satisfiedByAny"/>)</i></c:if>
            <a href="#markAllAsRead" onclick="return markAllAlertsRead(this)" HIDEFOCUS
               class="markAllAsRead">
                <img src="${pageContext.request.contextPath}/images/markRead.gif"
                     alt='<openmrs:message code="Alert.markAllAsRead"/>'
                     title='<openmrs:message code="Alert.markAllAlertsAsRead"/>'/> <span
                    class="markAllAsRead"><openmrs:message code="Alert.markAllAsRead"/></span>
            </a>
        </div>
    </c:if>
    </openmrs:forEachAlert>
    <openmrs:forEachAlert>
    <c:if test="${varStatus.first}">
    <div id="alertInnerBox"></c:if>
        <div class="alert">
            <a href="#markRead" onClick="return markAlertRead(this, '${alert.alertId}')" HIDEFOCUS
               class="markAlertRead">
                <img src="${pageContext.request.contextPath}/images/markRead.gif"
                     alt='<openmrs:message code="Alert.mark"/>'
                     title='<openmrs:message code="Alert.mark"/>'/> <span
                    class="markAlertText"><openmrs:message code="Alert.markAsRead"/></span>
            </a>
                ${alert.text} ${alert.dateToExpire}
        </div>
        <c:if test="${varStatus.last}">
    </div>
</div>
</c:if>
</div>
</openmrs:forEachAlert>

<c:if test="${msg != null}">
<div id="openmrs_msg" style="background: lightyellow; border: 1px dashed; margin: 10px 0 20px 0; padding-left: 1%; padding-top: 4px;">
    <i class="fa fa-info-circle fa-lg" aria-hidden="true">
        <span style="color: darkgreen;">
        <openmrs:message code="${msg}" text="${msg}" arguments="${msgArgs}" htmlEscape="false"/>
    </span>
    </i>
</div>
</c:if>

<c:if test="${err != null}">
<div id="openmrs_error" style="background: lightyellow; border: 1px dashed; margin: 10px 0 20px 0; padding-left: 1%; padding-top: 4px;">
    <i class="fa fa-info-circle fa-lg" aria-hidden="true"></i>
    <span style="color: darkred;">
        <openmrs:message code="${err}" text="${err}" arguments="${errArgs}" htmlEscape="false"/>
    </span>
</div>
</c:if>

<div id="openmrs_dwr_error" style="display:none" class="error">
    <div id="openmrs_dwr_error_msg"></div>
    <div id="openmrs_dwr_error_close" class="smallMessage">
        <i><openmrs:message code="error.dwr.stacktrace"/></i>
        <a href="#" onclick="this.parentNode.parentNode.style.display='none'"><openmrs:message
                code="error.dwr.hide"/></a>
    </div>
</div>


