<?php
$user_ID = isset($_POST['user_ID']) ? $_POST['user_ID'] : "0";
$con = mysqli_connect("localhost", "root", "111111111", "ubereat");
$table = $_POST['t0'];
$name = $_POST['t1'];
//select store_num from store_name, type and meal_name
if ($table == 'get_meal_search') {
  $sql = "SELECT `store_name`, `type`, `meal_name`  FROM `store`,`meal` WHERE `store_number`=`store_N` and (`type` like '%$name%' or `meal_name` like '%$name%' or `store_name` like '%$name%')";
  $result = mysqli_query($con, $sql);
}
?>
<html>

<head>
  <title>Ubeneat</title>
</head>

<body>
  <center>

    <h1>Menu</h1>

    <p>
    <form action="/Ubeneat_web/Jap.php" method="post">
      <input type="hidden" name="user_ID" value=<?php echo $user_ID; ?>>
      <input type="hidden" name="store_ID" value="1">
      <input type="image" img src="https://doqvf81n9htmm.cloudfront.net/data/jamiesu_149/2020SEP/0904/DF4.jpg" onClick="document.form1.submit()" style="width:200px; height:150px;background-size: 100% 100%;" />
    </form>
    <p>
      <font size="4">Japanese Food</font>
    </p>
    <form action="/Ubeneat_web/Chin.php" method="post">
      <input type="hidden" name="user_ID" value=<?php echo $user_ID; ?>>
      <input type="hidden" name="store_ID" value="2">
      <input type="image" img src="https://www.gomaji.com/blog/wp-content/uploads/2021/03/DSC06201-e1616732244845.jpg" onClick="document.form1.submit()" style="width:200px; height:150px;background-size: 100% 100%;" />
    </form>
    <p>
      <font size="4">Chinese Food</font>
    </p>
    <form action="/Ubeneat_web/Kor.php" method="post">
      <input type="hidden" name="user_ID" value=<?php echo $user_ID; ?>>
      <input type="hidden" name="store_ID" value="3">
      <input type="image" img src="https://e3ix98zp3o3.exactdn.com/wp-content/uploads/2020/09/iStock-1253629795-1-1024x681.jpg" onClick="document.form1.submit()" style="width:200px; height:150px;background-size: 100% 100%;" />
    </form>
    <p>
      <font size="4">Korean Food</font>
    </p>
    <form action="/Ubeneat_web/Dri.php" method="post">
      <input type="hidden" name="user_ID" value=<?php echo $user_ID; ?>>
      <input type="hidden" name="store_ID" value="4">
      <input type="image" img src="https://cdn-a.william-reed.com/var/wrbm_gb_food_pharma/storage/images/publications/food-beverage-nutrition/beveragedaily.com/article/2020/01/02/2020-trends-to-watch-in-us-beverage/10484034-2-eng-GB/2020-trends-to-watch-in-US-beverage_wrbm_large.jpg" onClick="document.form1.submit()" style="width:200px; height:150px;background-size: 100% 100%;" />
    </form>
    <p>
      <font size="4">Drink</font>
    </p>
    <form action="/Ubeneat_web/Menu.php" method="post">
      <p>
        <input type="hidden" name="user_ID" value=<?php echo $user_ID; ?>>
        <input type="hidden" name="t0" value="get_meal_search">
        <input type="text" name="t1" placeholder="type/store/meal">
        <input type="submit" value="search">
      </p>
    </form>
    <?php while (($row_result = $result->fetch_assoc())) { ?>
      <p>
        <?php echo $row_result["type"]; ?>-><?php echo $row_result["store_name"]; ?>-><?php echo $row_result["meal_name"]; ?>
      </p>
    <?php } ?>
  </center>
</body>
<?php mysqli_close($con); ?>

</html>