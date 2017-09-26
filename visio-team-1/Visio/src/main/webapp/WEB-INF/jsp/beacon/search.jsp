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
    <div class="row col-md-offset-2 col-md-10 ">
        <div class="well">
        <f:form method="POST" commandName="Beacon" action="${pageContext.request.contextPath}/beacons/beacons">
            <a class="btn btn-primary" href="${pageContext.servletContext.contextPath}/beacons/add">Create</a>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th>major</th>
                            <th>minor</th>
                            <th>danger level</th>
                            <th>longitude</th>
                            <th>latitude</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="beacon" items="${beacons}">
                            <tr>
                                <td>${beacon.major}</td>
                                <td>${beacon.minor}</td>
                                <td>${beacon.dangerLevel}</td>
                                <td>${beacon.longitude}</td>
                                <td>${beacon.latitude}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/beacons/edit/${beacon.beaconId}">Edit</a>
                                    <a href="${pageContext.request.contextPath}/beacons/delete/${beacon.beaconId}">Delete</a><br/>
                                    <a href="${pageContext.request.contextPath}/beacondata/list/${beacon.beaconId}">Beacon Data</a>
                                </td>
                            </tr> 
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="well">
                <p></p>
            </div>

            </f:form>
        </div>

        <style>
            #map {
                height: 400px;
            }
        </style>
        <h3>Beacon Map</h3>
        <div id="map"></div>
</div>
        <script>

            var selectedMarkers = [];
            var marker = null;

            function initMap() {
                var amsterdam = {lat: ${centerLat}, lng: ${centerLon}};
                var map = new google.maps.Map(document.getElementById('map'), {
                    zoom: ${zoomLevel},
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
                window.location = window.location.origin + "/visio/beacons/search/" + location.lat() + "/" + location.lng();
                //xmlhttp = new XMLHttpRequest;
                //xmlhttp.open("POST", window.location.href);
                //xmlhttp.setRequestHeader("Content-Type", "application/json");
                //console.log(" tostring location: " + location.toString());
                //console.log(" json location: " + JSON.stringify(location.toJSON()));
                //xmlhttp.send(JSON.stringify(location.toJSON()));
                console.log(loc);
            }
        </script>
        <script async defer
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCi6nY5x3g1jWA-nFAZoSFFQDZGrM9MqJ0&callback=initMap">
        </script>

        <input type="submit" value="Zoeken" hidden="true" />

<jsp:include page="../footer.jsp"></jsp:include>
