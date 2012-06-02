<!DOCTYPE html>

<html>
    <head>
        <?php require_once('../controllers/Comentario.php')?>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div><?php $prueba = new Comentario(); 
        $result = $prueba->consumirListar();
        foreach ($result as $value) {
            echo $value['id__c'];
        }
        ?></div>
    </body>
</html>






