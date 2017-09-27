<%-- 
    Document   : beaconPageChange
    Created on : 21-nov-2016, 23:41:12
    Author     : gerbrecht
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Custom styles for this template -->
        <link href="resources/css/css/dashboard.css" rel="stylesheet">
        
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" rel="stylesheet" media="screen">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <jsp:include page="../header.jsp"></jsp:include> 

            <div class="col-sm-6 col-sm-offset-3 col-md-8 col-md-offset-2 main">

                

                    <div class="col-md-4 text-left">
                        <h1>Beacon Information</h1>
                        <div class="container-fluid">
                        <form:form method="POST" modelAttribute="Beacon" action="${pageContext.request.contextPath}/beacon/add">
                              <table>
                                  <tr>  
                                    <td></td>    
                                    <td><form:hidden  path="id" /></td>  
                                   </tr>   
                                  <tr>
                                      <td><form:label class="control-label col-sm-2" for="uuid">UUID:</form:label></td>
                                      <td><!--form:input path="uuid" cssClass="form-control" placeholder="Enter UUID:"/--></td>
                                  </tr>    
                                   <tr>
                                      <td><form:label class="control-label col-sm-2"  for="major">Major:</form:label></td>
                                      <td><form:input path="major" cssClass="form-control" placeholder="Enter Major:"/></td>
                                  </tr> 
                              
                                  
                               
                                <!--div class="form-group">
                                    <label class="control-label col-sm-2" for="minor">Minor:</label>
                                    
                                        <form:input path="minor" cssClass="form-control" placeholder="Enter Minor:"/>
                                    
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="dangerLevel">DangerLevel:</label>
                                    <div>
                                        <form:input path="dangerLevel" cssClass="form-control" placeholder="Enter Danger Level:"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="longitude">Longitude:</label>
                                    <div>
                                        <form:input path="longitude" cssClass="form-control" placeholder="Enter Longitude:">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="latitude">Latitude:</label>
                                    <div>
                                        <form:input path="latitude" cssClass="form-control" placeholder="Enter Latitude:">
                                    </div>
                                </div-->
                                </tr>
                                </table>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button type="Save" class="btn btn-default">Save</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        <jsp:include page="../footer.jsp"></jsp:include>

    </body>
</html>
