<?php
// echo json_encode();
$path = realpath(__DIR__) . "/";


$arr = array();

function random_str($length = 10)
{
    return substr(str_shuffle(str_repeat($x = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', ceil($length / strlen($x)))), 1, $length);
}
function random_resto_id($length = 1)
{
    return substr(str_shuffle(str_repeat($x = '12345', ceil($length / strlen($x)))), 1, $length);
}

for ($i=1; $i < 21 ; $i++) { 
    # code...
    $resto = array("id"=> $i, "name"=> random_str(10), "description"=> random_str(30), "restaurant_id"=>random_int(1,5));
    array_push($arr, $resto);
}

file_put_contents($path . "food.json", json_encode($arr));


?>