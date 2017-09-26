<%-- 
    Document   : TestHeader
    Created on : 8-nov-2016, 12:39:51
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>

        <%-- script for linking documents used in beaconpage --%>
        <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" charset="utf-8"></script>
        <script type="text/javascript" src="http://localhost:8080/visio/resources/css/js/bootstrap-filestyle.min.js" charset="utf-8">

            $(":file").filestyle({buttonName: "btn-primary"});
        </script>
        <%-- script for sidebar active--%>
        <script type="text/javascript">
            $(document).ready(function () {
                var url = window.location;
                // Will only work if string in href matches with location
                $('ul.nav a[href="' + url + '"]').parent().addClass('active');

                // Will also work for relative and absolute hrefs
                $('ul.nav a').filter(function () {
                    return this.href == url;
                }).parent().addClass('active').parent().parent().addClass('active');
            });
        </script>
        
        <%-- --%>   

        
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" rel="stylesheet" media="screen">
        
        <!-- Custom styles for this template -->
        <link href="<h:url value="http://localhost:8080/visio/resources/css/css/dashboard.css" />" rel="stylesheet">
        <!--<link href="../resources/css/css/dashboard.css" rel="stylesheet">-->
    </head>
    <body>

        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Visio</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="${pageContext.request.contextPath}/">Dashboard</a></li>
                        <li><a>Settings</a></li>
                        <li><a>Profile</a></li>
                        <li><a>Help</a></li>
                        <li><a href="${pageContext.request.contextPath}/user/logout">Logout</a></li>
                    </ul>
                    <form class="navbar-form navbar-right">
                        <input type="text" class="form-control" placeholder="Search...">
                    </form>
                </div>
            </div>
        </nav>

        <div class="container-fluid" >
            <div class="row" >

                <div class="col-sm-3 col-md-2 sidebar" style="background-color: #D8D8D8;">
                    <ul class="nav nav-sidebar">
                        <li class=""><a href="${pageContext.request.contextPath}/">Dashboard <span class="sr-only">(current)</span></a></li>
                        <li><a href="${pageContext.request.contextPath}/beacons/search">Beacons</a></li>
                        <li><a href="${pageContext.request.contextPath}/externaluser/overview">External users</a></li>
                        <li><a href="${pageContext.request.contextPath}/routeMain">Routes</a></li>
                    </ul>
                    <ul class="nav nav-sidebar">
                        <li><a href="${pageContext.request.contextPath}/vits/overview">User types</a></li>
                        <li><a href="${pageContext.request.contextPath}/datatypes/overview">Data types</a></li>
                    </ul>
                    <ul class="nav nav-sidebar">
                        <li><a href="${pageContext.request.contextPath}/user/userlist">Administrators</a></li>
                    </ul>


                </div>



