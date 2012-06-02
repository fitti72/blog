<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Blog | Desarrollo del SoftWare</title>
        <link href="public/css/styles.css" rel="stylesheet" type="text/css" media="all" />
        <link href="public/css/faary.css" rel="stylesheet" type="text/css" media="all" />
        <link href="public/js/gen_validatorv4.js" rel="stylesheet" type="text/css" media="all" />
    </head>

    <body>



        <div id="head">
            <div id="head_cen">
                <div id="head_sup" class="head_pad">
                    <form class="log-in"method="post" action="views/Message/ValidarUsuario.php" id="formlogin" name="formlogin" >
                        <label>Nick</label>
                        <input name="nick" type="text" class="txt" id="nick"/>
                        <label>Password</label> 
                        <input name="pass" type="password" class="txt" id="pass"/>
                        <input type="submit" class="btn" value="Log In" onclick="sendForm()"  name="login"  id="login" />
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
                        <h2>Register</h2>
                        <div id="Register">

                            <form method="post" action="views/timeline.php" class="iform">
                                <div id="imessageOK">Thank you! Message Sent!</div>
                                <div id="imessageERROR">ERROR: Message Not Sent!</div>
                                <ul>
                                    <li>
                                        <label for="FirstName">*First Name</label>
                                        <input class="itext" type="text" name="FirstName" id="FirstName" /></li>
                                    <li><label for="SecondName">*Second Name</label><input class="itext" type="text" name="SecondName" id="SecondName" /></li>
                                    <li><label for="LastName">*Last Name</label><input class="itext" type="text" name="LastName" id="LastName" /></li>
                                    <li><label for="YourEmail">*Your Email</label><input class="itext" type="text" name="YourEmail" id="YourEmail" /></li>
                                    <li><label for="Re-enterEmail">*Re-enter Email</label><input class="itext" type="text" name="Re-enterEmail" id="Re-enterEmail" /></li>
                                    <li><label for="Nick">*Nick</label><input class="itext" type="text" name="Nick" id="Nick" /></li>
                                    <li><label for="NewPassword">*New Password</label><input class="itext" type="password" name="NewPassword" id="NewPassword" /></li>
                                    <li><label for="ReplayPassword">*Replay Password</label><input class="itext" type="password" name="ReplayPassword" id="ReplayPassword" /></li>
                                    <li><label for="Sex">Sex</label><select class="iselect" name="Sex" id="Sex"><option value="Male">Male</option>
                                            <option value="Female">Female</option>
                                        </select></li>
                                    <li><label for="Bhiografi">*Biografi</label><input class="itextarea" type="text" name="Bhiografi" id="Bhiografi" /></li>
                                    <li><label for="Country">*Country</label><input class="itext" type="text" name="Country" id="Country" /></li>
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

