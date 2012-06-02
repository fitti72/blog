<?php

if ($_POST["login"] != "") {

    $nick = $_POST['nick'];
    $pass = $_POST['pass'];



    require_once('../../controllers/Usuario.php');
    $Usuario = new Usuario();

    $result = $Usuario->ValidarUsuario($nick, $pass);
    $sol = $result['id_u'];
    if (strpbrk($sol,"Error: ")) {
        
        ?> 
        <script>
            window.alert("<?php echo $sol;?>")
            location = "../../index.php";
        </script>    
        <?php

    } else {        
         ?> 
        <script>
            window.alert("Holaaaaaaaaaaaaa <?php echo $result['nick'];?>")
            location = "../../views/timeline.php";
        </script>    
        <?php

    }
}
?>
