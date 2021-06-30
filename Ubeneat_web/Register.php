<?php

$con = mysqli_connect("localhost", "root", "111111111", "ubereat");

$name = $_POST['t1'];
$phone = $_POST['t2'];
$address = $_POST['t3'];
//select customer_number from customer_info
$sql = "SELECT `customer_number` FROM `customer` WHERE `name`='$name' and `customer_address`='$address' and `phone`='$phone'";
$result = mysqli_query($con, $sql);

?>

<html>

<head>
  <title>Ubeneat</title>
</head>

<body>
  <center>
    <h1>Register</h1>
    <form action="http://127.0.0.1/Ubeneat_web/Register.php" method="post">

      <p>Name:</p>
      <p><input type="text" name="t1"></p>

      <p>Phone number:</p>
      <p><input type="text" name="t2"></p>

      <p>Address:</p>
      <p><input type="text" name="t3"></p>
      </br>
      <p><input type="submit" value="register/login"></p>
    </form>
    <?php if (!($row_result = $result->fetch_assoc())) {
      $sql_ins = "INSERT INTO `customer`(`name`, `customer_address`,`phone`) VALUES ('$name','$address','$phone')";
      mysqli_query($con, $sql_ins);
      $sql_sel_sec = "SELECT `customer_number` FROM `customer` WHERE `name`='$name' and `customer_address`='$address' and `phone`='$phone'";
      $result = mysqli_query($con, $sql_sel_sec);
      $row_result = $result->fetch_assoc();
    }
    ?>
    <form action="/Ubeneat_web/Menu.php" method="post">
      <input type="hidden" name="user_ID" value=<?php echo $row_result["customer_number"]; ?>>
      <input type="submit" value="Go to menu">
    </form>
    </br>

    <form action="/Ubeneat_web/Delivery.php" method="post">
      <input type="submit" value="Delivery backend">
    </form>
    <form action="/Ubeneat_web/Store.php" method="post">
      <input type="submit" value="Store backend">
    </form>

  </center>
</body>
<?php mysqli_close($con); ?>

</html>