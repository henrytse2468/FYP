<?php
    ini_set('display_errors', 1);
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $start_time = $_POST['start_time'];
        $id = $_POST['id'];
	    $end_loc = $_POST['destination'];
	    $start_loc = $_POST['start_loc'];
	    $booking_date = $_POST['booking_date'];

        $hostname = "localhost:3306";
        $username = "root";
        $pwd = "Ming@12321";
        $db = "robotDogService";
        $conn = mysqli_connect($hostname, 
                $username, $pwd, $db) 
                or die(mysqli_connect_error());

	    $sql = "INSERT INTO Reservations (userID, start_time, start_point, destination, reserve_date)
		    VALUES ('$id', '$start_time', '$start_loc', '$end_loc', '$booking_date')";

        if (mysqli_query($conn, $sql)){
            echo "New record created successfully";
            } else {
                echo "Error: " . $sql . "<br>" . mysqli_error($conn);
            }

        $sql2 = "UPDATE Timeslots SET availability ='occupied' WHERE start_time= '$start_time' ";


        if (mysqli_query($conn, $sql2)){
            echo "timeSlot update successfully";
            } else {
                echo "Error: " . $sql2 . "<br>" . mysqli_error($conn);
            }
        
        mysqli_close($conn);
    }else{
       echo "No data received";
    }
?>
