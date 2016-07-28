<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="template/header.jsp" %>
<title>Patient Search</title> <!--set page title-->
</head>
<openmrs:htmlInclude file="/moduleResources/cohort/styles/genericPageStyle.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/libraries/jquery-ui.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/pages/cohortSearch.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/libraries/jquery.typeahead.css" />
<%@ include file="template/navbar.jsp" %>

<body>
<div class="container">
    <div class="row">


        <div class="col-sm-4 col-sm-offset-4">
            <div class="jumbotron">
                <div class="container">
                    <h2 >Cohort Search</h2>
                </div>
            </div>
        </div>

        <div class="col-sm-6">
            <div class="patient-form-heading">
                <h4>Cohort Details</h4>
            </div>
            <form class="form-container">
                <ul class="list-styled">
                    <li>
                        <fieldset class="form-group">
                            <h4>Cohort Name</h4>
                            <div class="typeahead__container">
                                <span class="typeahead__query">
                                   <input id="cohortName" class="form-control js-typeahead-input" type="search" autofocus autocomplete="off" placeholder="Enter Cohort Name">
                                </span>
                            </div>
                        </fieldset>
                    </li>

                    <li>
                        <fieldset class="form-group">
                            <h4>Cohort Program</h4>
                            <div class="typeahead__container">
                                <span class="typeahead__query">
                                   <input id="cohortProgram" class="form-control js-typeahead-input" type="search" autofocus autocomplete="off" placeholder="Enter Cohort Program">
                                </span>
                            </div>
                        </fieldset>
                    </li>

                    <li>
                        <fieldset class="form-group">
                            <h4>Cohort Head</h4>
                            <div class="typeahead__container">
                                <span class="typeahead__query">
                                   <input id="cohortHead" class="form-control js-typeahead-input" type="search" autofocus autocomplete="off" placeholder="Enter Cohort Head">
                                </span>
                            </div>
                        </fieldset>
                    </li>

                    <li>
                        <fieldset class="form-group">
                            <h4>Date Registered</h4>
                                <span class="typeahead__query form-inline">
                                    <label>
                                        <input class="form-control" type="text" id="datepicker" style="width: 50%"/>
                                    </label>
                                </span>
                        </fieldset>
                    </li>

                    <li>
                        <fieldset class="form-group">
                            <h4>Location</h4>
                            <div class="typeahead__container">
                                <span class="typeahead__query">
                                   <input id="location" class="form-control js-typeahead-input" type="search" autofocus autocomplete="off" placeholder="Enter Location">
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
                        <th scope="row">Cohort Name</th>
                        <th scope="row">Details</th>
                    </tr>

                    <%--<c:forEach>--%>
                    <tr>
                        <th> <h4>1</h4> </th>
                        <td> <h4>Cohort-Name-A</h4> </td>
                        <td>
                            <div class="dropdown">
                                <button class="btn dropdown-toggle" type="button" id="dropdownMenu1" aria-haspopup="true" aria-expanded="true">
                                    <i class="fa fa-info-circle" aria-hidden="true"></i> Details
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                    <li>
                                        <table class="table table-hover person-details-table">
                                            <tbody>
                                            <tr>
                                                <span class="person-attribute-label"><strong>Name</strong>:</span>
                                            </tr>
                                    <li role="separator" class="divider"></li>

                    <tr>
                        <span class="person-attribute-label"><strong>Member Id</strong>:</span>
                        <span class="person-detail-label">2</span>
                    </tr>
                    <li role="separator" class="divider"></li>


                    <tr>
                        <span class="person-attribute-label"><strong>Start Date</strong>:</span>
                        <span class="person-detail-label">12/24/52</span>
                    </tr>
                    <li role="separator" class="divider"></li>

                    <tr>
                        <span class="person-attribute-label"><strong>End Date</strong>:</span>
                        <span class="person-detail-label">12/2/5</span>
                    </tr>
                    <li role="separator" class="divider"></li>

                    <tr>
                        <span class="person-attribute-label"><strong>Role</strong>:</span>
                        <span class="person-detail-label">Head</span>
                    </tr>
                    </tbody>
                </table>
                </li>
                </ul>
            </div>
            </td>
            </tr>
            <%--</c:forEach>--%>
            </tbody>
            </table>
        </div>
    </div>
</div>
</div>

</body>



<!-- File JS -->
<openmrs:htmlInclude file="/moduleResources/cohort/scripts/pages/cohortSearch.js" />
<!--Typeahead JS -->
<openmrs:htmlInclude file="/moduleResources/cohort/scripts/libraries/jquery.typeahead.js" />
<!--jQuery UI -->
<openmrs:htmlInclude file="/moduleResources/cohort/scripts/libraries/jquery-ui.js" />

<!--END-->