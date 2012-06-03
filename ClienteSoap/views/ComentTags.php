<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Blog | Desarrollo del SoftWare</title>
        <link href="../public/css/styles.css" rel="stylesheet" type="text/css" media="all" />

        <link href="../public/css/faary.css" rel="stylesheet" type="text/css" media="all" />
        <link href="../public/js/gen_validatorv4.js" rel="stylesheet" type="text/css" media="all" />


        <?php
        session_start();
        echo $_SESSION['nick'];
        echo $_SESSION['token'];
        echo $_SESSION['id_u'];
        if ($_GET['tag'] != "") {
         
      
        require_once('../controllers/Usuario.php');    
        require_once('../controllers/Comentario.php');
        $Comentario = new Comentario();
        //echo $_POST['nick'];
        $Usuario = new Usuario();
        $UsuarioEsp = $Usuario->BuscarUsuarioEspe($_GET['nick']);
        $result = $Comentario->ListarComentarioTag($_GET['tag']);
       
        $resultUsu = $Usuario->ListarUsuarios();
        $resultTags = $Comentario->ListarTag();
}
        ?>

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



        <div id="content">
            <div id="content_cen">
                <div id="content_sup" class="head_pad">
                    <h2><span>PEDRO SCOTT</span>SCOTT746</h2>
                    <div id="welcom_pan">
                        <h2>TimeLine</h2>
                        <div id="service_pan">
                            <ul>
                                <?php $result = array_reverse($result);
                                foreach ($result as $value) { ?>  
                                    <form action="Coment.php">
                                        <li><img src="../public/images/ps.jpg" width="93" height="82"/>
                                            <input type="hidden" name="id_c" id="id_c" value="<?php echo $value['id_c']; ?>" /><br />
                                            <h5 id="nick"><?php echo $value['id_u']; ?></h5>
                                            <p><?php echo $value['texto']; ?><a href="#">Like <?php echo $value['gusta'] ?></a><a href="#" >Dis-Like <?php echo $value['nogusta'] ?></a> </p><br />
                                            <input type="submit" class="btn" id="but-replay" value="replay" />
                                        </li>
                                    </form>
<?php } ?>
                            </ul>
                        </div>

                        <div id="user">
                            <ul>
                                <li><img src="../public/images/ps.jpg" width="93" height="82"/>
                                    <h4><a href="Profile.html"><?php echo $UsuarioEsp['nick']; ?></a></h4>

                                </li>      
                            </ul>
                        </div>


                        <div id="user">
                            <ul>
                                <?php
                                $i = 0;
                                foreach ($resultUsu as $value) {
                                    if ($i <= 5) {
                                        ?> 
                                        <li><h5><?php echo $value['nick']; ?> </h5><br /> <p><?php echo $value['pais']; ?></p></li>     
        <?php $i++;
    }
} ?>
                            </ul>
                        </div>


                        <div id="user">
                            <ul>
                                <h2>Tags</h2>
<?php foreach ($resultTags as $tags) { ?> 

                                    <li><h5><a href="#" /><?php echo $tags['nombre']; ?></a></h5></li>

<?php } ?>
                            </ul>
                        </div>


                    </div>

                </div>
            </div>
        </div>


        <div id="foot">
            <div id="foot_cen">
                <h6><a href="index.html">mcube</a></h6>
                <p>© Your Copyright Info Here. Designed by: <a href="http://www.templateworld.com" target="_blank">Template World</a></p>
            </div>
        </div>
    </body>
</html>