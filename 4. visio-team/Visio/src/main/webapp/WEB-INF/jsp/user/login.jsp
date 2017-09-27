<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visio Login</title>

        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" rel="stylesheet" media="screen">
        <link href="<c:url value="resources/css/style.css" />">
    </head>
    <body class="back-color-login">
        <div class="row">
            <div class="col-lg-4 col-lg-offset-4 col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

            <div class="alert alert-danger ${message != null ? "" : "hidden"}" role="alert">${message}</div>
            <form:form method="POST" modelAttribute="User" commandName="User" action="${pageContext.request.contextPath}/user/login">
                <div class="form-group">
                    <label class="col-sm-4 control-label">Username</label>
                    <div class="col-sm-8">
                        <form:input path="loginName" cssClass="form-control" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">Password</label>
                    <div class="col-sm-8">
                        <form:password path="password" cssClass="form-control" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> Remember me
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
                        <button type="submit" class="btn btn-default">Sign in</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>

</body>
</html>
