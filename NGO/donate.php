php
<?php include 'db_connection.php'; ?>

<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $name = $_POST['name'];
    $email = $_POST['email'];
    $amount = $_POST['amount'];

    $sql = "INSERT INTO donations (donor_id, amount, date, payment_method) VALUES (NULL, '$amount', NOW(), 'Online')";
    
    if ($conn->query($sql) === TRUE) {
        echo "Donation successful! Thank you, $name.";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Donate</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <?php include 'header.php'; ?>
    <section>
        <h2>Make a Donation</h2>
        <form action="donate.php" method="post">
            <label for="name">Full Name:</label>
            <input type="text" id="name" name="name" required>
            
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            
            <label for="amount">Donation Amount ($):</label>
            <input type="number" id="amount" name="amount" required>

            <button type="submit">Donate</button>
        </form>
    </section>
    <?php include 'footer.php'; ?>
</body>
</html>