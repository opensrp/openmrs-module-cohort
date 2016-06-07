<!--Uncomment this and comment the rest to see new UI prototype -->

<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="template/header.jsp" %>
<title>Patient Search</title> <!--set page title-->
</head>
<openmrs:htmlInclude file="/moduleResources/cohort/styles/genericPageStyle.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/patientSearch.css" />
<%@ include file="template/navbar.jsp" %>

<!--Code body here -->
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <h1>${msg}</h1>
        </div>
    </div>
</div>

<!--Script includes for new UI -->
<openmrs:htmlInclude file="/moduleResources/cohort/scripts/jquery.js" />
<openmrs:htmlInclude file="/moduleResources/cohort/bootstrap/js/bootstrap.js" />
<openmrs:htmlInclude file="/moduleResources/cohort/scripts/patientSearch.js" />
<!--END-->