<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="template/header.jsp" %>
<title>Patient Search</title> <!--set page title-->
</head>
<openmrs:htmlInclude file="/moduleResources/cohort/styles/genericPageStyle.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/pages/patientSearch.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/libraries/jquery.typeahead.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/libraries/jquery-ui.css" />
<%@ include file="template/navbar.jsp" %>

<body>
<div class="container">
    <div class="row">
        <div class="col-sm-4 col-sm-offset-4">
            <div class="jumbotron">
                <div class="container">
                    <h2 >Patient Search</h2>
                </div>
            </div>
        </div>
    </div>
    <div class="col-sm-6">
        <div class="patient-form-heading">
            <h4>Patient Details</h4>
        </div>
        <form class="form-container">
            <ul class="list-styled">
                <li>
                    <fieldset class="form-group">
                        <h4>Patient Name</h4>
                        <div class="typeahead__container">
                                <span class="typeahead__query">
                                   <input id="patientName" class="form-control js-typeahead-input" type="search" autofocus autocomplete="off" placeholder="Enter Patient Name">
                                </span>
                        </div>
                    </fieldset>
                </li>

                <li>
                    <fieldset class="form-group">
                        <h4>Gender</h4>
                        <div class="radio-inline">
                            <label>
                                <input type="radio" name="optionsRadios" id="MRadio" value="option1" checked>
                                Male
                            </label>
                        </div>
                        <div class="radio-inline">
                            <label>
                                <input type="radio" name="optionsRadios" id="FRadio" value="option2">
                                Female
                            </label>
                        </div>
                    </fieldset>
                </li>

                <li>
                    <fieldset class="form-group">
                        <h4>Cohort Name</h4>
                        <div class="typeahead__container">
                                <span class="typeahead__query">
                                   <input id="cohortName" class="form-control js-typeahead-input" type="search" autofocus autocomplete="off" placeholder="Enter Cohort Program">
                                </span>
                        </div>
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


                <li>
                    <fieldset class="form-group">
                        <div id="ageslider">
                            <p>
                            <h4>Age</h4>
                            <input type="text" id="amount" style="border: 0; color: #f6931f; font-weight: bold;" readonly />
                            </p>
                            <div id="slider-range"></div>
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
                    <th scope="row">Patient Name</th>
                    <th scope="row">Details</th>
                </tr>

                <%--<c:forEach>--%>
                <tr>
                    <th> <h4>1</h4> </th>
                    <td> <h4>Shreyans Sheth</h4> </td>
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
<openmrs:htmlInclude file="/moduleResources/cohort/scripts/pages/patientSearch.js" />
<!--Typeahead JS -->
<openmrs:htmlInclude file="/moduleResources/cohort/scripts/libraries/jquery.typeahead.js" />
<!-- Slider JS -->
<openmrs:htmlInclude file="/moduleResources/cohort/scripts/libraries/jquery-ui.js" />


<!--END-->