

          
          
          
          
          
          <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Blog | Desarrollo del SoftWare</title>
      
        <link href="../public/css/faary.css" rel="stylesheet" type="text/css" media="all" />
        <link href="../public/css/styles.css" rel="stylesheet" type="text/css" media="all" />


        

    </head>

    <body>



        <div id="head">
            <div id="head_cen">
                <div id="head_sup" class="head_pad">
                    <form class="log-in">
                        <label>search</label>
                        <input name="search" type="text" class="txt" id="search"/>
                        <input name="search-btn" type="submit" class="btn" value="Search" />
                    </form>

                    <h1 class="logo"><a href="index.html">BLOG</a></h1>
                </div>
            </div>
        </div>



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


        <div id="foot">
            <div id="foot_cen">
                <h6><a href="index.html">mcube</a></h6>
                <p>© Your Copyright Info Here. Designed by: <a href="http://www.templateworld.com" target="_blank">Template World</a></p>
            </div>
        </div>
    </body>
</html>