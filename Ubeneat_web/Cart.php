<?php
$user_ID = isset($_POST['user_ID']) ? $_POST['user_ID'] : "0";
$store_ID = isset($_POST['store_ID']) ? $_POST['store_ID'] : "0";
$m1 = isset($_POST['m1']) ? $_POST['m1'] : "0";
$m2 = isset($_POST['m2']) ? $_POST['m2'] : "0";
$m3 = isset($_POST['m3']) ? $_POST['m3'] : "0";
$i = 0;
$price = array();
$con = mysqli_connect("localhost", "root", "111111111", "ubereat");
$sql = "SELECT `meal_N`, `meal_name`, `meal_price`, `image` FROM `meal` WHERE `store_N`=$store_ID";
$result = mysqli_query($con, $sql); ?>

<html>

<head>
  <title>Ubeneat</title>
</head>

<body>
  <center>
    <h1>Cart</h1>
    <?php
    while (($row_result = $result->fetch_assoc())) {
      $price[$i] = $row_result["meal_price"];
    ?>
      <?php if ($i == 0) { ?>
        <?php if ($m1 > 0) { ?>
          <font size="4"><?php echo $row_result["meal_name"]; ?></br>$<?php echo $price[$i]; ?>*<?php echo $m1; ?></br></font>
        <?php } ?>
      <?php } elseif ($i == 1) { ?>
        <?php if ($m2 > 0) { ?>
          <font size="4"><?php echo $row_result["meal_name"]; ?></br>$<?php echo $price[$i]; ?>*<?php echo $m2; ?></br></font>
        <?php } ?>
      <?php } elseif ($i == 2) { ?>
        <?php if ($m3 > 0) { ?>
          <font size="4"><?php echo $row_result["meal_name"]; ?></br>$<?php echo $price[$i]; ?>*<?php echo $m3; ?></br></font>
        <?php } ?>
      <?php } ?>
      <?php $i = $i + 1; ?>
    <?php } ?>
    <?php
    $total = $m1 * $price[0] + $m2 * $price[1] + $m3 * $price[2];
    ?>
    <font size="4">total=$<?php echo $total; ?></font>
    <form action="/Ubeneat_web/Order.php" method="post">
      <p>
        <font size="4">Note:</font>
        <input type="text" name="note" placeholder="spicy,ice/sugar">
      </p>
      </br>
      <input type="hidden" name="insert" value="1">
      <input type="hidden" name="user_ID" value=<?php echo $user_ID; ?>>
      <input type="hidden" name="store_ID" value=<?php echo $store_ID; ?>>
      <input type="hidden" name="m1" value=<?php echo $m1; ?>>
      <input type="hidden" name="m2" value=<?php echo $m2; ?>>
      <input type="hidden" name="m3" value=<?php echo $m3; ?>>
      <input type="hidden" name="total" value=<?php echo $total; ?>>
      <input type="submit" value="Order">
    </form>
  </center>
</body>
<?php mysqli_close($con); ?>

</html>