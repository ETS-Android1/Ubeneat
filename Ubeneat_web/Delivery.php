<?php
$select = isset($_POST['select']) ? $_POST['select'] : "0";
$pick_up = isset($_POST['pick_up']) ? $_POST['pick_up'] : "0";
$arrived = isset($_POST['arrived']) ? $_POST['arrived'] : "0";
$new = "1";
$con = mysqli_connect("localhost", "root", "111111111", "ubereat");
if ($select != "0") {
  $sql = "UPDATE `orderlist` SET `state`=2 WHERE order_num=$select";
  mysqli_query($con, $sql);
} elseif ($pick_up != "0") {
  $sql = "UPDATE `orderlist` SET `state`=3 WHERE order_num=$pick_up";
  mysqli_query($con, $sql);
} elseif ($arrived != "0") {
  $sql = "UPDATE `orderlist` SET `state`=4 WHERE order_num=$arrived";
  mysqli_query($con, $sql);
}
$i = 0;
$order_num = array();
?>
<html>

<head>
  <title>Ubeneat</title>
</head>

<body>
  <center>
    <h1>
    </h1>
    <h1>Deliver list</h1>
    <?php
    if ($new == "1") {
      $sql_order = "SELECT `store_address`,`customer_address`,`total_price`, `state`, `order_num` FROM `customer`,`store`,`orderlist` WHERE `customer_number`=`customer_num` and `store_number`=`store_num`";
      $result_order = mysqli_query($con, $sql_order);
      $new = "0";
      while ($row_result_order = $result_order->fetch_assoc()) { ?>
        <?php $order_num[$i] = $row_result_order["order_num"]; ?>
        <?php $i = $i + 1; ?>
        <?php if ($i > 4) {
          continue;
        } ?>
        <font size="4">
          Order<?php echo $i; ?></br>
          store_address=<?php echo $row_result_order["store_address"]; ?></br>
          customer_address=<?php echo $row_result_order["customer_address"]; ?></br>
          total_price=<?php echo $row_result_order["total_price"]; ?></br>
        </font>
        <?php switch ($row_result_order["state"]) {
          case 1:
        ?><font size="4">state=processing at store</br></br></font>
          <?php
            break;
          case 2:
          ?><font size="4">state=selected</br></br></font>
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
      <?php } ?>
    <?php } ?>


    <form action="/Ubeneat_web/Delivery.php" method="post">
      <?php if ($i > 4) { ?>
        <font>and <?php echo ($i - 4); ?> order(s) more...</font>
      <?php } ?>
      <?php if ($i == 1) { ?>
        <p><input type="radio" name="select" value=<?php echo $order_num[0]; ?>> Order1</p>
      <?php } elseif ($i == 2) { ?>
        <p>
          <input type="radio" name="select" value=<?php echo $order_num[0]; ?>> Order1
          <input type="radio" name="select" value=<?php echo $order_num[1]; ?>> Order2
        </p>
      <?php } elseif ($i == 3) { ?>
        <p>
          <input type="radio" name="select" value=<?php echo $order_num[0]; ?>> Order1
          <input type="radio" name="select" value=<?php echo $order_num[1]; ?>> Order2
          <input type="radio" name="select" value=<?php echo $order_num[2]; ?>> Order3
        </p>
      <?php } elseif ($i >= 4) { ?>
        <p>
          <input type="radio" name="select" value=<?php echo $order_num[0]; ?>> Order1
          <input type="radio" name="select" value=<?php echo $order_num[1]; ?>> Order2
          <input type="radio" name="select" value=<?php echo $order_num[2]; ?>> Order3
          <input type="radio" name="select" value=<?php echo $order_num[3]; ?>> Order4
        </p>
      <?php } ?>
      <input type="submit" value="select">
    </form>
    <form action="/Ubeneat_web/Delivery.php" method="post">
      <?php if ($i == 1) { ?>
        <p><input type="radio" name="pick_up" value=<?php echo $order_num[0]; ?>> Order1</p>
      <?php } elseif ($i == 2) { ?>
        <p>
          <input type="radio" name="pick_up" value=<?php echo $order_num[0]; ?>> Order1
          <input type="radio" name="pick_up" value=<?php echo $order_num[1]; ?>> Order2
        </p>
      <?php } elseif ($i == 3) { ?>
        <p>
          <input type="radio" name="pick_up" value=<?php echo $order_num[0]; ?>> Order1
          <input type="radio" name="pick_up" value=<?php echo $order_num[1]; ?>> Order2
          <input type="radio" name="pick_up" value=<?php echo $order_num[2]; ?>> Order3
        </p>
      <?php } elseif ($i >= 4) { ?>
        <p>
          <input type="radio" name="pick_up" value=<?php echo $order_num[0]; ?>> Order1
          <input type="radio" name="pick_up" value=<?php echo $order_num[1]; ?>> Order2
          <input type="radio" name="pick_up" value=<?php echo $order_num[2]; ?>> Order3
          <input type="radio" name="pick_up" value=<?php echo $order_num[3]; ?>> Order4
        </p>
      <?php } ?>
      <input type="submit" value="pick_up">
    </form>
    <form action="/Ubeneat_web/Delivery.php" method="post">
      <?php if ($i == 1) { ?>
        <p><input type="radio" name="arrived" value=<?php echo $order_num[0]; ?>> Order1</p>
      <?php } elseif ($i == 2) { ?>
        <p>
          <input type="radio" name="arrived" value=<?php echo $order_num[0]; ?>> Order1
          <input type="radio" name="arrived" value=<?php echo $order_num[1]; ?>> Order2
        </p>
      <?php } elseif ($i == 3) { ?>
        <p>
          <input type="radio" name="arrived" value=<?php echo $order_num[0]; ?>> Order1
          <input type="radio" name="arrived" value=<?php echo $order_num[1]; ?>> Order2
          <input type="radio" name="arrived" value=<?php echo $order_num[2]; ?>> Order3
        </p>
      <?php } elseif ($i >= 4) { ?>
        <p>
          <input type="radio" name="arrived" value=<?php echo $order_num[0]; ?>> Order1
          <input type="radio" name="arrived" value=<?php echo $order_num[1]; ?>> Order2
          <input type="radio" name="arrived" value=<?php echo $order_num[2]; ?>> Order3
          <input type="radio" name="arrived" value=<?php echo $order_num[3]; ?>> Order4
        </p>
      <?php } ?>
      <input type="submit" value="arrived">
    </form>
    </br>
    <form action="/Ubeneat_web/Register.php" method="post">
      <input type="submit" value="home">
    </form>
  </center>
</body>
<?php mysqli_close($con); ?>

</html>