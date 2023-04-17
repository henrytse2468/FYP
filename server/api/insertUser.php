<?php
    ini_set('display_errors', 1);
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        
        $id = $_POST['id'];

        $hostname = "localhost:3306";
        $username = "root";
        $pwd = "Ming@12321";
        $db = "robotDogService";
        $conn = mysqli_connect($hostname,
                $username, $pwd, $db)
                or die(mysqli_connect_error());

        $sql = "INSERT INTO Users 
                VALUES ('$id', '/home/ivetm/faceDataset/$id')";

        if (mysqli_query($conn, $sql)){
            echo "New record created successfully";
            } else {
                echo "Error: " . $sql . "<br>" . mysqli_error($conn);
        }

        mysqli_close($conn);
        
    }else{
       echo "No data received";
    }
?>


