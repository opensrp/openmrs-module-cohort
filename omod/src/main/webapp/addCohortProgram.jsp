<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="template/header.jsp" %>
    <title>Add Cohort Program</title>
</head>
<openmrs:htmlInclude file="/moduleResources/cohort/styles/genericPageStyle.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/pages/genericAddPageStyling.css"/>
<%@ include file="template/navbar.jsp" %>


<body>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h4 class="heading"><span>Add Cohort Program</span></h4>
            <form class="form-container" method="post">
                <ul>
                    <li>
                        <fieldset>
                            <spring:bind path="cohortprogram.name">
                               <h4> <spring:message code="cohort.cohorttypename"/> </h4><br/>
                                <input class="form-control" type="text" name="name" id="name" size="25" value="${status.value}" maxlength="100"/> <br/> <br/>
                                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                            </spring:bind>
                        </fieldset>
                    </li>
                    <li>
                        <fieldset>
                            <spring:bind path="cohortprogram.description">
                               <h4><spring:message code="cohort.cohorttypedescription"/> </h4><br/>
                                <textarea class="form-control" rows="4" name="description" id="description">${status.value}</textarea><br/><br/>
                                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                            </spring:bind>
                        </fieldset>
                    </li>
                    <br/>
                    
                    <input class="btn btn-primary" type="submit" value="submit" id="submit"/>

                </ul>

            </form>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">
    $('#management-label-nav').addClass('active')
</script>


<!--Script includes for new UI -->
<%--<openmrs:htmlInclude file="/moduleResources/cohort/scripts/pages/patientSearch.js" />--%>
<!--END-->
