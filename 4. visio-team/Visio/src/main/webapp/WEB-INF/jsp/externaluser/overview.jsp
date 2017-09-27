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
    <div class="container-fluid">
        <div class="col-sm-6 col-sm-offset-3 col-md-8 col-md-offset-2 main">
            <div class="well">
            <f:form method="POST" commandName="SearchModel" action="${pageContext.request.contextPath}/externaluser/overview">
                <f:input path="filter" class="form-control" />
                <input type="submit" value="Zoeken" class="btn btn-primary" />
                
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Naam</th>
                                <th>Geslacht</th>
                                <th>Geboortedatum</th>
                                <th>Gebruiker Type</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach var="externalUser" items="${externalUsers}">
                                <tr>
                                    <td>${externalUser.name}</td>
                                    <td>${externalUser.gender}</td>
                                    <td>${externalUser.birthdate}</td>
                                    <td>${externalUser.VITId}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/externaluser/edit/${externalUser.externalUserId}">Edit</a>
                                    </td>
                                </tr> 
                            </c:forEach>

                        </tbody>
                    </table>
                </div>
                <div class="well">
                    <p></p>
                </div>

            </div>

        </f:form>
    </div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>
