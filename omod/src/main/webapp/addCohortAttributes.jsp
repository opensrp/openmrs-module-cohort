<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="template/header.jsp" %>
    <title>Add Cohort Attributes</title> <!--set page title-->
</head>
<openmrs:htmlInclude file="/moduleResources/cohort/styles/genericPageStyle.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/pages/genericAddPageStyling.css"/>
<%@ include file="template/navbar.jsp" %>

<body>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h4 class="heading"><span>Add Cohort Attribute</span></h4>
            <form class="form-container" method="post" id="form1">
                <ul>
                    <li>
                        <h4>Cohort Name:</h4> <br/>
                        <h5 class="attribute-name-header">${cohortmodule.name}</h5>
                        <br/>
                        <br/>
                    </li>
                    <li>
                        <fieldset>
                            <h4>Cohort Attribute Type:</h4>
                            <select class="form-control" name="cohortAttributeTypeId" onchange="document.getElementById('form1').method='GET'; document.getElementById('form1').submit()">
                                <c:forEach var="attype" items="${attypes}">
                                    <option value="${attype.cohortAttributeTypeId}"
                                            <c:if test="${attype ==cohortatt.cohortAttributeType}">
                                                selected
                                            </c:if> >${attype}
                                    </option>
                                </c:forEach>
                                <c:if test="${status.errorMessage != ''}">
                                    <span class="error">${status.errorMessage}</span>
                                </c:if>
                            </select>
                        </fieldset>
                        <br/>
                    </li>

                    <input type="hidden" name="ca" value="${cohortatt.cohort.cohortId}"/>
                    
                    <li class="list-unstyled">
                        <h4 style="padding-bottom: 5px;">Value:<br/>
                            <spring:bind path="cohortatt.value">
                                <openmrs:fieldGen
                                        type="${cohortatt.cohortAttributeType.format}"
                                        formFieldName="selectedvalue"
                                        val="${selectedvalue}"
                                        parameters="optionHeader=[blank]|showAnswers=${attrType.foreignKey}|isNullable=false"
                                /> <%-- isNullable=false so booleans don't have 'unknown' radiobox --%>
                            </spring:bind> </h4>
                        <br/>
                        <br/>
                    </li>

                    <li class="list-unstyled">
                        <input class="btn btn-primary" type="submit" value="Add Cohort Attribute" id="add" name="add"/>
                        <br/>
                        <br/>
                    </li>

                    <li class="list-unstyled">
                        <input class="btn btn-default" type="submit" value="Next" id="next" name="next"/>
                    </li>

                </ul>
            </form>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">
    $('#management-label-nav').addClass('active')
</script>
