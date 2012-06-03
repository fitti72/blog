<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Blog | Desarrollo del SoftWare</title>
        
        <link href="../../public/css/faary.css" rel="stylesheet" type="text/css" media="all" />
        <link href="../../public/css/styles.css" rel="stylesheet" type="text/css" media="all" />


        

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
        $nick = $_SESSION['nick'];
    
        require_once('../../controllers/Usuario.php');
            $Usuario = new Usuario();
            $id_u = $Usuario->BuscarUsuarioEspe($nick);
            $id_u = $id_u['id_u'];
        if ($_POST['texto'] != "") {
            
            $texto = $_POST['texto'];
            if($_POST['tags']!=""){
            $tags[0] = $_POST['tags'];
            }
            $priva = $_POST['priva'];
            $status = "";
            if ($_POST["action"] == "upload") 
            {
                $tamano = $_FILES["direccionFoto"]['size'];
		$tipo = $_FILES["direccionFoto"]['type'];
		$archivo = $_FILES["direccionFoto"]['name'];
		//echo $archivo;
		$prefijo = substr(md5(uniqid(rand())),0,6);
		//echo $prefijo;
                $flag = 0;
		if ($archivo != "")
	 	{
			echo '2';
                        $flag = 1;
			$destino =  "../../images/".$archivo;
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
            
            
            
            require_once('../../controllers/Comentario.php');
            $comentario = new comentario();
            
            if ($flag == 0)
            {
                $result = $comentario->InsertarComentario($texto,$priva,$id_u,"0",$tags,$token);
                $sol = $result[0]['id_c'];
            }
            else
            {
                echo 'estoy entrando aki';
                $result = $comentario->InsertarAdjunto($texto, $priva, $id_u, "0", $tags, $token, $archivo);
                $sol = $result[0]['id_c'];
            }
            if (strstr($sol, "Error:")) {
                ?><script> 
                            window.alert("<?php echo $sol; ?>")
                            location = "../timeline.php";  
                </script>     <?php
    } else {

   ?><script> 
                            window.alert("Send :) ")
                            location = "../timeline.php";  
                </script>     <?php
    }
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