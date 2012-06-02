<?php
require_once('/controllers/Usuario.php');

require_once('/controllers/Comentario.php');
$Usuario = new Usuario();
//$result = $Usuario->InsertarComentario("hola mundo", "1", "1", "0",$tags,"146230186");
$result = $Usuario->ListarUsuarios();
/* foreach ($result as $value) {
    echo $value['nombre'];
    echo $value['id_u']."<br />";
    echo $value['biografia']."<br />";   
} */


    //echo $result;
    //echo $result['id_u']."<br />";
    //echo $result['biografia']."<br />";   




?>
