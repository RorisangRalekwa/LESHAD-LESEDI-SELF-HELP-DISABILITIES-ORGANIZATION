<?php include 'db_connection.php'; ?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>NGO Website</title>
    <link rel="stylesheet" href="css/style.css">  <!-- Link to CSS file -->
</head>
<body>

    <header>
        <h1>Welcome to Our NGO</h1>
        <nav>
            <ul>
                <li><a href="index.php">Home</a></li>
                <li><a href="about.php">About Us</a></li>
                <li><a href="donate.php">Donate</a></li>
                <li><a href="contact.php">Contact</a></li>
            </ul>
        </nav>
    </header>

    <section>
        <h2>Our Mission</h2>
        <p>We strive to help communities by providing food, shelter, and education to those in need.</p>
        <a href="donate.php" class="btn">Make a Donation</a>
    </section>

    <footer>
        <p>&copy; 2025 NGO Organization. All Rights Reserved.</p>
    </footer>

</body>
</html>