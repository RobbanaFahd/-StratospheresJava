<?php
// Create a connection to the database
$host = 'localhost';
$username = 'root';
$password = '';
$database = 'pidev';

$conn = mysqli_connect($host, $username, $password, $database);

// Retrieve the longitude and latitude from the database
$query = 'SELECT longitude, latitude FROM banque_de_sang WHERE id = 50'; // Replace '1' with the ID of the marker you want to retrieve
$result = mysqli_query($conn, $query);
$data = mysqli_fetch_assoc($result);

// Return the data as JSON
header('Content-Type: application/json');
echo json_encode($data);
?>
