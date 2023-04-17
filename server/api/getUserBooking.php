<?php
header('content-type: application/json');
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: *");
header("Access-Control-Allow-Headers: Origin, Methods, Content-Type, Authorization");

ini_set('display_error', 1);

$con = mysqli_connect('localhost:3306', 'root', 'Ming@12321', 'robotDogService') or die(mysqli_connect_error());

$sql = 'select * from Reservations';
$result = mysqli_query($con, $sql);

$json_data = array();
while($row = mysqli_fetch_assoc($result)){
	$json_data[] = $row;
}
echo json_encode($json_data)
?>
