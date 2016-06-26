<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page import="org.openmrs.web.WebConstants" %>
<%
    pageContext.setAttribute("msg", session.getAttribute(WebConstants.OPENMRS_MSG_ATTR));
    pageContext.setAttribute("msgArgs", session.getAttribute(WebConstants.OPENMRS_MSG_ARGS));
    pageContext.setAttribute("err", session.getAttribute(WebConstants.OPENMRS_ERROR_ATTR));
    pageContext.setAttribute("errArgs", session.getAttribute(WebConstants.OPENMRS_ERROR_ARGS));
    session.removeAttribute(WebConstants.OPENMRS_MSG_ATTR);
    session.removeAttribute(WebConstants.OPENMRS_MSG_ARGS);
    session.removeAttribute(WebConstants.OPENMRS_ERROR_ATTR);
    session.removeAttribute(WebConstants.OPENMRS_ERROR_ARGS);
%>

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:c="http://java.sun.com/jsp/jstl/core"
      xmlns:jsp="http://java.sun.com/JSP/Page"
      xmlns:spring="http://www.springframework.org/tags"
      xmlns:openmrs="urn:jsptld:/WEB-INF/taglibs/openmrs.tld">
<head>
    <openmrs:htmlInclude file="/openmrs.js" />
    <openmrs:htmlInclude file="/scripts/openmrsmessages.js" appendLocale="true" />
    <openmrs:htmlInclude file="/dwr/engine.js" />
    <openmrs:htmlInclude file="/scripts/html-sanitizer-min.js" />
    <openmrs:htmlInclude file="/dwr/interface/DWRAlertService.js" />
    
    <openmrs:htmlInclude file="/scripts/jquery/jquery.min.js" />
    <openmrs:htmlInclude file="/scripts/jquery-ui/js/jquery-ui.custom.min.js" />
    <openmrs:htmlInclude file="/scripts/jquery-ui/js/jquery-ui-timepicker-addon.js" />
    <openmrs:htmlInclude file="/scripts/jquery-ui/js/jquery-ui-datepicker-i18n.js" />
    <openmrs:htmlInclude file="/scripts/jquery-ui/js/jquery-ui-timepicker-i18n.js" />
    <link href="<openmrs:contextPath/>/scripts/jquery-ui/css/<spring:theme code='jqueryui.theme.name' />/jquery-ui.custom.css" type="text/css" rel="stylesheet" />
    

    <!--Style includes for new UI -->
    <openmrs:htmlInclude file="/moduleResources/cohort/bootstrap/css/bootstrap.css" />
    <openmrs:htmlInclude file="/moduleResources/cohort/font-awesome/css/font-awesome.css" />
    <openmrs:htmlInclude file="/moduleResources/cohort/styles/navbar.css" />
    <!--END-->
    
    <link rel="shortcut icon" type="image/ico" href="<openmrs:contextPath/><spring:theme code='favicon' />">
    <link rel="icon" type="image/png" href="<openmrs:contextPath/><spring:theme code='favicon.png' />">

    <c:choose>
        <c:when test="${!empty pageTitle}">
            <title>${pageTitle}</title>
        </c:when>
        <c:otherwise>
            <title><openmrs:message code="openmrs.title"/></title>
        </c:otherwise>
    </c:choose>


    <script type="text/javascript">
        <c:if test="${empty DO_NOT_INCLUDE_JQUERY}">
            var $j = jQuery.noConflict();
        </c:if>
        /* variable used in js to know the context path */
        var openmrsContextPath = '${pageContext.request.contextPath}';
        var dwrLoadingMessage = '<openmrs:message code="general.loading" />';
        var jsDateFormat = '<openmrs:datePattern localize="false"/>';
        var jsTimeFormat = '<openmrs:timePattern format="jquery" localize="false"/>';
        var jsLocale = '<%= org.openmrs.api.context.Context.getLocale() %>';

        /* prevents users getting false dwr errors msgs when leaving pages */
        var pageIsExiting = false;
        if (typeof(jQuery) != "undefined")
            jQuery(window).bind('beforeunload', function () { pageIsExiting = true; } );

        var handler = function(msg, ex) {
            if (!pageIsExiting) {
                var div = document.getElementById("openmrs_dwr_error");
                div.style.display = ""; // show the error div
                var msgDiv = document.getElementById("openmrs_dwr_error_msg");
                msgDiv.innerHTML = '<openmrs:message code="error.dwr"/>' + " <b>" + msg + "</b>";
            }

        };
        dwr.engine.setErrorHandler(handler);
        dwr.engine.setWarningHandler(handler);
    </script>

    <openmrs:extensionPoint pointId="org.openmrs.headerFullIncludeExt" type="html" requiredClass="org.openmrs.module.web.extension.HeaderIncludeExt">
        <c:forEach var="file" items="${extension.headerFiles}">
            <openmrs:htmlInclude file="${file}" />
        </c:forEach>
    </openmrs:extensionPoint>
