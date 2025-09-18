php
<?php
$servername = "localhost";
$username = "root";  // Default WAMP username
$password = "";      // Default password is empty
$dbname = "NGO";  // Your database name

$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
?>