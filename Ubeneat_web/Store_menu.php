<?php
$store_ID = isset($_POST['store_ID']) ? $_POST['store_ID'] : "0";
$meal_del = isset($_POST['meal_del']) ? $_POST['meal_del'] : "0";
$meal_up = isset($_POST['meal_up']) ? $_POST['meal_up'] : "0";
$meal_ins = isset($_POST['meal_ins']) ? $_POST['meal_ins'] : "0";
$con = mysqli_connect("localhost", "root", "111111111", "ubereat");
$new = "1";
if ($meal_del != "0") {
  $sql_del = "DELETE FROM `meal` WHERE meal_N=$meal_del";
  mysqli_query($con, $sql_del);
} elseif ($meal_ins == "1") {
  $name = $_POST['name'];
  $price = $_POST['price'];
  $url = $_POST['url'];
  $meal_ins == "0";
  $sql_ins = "INSERT INTO `meal`(`store_N`, `meal_name`, `meal_price`, `image`) VALUES ($store_ID,'$name',$price,'$url')";
  mysqli_query($con, $sql_ins);
} elseif ($meal_up != "0") {
  $name = $_POST['name'];
  $price = $_POST['price'];
  $url = $_POST['url'];
  $sql_up = "UPDATE `meal` SET `meal_name`='$name',`meal_price`=$price,`image`='$url'  WHERE meal_N=$meal_up";
  mysqli_query($con, $sql_up);
}
$i = 0;
$meal_num = array();

?>

<html>

<head>
  <title>Ubeneat</title>
</head>

<body>
  <center>
    <h1>Store menu</h1>
    <?php if ($new == "1") {
      $sql_meal = "SELECT `meal_N`, `meal_name`, `meal_price`, `image` FROM `meal` WHERE `store_N`=$store_ID";
      $result_meal = mysqli_query($con, $sql_meal);
      $new = "0";
      while ($row_result_meal = $result_meal->fetch_assoc()) { ?>
        <?php $meal_num[$i] = $row_result_meal["meal_N"]; ?>
        <?php $i = $i + 1; ?>
        <font size="4">Meal<?php echo $i; ?></br><?php echo $row_result_meal["meal_name"]; ?> $<?php echo $row_result_meal["meal_price"]; ?></br></font>
        <font size="4"><?php echo $row_result_meal["image"]; ?></br></br></font>
      <?php } ?>
    <?php } ?>


    <form action="/Ubeneat_web/Store_menu.php" method="post">
      <font>you have <?php echo $i; ?> meal(s)</font>
      <?php if ($i == 1) { ?>
        <p><input type="radio" name="meal_del" value=<?php echo $meal_num[0]; ?>> Meal1</p>
      <?php } elseif ($i == 2) { ?>
        <p>
          <input type="radio" name="meal_del" value=<?php echo $meal_num[0]; ?>> Meal1
          <input type="radio" name="meal_del" value=<?php echo $meal_num[1]; ?>> Meal2
        </p>
      <?php } elseif ($i >= 3) { ?>
        <p>
          <input type="radio" name="meal_del" value=<?php echo $meal_num[0]; ?>> Meal1
          <input type="radio" name="meal_del" value=<?php echo $meal_num[1]; ?>> Meal2
          <input type="radio" name="meal_del" value=<?php echo $meal_num[2]; ?>> Meal3
        </p>
      <?php } ?>
      <input type="hidden" name="store_ID" value=<?php echo $store_ID; ?>>
      <input type="submit" value="delete">
    </form>

    <form action="/Ubeneat_web/Store_menu.php" method="post">
      <?php if ($i == 1) { ?>
        <p><input type="radio" name="meal_up" value=<?php echo $meal_num[0]; ?>> Meal1</p>
      <?php } elseif ($i == 2) { ?>
        <p>
          <input type="radio" name="meal_up" value=<?php echo $meal_num[0]; ?>> Meal1
          <input type="radio" name="meal_up" value=<?php echo $meal_num[1]; ?>> Meal2
        </p>
      <?php } elseif ($i >= 3) { ?>
        <p>
          <input type="radio" name="meal_up" value=<?php echo $meal_num[0]; ?>> Meal1
          <input type="radio" name="meal_up" value=<?php echo $meal_num[1]; ?>> Meal2
          <input type="radio" name="meal_up" value=<?php echo $meal_num[2]; ?>> Meal3
        </p>
      <?php } ?>
      <input type="text" name="name" placeholder="meal_name">
      <input type="text" name="price" placeholder="meal_price">
      <input type="text" name="url" placeholder="image_url"></br>
      <input type="hidden" name="store_ID" value=<?php echo $store_ID; ?>>
      <input type="submit" value="update">
    </form>


    <form action="/Ubeneat_web/Store_menu.php" method="post">
      <input type="hidden" name="meal_ins" value="1">
      <input type="text" name="name" placeholder="meal_name">
      <input type="text" name="price" placeholder="meal_price">
      <input type="text" name="url" placeholder="image_url"></br>
      <input type="hidden" name="store_ID" value=<?php echo $store_ID; ?>>
      <input type="submit" value="insert">
    </form>
    </br>
    <form action="/Ubeneat_web/Register.php" method="post">
      <input type="submit" value="home">
    </form>


  </center>
</body>
<?php mysqli_close($con); ?>

</html>