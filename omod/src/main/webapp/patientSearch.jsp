<!--Uncomment this and comment the rest to see new UI prototype -->

<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="template/header.jsp" %>
<title>Patient Search</title> <!--set page title-->
</head>
<openmrs:htmlInclude file="/moduleResources/cohort/styles/genericPageStyle.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/patientSearch.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/jquery.typeahead.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/jquery-ui.css" />
<%@ include file="template/navbar.jsp" %>

<!--Code body here -->
<div class="container">
    <div class="row">
        <div class="col-sm-8 col-sm-offset-2">
            <h2 style="text-align: center">Patient Search</h2>
        </div>
        <div class="col-sm-6">
            
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
    </div>
</div>



<!--jQuery-->
<openmrs:htmlInclude file="/moduleResources/cohort/scripts/jquery.js" />
<!--Bootstrap JS -->
<openmrs:htmlInclude file="/moduleResources/cohort/bootstrap/js/bootstrap.js" />
<!--Typeahead JS -->
<openmrs:htmlInclude file="/moduleResources/cohort/scripts/jquery.typeahead.js" />
<!-- File JS -->
<openmrs:htmlInclude file="/moduleResources/cohort/scripts/patientSearch.js" />
<!-- Slider JS -->
<openmrs:htmlInclude file="/moduleResources/cohort/scripts/jquery-ui.js" />



<!--END-->