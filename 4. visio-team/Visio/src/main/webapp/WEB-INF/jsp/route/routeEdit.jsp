<%-- 
    Document   : routeEdit
    Created on : Jan 9, 2017, 2:13:58 PM
    Author     : geoffrey
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<jsp:include page="../header.jsp"></jsp:include> 


    <div class="row col-md-offset-2 col-md-10 ">
        <h1>Route</h1>

        <div class="col-md-6 text-left">
                        <h3>Information</h3>
            <div class="container-fluid">
                <form action="" class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="pwd">Begin:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="routeBegin" placeholder="${route.begin}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="pwd">End:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="routeEnd" placeholder="${route.end}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="pwd">Description:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="routeDescription" placeholder="${route.description}">
                        </div>
                    </div>

                    
                </form>
            </div>
        </div>
        <div class="col-md-6">
                        <h3>External users</h3>
            <div class="well">
                <div class = "table-responsive">
                     <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>birth date</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${users}" >
                            <tr>
                                <td>${user.externalUserId}</td>
                                <td>${user.name}</td>
                                <td>${user.birthdate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>

<div class="row col-md-10 col-md-offset-2">
    <h3>waypoints with order</h3>

    <div class="" id="map"></div>
</div>
<script>

    var selectedMarkers = [];

    function initMap() {
        var amsterdam = {lat: 52.379189, lng: 4.899431};
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 4,
            center: amsterdam
        });
    <c:forEach var="waypoint" items="${waypoints}" varStatus="loop">
        var marker${loop.index} = new google.maps.Marker({
            position: new google.maps.LatLng(${waypoint.beaconbeaconId.getLatitude()},${waypoint.beaconbeaconId.getLongitude()}),
            map: map,
            label: "${waypoint.waypointsPK.orderId + 1}"
        });

        google.maps.event.addListener(marker${loop.index}, 'click', function (a) {
            if (addSelectedMarker(${waypoint.beaconbeaconId.getBeaconId()})) {
                marker${loop.index}.setLabel("" + (selectedMarkers.length));
            }
        });
    </c:forEach>
    }
    function addSelectedMarker(beaconId) {
        if (selectedMarkers.indexOf(beaconId) < 0) {
            selectedMarkers.push(beaconId);
            return true;
        } else {
            console.log("marker is al geselecteerd");
        }
        console.log(selectedMarkers.toString());
        return false;
    }
    
    function test(position) {
        window.alert(position);
    }

    function postSelectedBeacons() {
        xmlhttp = new XMLHttpRequest;

        var begin = $("#routeBegin").val();
        var end = $("#routeEnd").val();
        var description = $("#routeDescription").val();
        
        customJson = {
            begin: begin,
            end: end,
            description: description,
            waypoints: selectedMarkers
        };

        xmlhttp.open("POST", window.location.href);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(JSON.stringify(customJson));
        
        //TODO: handle response.
    }

    function saveRoute() {
        postSelectedBeacons();
    }
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCi6nY5x3g1jWA-nFAZoSFFQDZGrM9MqJ0&callback=initMap">
</script>
<style>
    #map {
        height: 400px;
    }
</style>
<jsp:include page="../footer.jsp"></jsp:include>  
