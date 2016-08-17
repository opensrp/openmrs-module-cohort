<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="template/header.jsp" %>
<title>Edit Cohort Member Role</title> <!--set page title-->
</head>
<openmrs:htmlInclude file="/moduleResources/cohort/styles/genericPageStyle.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/pages/genericEditPageStyling.css" />
<%@ include file="template/navbar.jsp" %>

<body>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h4 class="heading"><span>Edit Cohort Member Role</span></h4>
            <form id="form1" class="form-container" method="post">
                <form:errors path="*" cssClass="errorblock" element="div"/>
                <br>

                <li>
                    <h4>Cohort Type </h4>
                    <select class="form-control generic-dropdown" name="format" id="format">
                        
                        <c:if test="${empty formats}">
                            <option value=""></option>
                        </c:if>
                        
                        <c:forEach var="format" items="${formats}">
                            <option value="${format}" <c:if test="${format == cohortrole.cohortType}">selected</c:if>>${format}</option>
                        </c:forEach>
                        
                        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                    </select>
                    <br/><br/>
                </li>

                <li>

                    <spring:bind path="cohortrole.name">
                        <h4>Cohort Member Role Name </h4>
                        <input class="form-control" type="text" name="name" id="name" size="25" value="${status.value}"/> <br/> <br/>
                        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                    </spring:bind>
                </li>

                <br/>
                <div class="button-container">
                    <input class="btn btn-primary" type="submit" value="Edit Role" id="Edit Role" name="Edit Role"/><br/>
                </div>

                <br/>
                <hr/>
                <h4 class="alert-danger">Delete Role</h4> <br><br/>
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