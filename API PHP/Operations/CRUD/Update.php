<?php

    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Headers: access");
    header("Access-Control-Allow-Methods: PATCH");
    header("Content-Type: application/json;charset=UTF-8");
    header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

    include_once("./../../DataBase/Database.php");

    $db=new Database();
    $con=$db->Connection();

    $data=json_decode(file_get_contents("php://input"));

    if($_SERVER["REQUEST_METHOD"]!="PATCH"):
        $db->Message(0,404,"Page Not Found !");
    elseif( !isset($data->id) 
        || !isset($data->username) 
            || !isset($data->email) ||!isset($data->password)
            || empty(trim($data->id))
            || empty(trim($data->username))
            || empty(trim($data->email)) ||empty(trim($data->password))
        ):
        $db->Message(0,422,"Please Fill all The Required Fields !");
    else:
        $id=$data->id;
        $username=$data->username;
        $email=trim($data->email);
        $password = $data->password

        if(!is_numeric($data->id)):
            $db->Message(0,422,"ID Must be Integer !");
        elseif(!$db->SelectedByID($id)):
            $db->Message(0,422,"No Data With This User Id !!");
        elseif(strlen($username) < 3):
            $db->Message(0,422,"Your User Name Must Be At Least 3 Characters !");
        else:
            try{
               if($db->UpdateUser($id,$username,$email,$password)):
                    $db->Message(1,201,"User Updated Successfully .");
                endif;
            }catch(PDOEception $e){
                echo $e->getMessage(); 
                $db->Message(0,401,"".$e->getMessage());
            }
        endif;
    endif;

?>