
          
          
          
          
          
          <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Blog | Desarrollo del SoftWare</title>
        <link href="../public/css/styles.css" rel="stylesheet" type="text/css" media="all" />

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
        
        //  if ($_SESSION['nick'] != "") {
        if ($_POST['nick'] != "") {

            $nick = $_POST['nick'];
            $pass = $_POST['pass'];
            require_once('../../controllers/Usuario.php');
            $Usuario = new Usuario();
            $result = $Usuario->ValidarUsuario($nick, $pass);
            $sol = $result['id_u'];
            
            
            
            echo $sol;
            if (strstr($sol, "Error: No se puede Iniciar Sesion desde la misma pc")) {
                ?><script>
                    window.alert(<?php echo $sol; ?>)
                    location = "../../index.php";
                </script> <?php
    } else if (strstr($sol, "Error: Clave incorrecta")) {
                ?><script>
                    window.alert("<?php echo $sol; ?>")
                    location = "../../index.php";
                </script> <?php
    } else if (strstr($sol, "Error: Nick incorrecto")) {
                ?><script>
                    window.alert("<?php echo $sol; ?>")
                    location = "../../index.php";
                </script> <?php
    }else {
        
        
        $Usuario = new Usuario();
        $UsuarioEsp = $Usuario->BuscarUsuarioEspe($nick);
        $token = $UsuarioEsp['token'];
        $id_u = $UsuarioEsp['id_u'];
        session_start();
        $_SESSION['nick'] = "$nick";
        $_SESSION['token'] = "$token";
        $_SESSION['id_u'] = "$id_u";
        
        
        ?><script>
                    window.alert("welcome  <?php echo $nick; ?>")
                    location = "../timeline.php";
          </script> <?php
        
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