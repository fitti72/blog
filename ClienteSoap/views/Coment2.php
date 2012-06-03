<?php
	require_once('../controllers/Comentario.php');
	//necesito id_c, token y comentario
	$comentario=$_POST['Bhiografi'];
	echo $comentario;
	
	$id_c = $_POST['prueba'];
	echo $id_c;
	
	$token = $_POST['prueba2'];
	echo $token;
	
	$id_u = $_POST['prueba3'];
	echo $id_u;
	
	$privacidad = 1;
	
	/*<texto>comentario del peruano</texto>
	<privacidad>1</privacidad>
	<id_u>5</id_u>
	<padre>1</padre>*/
	$tags=null;$
	
	$Comentario = new Comentario();
	$result = $Comentario->InsertarComentario($comentario,$privacidad,$id_u,$id_c,$tags,$token);
	echo $result;
	/*if ($result == "comentario insertado")		
		header("location: Coment.php");  
	else
		echo $result;*/
	
?>
