
          
          
          
          
          
          <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Blog | Desarrollo del SoftWare</title>
        <link href="../public/css/styles.css" rel="stylesheet" type="text/css" media="all" />

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
        
        
        $id_c = $_POST['mensaje2'];//comentario padre
        $token = $_SESSION['token'];;
        $id_u = $_SESSION['id_u'];
        $nick = $_SESSION['nick'];;
		$comentarioNuevo = $_POST['Bhiografi'];
		
		echo $id_c.'////';
		echo $token.'////';
		echo $id_u.'////';
		echo $nick.'////';
		
		
	    require_once('../controllers/Comentario.php');		
		$Comentario = new Comentario();
		
		

	
	
	$result = $Comentario->InsertarComentario($comentarioNuevo,"0",$id_u,$id_c,null,$token);
	//echo $result;
	         ?><script>
                    window.alert(<?php echo "OK" ?>)
                    location = "../index.php";
                </script> <?php

	
?>


        <div id="foot">
            <div id="foot_cen">
                <h6><a href="index.html">mcube</a></h6>
                <p>© Your Copyright Info Here. Designed by: <a href="http://www.templateworld.com" target="_blank">Template World</a></p>
            </div>
        </div>
    </body>
</html>