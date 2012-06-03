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
        $nick = $_SESSION['nick'];
        $token = $_SESSION['token'];
        $id_u = $_SESSION['id_u'];
        
        
        require_once('../controllers/Usuario.php');
        require_once('../controllers/Comentario.php');
        
        $Usuario = new Usuario();
        $Comentario = new Comentario();
        $UsuarioEsp = $Usuario->BuscarUsuarioEspe($nick);
        $result = $Comentario->ListarComentario();
        $resultTags = $Comentario->ListarTag();
        $resultUsu = $Usuario->ListarUsuarios();
        
        
        
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
                    <h2><span><?php echo $UsuarioEsp['nombre'] . " " . $UsuarioEsp['apellido']; ?></span><?php echo $UsuarioEsp['nick']; ?></h2>
                    <div id="welcom_pan">
                        <h2>TimeLine</h2>
                        <div id="service_pan">
                            <form class="iform"method="post" action="Message/comentar.php" id="formlogin" name="formlogin" enctype="multipart/form-data" >

                                <li><label for="Texto">*Text</label><input class="itextarea" type="text" name="texto" id="texto" /></li>
                                <li><label for="FirstName">*Tags</label><input class="itext" type="text" name="tags" id="tags" /></li>


                                <li><label for="direccionFoto">Photo</label>       <input type="file" name="direccionFoto" id="direccionFoto" />
                                    <span class="text">
                                        <input name="action" type="hidden" value="upload" />
                                    </span></li>
                                <li> <select class="iselect" name="priva" id="priva">
                                        <option selected="true" value="1">Publico</option>
                                        <option value="0">Privado</option>
                                    </select></li>

                                <li><label>&nbsp;</label><input type="submit" class="ibutton" onclick="sendForm()" name="Register" id="Register" value="Register!" /></li>

                            </form>

                            <ul>
                                <?php
                                $result = array_reverse($result);
                                foreach ($result as $value) {
                                    $result2 = $Usuario->obtenerNick($value['id_u']);
				$nick = $result2['id_u'];
				 $result2 = $Usuario->obtenerFoto($nick);	
				 $rutaFoto = $result2['id_u'];
                                 echo $rutaFoto;
				 ?>
                                <form class="log-in"method="post" action="Coment.php" id="hola" name="hola" >
                                        <li><img src="../images/<?php echo $rutaFoto; ?>" width="93" height="82"/><input type="hidden" name="id_c" id="id_c" value="<?php echo $value['id_c']; ?>" /><br />
                                             <input type="hidden" name="id_c" id="id_c" value="<?php echo $value['id_c']; ?>" /><br />
                                            <h5 id="nick"><a href="Profile.php?&nick=<?php echo $UsuarioEsp['nick']; ?>&token=<?php echo $UsuarioEsp['token']; ?>" ><?php
                                $usulista = $Usuario->nickdelid($value['id_u']);
                                echo $usulista;
                                ?></a></h5><input type="hidden" value="<?php echo $value['id_c'];?>">
                                            <p><?php echo $value['texto']; ?><a href="#">Like <?php echo $value['gusta'] ?></a><a href="#" >Dis-Like <?php echo $value['nogusta'] ?></a> </p><br />
                                            <input type="submit" class="btn" id="butreplay" value="Look" />
                                        </li>
                                    </form>
                                <?php } ?>
                            </ul>
                        </div>

                        <div id="user">
                            <ul>
                                <li><img src="../public/images/ps.jpg" width="93" height="82"/>
                                    <h4><a href="href="Profile.php?&nick=<?php echo $UsuarioEsp['nick']; ?>&token=<?php echo $UsuarioEsp['token']; ?>" <?php echo $UsuarioEsp['nick']; ?></a></h4>

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

                                <?php foreach ($resultTags as $tags) { ?> 
                                    <li><h5><a href="ComentTags.php?tag=<?php echo $tags['nombre']; ?>&nick=<?php echo $UsuarioEsp['nick']; ?>&token=<?php echo $UsuarioEsp['token']; ?>" /><?php echo $tags['nombre']; ?></a></h5></li>
                                    <li><input id="tag" type="hidden" value="<?php echo $tags['nombre']; ?>" name="tag" /></a></h5></li>
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