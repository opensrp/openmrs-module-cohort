<!--Uncomment this and comment the rest to see new UI prototype -->

<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="**PATH TO header.jsp**" %>
<title><openmrs:message code="openmrs.title"/></title> <!--set page title-->
</head>
<%@ include file="**PATH TO navbar.jsp**" %>

<!--Code body here -->


<!--Script includes for new UI -->
<openmrs:htmlInclude file="/moduleResources/cohort/scripts/jquery.js" />
<openmrs:htmlInclude file="/moduleResources/cohort/bootstrap/js/bootstrap.js" />
<!--END-->