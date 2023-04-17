<?php
	ini_set('display_errors', 1);
	if($_SERVER['REQUEST_METHOD'] == 'POST'){
		$rid = $_POST['rid'];
		$con = mysqli_connect('localhost:3306', 'root', 'Ming@12321', 'robotDogService') or die(mysqli_connect_error());

		$sql = "delete from Reservations where ReserveID = $rid";

		mysqli_query($con, $sql) or die(mysqli_error($con));
		$rows = mysqli_affected_rows($con);
		if($rows > 0){
			echo 'The Booking is deleted';
		}else{
			echo 'Error: ' . $sql . '<br />' . mysqli_error($con);
		}
	}else{
		echo 'No data received';
	}
	
?>
