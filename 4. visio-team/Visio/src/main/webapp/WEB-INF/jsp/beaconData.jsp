<%-- 
    Document   : beaconData
    Created on : Dec 11, 2016, 9:17:42 PM
    Author     : Kostis
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="header.jsp"></jsp:include> 



<html>
    <head>
        <!-- Custom styles for this template -->
        <link href="resources/css/css/dashboard.css" rel="stylesheet">
        
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" rel="stylesheet" media="screen">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <h1>BeaconData(${beaconid})</h1>
    </head>
    <body>
<table class="table table-striped">
                <thead>
                    <tr>
                        <th>VITID</th>
                        <th>Datatype</th>
                        <th></th>
                    </tr>
                </thead>
                
                
               
                
          
               
<!--                modelattribute matcht de klasse -->
                    <form:form class="form-horizontal" method="POST" modelAttribute="BeaconData" 
                               commandName="BeaconData" action="${pageContext.request.contextPath}/beacondata/add/${beaconid}">

                   
                     <div class="col-md-8" >
                        
                            <label  style ="text-align: left;" for="pwd">Data:</label>
                           
                                <form:input path="data" cssClass="form-control" type="text"  />
                                <form:errors path="data" />
                          
                    </div>
                            <div class="col-md-6" >
                        <!--                    path matcht het attribuut-->
                        <label style ="text-align: left;" for="pw">VIT :</label>
                       <form:select path="vit.vITId">      
<!--                        vitlist is gelijk met naam in controller view.addObject("vitlist"...-->
                        <form:options items="${vitlist}" itemValue="vITId" itemLabel="description" />
                        </form:select>
                        
                </div>
                <div class="col-md-8" >
                    <label style ="text-align: left;" for="pw">Datatype :</label>
                    <form:select path="dataType.dataTypeId">   
<!--                        itemvalue-itemlabel = key-value attributen uit het datatypemodel-->
                        <form:options items="${datatypelist}" itemValue="dataTypeId" itemLabel="description" />
                        </form:select>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-0 col-sm-8">
                        <input type="submit" value="Create" class="btn btn-primary"/>
                    </div>
                </div>
                    </form:form>
                    
                <tbody>

                    <c:forEach var="beacondata" items="${beacondatalist}">
                        <tr>
                            <td>${beacondata.vit.description}</td>
                            <td>${beacondata.dataType.description}</td>

                            <td>
<!--                            <a href="${pageContext.request.contextPath}/beacon/edit/${beaconid}">Edit</a>-->
                                <a href="${pageContext.request.contextPath}/beacon/delete/${beaconid}">Delete</a>
<!--                                <a href="${pageContext.request.contextPath}/beacondata/doUpload/${beaconid}/${beacondata.dataType.dataTypeId}/${beacondata.vit.vITId}">Upload File</a>-->
                            </td>
                        </tr> 
                    </c:forEach>     
                </tbody>
            </table>
 </body>
 
</html
             