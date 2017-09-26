<%-- 
    Document   : add
    Created on : Jan 10, 2017, 2:20:38 PM
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
                <h2>Add Datatype</h2>
                <div class="alert alert-success ${message != null ? "" : "hidden"}" role="alert">${message}</div>
                <form:form class="form-horizontal" method="POST" modelAttribute="DataType" commandName="DataType" action="${pageContext.request.contextPath}/datatypes/add">
                    <div class="form-group">
                        <label class="col-md-4 control-label" style ="text-align: left;">Description</label>
                        <div class="col-md-7">
                            <form:input path="description" cssClass="form-control" />
                            <form:errors path="description" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-4 col-md-8">
                            <button type="submit" class="btn btn-primary">Add</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp" />
