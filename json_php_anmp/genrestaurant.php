<?php
// echo json_encode();
$path = realpath(__DIR__) . "/";


$arr = array();

function random_str($length = 10)
{
    return substr(str_shuffle(str_repeat($x = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', ceil($length / strlen($x)))), 1, $length);
}
function random_phone($length = 10)
{
    return substr(str_shuffle(str_repeat($x = '0123456789', ceil($length / strlen($x)))), 1, $length);
}

for ($i=1; $i < 6 ; $i++) { 
    # code...
    $resto = array("id"=> $i, "name"=> random_str(15), "alamat"=> random_str(35), "phone"=>random_phone(11));
    array_push($arr, $resto);
}

file_put_contents($path . "resto.json", json_encode($arr));


?>