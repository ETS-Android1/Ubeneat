<?php

$con=mysqli_connect("localhost","root","111111111","ubereat");
$table=$_POST['t0'];
//update order_state (1:processing in store, 2:selected, 3:picked up, 4:arrived, 5:done)
if($table=='update_order_state'){
    $order_number=$_POST['t1'];
    $state=$_POST['t2'];


    $sql="UPDATE `orderlist` SET `state`=$state WHERE order_num=$order_number";

    if(mysqli_query($con,$sql)) {
        echo("update success");
    }else{
        echo("please update again");
    }
//update order_info
}elseif($table=='update_order'){
    $order_num=$_POST['t1'];
    $m1=$_POST['t2'];
    $m2=$_POST['t3'];
    $m3=$_POST['t4'];
    $note=$_POST['t5'];
    $price=$_POST['t6'];

    $sql="UPDATE `orderlist` SET `meal1`=$m1,`meal2`=$m2,`meal3`=$m3,`note`='$note',`total_price`=$price  WHERE order_num=$order_num";

    if(mysqli_query($con,$sql)) {
        echo("update success");
    }else{
        echo("please update again");
    }
//update meal_info
}elseif($table=='update_meal'){
    $meal_num=$_POST['t1'];
    $name=$_POST['t2'];
    $price=$_POST['t3'];
    $url=$_POST['t4'];

    $sql="UPDATE `meal` SET `meal_name`='$name',`meal_price`=$price,`image`='$url'  WHERE meal_N=$meal_num";

    if(mysqli_query($con,$sql)) {
        echo("update success");
    }else{
        echo("please update again");
    }
}

mysqli_close($con);
?>