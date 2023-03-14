<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<?php

// Database connection settings
$host = "localhost";
$user = "root";
$password = "";
$dbname = "test";

// Connect to the database
$conn = mysqli_connect($host, $user, $password, $dbname);

// Check for errors
if (mysqli_connect_errno()) {
  die("Failed to connect to MySQL: " . mysqli_connect_error());
}
$table="info";
// Define the SQL query to retrieve data
$sql = "SELECT * FROM ".$table;

// Execute the query
$result = mysqli_query($conn, $sql);

// Check for errors
if (!$result) {
  die("Failed to retrieve data from the database: " . mysqli_error($conn));
}

?>
<div class="container">
    <table>
        <tr>
            <th>id</th>
            <th>state</th>
            <th>district</th>
            <th>locality</th>
            <th>building</th>
            <th>departement</th>
            <th>temperature</th>
            <th>humidity</th>
            <th>consumption</th>
            <th>status</th> 
            <th>date_time</th>
        </tr>

            <?php
        while ($row = mysqli_fetch_assoc($result)) {
                    $id=$row['id'] ;
                    $state=$row['state'] ;
                    $district=$row['district'] ;
                    $locality=$row['locality'] ;
                    $building=$row['building'] ;
                    $departement=$row['department'] ;
                    $temperature=$row['temperature'] ;
                    $humidity=$row['humidity'] ;
                    $consumption=$row['consumption'] ;
                    $status=$row['status'] ;
                    $date_time=$row['date_time'] ;


                    ?>
                     <tr>
                         <td><?php echo $id; ?></td>
                         <td><?php echo $state; ?></td>
                         <td><?php echo $district; ?></td>
                         <td><?php echo $locality; ?></td>
                         <td><?php echo $building; ?></td>
                         <td><?php echo $departement; ?></td>

                         <td><?php echo $temperature; ?></td>
                         <td><?php echo $humidity; ?></td>
                         <td><?php echo $consumption; ?></td>
                         <td><?php echo $status; ?></td>
                         <td><?php echo $date_time; ?></td>

                     </tr>

                    <?php
                    }
                     ?> 
    </table> 

</div>


<div class="address">
    <form action="monitoring.php" method="POST">
        <label for="state">state:    </label>
        <input type="text" id="state" name="state"><br>
        <label for="state">district: </label>
        <input type="text" id="district" name="district"><br>
        <label for="locality">locality: </label>
        <input type="text" id="locality" name="locality"><br>
        <label for="state">building:   </label>
        <input type="text" id="building" name="building"><br>
        <label for="state">departement: </label>
        <input type="text" id="departement" name="departement"><br>
        <input type="submit" value="submit">
    </form>
</div>


<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST'){
$state=$_POST['state'];
$district=$_POST['district'];
$locality=$_POST['locality'];
$building=$_POST['building'];
$departement=$_POST['departement'];


$sql = "SELECT * FROM info WHERE department=".$departement;
echo $state;
// Execute the query
$result = mysqli_query($conn, $sql);

// Check for errors
if (!$result) {
  die("Failed to retrieve data from the database: " . mysqli_error($conn));
}

?>
<div class="container">
    <table>
        <tr>
            <th>id</th>
            <th>state</th>
            <th>district</th>
            <th>locality</th>
            <th>building</th>
            <th>departement</th>
            <th>temperature</th>
            <th>humidity</th>
            <th>consumption</th>
            <th>status</th> 
            <th>date_time</th>
        </tr>

            <?php
        while ($row = mysqli_fetch_assoc($result)) {
                    $id=$row['id'] ;
                    $state=$row['state'] ;
                    $district=$row['district'] ;
                    $locality=$row['locality'] ;
                    $building=$row['building'] ;
                    $departement=$row['department'] ;
                    $temperature=$row['temperature'] ;
                    $humidity=$row['humidity'] ;
                    $consumption=$row['consumption'] ;
                    $status=$row['status'] ;
                    $date_time=$row['date_time'] ;


                    ?>
                     <tr>
                         <td><?php echo $id; ?></td>
                         <td><?php echo $state; ?></td>
                         <td><?php echo $district; ?></td>
                         <td><?php echo $locality; ?></td>
                         <td><?php echo $building; ?></td>
                         <td><?php echo $departement; ?></td>

                         <td><?php echo $temperature; ?></td>
                         <td><?php echo $humidity; ?></td>
                         <td><?php echo $consumption; ?></td>
                         <td><?php echo $status; ?></td>
                         <td><?php echo $date_time; ?></td>

                     </tr>

                    <?php
                    }//end of while
                
                     ?> 
    </table> 





<?php
}//end of if(request==post)
// Close the database connection
mysqli_close($conn);

?>
</body>
</html>


