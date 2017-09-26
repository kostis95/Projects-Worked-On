<%-- 
    Document   : edit
    Created on : Dec 20, 2016, 1:55:09 PM
    Author     : Gjow - Admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../header.jsp" />
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

    <div class="col-md-12 text-left">
        <div class="col-md-6 text-left">
            <div class="container-fluid">
                <h2>Edit Administrator</h2>
                <div class="alert alert-success ${message != null ? "" : "hidden"}" role="alert">${message}</div>
                <form:form class="form-horizontal" method="POST" modelAttribute="User" commandName="User" action="${pageContext.request.contextPath}/user/edit">
                    <div class="form-group">
                            <form:input path="userId" cssClass="form-control" value="${user.userId}" style="display: none"/>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" style ="text-align: left;">Username</label>
                        <div class="col-md-7">
                            <form:input path="loginName" cssClass="form-control" value="${user.loginName}"/>
                            <form:errors path="loginName" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" style ="text-align: left;">Password</label>
                        <div class="col-md-7">
                            <form:password path="password" cssClass="form-control" value="${user.password}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" style ="text-align: left;" >Firstname</label>
                        <div class="col-md-7">
                            <form:input path="firstName" cssClass="form-control" value="${user.firstName}" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" style ="text-align: left;">Lastname</label>
                        <div class="col-md-7">
                            <form:input path="lastName" cssClass="form-control"  value="${user.lastName}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" style ="text-align: left;">Address</label>
                        <div class="col-md-7">
                            <form:input path="address" cssClass="form-control" value="${user.address}" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" style ="text-align: left;">City</label>
                        <div class="col-md-7">
                            <form:input path="city" cssClass="form-control" value="${user.city}" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" style ="text-align: left;">Role</label>
                        <div class="col-md-7">
                            <form:select path="role" cssClass="form-control" value="${user.role}">
                                <form:option value="0">Visio admin</form:option>
                                <form:option value="1">Admin</form:option>
                            </form:select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-4 col-md-8">
                            <button type="submit" class="btn btn-primary">Update</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp" />
