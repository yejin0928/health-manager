<?php 
    $con = mysqli_connect("localhost", "yejin0928", "rnjsdPwls99!", "yejin0928");
    mysqli_query($con,'SET NAMES utf8');

    $user_id = $_POST["user_id"];
    $record_date = $_POST["record_date"];
    $record_time = $_POST["record_time"];
    $record_sugar = $_POST["record_sugar"];
    $record_p1 = $_POST["record_p1"];
    $record_p2 = $_POST["record_p2"];

    $statement = mysqli_prepare($con, "INSERT INTO RECORD VALUES (?,?,?,?,?,?)");
    mysqli_stmt_bind_param($statement, "sssssi", $user_id, $record_date, $record_time, $record_sugar, $record_p1, $record_p2);
    mysqli_stmt_execute($statement);


    $response = array();
    $response["success"] = true;
 
   
    echo json_encode($response);



?>