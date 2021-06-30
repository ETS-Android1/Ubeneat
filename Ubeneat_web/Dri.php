<?php
$user_ID = isset($_POST['user_ID']) ? $_POST['user_ID'] : "0";
$store_ID = isset($_POST['store_ID']) ? $_POST['store_ID'] : "0";
$con = mysqli_connect("localhost", "root", "111111111", "ubereat");
//select meal_info from stroe_number

$sql = "SELECT `meal_N`, `meal_name`, `meal_price`, `image` FROM `meal` WHERE `store_N`=$store_ID";
$result = mysqli_query($con, $sql);
$i = 0;
?>
<html>

<head>
  <title>Ubeneat</title>
</head>

<body>
  <center>
    <h1>Fifty blue</h1>
    <form action="/Ubeneat_web/Cart.php" method="post">
      <?php while (($row_result = $result->fetch_assoc())) { ?>
        <p><img src=<?php echo $row_result["image"]; ?> width="200" height="150"></p>
        <font size="4"><?php echo $row_result["meal_name"]; ?></br>$<?php echo $row_result["meal_price"]; ?></font>
        <?php if ($i == 0) { ?>
          <p><input type="text" name="m1" value="0"></p>
        <?php } elseif ($i == 1) { ?>
          <p><input type="text" name="m2" value="0"></p>
        <?php } elseif ($i == 2) { ?>
          <p><input type="text" name="m3" value="0"></p>
        <?php } ?>
        <?php $i = $i + 1; ?>
      <?php } ?>
      </br>
      <input type="hidden" name="user_ID" value=<?php echo $user_ID; ?>>
      <input type="hidden" name="store_ID" value=<?php echo $store_ID; ?>>
      <input type="submit" value="Cart">

    </form>
  </center>
</body>
<?php mysqli_close($con); ?>

</html>