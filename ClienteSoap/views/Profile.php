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
        require_once('../controllers/Usuario.php');
        require_once('../controllers/Comentario.php');
        $Comentario = new Comentario();
        $Usuario = new Usuario();
        if ($_GET['nick'] != "") {
            $result = $Comentario->ListarCoemntarioUsuario($_GET['nick']);
            $resultUsu = $Usuario->BuscarUsuarioEspe($_GET['nick']);
        } else {

            session_start();
            $result = $Comentario->ListarCoemntarioUsuario($_SESSION['nick']);
            $resultUsu = $Usuario->BuscarUsuarioEspe($_SESSION['nick']);
        }
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

                    <div id="welcom_pan">
                        <h2>History</h2>


                        <div id="service_pan">
                            <ul>
                                <?php foreach ($result as $value) { ?>  
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
                            <li><img src="../public/images/ps.jpg" width="93" height="82"/><a href="#"> Edit Picture</a></li>
                            <li> <h4><?php echo $resultUsu['nick'] ?></h4> </li>
                            <li><h5>Name</h5><p><?php echo $resultUsu['nombre'] . " " . $resultUsu['nombres'] . " " . $resultUsu['apellido'] . " " . $resultUsu['apellidos']; ?></p> </li>
                            <li><h5>Email</h5><p><?php echo $resultUsu['email'] ?></p> </li>
                            <li><h5>Country</h5><p><?php echo $resultUsu['pais'] ?></p></li>
                            <li><h5>Date</h5><p><?php echo $resultUsu['fecha'] ?></p> </li>
                            <li><h5>Country</h5><p><?php echo $resultUsu['pais'] ?></p></li> 
                            <li><h5>Biografi</h5><p><?php echo $resultUsu['biografia'] ?></p> </li>


                             <h4><a href="Edit-User.php">Editar..</a></h4>

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