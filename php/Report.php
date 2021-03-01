<?php
    $con = mysqli_connect("localhost", "yejin0928", "rnjsdPwls99!", "yejin0928");
    mysqli_query($con,'SET NAMES utf8');

    $user_id = $_POST["user_id"];
  
    
    $statement = mysqli_prepare($con, "SELECT AVG(record_sugar), AVG(record_p1),AVG(record_p2) FROM RECORD WHERE user_id = ?");
    mysqli_stmt_bind_param($statement, "s", $user_id);
    mysqli_stmt_execute($statement);


    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $avg_sugar, $avg_p1, $avg_p2 );

    $response = array();
    $response["success"] = false;
 
    while(mysqli_stmt_fetch($statement)) {
        $response["success"] = true;
        $response["avg_sugar"] = $avg_sugar;
        $response["avg_p1"] = $avg_p1;
        $response["avg_p2"] = $avg_p2;        
    }

    echo json_encode($response);



?>