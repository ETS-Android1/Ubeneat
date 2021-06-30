<?php

$con=mysqli_connect("localhost","root","111111111","ubereat");
$table=$_POST['t0'];

if($table=='delete_meal'){
    $meal_num=$_POST['t1'];

    $sql="DELETE FROM `meal` WHERE meal_N=$meal_num";

    if(mysqli_query($con,$sql)) {
        echo("delete success");
    }else{
        echo("please delete again");
    }
}elseif($table=='delete_order'){
    $order_number=$_POST['t1'];

    $sql1="DELETE FROM `orderlist` WHERE order_num=$order_number";

    if(mysqli_query($con,$sql1)) {
        echo("delete success");
    }else{
        echo("please delete again");
    }
}
mysqli_close($con);
?>