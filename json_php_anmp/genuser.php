<?php
// echo json_encode();
$path = realpath(__DIR__) . "/";


$arr = array();

function random_str($length = 10)
{
    return substr(str_shuffle(str_repeat($x = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', ceil($length / strlen($x)))), 1, $length);
}
function random_phone($length = 10)
{
    return substr(str_shuffle(str_repeat($x = '0123456789', ceil($length / strlen($x)))), 1, $length);
}
$resto = array("id" => 1, "username" => "ken", "password" => "ken", "email" => "ken@mail.com", "name" => "kenda", "balance" => random_int(30000, 300000), "phone" => random_phone(11));
array_push($arr, $resto);
for ($i = 2; $i < 7; $i++) {
    # code...
    $email = random_str(7) . "@" . random_str(random_int(4, 7)) . ".com";
    $resto = array("id" => $i, "username" => random_str(4), "password" => random_str(10), "email" => $email, "name" => random_str(10), "balance" => random_int(30000, 300000), "phone" => random_phone(11));
    array_push($arr, $resto);
}

file_put_contents($path . "user.json", json_encode($arr));


?>