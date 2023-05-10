<?php
// echo json_encode();
$path = realpath(__DIR__) . "/";


$arr = array();

function random_str($length = 10)
{
    return substr(str_shuffle(str_repeat($x = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', ceil($length / strlen($x)))), 1, $length);
}
function random_resto($length = 1)
{
    return substr(str_shuffle(str_repeat($x = '12345', ceil($length / strlen($x)))), 1, $length);
}

for ($i=1; $i < 50 ; $i++) { 
    # code...
    $resto = array("id"=> $i, "code"=> random_str(5), "discount"=> random_int(1, 50), "restaurant_id"=>random_int(1,5));
    array_push($arr, $resto);
}

file_put_contents($path . "promo.json", json_encode($arr));


?>