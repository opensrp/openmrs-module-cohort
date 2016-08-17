
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container top-nav">
        
        <ul class="nav navbar-nav navbar-left">
            <li>
                <a class="logo" href="/openmrs/index.htm"></a>
            </li>
        </ul>
        
        <ul class="nav navbar-nav navbar-right navbar-right-up">
            <openmrs:authentication>
                <c:if test="${authenticatedUser != null}">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-user" aria-hidden="true"></i><c:out
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
                                code="header.logout"/>  <i class="fa fa-sign-out" aria-hidden="true"></i></a>
                    </li>
                </c:if>
                <c:if test="${authenticatedUser == null}">
                    <li>
                        <p><openmrs:message code="header.logged.out"/></p>
                    </li>
                    <li>
                        <a href='${pageContext.request.contextPath}/login.htm'><openmrs:message
                                code="header.login"/></a>
                    </li>
                </c:if>
            </openmrs:authentication>
        </ul>
    </div>
    <div class="color-wrapper">
        <div class="container-fluid">
            <div class="col-sm-3">
                <div class="navbar-header">
                    <p><i class="fa fa-users" aria-hidden="true"> </i> <span>Cohort Module </span>
                    </p>
                </div>
            </div>

            <div class="col-sm-6 navbar-navx">
                <ul class="nav navbar-nav">
                    <li class="main-links">
                        <a class="btn-default" id="cohort-dashboard-link"
                           href="${pageContext.request.contextPath}/module/cohort/cohortDashboard.form">
                            <i id="cohort-dashboard-icon" class="fa fa-dashboard fa-lg" aria-hidden="true"> </i>
                                <span
                                        class="navbar-label">Dashboard
                                </span>
                        </a>
                    </li>

                    <li class="main-links">
                        <a class="btn-default" id="search-label-nav"
                           type="button" data-toggle="dropdown">
                            <i class="fa fa-search fa-lg" aria-hidden="true"> </i>
                                <span class="navbar-label">
                                        <span class="navbar-label"> Search <span class="caret"></span></span>
                                </span>
                            <ul class="dropdown-menu">
                                <li><a href="patientSearch.form"><h4>Patients</h4></a></li>
                                <li><a href="cohortSearch.form"><h4>Cohorts</h4></a></li>
                            </ul>
                        </a>
                    </li>

                    <li class="main-links">
                        <a class="btn-default" id="management-label-nav"
                           type="button" data-toggle="dropdown">
                            <i class="fa fa-indent fa-lg" aria-hidden="true"> </i>
                                <span class="navbar-label">
                                        <span class="navbar-label"> Management <span class="caret"></span></span>
                                </span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="dropdown-header"><h4>Manage Metadata</h4></li>
                            <li><a href="manageCohortType.form"><h5>Cohort Type</h5></a></li>
                            <li><a href="manageCohortAttributesType.form"><h5>Cohort Attribute Type</h5></a></li>
                            <li><a href="manageCohortMemberAttributeType.form"><h5>Cohort Member Attribute Type</h5></a></li>
                            <li><a href="manageCohortProgram.form"><h5>Cohort Programs</h5></a></li>
                            <li><a href="manageCohortRole.form"><h5>Cohort Member Role</h5></a></li>
                            <li class="divider">
                        </ul>
                    </li>
                </ul>
            </div>


            <div class="col-sm-3 navbar-navx">
                <ul class="nav navbar-nav">
                    <li class="main-links">
                        <a class="btn-default" id="admin-button"
                           href="${pageContext.request.contextPath}/admin">
                            <i class="fa fa-arrow-left fa-lg" aria-hidden="true"> </i>
                                <span
                                        class="navbar-label">Admin
                                </span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col-sm-12">

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

    </div>
    </div>
</div>
