<?php

$con=mysqli_connect("localhost","root","111111111","ubereat");
$table=$_POST['t0'];
//select customer_number from customer_info
if($table=='get_customer_number'){
   $name=$_POST['t1'];
   $phone=$_POST['t2'];
   $address=$_POST['t3'];

   $sql="SELECT `customer_number` FROM `customer` WHERE `name`='$name' and `customer_address`='$address' and `phone`='$phone'";
   $result=mysqli_query($con,$sql);
   $response = array();

   while ($row = $result->fetch_assoc()) {    	
      $response[] = $row;
   }   
   echo json_encode($response, JSON_UNESCAPED_UNICODE);

//select customer_info from customer_number
}elseif($table=='get_customer_info'){
   $num=$_POST['t1'];

   $sql="SELECT `name`, `customer_address`, `phone` FROM `customer` WHERE `customer_number`=$num";
   $result=mysqli_query($con,$sql);
   $response = array();
   
   while ($row = $result->fetch_assoc()) {    	
      $response[] = $row;
   }   
   echo json_encode($response, JSON_UNESCAPED_UNICODE);

//select order_info from customer_number
}elseif($table=='get_customer_order'){
   $ID=$_POST['t1'];

   $sql="SELECT `store_num`, `meal1`, `meal2`, `meal3`, `note`, `total_price`, `state`, `order_num` FROM `orderlist` WHERE `customer_num`=$ID";
   $result=mysqli_query($con,$sql);
   $response = array();

   while ($row = $result->fetch_assoc()) {    	
      $response[] = $row;
   } 
   echo json_encode($response, JSON_UNESCAPED_UNICODE);

//select store_number from store_name
}elseif($table=='get_store_num'){
   $name=$_POST['t1'];

   $sql="SELECT `store_number` FROM `store` WHERE `store_name`='$name'";
   $result=mysqli_query($con,$sql);
   $response = array();
   
   while ($row = $result->fetch_assoc()) {    	
      $response[] = $row;
   }   
   echo json_encode($response, JSON_UNESCAPED_UNICODE);

//select meal_info from stroe_number
}elseif($table=='get_meal_info'){
   $num=$_POST['t1'];

   $sql="SELECT `meal_N`, `meal_name`, `meal_price`, `image` FROM `meal` WHERE `store_N`=$num";
   $result=mysqli_query($con,$sql);
   $response = array();
   
   while ($row = $result->fetch_assoc()) {    	
      $response[] = $row;
   }   
   echo json_encode($response, JSON_UNESCAPED_UNICODE);

//select order_info from store_number
}elseif($table=='get_store_order'){
   $num=$_POST['t1'];

   $sql="SELECT  `meal1`, `meal2`, `meal3`, `note`, `total_price`, `state` FROM `orderlist` WHERE `store_num`=$num";
   $result=mysqli_query($con,$sql);
   $response = array();
   
   while ($row = $result->fetch_assoc()) {    	
      $response[] = $row;
   }   
   echo json_encode($response, JSON_UNESCAPED_UNICODE);

//select deliver address and price
}elseif($table=='get_deliver_info'){

   $sql="SELECT `store_address`,`customer_address`,`total_price`, `state`, `order_num` FROM `customer`,`store`,`orderlist` WHERE `customer_number`=`customer_num` and `store_number`=`store_num`";
   $result=mysqli_query($con,$sql);
   $response = array();

   while ($row = $result->fetch_assoc()) {    	
      $response[] = $row;
   }   
   echo json_encode($response, JSON_UNESCAPED_UNICODE);
//select store_num from store_name, type and meal_name
}elseif($table=='get_meal_search'){
   $name=$_POST['t1'];
   
   $sql="SELECT `store_name`, `type`, `meal_name`  FROM `store`,`meal` WHERE `store_number`=`store_N` and (`type` like '%$name%' or `meal_name` like '%$name%' or `store_name` like '%$name%')";
   $result=mysqli_query($con,$sql);
   $response = array();

   while ($row = $result->fetch_assoc()) {    	
      $response[] = $row;
   }   
   echo json_encode($response, JSON_UNESCAPED_UNICODE);
}

mysqli_close($con);
?>


