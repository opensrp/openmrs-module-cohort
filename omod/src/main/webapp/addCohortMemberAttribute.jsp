<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="template/header.jsp" %>
<title>Add Cohort Member Attribute</title> <!--set page title-->
</head>
<openmrs:htmlInclude file="/moduleResources/cohort/styles/genericPageStyle.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/pages/genericAddPageStyling.css"/>
<%@ include file="template/navbar.jsp" %>

<body>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h4 class="heading"><span>Add Cohort Member Attribute</span></h4>
            <form class="form-container" method="post" id="form2">
                <ul>
                    <li>
                        <h4>Cohort Member Name </h4> <br/>
                            <h5 class="attribute-name-header">${cohortmember.person.names}</h5>
                        <br/>
                        <br/>
                    </li>
                    <li>
                        <fieldset>
                            <h4>Cohort Member Attribute Type:</h4>
                            <select class="form-control" name="cohortMemberAttributeTypeId"
                                    onchange="document.getElementById('form2').method='GET'; document.getElementById('form2').submit()">
                                <option value=""></option>
                                <c:forEach var="attype" items="${attypes}">
                                    <option value="${attype.cohortMemberAttributeTypeId}"
                                            <c:if test="${attype ==cohortatt.cohortMemberAttributeType}">selected</c:if>>${attype}</option>
                                </c:forEach>
                                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                            </select>
                        </fieldset>
                        <br/>
                    </li>
                    
                    <input type="hidden" name="cma" value="${cohortatt.cohortMember.cohortMemberId}"/>
                    
                    <li class="list-unstyled">
                        <spring:bind path="cohortatt.value">
                            <h4>Value:</h4><br/>
                            <openmrs:fieldGen
                                    type="${cohortatt.cohortMemberAttributeType.format}"
                                    formFieldName="selectedvalue"
                                    val="${selectedvalue}"
                                    parameters="optionHeader=[blank]|showAnswers=${attrType.foreignKey}|isNullable=false"/> <%-- isNullable=false so booleans don't have 'unknown' radiobox --%>
                        </spring:bind>
                    </li>
                    <br/>
                    <br/>

                    <input class="form-control btn btn-primary" type="submit" value="Add Cohort Member Attribute" id="add"/>
                </ul>
            </form>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">
    $('#management-label-nav').addClass('active')
</script>
