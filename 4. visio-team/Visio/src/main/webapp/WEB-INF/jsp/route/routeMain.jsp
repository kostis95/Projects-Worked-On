<%-- 
    Document   : RouteMain
    Created on : 24-nov-2016, 12:03:08
    Author     : gerbrecht
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<jsp:include page="../header.jsp"></jsp:include> 


    <div class="row col-md-offset-2 col-md-10 ">
        <h1>Route Information</h1>

        <div class="col-md-6 text-left">
            <h3>Information</h3>
            <div class="container-fluid">
                <form action="" class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="pwd">Begin:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="routeBegin" placeholder="Enter Begin:">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="pwd">End:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="routeEnd" placeholder="Enter End:">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="pwd">Description:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="routeDescription" placeholder="Enter Description:">
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="pwd">User:</label>
                        <div class="col-sm-10">
                            <select multiple id="externalUser" class="form-control">
                            <c:forEach var="user" items="${externalUsers}" >                                
                                <option value="${user.externalUserId}">${user.name}</option>
                            </c:forEach>
                              </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <input value="Create" class="btn btn-primary" onclick="saveRoute();" />
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-md-6">
            <h3>Bestaande routes</h3>
            <div class="well">
                <div class = "table-responsive">
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>RouteId</th>
                                <th>Begin</th>
                                <th>End</th>
                                <th>Description</th>


                            </tr>
                        </thead>
                        <tbody>

                        <c:forEach var="route" items="${routes}" >
                            <tr onclick="document.location = '${pageContext.request.contextPath}/route/view/${route.routeId}';">
                                <td>${route.routeId}</td>
                                <td>${route.begin}</td>
                                <td>${route.end}</td>
                                <td>${route.description}</td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="row col-md-10 col-md-offset-2">
    <h3>Beacons</h3>

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
        var externalUser = $("#externalUser").val();
        
        customJson = {
            begin: begin,
            end: end,
            description: description,
            waypoints: selectedMarkers,
            externalUsers: externalUser
        };

        xmlhttp.open("POST", window.location.href);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(JSON.stringify(customJson));
        //TODO: handle response.
        location.reload();
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
