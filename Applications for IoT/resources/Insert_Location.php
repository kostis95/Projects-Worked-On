<?php
	include 'datalogin.php';
	$lat = $_GET["lat"];
	$lon = $_GET["lon"];
	serialize($lat);
	echo "<p>lattitude=$lat</p>";
	echo "<p>longitude=$lon</p>";

	date_default_timezone_set('Europe/Amsterdam');
	// $mydate = date("Y-m-d H:i:s");
	$date = date("Y-m-d H:i:s");
	// print( time ());


	//Geolocatie insert into Database
	$sql = "INSERT INTO locations (lat,lon,time)
	VALUES ('$lat','$lon', '$date')";
	// public int DateTime::getTimestamp ( void )
	if (mysqli_query($conn, $sql)) {
	    echo "New record created successfully";
	} else {
	    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
	}
	
	mysqli_close($conn);

?>
