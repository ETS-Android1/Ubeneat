<?php

$con = mysqli_connect("localhost", "root", "111111111", "ubereat");
$table = $_POST['t0'];
if ($table == 'get_store_num') {
  $name = $_POST['t1'];
  $sql_sel = "SELECT `store_number` FROM `store` WHERE `store_name`='$name'";
  $result_sel = mysqli_query($con, $sql_sel);
  while (($row_result_sel = $result_sel->fetch_assoc())) {
    $store_ID = $row_result_sel["store_number"];
  }
  $sql_order = "SELECT  `meal1`, `meal2`, `meal3`, `note`, `total_price`, `state` FROM `orderlist` WHERE `store_num`=$store_ID";
  $result_order = mysqli_query($con, $sql_order);
  $sql_meal = "SELECT `meal_N`, `meal_name`, `meal_price`, `image` FROM `meal` WHERE `store_N`=$store_ID";
  $result_meal = mysqli_query($con, $sql_meal);
  $meal_name = array();
  $i = 0;
  while ($row_result_meal = $result_meal->fetch_assoc()) {
    $meal_name[$i] = $row_result_meal["meal_name"];
    $i = $i + 1;
  }
  $j = 0;
}
?>

<html>

<head>
  <title>Ubeneat</title>
</head>

<body>
  <center>
    <h1>Store backend</h1>
    <form action="http://127.0.0.1/Ubeneat_web/Store.php" method="post">
      <input type="hidden" name="t0" value="get_store_num">
      <input type="text" name="t1" placeholder="store name">
      <input type="submit" value="search">
    </form>
    <form action="http://127.0.0.1/Ubeneat_web/Store_menu.php" method="post">
      <input type="hidden" name="store_ID" value=<?php echo $store_ID; ?>>
      <input type="submit" value="store menu setting">
    </form>
    <?php while ($row_result_order = $result_order->fetch_assoc()) { ?>
      <?php $j = $j + 1; ?>
      <?php if ($i == 1) { ?>
        <?php if ($row_result_order["meal1"]) { ?>
          <font size="4">Order<?php echo $j; ?></br><?php echo $meal_name[0]; ?>*<?php echo $row_result_order["meal1"]; ?></br></font>
        <?php } ?>
      <?php } elseif ($i == 2) { ?>
        <?php if ($row_result_order["meal1"]) { ?>
          <font size="4">Order<?php echo $j; ?></br><?php echo $meal_name[0]; ?>*<?php echo $row_result_order["meal1"]; ?></br></font>
        <?php } ?>
        <?php if ($row_result_order["meal2"]) { ?>
          <font size="4"><?php echo $meal_name[1]; ?>*<?php echo $row_result_order["meal2"]; ?></br></font>
        <?php } ?>
      <?php } elseif ($i == 3) { ?>
        <?php if ($row_result_order["meal1"]) { ?>
          <font size="4">Order<?php echo $j; ?></br><?php echo $meal_name[0]; ?>*<?php echo $row_result_order["meal1"]; ?></br></font>
        <?php } ?>
        <?php if ($row_result_order["meal2"]) { ?>
          <font size="4"><?php echo $meal_name[1]; ?>*<?php echo $row_result_order["meal2"]; ?></br></font>
        <?php } ?>
        <?php if ($row_result_order["meal3"]) { ?>
          <font size="4"><?php echo $meal_name[2]; ?>*<?php echo $row_result_order["meal3"]; ?></br></font>
        <?php } ?>
      <?php } ?></br>
    <?php } ?>
    
  </center>
</body>
<?php mysqli_close($con); ?>

</html>