<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="template/header.jsp" %>
<title>Add Role</title>
</head>
<openmrs:htmlInclude file="/moduleResources/cohort/styles/genericPageStyle.css" />
<openmrs:htmlInclude file="/moduleResources/cohort/styles/pages/genericAddPageStyling.css"/>
<%@ include file="template/navbar.jsp" %>


<body>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h4 class="heading"><span>Add Cohort Member Role</span></h4>
            <form class="form-container" method="post">
                <ul>
                    <li>
                        <fieldset>
                            <h4>Cohort Type</h4>
                            <select class="form-control" name="format" id="format">
                                <option value=""></option>
                                <c:forEach var="format" items="${formats}">
                                    <option value="${format}" <c:if test="${format == status.value}">selected</c:if>>${format}</option>
                                </c:forEach>
                                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                            </select>
                        </fieldset>
                    </li>
                    <br/>
                    <li class="list-unstyled">
                        <spring:bind path="cohortrole.name">
                            <h4>Enter Role Name:</h4>
                            <input class="form-control" type="text" name="name" id="name" size="25" value="${status.value}" maxlength="100"/>
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </spring:bind>
                    </li>
                    <br/><br/>
                    
                    <input class="btn btn-primary" type="submit" value="Add Role" id="submit"/><br/><br/>

                </ul>

            </form>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">
    $(document).ready(function () {
        $('#management-label-nav').css({color: '#007aff !important'});
    });
</script>
