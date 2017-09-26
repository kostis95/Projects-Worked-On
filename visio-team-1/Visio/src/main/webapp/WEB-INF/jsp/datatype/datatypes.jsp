<%-- 
    Document   : datatypes
    Created on : Jan 10, 2017, 2:20:27 PM
    Author     : Gabriël 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:include page="../header.jsp"></jsp:include>
<div class="col-sm-6 col-sm-offset-3 col-md-8 col-md-offset-2 main">
    <h2>Data types</h2>
    <div class="alert alert-success ${message != null ? "" : "hidden"}" role="alert">${message}</div>
        <div class="well">
        <f:form method="POST" commandName="DataType" action="${pageContext.request.contextPath}/datatypes">
            <div class="table-responsive">
                <table class="table">
                    <a class="btn btn-primary" href="${pageContext.servletContext.contextPath}/datatypes/add">Add data type</a>
                    <thead>
                        <tr>
                            <th>Description</th>
                            <th></th>
                        </tr>
                    </thead>
                  
                    <tbody>
                        <c:forEach var="dataType" items="${dataTypes}">
                            <tr>
                                <td>${dataType.description}</td>
                                <td>
                                    <a href="${pageContext.servletContext.contextPath}/datatypes/edit/${dataType.dataTypeId}">Edit</a>
                                    <a href="${pageContext.servletContext.contextPath}/datatypes/delete/${dataType.dataTypeId}" onclick="return confirm('Are you sure?');">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    
                </table>
            </div>
        </div>
    </f:form>
</div>
<jsp:include page="../footer.jsp"></jsp:include>

