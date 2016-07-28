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
    <div class="col-sm-5">
        <div class="patient-form-heading">
            <h4>Enter Patient Details to Search</h4>
        </div>
        <form class="form-container" method="post">
            <ul class="list-styled">
                <li>
                    <fieldset class="form-group">
                        <h4>Patient Name</h4>
                        <div class="typeahead__container">
                                <span class="typeahead__query">
                                        <input name="patientName" id="patientName" class="form-control js-typeahead-input" type="search" autofocus autocomplete="off" placeholder="Enter Patient Name">
                                </span>
                        </div>
                    </fieldset>
                </li>

                <li>
                    <fieldset class="form-group">
                        <h4>Gender</h4>
                        <div class="radio-inline">
                            <label>
                                <input type="radio" name="optionsRadios" id="MRadio" value="M" checked>
                                Male
                            </label>
                        </div>
                        <div class="radio-inline">
                            <label>
                                <input type="radio" name="optionsRadios" id="FRadio" value="F">
                                Female
                            </label>
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
                        <div id="ageslider">
                            <p>
                            <h4>Age</h4>
                            <input name="amount" type="text" id="amount" style="border: 0; color: #f6931f; font-weight: bold;" readonly />
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

    <c:if test="${personsExist == true}">
    
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

                <c:forEach var="member" items="${resultList}" varStatus="memberStatus">
                <tr>
                    <td> <h4>${memberStatus.index + 1}</h4> </td>
                    <td> <h4> <a href="/openmrs/patientDashboard.form?patientId=${member.person.personId}" target="_blank">${member.person.personName.fullName}</a></h4></td>
                    <td> <div class="dropdown">
                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            Details
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu dropdown-menu-left" aria-labelledby="dropdownMenu1">

                            <li>
                                <span class="person-attribute-label"><strong>Cohort</strong>:</span>
                                <span class="person-detail-label">${member.cohort.name}</span>
                            </li>

                            <li role="separator" class="divider"></li>
                            
                            <li>
                                <span class="person-attribute-label"><strong>Member Id</strong>:</span>
                                <span class="person-detail-label">${member.cohortMemberId}</span>
                            </li>

                            <li role="separator" class="divider"></li>


                            <li>
                                <span class="person-attribute-label"><strong>Start Date</strong>:</span>
                                <span class="person-detail-label"><openmrs:formatDate date="${member.startDate}"/></span>
                            </li>

                            <li role="separator" class="divider"></li>

                            <li>
                                <span class="person-attribute-label"><strong>End Date</strong>:</span>
                                <span class="person-detail-label"><openmrs:formatDate date="${member.endDate}"/></span>
                            </li>

                            <li role="separator" class="divider"></li>

                            <li>
                                <span class="person-attribute-label"><strong>Role</strong>:</span>
                                <span class="person-detail-label">${member.role}</span>
                            </li>

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

    <c:if test="${personsExist == false}">
        
        <div class="container">
            <div class="row">
                <div class="col-sm-4 col-sm-offset-1 not-found-container">
                    <h3 class="not-found">No patients found!</h3>
                </div>
            </div>
        </div>
    </c:if>
    
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