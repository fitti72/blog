<?php

        session_start();
        $token = $_SESSION['token'];
	$id_u=$_SESSION['id_u'];
        if($_POST['nombre']!=''){
	$primerNombre=$_POST['nombre'];
	$segundoNombre=$_POST['nombres'];
	$primerApellido=$_POST['apellido'];
	$segundoApellido=$_POST['apellidos'];
	$email=$_POST['email'];
	
	$nick=$_POST['nick'];
	$password=$_POST['pass'];
	
	$sexo= $_POST['sexo'];
	$biografia= $_POST['biografia'];
	$password=$_POST['pais'];
	$foto="1234";
	$pais= $_POST['pais']; 
	$fecha=$_POST['fecha'];

	//echo $primerNombre;
	
	
	$status = "";
	if ($_POST["action"] == "upload") 
	{
	
		echo '1';
		$tamano = $_FILES["direccionFoto"]['size'];
		$tipo = $_FILES["direccionFoto"]['type'];
		$archivo = $_FILES["direccionFoto"]['name'];
		//echo $archivo;
		$prefijo = substr(md5(uniqid(rand())),0,6);
		//echo $prefijo;
	
		if ($archivo != "")
	 	{
			echo '2';
	
			$destino =  "../images/".$archivo;
			echo $destino;
			if (copy($_FILES['direccionFoto']['tmp_name'],$destino)) 
			{
				$status = "Archivo subido: <b>".$archivo."</b>";
			} 
			else 
			{
				$status = "Error al subir el archivo";
			}
		} 
		else 
		{
			$status = "Error al subir archivo";
		}	
	}
	require_once('../controllers/Usuario.php');
	$Usuario = new Usuario();

$sexo="M";

//ahora voy contra el servicio
	 $resultModificar = $Usuario->ModificarUsuario($id_u,$primerNombre,$segundoNombre,$primerApellido,$segundoApellido,$email,$nick,$pais,$biografia,$sexo,$fecha,$foto,$password,$token);
	 
	 $resultImagen = $Usuario->InsertarFoto($id_u,$archivo,$token);
         
         ?><script>
                    window.alert(<?php echo "OK" ?>)
                    location = "../index.php";
                </script> <?php
        }
?>
