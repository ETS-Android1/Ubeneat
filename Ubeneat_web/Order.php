<?php
$insert = isset($_POST['insert']) ? $_POST['insert'] : "0";
$user_ID = isset($_POST['user_ID']) ? $_POST['user_ID'] : "0";
$store_ID = isset($_POST['store_ID']) ? $_POST['store_ID'] : "0";
$m1 = isset($_POST['m1']) ? $_POST['m1'] : "0";
$m2 = isset($_POST['m2']) ? $_POST['m2'] : "0";
$m3 = isset($_POST['m3']) ? $_POST['m3'] : "0";
$p1 = isset($_POST['p1']) ? $_POST['p1'] : "0";
$p2 = isset($_POST['p2']) ? $_POST['p2'] : "0";
$p3 = isset($_POST['p3']) ? $_POST['p3'] : "0";
$total = isset($_POST['total']) ? $_POST['total'] : "0";
$note = isset($_POST['note']) ? $_POST['note'] : "";
$Order_del = isset($_POST['Order_del']) ? $_POST['Order_del'] : "0";
$Order_up = isset($_POST['Order_up']) ? $_POST['Order_up'] : "0";
$Order_up_state = isset($_POST['Order_up_state']) ? $_POST['Order_up_state'] : "0";

$new = "1";
$i = 0;
$j = 0;
$j1 = 0;
$con = mysqli_connect("localhost", "root", "111111111", "ubereat");
if ($insert == "1") {
  $sql_ins = "INSERT INTO `orderlist`(`customer_num`, `store_num`, `meal1`, `meal2`, `meal3`, `note`, `total_price`, `state`) VALUES ($user_ID,$store_ID,$m1,$m2,$m3,'$note',$total,1)";
  mysqli_query($con, $sql_ins);
} elseif ($Order_del != "0") {
  $sql_del = "DELETE FROM `orderlist` WHERE order_num=$Order_del";
  mysqli_query($con, $sql_del);
} elseif ($Order_up_state != "0") {
  $sql = "UPDATE `orderlist` SET `state`=5 WHERE order_num=$Order_up_state";
  mysqli_query($con, $sql);
} elseif ($Order_up != "0") {
  $p = $m1 * $p1 + $m2 * $p2 + $m3 * $p3;
  $sql_up = "UPDATE `orderlist` SET `meal1`=$m1,`meal2`=$m2,`meal3`=$m3, `total_price`=$p WHERE order_num=$Order_up";
  mysqli_query($con, $sql_up);
}
$order_num = array();
$order_state = array();
$meal_price1 = array();
$meal_price2 = array();
?>
<html>

<head>
  <title>Ubeneat</title>
</head>

