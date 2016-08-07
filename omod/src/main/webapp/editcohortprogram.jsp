<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="template/header.jsp" %>
<title>Edit Cohort Program</title> <!--set page title-->
</head>
<openmrs:htmlInclude file="/moduleResources/cohort/styles/genericPageStyle.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/pages/genericEditPageStyling.css" />
<%@ include file="template/navbar.jsp" %>

<body>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h4 class="heading"><span>Edit Cohort Program</span></h4>
            <form class="form-container" method="post">
                <br>
                <li>
                    <spring:bind path="cohortprogram.name">
                        <h4><spring:message code="cohort.cohorttypename"/> </h4>
                        <input class="form-control" type="text" name="name" id="name" size="25" value="${status.value}"/> <br/>
                        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                    </spring:bind>
                    <br>
                </li>
                <li>
                    <fieldset>
                        <spring:bind path="cohortprogram.description">
                           <h4> <spring:message code="cohort.cohorttypedescription"/> </h4>
                            <textarea class="form-control" rows="4" name="description" id="description" cols="50">${status.value}</textarea><br/>
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </spring:bind>
                    </fieldset>
                    <br/>
                </li>
                
                <div class="button-container">
                    <input class="btn btn-primary" type="submit" value="edit" id="edit" name="edit"/><br/>
                </div>
                
                <br/>
                <hr/>
                <h4 class="alert-warning">Void Cohort Program</h4> <br>
                <h4>Reason</h4>:<input class="form-control" type="text" name="voidReason" id="voidReason" size="25" value="${status.value}"/> <br/>
                <input class="btn btn-warning" type="submit" value="void" id="void" name="void"/>
                <hr/>
                <h4 class="alert-danger">Delete Cohort Program</h4> <br><br/>
                <input class="btn btn-danger" type="submit" value="delete" id="delete" name="delete"/><br/>
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