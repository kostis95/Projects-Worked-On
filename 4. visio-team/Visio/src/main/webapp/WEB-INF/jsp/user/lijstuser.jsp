<%-- 
    Document   : lijstuser
    Created on : 2-nov-2016, 9:05:40
    Author     : PC
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<jsp:include page="../header.jsp"></jsp:include>
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

        <h2>Administrators</h2>
        <div class="alert alert-success ${message != null ? "" : "hidden"}" role="alert">${message}</div>
    <div class="well">          
        <table class="table">
            <a href="${pageContext.request.contextPath}/user/add" class="btn btn-primary">Add admin</a>
            <thead>
                <tr>
                    <th><strong> Username </strong></th>
                    <th><strong> Firstname </strong></th>
                    <th><strong> Lastname </strong></th>
                    <th><strong> Address </strong></th>
                    <th><strong> City </strong></th>
                    <th><strong></strong</th> 
                </tr>
            </thead>

            <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.loginName}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.address}</td>
                        <td>${user.city}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/user/edit/${user.userId}">Edit</a>
                            <a href="${pageContext.request.contextPath}/user/delete/${user.userId}" id="deleteUser" onclick="return confirm('Are you sure?');">Delete</a>
                        </td>
                    </tr> 
                </c:forEach>
            </tbody>
        </table>
    </div>
</div> 
<jsp:include page="../footer.jsp"></jsp:include>
