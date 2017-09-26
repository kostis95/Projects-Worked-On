<%-- 
    Document   : beaconPage
    Created on : 15-nov-2016, 14:19:05
    Author     : gerbrecht
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<jsp:include page="header.jsp"></jsp:include> 


    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


        <div class="col-md-12 text-left">
            <div class="col-md-6 text-left">
                <div class="container-fluid">
                    <h1>Beacon Information</h1>

                    <div class="alert alert-success ${message != null ? "" : "hidden"}" role="alert">${message}</div>
                <form:form class="form-horizontal" method="POST" modelAttribute="Beacon" commandName="Beacon" action="${pageContext.request.contextPath}/beacons/add">
                    <div class="form-group">
                        <label class="control-label col-md-4" style ="text-align: left;" for="pwd">UUID:</label>
                        <div class="col-md-7">
                            <form:input path="uuid" cssClass="form-control" />
                            <form:errors path="uuid" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-4" style ="text-align: left;" for="email">Major:</label>
                    <div class="col-md-7">
                        <form:input path="major" cssClass="form-control" />
                        <form:errors path="major" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-4" style ="text-align: left;" for="pwd">Minor:</label>
                    <div class="col-md-7">
                        <form:input path="minor" cssClass="form-control" />
                        <form:errors path="minor" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-4" style ="text-align: left;" for="pwd">DangerLevel:</label>
                    <div class="col-md-7">
                        <form:input path="dangerLevel" cssClass="form-control" />
                        <form:errors path="dangerLevel" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-4" style ="text-align: left;" for="pwd">Longitude:</label>
                    <div class="col-md-7">
                        <form:input path="longitude" cssClass="form-control" />
                        <form:errors path="longitude" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-4" style ="text-align: left;" for="pwd">Latitude:</label>
                    <div class="col-md-7">
                        <form:input path="latitude" cssClass="form-control" />
                        <form:errors path="latitude" />
                    </div>
                </div>


                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type="submit" value="Create" class="btn btn-primary"/>
                    </div>
                </div>
            </div>
        </form:form>
    </div>


</div>


<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <div class = "table-responsive">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>BeaconId</th>
                    <th>UUID</th>
                    <th>Major</th>
                    <th>Minor</th>
                    <th>Dangerlevel</th>
                    <th>Longitude</th>
                    <th>Latitude</th>
                    <th></th>

                </tr>
            </thead>
            <tbody>

                <c:forEach var="beacon" items="${beacons}">
                    <tr>
                        <td>${beacon.beaconId}</td>
                        <td>${beacon.uuid}</td>
                        <td>${beacon.major}</td>
                        <td>${beacon.minor}</td>
                        <td>${beacon.dangerLevel}</td>
                        <td>${beacon.longitude}</td>
                        <td>${beacon.latitude}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/beacon/edit/${beacon.beaconId}">Edit</a>
                            <a href="${pageContext.request.contextPath}/beacon/delete/${beacon.beaconId}">Delete</a>
                            <a href="${pageContext.request.contextPath}/beacondata/list/${beacon.beaconId}">Beacon Data</a>


                        </td>
                    </tr> 
                </c:forEach>     
            </tbody>
        </table>
    </div>
</div>


<jsp:include page="footer.jsp"></jsp:include>  

