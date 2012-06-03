<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Blog | Desarrollo del SoftWare</title>
        <link href="../public/css/faary.css" rel="stylesheet" type="text/css" media="all" />

        <link href="../public/css/styles.css" rel="stylesheet" type="text/css" media="all" />

        <script type="text/javascript" src="../public/js/jquery-validation-1.9.0/lib/jquery.js" ></script>
        <script type="text/javascript" src="../public/js/jquery-ui-1.8.20.custom/js/jquery-ui-1.8.20.custom.min.js"></script>
        <script type="text/javascript" src="../public/js/jquery-validation-1.9.0/jquery.validate.js"></script>
        <script type="text/javascript" src="../public/js/ValidarForm.js"></script>
        <script type="text/javascript" src="../public/js/ValidarFormRegister.js"></script>
        <script type="text/javascript" src="../public/js/datepiker.js"></script>
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
                    <h1 class="logo"><a href="index.html">M CUBE</a></h1>
                </div>
            </div>
        </div>


        <div id="content">
            <div id="content_cen">
                <div id="content_sup" class="head_pad">
                    <h2><span>Welcome to</span> BLOG</h2>
                    <div id="welcom_pan">
                        <h2>Edit your Porfile.</h2>
                        <div id="Register">


                            <form method="post" action="userEdited.php"  enctype="multipart/form-data"  class="iform" value="register" id="register" name="register">

                                <input type="hidden" name="orig" value="redister" />
                                <ul>
                                    <li><label for="FirstName">*First Name</label><input class="itext" type="text" name="nombre" id="nombre" /></li>
                                    <li><label for="SecondName">*Second Name</label><input class="itext" type="text" name="nombres" id="nombres" /></li>
                                    <li><label for="LastName">*Last Name</label><input class="itext" type="text" name="apellido" id="apellido" /></li>
                                    <li><label for="LastName">*Second Last Name</label><input class="itext" type="text" name="apellidos" id="apellidos" /></li>
                                    <li><label for="YourEmail">*Your Email</label><input class="itext" type="text" name="email" id="email" /></li>
                                    <li><label for="Re-enterEmail">*Re-enter Email</label><input class="itext" type="text" name="reemail" id="reemil" /></li>
                                    <li><label for="Nick">*Nick</label><input class="itext" type="text" name="nick" id="nick" /></li>
                                    <li><label for="NewPassword">*New Password</label><input class="itext" type="password" name="pass" id="pass" /></li>
                                    <li><label for="ReplayPassword">*Replay Password</label><input class="itext" type="password" name="rpass" id="rpass" /></li>
                                    <li><label for="date">*Date</label><input class="itext" type="text" name="fecha" id="fecha" /></li>
                                    <li><label for="Sex">Sex</label>
                                        <select class="iselect" name="sexo" id="sexo">
                                            <option value="M">Male</option>
                                            <option value="F">Female</option>
                                        </select></li>
                                    <li><label for="Bhiografi">*Biografi</label><input class="itextarea" type="text" name="biografia" id="biografia" /></li>
                                    <li><label for="Country">*Country</label><select id="pais" class="iselect" name="pais">
                                            <option>Afganistán</option>
                                            <option>Albania</option>
                                            <option>Alemania</option>
                                            <option>Andorra</option>
                                            <option>Anguila</option>
                                            <option>Antigua República Yugoslava de Macedonia</option>
                                            <option>Antigua y Barbuda</option>
                                            <option>Arabia Saudí</option>
                                            <option>Argelia</option>
                                            <option>Argentina</option>
                                            <option>Armenia</option>
                                            <option>Australia</option>
                                            <option>Austria</option>
                                            <option>Azerbaiyán</option>
                                            <option>Bahamas</option>
                                            <option>Bahráin</option>
                                            <option>Bangladesh</option>
                                            <option>Barbados</option>
                                            <option>Bélgica</option>
                                            <option>Belice</option>
                                            <option>Benín</option>
                                            <option>Bermudas</option>
                                            <option>Bielorrusia</option>
                                            <option>Birmania (actualmente Myanmar) </option>
                                            <option>Bolivia</option>
                                            <option>Bosnia y Herzegovina</option>
                                            <option>Botsuana</option>
                                            <option>Brasil</option>
                                            <option>Brunéi</option>
                                            <option>Bulgaria</option>
                                            <option>Burkina Faso</option>
                                            <option>Burundi</option>
                                            <option>Bután</option>
                                            <option>Cabo Verde</option>
                                            <option>Camboya</option>
                                            <option>Camerún</option>
                                            <option>Canadá</option>
                                            <option>Chad</option>
                                            <option>Chequia</option>
                                            <option>Chile</option>
                                            <option>Chile</option>
                                            <option>Chipre</option>
                                            <option>Cisjordania y Franja de Gaza</option>
                                            <option>Colombia</option>
                                            <option>Comoras</option>
                                            <option>Congo</option>
                                            <option>Corea del Norte</option>
                                            <option>Corea del Sur</option>
                                            <option>Costa de Marfil</option>
                                            <option>Costa Rica</option>
                                            <option>Croacia</option>
                                            <option>Cuba</option>
                                            <option>Dinamarca</option>
                                            <option>Dominica</option>
                                            <option>Ecuador</option>
                                            <option>Egipto</option>
                                            <option>El Salvador</option>
                                            <option>Emiratos Árabes Unidos</option>
                                            <option>Eritrea</option>
                                            <option>Eslovaquia</option>
                                            <option>Eslovenia</option>
                                            <option>España</option>
                                            <option>Estados Unidos</option>
                                            <option>Estonia</option>
                                            <option>Estonia</option>
                                            <option>Etiopía</option>
                                            <option>Filipinas</option>
                                            <option>Finlandia</option>
                                            <option>Fiyi</option>
                                            <option>Francia</option>
                                            <option>Gabón</option>
                                            <option>Gambia</option>
                                            <option>Georgia</option>
                                            <option>Ghana</option>
                                            <option>Granada</option>
                                            <option>Grecia</option>
                                            <option>Guadalupe</option>
                                            <option>Guadalupe</option>
                                            <option>Guam</option>
                                            <option>Guatemala</option>
                                            <option>Guinea</option>
                                            <option>Guinea-Bissau</option>
                                            <option>Guinea Ecuatorial</option>
                                            <option>Guyana</option>
                                            <option>Haití</option>
                                            <option>Holanda (Países Bajos)</option>
                                            <option>Honduras</option>
                                            <option>Hong Kong</option>
                                            <option>Hungría</option>
                                            <option>India</option>
                                            <option>Indonesia</option>
                                            <option>Irán</option>
                                            <option>Iraq</option>
                                            <option>Irlanda</option>
                                            <option>Islandia</option>
                                            <option>Islas Marshall</option>
                                            <option>Islas Salomón</option>
                                            <option>Israel</option>
                                            <option>Italia</option>
                                            <option>Jamaica</option>
                                            <option>Japón</option>
                                            <option>Jordania</option>
                                            <option>Kazajistán</option>
                                            <option>Kenia</option>
                                            <option>Kirguizistán</option>
                                            <option>Kiribati</option>
                                            <option>Kuwait</option>
                                            <option>Laos</option>
                                            <option>Lesoto</option>
                                            <option>Letonia</option>
                                            <option>Líbano</option>
                                            <option>Liberia</option>
                                            <option>Libia</option>
                                            <option>Liechtenstein</option>
                                            <option>Lituania</option>
                                            <option>Luxemburgo</option>
                                            <option>Macedonia</option>
                                            <option>Madagascar</option>
                                            <option>Malasia</option>
                                            <option>Malaui</option>
                                            <option>Maldivas</option>
                                            <option>Malí</option>
                                            <option>Malta</option>
                                            <option>Marruecos</option>
                                            <option>Mauritania</option>
                                            <option>Mauricio</option>
                                            <option>México</option>
                                            <option>Micronesia</option>
                                            <option>Moldavia</option>
                                            <option>Mónaco</option>
                                            <option>Mongolia</option>
                                            <option>Montenegro</option>
                                            <option>Mozambique</option>
                                            <option>Myanmar</option>
                                            <option>Namibia</option>
                                            <option>Nauru</option>
                                            <option>Nepal</option>
                                            <option>Nueva Zelanda</option>
                                            <option>Nicaragua</option>
                                            <option>Níger</option>
                                            <option>Nigeria</option>
                                            <option>Niue</option>
                                            <option>Noruega</option>
                                            <option>Omán</option>
                                            <option>Países Bajos</option>
                                            <option>Pakistán</option>
                                            <option>Palau</option>
                                            <option>Palestina</option>
                                            <option>Panamá</option>
                                            <option>Papúa-Nueva Guinea</option>
                                            <option>Paraguay</option>
                                            <option>Perú</option>
                                            <option>Polonia</option>
                                            <option>Portugal</option>
                                            <option>Puerto Rico</option>
                                            <option>Quatar</option>
                                            <option>Reino Unido</option>
                                            <option>República Centroafricana</option>
                                            <option>República Democrática del Congo</option>
                                            <option>República Democrática del Congo</option>
                                            <option>República Dominicana</option>
                                            <option>Rumania</option>
                                            <option>Rusia</option>
                                            <option>Ruanda</option>
                                            <option>Sahara Occidental</option>
                                            <option>Samoa</option>
                                            <option>San Cristóbal y Nieves</option>
                                            <option>San Marino</option>
                                            <option>San Vicente y las Granadinas</option>
                                            <option>Santa Lucía</option>
                                            <option>Santo Tomé y Príncipe</option>
                                            <option>Senegal</option>
                                            <option>Serbia</option>
                                            <option>Seychelles</option>
                                            <option>Sierra Leona</option>
                                            <option>Singapur</option>
                                            <option>Siria</option>
                                            <option>Somalía</option>
                                            <option>Sri Lanka</option>
                                            <option>Sudáfrica</option>
                                            <option>Sudán</option>
                                            <option>Suecia</option>
                                            <option>Suiza</option>
                                            <option>Surinam</option>
                                            <option>Suazilandia</option>
                                            <option>Tailandia</option>
                                            <option>Taiwán</option>
                                            <option>Tanzania</option>
                                            <option>Tayikistán</option>
                                            <option>Timor Oriental</option>
                                            <option>Togo</option>
                                            <option>Tonga</option>
                                            <option>Trinidad y Tobago</option>
                                            <option>Túnez</option>
                                            <option>Turkmenistán</option>
                                            <option>Turquía</option>
                                            <option>Tuvalu</option>
                                            <option>Ucrania</option>
                                            <option>Uganda</option>
                                            <option>Uruguay</option>
                                            <option>Uzbekistán</option>
                                            <option>Vanuatu</option>
                                            <option>Vaticano</option>
                                            <option selected="true">Venezuela</option>
                                            <option>Vietnam</option>
                                            <option>Yemen</option>
                                            <option>Yibuti</option>
                                            <option>Yugoslavia</option>
                                            <option>Zambia</option>
                                            <option>Zimbabue</option>
                                        </select></li>
                                    <li><label for="direccionFoto">Photo</label>       <input type="file" name="direccionFoto" id="direccionFoto">
                                            <span class="text">
                                                <input name="action" type="hidden" value="upload" />
                                            </span></li>
                                    <li></li>
                                    <br />
                                    <li><label>&nbsp;</label><input type="submit" class="ibutton" onclick="sendForm()" name="Register" id="Register" value="Register!" /></li>
                                </ul></form>
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
