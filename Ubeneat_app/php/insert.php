<?php

$con=mysqli_connect("localhost","root","111111111","ubereat");
$table=$_POST['t0'];
//insert orderlist
if($table=='orderlist'){
  $customer=$_POST['t1'];
  $store=$_POST['t2'];
  $meal1=$_POST['t3'];
  $meal2=$_POST['t4'];
  $meal3=$_POST['t5'];
  $note=$_POST['t6'];
  $price=$_POST['t7'];
  $state=$_POST['t8'];
  $sql="INSERT INTO `orderlist`(`customer_num`, `store_num`, `meal1`, `meal2`, `meal3`, `note`, `total_price`, `state`) VALUES ($customer,$store,$meal1,$meal2,$meal3,'$note',$price,$state)";
  if(mysqli_query($con,$sql)){
    echo("order success");
  }else{
    echo("please order again");
  }
//insert customer
}elseif($table=='customer'){
  $name=$_POST['t1'];
  $phone=$_POST['t2'];
  $address=$_POST['t3'];

  $sql="INSERT INTO `customer`(`name`, `customer_address`,`phone`) VALUES ('$name','$address','$phone')";
  if(mysqli_query($con,$sql)){
    echo("register success");
  }else{
    echo("register fail");
  }
//insert meal
}elseif($table=='meal'){
  $num=$_POST['t1'];
  $name=$_POST['t2'];
  $price=$_POST['t3'];
  $url=$_POST['t4'];

  $sql="INSERT INTO `meal`(`store_N`, `meal_name`, `meal_price`, `image`) VALUES ($num,'$name',$price,'$url')";
  if(mysqli_query($con,$sql)){
    echo("insert success");
  }else{
    echo("insert fail");
  }
}

mysqli_close($con);
?>