<body>
  <center>
    <h1>
    </h1>
    <h1>Order list</h1>
    <?php

    if ($new == "1") {
      $sql_order = "SELECT `store_num`, `meal1`, `meal2`, `meal3`, `note`, `total_price`, `state`, `order_num` FROM `orderlist` WHERE `customer_num`=$user_ID";
      $result_order = mysqli_query($con, $sql_order);
      $new = "0";
      while ($row_result_order = $result_order->fetch_assoc()) {
        $num = $row_result_order["store_num"];
        $sql_meal = "SELECT `meal_N`, `meal_name`, `meal_price`, `image` FROM `meal` WHERE `store_N`=$num";
        $result = mysqli_query($con, $sql_meal);
        if ($i == 0) {
          $order_num[$i] = $row_result_order["order_num"];
          $order_state[$i] = $row_result_order["state"]; ?>
          <font size="5">Order1</font></br>
          <?php while ($row_result = $result->fetch_assoc()) {
            if ($j == 0) {
              $meal_price1[$j] = $row_result["meal_price"];
              if ($row_result_order["meal1"] > 0) { ?>
                <font size="4"><?php echo $row_result["meal_name"]; ?></br>$<?php echo $row_result["meal_price"]; ?>*<?php echo $row_result_order["meal1"]; ?></br></font>
              <?php }
            } elseif ($j == 1) {
              $meal_price1[$j] = $row_result["meal_price"];
              if ($row_result_order["meal2"] > 0) { ?>
                <font size="4"><?php echo $row_result["meal_name"]; ?></br>$<?php echo $row_result["meal_price"]; ?>*<?php echo $row_result_order["meal2"]; ?></br></font>
              <?php }
            } elseif ($j == 2) {
              $meal_price1[$j] = $row_result["meal_price"];
              if ($row_result_order["meal3"] > 0) { ?>
                <font size="4"><?php echo $row_result["meal_name"]; ?></br>$<?php echo $row_result["meal_price"]; ?>*<?php echo $row_result_order["meal3"]; ?></br></font>
              <?php } ?>
            <?php } ?>
            <?php $j = $j + 1; ?>
          <?php } ?>
          <font size="4">total=$<?php echo $row_result_order["total_price"]; ?></br></font>
        <?php } elseif ($i == 1) {
          $order_num[$i] = $row_result_order["order_num"];
          $order_state[$i] = $row_result_order["state"]; ?>
          <font size="5">Order2</font></br>
          <?php while ($row_result = $result->fetch_assoc()) {
            if ($j == 0) {
              $meal_price2[$j1] = $row_result["meal_price"];
              if ($row_result_order["meal1"] > 0) { ?>
                <font size="4"><?php echo $row_result["meal_name"]; ?></br>$<?php echo $row_result["meal_price"]; ?>*<?php echo $row_result_order["meal1"]; ?></br></font>
              <?php }
            } elseif ($j1 == 1) {
              $meal_price2[$j1] = $row_result["meal_price"];
              if ($row_result_order["meal2"] > 0) { ?>
                <font size="4"><?php echo $row_result["meal_name"]; ?></br>$<?php echo $row_result["meal_price"]; ?>*<?php echo $row_result_order["meal2"]; ?></br></font>
              <?php }
            } elseif ($j1 == 2) {
              $meal_price2[$j1] = $row_result["meal_price"];
              if ($row_result_order["meal3"] > 0) { ?>
                <font size="4"><?php echo $row_result["meal_name"]; ?></br>$<?php echo $row_result["meal_price"]; ?>*<?php echo $row_result_order["meal3"]; ?></br></font>
              <?php } ?>
            <?php } ?>
            <?php $j1 = $j1 + 1; ?>
          <?php } ?>
          <font size="4">total=$<?php echo $row_result_order["total_price"]; ?></br></font>

        <?php } ?>
        <?php

        switch ($order_state[$i]) {
          case 1:
        ?><font size="4">state=processing at store</br></br></font>
          <?php
            break;
          case 2:
          ?><font size="4">state=processing at store</br></br></font>
          <?php
            break;
          case 3:
          ?><font size="4">state=picked up</br></br></font>
          <?php
            break;
          case 4:
          ?><font size="4">state=arrived</br></br></font>
          <?php
            break;
          case 5:
          ?><font size="4">state=done</br></br></font>
            <?php break; ?>
        <?php } ?>
        <?php $i = $i + 1; ?>
      <?php } ?>
    <?php } ?>
    <form action="/Ubeneat_web/Order.php" method="post">
      <?php if ($i > 2) { ?>
        <font>and <?php echo ($i - 2); ?> order(s) more...</font>
      <?php } ?>
      <input type="hidden" name="user_ID" value=<?php echo $user_ID; ?>>
      <?php if ($i == 1) { ?>
        <p><input type="radio" name="Order_del" value=<?php echo $order_num[0]; ?>> Order1</p>
      <?php } elseif ($i >= 2) { ?>
        <p>
          <input type="radio" name="Order_del" value=<?php echo $order_num[0]; ?>> Order1
          <input type="radio" name="Order_del" value=<?php echo $order_num[1]; ?>> Order2
        </p>
      <?php } ?>
      <input type="submit" value="delete">
    </form>


    <form action="/Ubeneat_web/Order.php" method="post">
      <input type="hidden" name="user_ID" value=<?php echo $user_ID; ?>>
      <?php if ($i == 1) { ?>
        <p><input type="radio" name="Order_up" value=<?php echo $order_num[0]; ?>> Order1</p>
      <?php } elseif ($i >= 2) { ?>
        <p>
          <input type="radio" name="Order_up" value=<?php echo $order_num[0]; ?>> Order1
          <input type="radio" name="Order_up" value=<?php echo $order_num[1]; ?>> Order2
        </p>
      <?php } ?>
      <?php if ($j == 1) { ?>
        <p><input type="text" name="m1" placeholder="meal1"></p>
      <?php } elseif ($j == 2) { ?>
        <p>
          <input type="text" name="m1" placeholder="meal1">
          <input type="text" name="m2" placeholder="meal2">
        </p>
      <?php } elseif ($j >= 3) { ?>
        <p>
          <input type="text" name="m1" placeholder="meal1">
          <input type="text" name="m2" placeholder="meal2">
          <input type="text" name="m3" placeholder="meal3">
        </p>
      <?php } ?>
      <input type="hidden" name="p1" value=<?php echo $meal_price1[0]; ?>>
      <input type="hidden" name="p2" value=<?php echo $meal_price1[1]; ?>>
      <input type="hidden" name="p3" value=<?php echo $meal_price1[2]; ?>>
      <input type="submit" value="update">
    </form>


    <form action="/Ubeneat_web/Order.php" method="post">
      <input type="hidden" name="user_ID" value=<?php echo $user_ID; ?>>
      <?php if ($i == 1) { ?>
        <p><input type="radio" name="Order_up_state" value=<?php echo $order_num[0]; ?>> Order1</p>
      <?php } elseif ($i >= 2) { ?>
        <p>
          <input type="radio" name="Order_up_state" value=<?php echo $order_num[0]; ?>> Order1
          <input type="radio" name="Order_up_state" value=<?php echo $order_num[1]; ?>> Order2
        </p>
      <?php } ?>
      <input type="submit" value="done">
    </form>
    </br>
    <form action="/Ubeneat_web/Register.php" method="post">
      <input type="submit" value="home">
    </form>
  </center>
</body>
<?php mysqli_close($con); ?>

</html>