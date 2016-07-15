<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="template/header.jsp" %>
<title>Edit Cohort Attributes</title> <!--set page title-->
</head>
<openmrs:htmlInclude file="/moduleResources/cohort/styles/genericPageStyle.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/pages/genericEditPageStyling.css" />
<%@ include file="template/navbar.jsp" %>

<body>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h4 class="heading"><span>Edit Cohort Attribute</span></h4>
            <form class="form-container" method="post">
                <br>
                    <li>
                        <spring:bind path="cohortatt.cohort">
                            <h4>Cohort Names</h4>
                            <select class="form-control" name="names" id="names">
                                <c:forEach var="names" items="${formats1}">
                                    <option value="${names}" <c:if test="${names ==cohortatt.cohort}">selected</c:if>>${names}</option>
                                </c:forEach>
                            </select>
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </spring:bind>
                        <br>
                    </li>
                    <li>
                        <fieldset>
                            <spring:bind path="cohortatt.cohortAttributeType">
                                <h4>Cohort Attribute Type</h4>
                                <select class="form-control" name="format" id="format">
                                    <c:forEach var="format" items="${formats}">
                                        <option value="${format}"
                                                <c:if test="${format ==cohortAttributeType}">selected</c:if>>${format}</option>
                                    </c:forEach>
                                </select>
                                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                            </spring:bind>
                        </fieldset>
                        <br/>
                    </li>

                    <li class="list-unstyled">
                        <spring:bind path="cohortatt.value">
                           <h4><spring:message code="cohort.cohorttypedescription"/>:</h4>
                            <input class="form-control" type="text" name="value" id="value" size="25" value="${status.value}"/> <br/> <br/>
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </spring:bind>
                    </li>
                    
                    <div class="button-container">
                        <input class="btn btn-primary" type="submit" value="Edit Cohort Attributes" id="submit"/>
                    </div>
                
                    <br/>
                    <br/>
                    <hr/>
                    <h4 class="alert-warning">Void Cohort Attribute</h4> <br>
                    <h4>Reason</h4>:<input class="form-control" type="text" name="voidReason" id="voidReason" size="25" value="${status.value}"/> <br/>
                    <input class="btn btn-warning" type="submit" value="void" id="void" name="void"/>  
                    <hr/>
                    <h4 class="alert-danger">Delete Cohort Attribute</h4> <br><br/>
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


<!--Code Body-->


<!--Script includes for new UI -->
<%--<openmrs:htmlInclude file="/moduleResources/cohort/scripts/pages/patientSearch.js" />--%>
<!--END-->