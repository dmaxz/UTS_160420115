<?php
// echo json_encode();
// $path = realpath(__DIR__) . "/";
// $json_str = file_get_contents($path . 'food.json');
// $json_arr = json_decode($json_str, true);

// $foods;

// if (isset($_POST)) {
    
//     $name = $_POST["name"];
//     $resto = $_POST["resto"];
//     if ($json_arr === null) {
//         $foods = array(array("id" => 1, "name" => $name, "resto" => $resto));
//     } else {
//         $newfoodid = array("id" => sizeof($json_arr) + 1);
//         $newfooddata = array("name" => $name, "resto" => $resto);
//         $newfood = array_merge($newfoodid, $newfooddata);

//         array_push($json_arr, $newfood);
//         // var_dump($json_arr);
//         $foods = $json_arr;
//     }
//     file_put_contents($path . "food.json", json_encode($foods));
// }

?>