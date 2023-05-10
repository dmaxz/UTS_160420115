<?php
// echo json_encode();
$path = realpath(__DIR__) . "/";


$arr = array();
// komentar (list) (user dan resto)
function random_str($length = 10)
{
    return substr(str_shuffle(str_repeat($x = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', ceil($length / strlen($x)))), 1, $length);
}
function random_resto($length = 1)
{
    return substr(str_shuffle(str_repeat($x = '12345', ceil($length / strlen($x)))), 1, $length);
}

for ($i=1; $i < 25 ; $i++) { 
    # code...
    $resto = array("id"=> $i, "comment"=> random_str(30), "user_id"=> random_int(1, 6), "restaurant_id"=>random_int(1, 5));
    array_push($arr, $resto);
}

file_put_contents($path . "comment.json", json_encode($arr));


?>