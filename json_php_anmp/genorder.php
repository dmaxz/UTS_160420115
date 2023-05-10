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

for ($i = 1; $i < 41; $i++) {
    # code...
    $epochTS = random_int(1270000000,1990000000);
    $tglorder = date("Y-m-d H:i:s", $epochTS);

    $resto = array("id" => $i, "quantity" => random_int(1, 10), "notes" => random_str(30), "user_id" => random_int(1, 6), "food_id" => random_int(1, 20), "date"=>$tglorder);
    array_push($arr, $resto);
}

file_put_contents($path . "order.json", json_encode($arr));


?>