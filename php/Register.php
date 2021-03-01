<?php 
    $con = mysqli_connect("localhost", "yejin0928", "rnjsdPwls99!", "yejin0928");
    mysqli_query($con,'SET NAMES utf8');

    $userID = $_POST["userID"];
    $userPassword = $_POST["userPassword"];
    $userGender = $_POST["userGender"];
    $userAge = $_POST["userAge"];

    $statement = mysqli_prepare($con, "INSERT INTO USER VALUES (?,?,?,?)");
    mysqli_stmt_bind_param($statement, "sssi", $userID, $userPassword, $userGender, $userAge);
    mysqli_stmt_execute($statement);


    $response = array();
    $response["success"] = true;
 
   
    echo json_encode($response);



?>