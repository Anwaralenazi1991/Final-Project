<?php

$conn = new mysqli('localhost', 'root', '', 'id13089838_id13058257_quiz');

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

?>
