<?php
    ini_set('display_errors', 1);
    $hostname = "localhost:3306";
    $username = "root";
    $pwd = "Ming@12321";
    $db = "testing";
    $conn = mysqli_connect($hostname, 
            $username, $pwd, $db) 
            or die(mysqli_connect_error());

    $sql = "SELECT * FROM booking";
    $rs = mysqli_query($conn, $sql);

    $emparray = array();
   
    while($rc = mysqli_fetch_assoc($rs)) {
        $emparray[] = $rc;
        
    };
    
    echo json_encode($emparray);
    
    mysqli_close($conn);
?>