<%-- 
    Document   : beaconPageChange
    Created on : 21-nov-2016, 23:41:12
    Author     : gerbrecht
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
                <h1>Edit Beacon</h1>
                <div class="alert alert-success ${message != null ? "" : "hidden"}" role="alert">${message}</div>
                <form:form class="form-horizontal" method="POST" modelAttribute="Beacon" commandName="Beacon" action="${pageContext.request.contextPath}/beacons/edit">
                    <div class="form-group">
                        <label class="col-md-4 control-label" style ="text-align: left; display: none">beaconId:</label>
                        <div class="col-md-7">
                            <form:input path="beaconId" cssClass="form-control" value="${beacon.beaconId}" style="display: none"/>
                            <form:input path="uuid" cssClass="form-control" value="${beacon.uuid}" style="display: none"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" style ="text-align: left;">Major:</label>
                        <div class="col-md-7">
                            <form:input path="major" cssClass="form-control" value="${becon.major}"/>
                           
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" style ="text-align: left;">Minor:</label>
                        <div class="col-md-7">
                            <form:input path="minor" cssClass="form-control" value="${beacon.minor}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" style ="text-align: left;" >Dangerlevel:</label>
                        <div class="col-md-7">
                            <form:input path="dangerLevel" cssClass="form-control" value="${beacon.dangerLevel}" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" style ="text-align: left;">longitude:</label>
                        <div class="col-md-7">
                            <form:input path="longitude" cssClass="form-control"  value="${beacon.longitude}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" style ="text-align: left;">latitude:</label>
                        <div class="col-md-7">
                            <form:input path="latitude" cssClass="form-control" value="${beacon.latitude}" />
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
