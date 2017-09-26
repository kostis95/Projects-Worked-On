<%-- 
    Document   : beacons
    Created on : 15-nov-2016, 14:19:05
    Author     : Thomas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:include page="../header.jsp"></jsp:include>
    <div class="col-sm-6 col-sm-offset-3 col-md-8 col-md-offset-2 main">
        <h2>Vision Impaired Types</h2>
        <div class="well">
        <f:form method="POST" commandName="VIT" action="${pageContext.request.contextPath}/vits/">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <a class="btn btn-primary" href="${pageContext.servletContext.contextPath}/vits/add">Add VIT</a>
                        <tr>
                            <th>Description</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="vit" items="${vits}">

                            <tr>
                                <td>${vit.description}</td>
                                <td>
                                    <a href="${pageContext.servletContext.contextPath}/vits/edit/${vit.VITId}">Edit</a>
                                    <a href="${pageContext.servletContext.contextPath}/vits/delete/${vit.VITId}" onclick="return confirm('Are you sure?');">Delete</a>
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
