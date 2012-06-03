<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Blog | Desarrollo del SoftWare</title>
        <link href="../public/css/styles.css" rel="stylesheet" type="text/css" media="all" />
        <link href="../public/css/faary.css" rel="stylesheet" type="text/css" media="all" />

    </head>

    <body>

        <?php
        session_start();
        
        
        $id_c = $_POST['id_c'];
        $token = $_SESSION['token'];;
        $id_u = $_SESSION['id_u'];
        $nick = $_SESSION['nick'];;
        
        
        require_once('../controllers/Comentario.php');
        $Comentario = new Comentario();
        $result = $Comentario->ListarComentarioEspe($id_c);
        $resultTags = $Comentario->ListarTagComentario($id_c);
        
        require_once('../controllers/Usuario.php');
        $Usuario = new Usuario();
        $UsuarioEsp = $Usuario->BuscarUsuarioEspe($nick);
        $resultUsu = $Usuario->ListarUsuarios();
        ?>

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
                    <h2><span><?php echo $UsuarioEsp['nombre'] . " " . $UsuarioEsp['apellido']; ?></span><?php echo $UsuarioEsp['nick']; ?></h2>
                    <div id="welcom_pan">
                        <h2>TimeLine</h2>
                        <div id="service_pan">
                            <ul>

<?php
$Usuario = new Usuario();
$result = array_reverse($result);

$Usuario2 = new Usuario();
//echo $rutaFoto;						
?>  


                                <?php
                                $result = array_reverse($result);
                                foreach ($result as $value) {
                                    ?>  
                                    <form action="Coment.php">
                                    <?php
                                    $result2 = $Usuario->obtenerNick($value['id_u']);
                                    $nick = $result2['id_u'];
                                    $result2 = $Usuario->obtenerFoto($nick);
                                    $rutaFoto = $result2['id_u'];
                                    ?>
                                        <li><img src="../images/<?php echo $rutaFoto; ?>" width="93" height="82"/><input type="hidden" name="id_c" id="id_c" value="<?php echo $value['id_c']; ?>" /><br />
                                            <h5 id="nick"><?php echo $nick; ?></h5>
                                            <p><?php echo $value['texto']; ?><a href="#">Like <?php echo $value['gusta'] ?></a><a href="#" >Dis-Like <?php echo $value['nogusta'] ?></a> </p><br />
                                            <input type="submit" class="btn" id="but-replay" value="replay" />
                                        </li>
                                    </form>
                                <?php } ?>

                            </ul>

                            <div id="Register">
                                <form method="post" action="Coment2.php"  class="iform">
                                    <input name="prueba" type="hidden" id="prueba" value="<?php echo $id_c; ?>" />
                                    <input name="prueba2" type="hidden" id="prueba2" value="<?php echo $token; ?>" />

                                    <input name="prueba3" type="hidden" id="prueba3" value="<?php echo $id_c; ?>" />

                                    <li>
                                        <label for="Bhiografi">*Replay</label>
                                        <input class="itextarea" type="text" name="Bhiografi" id="Bhiografi" /></li>

                                    <li><label>&nbsp;</label><input type="submit" class="ibutton" onclick="sendForm()" name="Register" id="Register" value="Register!" /></li>


                                    </ul></form>







                            </div>







                        </div>







                        <div id="user">
                            <ul>
                                <li><img src="images/ps.jpg" width="93" height="82"/>
                                    <h4>Scott746</h4>

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
                                        <li><h5><a href="Profile.php?&nick=<?php echo $UsuarioEsp['nick']; ?>&token=<?php echo $UsuarioEsp['token']; ?>" /><?php echo $value['nick']; ?> </h5><br /> <p><?php echo $value['pais']; ?></p></li>     
                                        <?php
                                        $i++;
                                    }
                                }
                                ?>
                            </ul>
                        </div>


                      <div id="user">
                            <ul>
                                <h2>Tags</h2>
                               
                                <?php 
                                 if( $resultTags!=null){
                                
                                foreach ($resultTags as $tags) { ?> 
                                    <li><h5><a href="ComentTags.php?tag=<?php echo $tags['nombre']; ?>&nick=<?php echo $UsuarioEsp['nick']; ?>&token=<?php echo $UsuarioEsp['token']; ?>" /><?php echo $tags['nombre']; ?></a></h5></li>
                                    <li><input id="tag" type="hidden" value="<?php echo $tags['nombre']; ?>" name="tag" /></a></h5></li>
                                <?php } 
                                
                                 }
                                ?>


                            </ul>


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