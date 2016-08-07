<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="template/header.jsp" %>
<title>Edit Cohort Member Attributes Type</title> <!--set page title-->
</head>
<openmrs:htmlInclude file="/moduleResources/cohort/styles/genericPageStyle.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/pages/genericEditPageStyling.css" />
<%@ include file="template/navbar.jsp" %>

<body>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h4 class="heading"><span>Edit Cohort Member Attribute Type</span></h4>
            <form class="form-container" method="post">
                <br>
                <li>
                    <spring:bind path="cohortattributes.name">
                        <h4><spring:message code="cohort.cohorttypename"/> </h4>
                        <input class="form-control" type="text" name="name" id="name" size="25" value="${status.value}"/> <br/>
                        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                    </spring:bind>
                        
                </li>
                
                <li>
                    <fieldset>
                        <spring:bind path="cohortattributes.description">
                            <h4><spring:message code="cohort.cohorttypedescription"/> </h4>
                            <textarea class="form-control" rows="4" name="description" id="description" cols="50">${status.value}</textarea><br/>
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </spring:bind>
                    </fieldset>
                </li>

                <li>
                    <spring:bind path="cohortattributes.format">
                        <h4> <spring:message code="cohort.format"/> </h4>
                        <select class="form-control generic-dropdown" name="format">
                            <c:forEach var="format" items="${formats}">
                                <option value="${format}"
                                        <c:if test="${format ==cohortattributes.format}">selected</c:if>>${format}</option>
                            </c:forEach>
                            <c:if test="${status.errorMessage != ''}"><span
                                    class="error">${status.errorMessage}</span></c:if>
                        </select>
                    </spring:bind>
                    <br/><br/>
                </li>

                <div class="button-container">
                    <input class="btn btn-primary" type="submit" value="edit" id="edit" name="edit"/>
                </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">
    $('#management-label-nav').addClass('active')
</script>

<!--Code Body-->


<!--Script includes for new UI -->
<%--<openmrs:htmlInclude file="/moduleResources/cohort/scripts/pages/patientSearch.js" />--%>
<!--END-->