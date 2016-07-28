<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="template/header.jsp" %>
<title>Cohort Search</title> <!--set page title-->
</head>
<openmrs:htmlInclude file="/moduleResources/cohort/styles/genericPageStyle.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/pages/cohortSearch.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/libraries/jquery.typeahead.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/libraries/jquery-ui.css" />
<%@ include file="template/navbar.jsp" %>

<body>
<div class="container">
    <div class="col-sm-5">
        <div class="cohort-form-heading">
            <h4>Enter Cohort Details to Search</h4>
        </div>
        <form class="form-container" method="post">
            <ul class="list-styled">
                <li>
                    <fieldset class="form-group">
                        <h4>Cohort Name</h4>
                        <div class="typeahead__container">
                                <span class="typeahead__query">
                                        <input name="cohortName" id="cohortName" class="form-control js-typeahead-input" type="search" autofocus autocomplete="off" placeholder="Enter Cohort Name">
                                </span>
                        </div>
                    </fieldset>
                </li>


                <li>
                    <fieldset class="form-group">
                        <h4>Cohort Program</h4>
                        <div class="typeahead__container">
                                <span class="typeahead__query">
                                   <input name="cohortProgram" id="cohortProgram" class="form-control js-typeahead-input" type="search" autofocus autocomplete="off" placeholder="Enter Cohort Program">
                                </span>
                        </div>
                    </fieldset>
                </li>

                <li>
                    <fieldset class="form-group">
                        <h4>Cohort Head</h4>
                        <div class="typeahead__container">
                                <span class="typeahead__query">
                                   <input name="cohortHead" id="cohortHead" class="form-control js-typeahead-input" type="search" autofocus autocomplete="off" placeholder="Enter Cohort Head">
                                </span>
                        </div>
                    </fieldset>
                </li>
                
                <li>
                    <fieldset class="form-group">
                        <h4>Cohort Location</h4>
                        <div class="typeahead__container">
                                <span class="typeahead__query">
                                   <input name="location" id="location" class="form-control js-typeahead-input" type="search" autofocus autocomplete="off" placeholder="Enter Location">
                                </span>
                        </div>
                    </fieldset>
                </li>


                <li>
                    <fieldset class="form-group">
                        <h4>Start Date</h4>
                        <div class="typeahead__container">
                                <span class="typeahead__query">
                                   <input name="startDate" id="startDate" class="form-control js-typeahead-input" type="search" autofocus autocomplete="off" placeholder="Enter Date">
                                </span>
                        </div>
                    </fieldset>
                </li>


            </ul>
            <div class="button-container">
                <button type="submit" class="submit-button btn btn-primary center-block"><h4>Submit Details</h4></button>
            </div>
        </form>
    </div>

    <c:if test="${cohortsExist == true}">

        <div class="col-sm-4 col-sm-offset-1">
            <div class="table-container details-container">
                <table class="table search-results-table">
                    <thead class="thead-inverse">
                    <tr>
                        <th><h4>Search</h4></th>
                        <th class="second-header"><h4>Results</h4></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th scope="row">#</th>
                        <th scope="row">Cohort</th>
                        <th scope="row">Details</th>
                    </tr>

                    <c:forEach var="cohort" items="${resultList}" varStatus="cohortStatus">
                        <tr>
                            <td> <h4>${cohortStatus.index + 1}</h4> </td>
                            <td> <h4> <a href="/openmrs/module/cohort/cohortDashboard.form?search=search&name=${cohort.name}" target="_blank">${cohort.name}</a></h4></td>
                            <td> <div class="dropdown">
                                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    Details
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu dropdown-menu-left" aria-labelledby="dropdownMenu1">

                                    <li>
                                        <span class="person-attribute-label"><strong>Description</strong>:<br></span>
                                        <span class="person-detail-label">${cohort.description}</span>
                                    </li>

                                    <li role="separator" class="divider"></li>

                                    <li>
                                        <span class="person-attribute-label"><strong>Program</strong>:<br></span>
                                        <span class="person-detail-label">${cohort.cohortProgram.name}</span>
                                    </li>

                                    <li role="separator" class="divider"></li>

                                    <li>
                                        <span class="person-attribute-label"><strong>Location</strong>:<br></span>
                                        <span class="person-detail-label">${cohort.clocation}</span>
                                    </li>

                                    <li role="separator" class="divider"></li>
                                    
                                    <li>
                                        <span class="person-attribute-label"><strong>Start Date</strong>:<br></span>
                                        <span class="person-detail-label"><openmrs:formatDate date="${cohort.startDate}"/></span>
                                    </li>

                                    <li role="separator" class="divider"></li>

                                    <li>
                                        <span class="person-attribute-label"><strong>End Date</strong>:<br></span>
                                        <span class="person-detail-label"><openmrs:formatDate date="${cohort.endDate}"/></span>
                                    </li>

                                    <li role="separator" class="divider"></li>

                                </ul>
                            </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
                <%--</c:forEach>--%>
        </div>
    </c:if>

    <c:if test="${cohortsExist == false}">

        <div class="container">
            <div class="row">
                <div class="col-sm-4 col-sm-offset-1 not-found-container">
                    <h3 class="not-found">No cohorts found!</h3>
                </div>
            </div>
        </div>
    </c:if>

</div>
</div>
</div>
</body>



<!-- File JS -->
<openmrs:htmlInclude file="/moduleResources/cohort/scripts/pages/cohortSearch.js" />
<!--Typeahead JS -->
<openmrs:htmlInclude file="/moduleResources/cohort/scripts/libraries/jquery.typeahead.js" />
<!-- Slider JS -->
<openmrs:htmlInclude file="/moduleResources/cohort/scripts/libraries/jquery-ui.js" />


<!--END-->