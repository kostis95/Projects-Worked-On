<?php
	include 'datalogin.php';

	// Use a mysql query to fetch data from the database
	$sql = "SELECT * FROM locations";
	$result = mysqli_query($conn, $sql);


	//fetch_assoc() will automatically keep fetching next row when called again
	//Convert MySQL Result Set to PHP Array & Convert php array to JSON string
	$allLocations = array();
	while($row = mysqli_fetch_assoc($result)) {
    $allLocations[] = $row;
		}
		header('Content-Type: application/json');
		echo json_encode($allLocations);
?>
