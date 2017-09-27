<%-- 
    Document   : beaconPage
    Created on : 15-nov-2016, 14:19:05
    Author     : gerbrecht
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<jsp:include page="../header.jsp"></jsp:include> 


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
    </div><div class="col-md-12">
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

            <head>
                <style>
                    #map {
                        height: 400px;
                        width: 100%;
                    }
                </style>
            </head>
            <body>
                <h3>My Google Maps Demo</h3>
                <div id="map"></div>
                <script>

                    var selectedMarkers = [];
                    var marker = null;

                    function initMap() {
                        var amsterdam = {lat: 52.379189, lng: 4.899431};
                        var map = new google.maps.Map(document.getElementById('map'), {
                            zoom: 4,
                            center: amsterdam
                        });
                        google.maps.event.addListener(map, 'click', function (event) {
                            addMarker(event.latLng, map);
                        });
                    <c:forEach var="beacon" items="${beacons}" varStatus="loop">
                        var marker${loop.index} = new google.maps.Marker({
                            position: new google.maps.LatLng(${beacon.getLatitude()},${beacon.getLongitude()}),
                            map: map

                        });

                        google.maps.event.addListener(marker${loop.index}, 'click', function (a) {
                            if (addSelectedMarker(${beacon.getBeaconId()})) {
                                marker${loop.index}.setLabel("" + (selectedMarkers.length));
                            }
                        });

                    </c:forEach>
                    }
                    function addMarker(location, map) {
                        if (marker === null) {
                            marker = new google.maps.Marker({
                                position: location,
                                map: map
                            });
                        } else {
                            marker.setMap(null);
                            marker = new google.maps.Marker({
                                position: location,
                                map: map
                            });
                        }

                        sendSelectedMark(location);
                    }

                    function sendSelectedMark(location) {
                        var loc = window.location.host + "/visio/beacons/search/" + location.lat() + "/" + location.lng();
                        window.location.href = loc;
                        //xmlhttp = new XMLHttpRequest;
                        //xmlhttp.open("POST", window.location.href);
                        //xmlhttp.setRequestHeader("Content-Type", "application/json");
                        //console.log(" tostring location: " + location.toString());
                        //console.log(" json location: " + JSON.stringify(location.toJSON()));
                        //xmlhttp.send(JSON.stringify(location.toJSON()));
                        console.log(loc);
                        $('#longitude').val(location.lng());
                        $('#latitude').val(location.lat());
                    }
                </script>
                <script async defer
                        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCi6nY5x3g1jWA-nFAZoSFFQDZGrM9MqJ0&callback=initMap">
                </script>
            </body>
        </div>
    </div>

</div>

<jsp:include page="../footer.jsp"></jsp:include>  

