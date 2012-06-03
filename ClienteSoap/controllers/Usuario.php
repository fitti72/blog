<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Usuario
 *
 * @author SCOTT
 */
class Usuario {

    var $resultado;

    /* function Usuario() 
     * Constructor de la Clase Usuario */

    function Usuario() {
        
    }

    /* function XMLtoUsuario($result) : array
     * Constructor array de  Usuario apartir de una string
     * Crea un xml y este lo castea arry para poder manipular dentro de la vista
     * Entrada: String 
     * Salida: Array[] 
     */
    
    
    
    
    
    function XMLtoUsuarioToken($result) {
        //echo $result;
        $xml = simplexml_load_string($result);

        if ($xml === false) {
            die('Error parsing XML');
        }
        
        
        foreach ($xml->xpath('//id__u') as $asunto) {
            $usuario['id_u'] = $asunto;
        }

        foreach ($xml->xpath('//objectid') as $asunto) {
            $usuario['objectid'] = $asunto;
        }
        foreach ($xml->xpath('//nombre') as $asunto) {
            //  echo $asunto."\n";
            $usuario['nombre'] = $asunto;
        }

        foreach ($xml->xpath('//nombres') as $asunto) {
            //  echo $asunto."\n";
            $usuario['nombres'] = $asunto;
        }

        foreach ($xml->xpath('//apellido') as $asunto) {
            // echo $asunto."\n";
            $usuario['apellido'] = $asunto;
        }

        foreach ($xml->xpath('//apellidos') as $asunto) {
            //echo $asunto."\n";
            $usuario['apellidos'] = $asunto;
        }
        $i = 0;
        foreach ($xml->xpath('//email') as $asunto) {
            // echo $asunto."\n";
            $usuario['email'] = $asunto;
            $i++;
        }
        $i = 0;
        foreach ($xml->xpath('//nick') as $asunto) {
            // echo $asunto."\n";
            $usuario['nick'] = $asunto;
            $i++;
        }

        foreach ($xml->xpath('//pais') as $asunto) {
            // echo $asunto."\n";
            $usuario['pais'] = $asunto;
        }

        foreach ($xml->xpath('//biografia') as $asunto) {
            // echo $asunto."\n";
            $usuario['biografia'] = $asunto;
        }

        foreach ($xml->xpath('//sexo') as $asunto) {
            // echo $asunto."\n";
            $usuario['sexo'] = $asunto;
        }

        foreach ($xml->xpath('//fecha') as $asunto) {
            // echo $asunto."\n";
            $usuario['fecha'] = $asunto;
        }

        foreach ($xml->xpath('//clave') as $asunto) {
            // echo $asunto."\n";
            $usuario['clave'] = $asunto;
        }
        
        foreach ($xml->xpath('//token') as $asunto) {
           
            $usuario['token'] = $asunto;
        }
        
        return $usuario;
    }

    function XMLtoUsuario($result) {
       
        $xml = simplexml_load_string($result);
    
        if ($xml === false) {
            die('Error parsing XML');
        }

        foreach ($xml->xpath('//id__u') as $asunto) {
            $usuario['id_u'] = $asunto;
        }

        foreach ($xml->xpath('//nombre') as $asunto) {
            //  echo $asunto."\n";
            $usuario['nombre'] = $asunto;
        }

        foreach ($xml->xpath('//nombres') as $asunto) {
            //  echo $asunto."\n";
            $usuario['nombres'] = $asunto;
        }

        foreach ($xml->xpath('//apellido') as $asunto) {
            // echo $asunto."\n";
            $usuario['apellido'] = $asunto;
        }

        foreach ($xml->xpath('//apellidos') as $asunto) {
            //echo $asunto."\n";
            $usuario['apellidos'] = $asunto;
        }
        $i = 0;
        foreach ($xml->xpath('//email') as $asunto) {
            // echo $asunto."\n";
            $usuario['email'] = $asunto;
            $i++;
        }
        $i = 0;
        foreach ($xml->xpath('//nick') as $asunto) {
            // echo $asunto."\n";
            $usuario['nick'] = $asunto;
            $i++;
        }

        foreach ($xml->xpath('//pais') as $asunto) {
            // echo $asunto."\n";
            $usuario['pais'] = $asunto;
        }

        foreach ($xml->xpath('//biografia') as $asunto) {
            // echo $asunto."\n";
            $usuario['biografia'] = $asunto;
        }

        foreach ($xml->xpath('//sexo') as $asunto) {
            // echo $asunto."\n";
            $usuario['sexo'] = $asunto;
        }

        foreach ($xml->xpath('//fecha') as $asunto) {
            // echo $asunto."\n";
            $usuario['fecha'] = $asunto;
        }

        foreach ($xml->xpath('//clave') as $asunto) {
            // echo $asunto."\n";
            $usuario['clave'] = $asunto;
        }

        foreach ($xml->xpath('//token') as $asunto) {
            // echo $asunto."\n";
            $usuario['token'] = $asunto;
        }
        return $usuario;
    }

    function XMLtoMensaje($result) {

        $xml = simplexml_load_string($result);
        if ($xml === false) {
            die('Error parsing XML');
        }

        foreach ($xml->xpath('//mensaje') as $asunto) {
            $usuario['id_u'] = $asunto;
           
        }
        return $usuario;
    }

    /* function   XMLtoListaDeUsuario($result) : array 
     * Constructor array de listas Usuario apartir de una string
     * Crea un xml y este lo castea arry para poder manipular dentro de la vista
     * Entrada: String 
     * Salida: Array[][] 
     */

    function XMLtoListaDeUsuario($result) {


        $xml = simplexml_load_string($result);
        
        if ($xml === false) {
            die('Error parsing XML');
        }


        $i = 0;
        foreach ($xml->xpath('//id__u') as $asunto) {
            $usuario[$i]['id_u'] = $asunto;
            $i++;
        }
        $i = 0;
        foreach ($xml->xpath('//nombre') as $asunto) {
            $usuario[$i]['nombre'] = $asunto;
            $i++;
        }
        $i = 0;
        foreach ($xml->xpath('//nombres') as $asunto) {
            // echo $asunto."\n";
            $usuario[$i]['nombres'] = $asunto;
            $i++;
        }
        $i = 0;
        foreach ($xml->xpath('//apellido') as $asunto) {
            //echo $asunto."\n";
            $usuario[$i]['apellido'] = $asunto;
            $i++;
        }
        $i = 0;
        foreach ($xml->xpath('//apellidos') as $asunto) {
            // echo $asunto."\n";
            $usuario[$i]['apellidos'] = $asunto;
            $i++;
        }
        $i = 0;
        foreach ($xml->xpath('//email') as $asunto) {
            // echo $asunto."\n";
            $usuario[$i]['email'] = $asunto;
            $i++;
        }
        $i = 0;
        foreach ($xml->xpath('//nick') as $asunto) {
            // echo $asunto."\n";
            $usuario[$i]['nick'] = $asunto;
            $i++;
        }
        $i = 0;
        foreach ($xml->xpath('//pais') as $asunto) {
            // echo $asunto."\n";
            $usuario[$i]['pais'] = $asunto;
            $i++;
        }
        $i = 0;
        foreach ($xml->xpath('//biografia') as $asunto) {
            // echo $asunto."\n";
            $usuario[$i]['biografia'] = $asunto;
            $i++;
        }
        $i = 0;
        foreach ($xml->xpath('//sexo') as $asunto) {
            // echo $asunto."\n";
            $usuario[$i]['sexo'] = $asunto;
            $i++;
        }
        $i = 0;
        foreach ($xml->xpath('//fecha') as $asunto) {
            // echo $asunto."\n";
            $usuario[$i]['fecha'] = $asunto;
            $i++;
        }
        
         $i = 0;
        foreach ($xml->xpath('//token') as $asunto) {
            // echo $asunto."\n";
            $usuario[$i]['token'] = $asunto;
            $i++;
        }
        return $usuario;
    }

    /* listar usuario
     * GET /blog/usuario/list controllers.Usuario.listar()
     * function ListarUsuarios() : array
     * Lista todos los Usuarios que se encuentren registrados en el sistema. 
     * Entrada: 
     * Salida: Array[][] 
     * 
     */

    function ListarUsuarios() {



        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, 'http://localhost:9000/blog/usuario/list');
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
        $result = curl_exec($curl);

        if ($result === false) {
            die('Error fetching data: ' . curl_error($curl));
        }
        curl_close($curl);
        if (strpos($result, "Error: ")) {
            return $this->XMLtoMensaje($result);
        } else {
            return $this->XMLtoListaDeUsuario($result);
        }
    }

    /* insertar usuarios
     * POST /blog/usuario/insertar controllers.Usuario.insertar()
     * function InsertarUsuario($nombre, $nombres, $apellido, $apellidos, $email, $nick, $pais, $biografia, $sexo, $fecha, $foto, $clave) : array
     * Agrega Un Usuario en el sistema y retorna los datos de ese usuario validado. 
     * Entrada: 12 string
     * Salida: Array[] 
     * 
     */

    function InsertarUsuario($nombre, $nombres, $apellido, $apellidos, $email, $nick, $pais, $biografia, $sexo, $fecha, $foto, $clave) {
        $prexml = " <usuario>
 	<nombre>" . $nombre . "</nombre>
   	<nombres>" . $nombres . "</nombres>
   	<apellido>" . $apellido . "</apellido>
   	<apellidos>" . $apellidos . "</apellidos>
   	<email>" . $email . "</email>
   	<nick>" . $nick . "</nick>
   	<pais>" . $pais . "</pais>
   	<biografia>" . $biografia . "</biografia>
   	<sexo>" . $sexo . "</sexo>
   	<fecha>" . $fecha . "</fecha>
   	<foto>" . $foto . "</foto>
   	<clave>" . $clave . "</clave>
</usuario>
";
        
        

        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, 'http://localhost:9000/blog/usuario/insertar');

        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
        // curl_setopt($curl,CURLOPT_HTTPGET,78447730);
        curl_setopt($curl, CURLOPT_POSTFIELDS, $prexml);
        curl_setopt($curl, CURLOPT_HTTPHEADER, array('Content-Type: text/xml', 'Content-Length: ' . strlen($prexml)));
        $result = curl_exec($curl);
      
        if ($result === false) {
            die('Error fetching data: ' . curl_error($curl));
        }
        curl_close($curl);


        if (strpos($result,"Error: ")) {
        
            return $this->XMLtoMensaje($result);
        } else {
           
            return $result = $this->XMLtoUsuario($result);
        }
    }

    /* eliminar Usuario 
     * DELETE	/blog/usuario/eliminar/:nick controllers.Usuario.eliminarUsuario(nick)
     * function  EliminarUsuarios($nick) :String
     * Elimina un usuario del sistema buscandolo por su nick retornando el mensaje de validacion. 
     * Entrada: String
     * Salida: String 
     * 
     */

    function EliminarUsuarios($nick) {


        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, 'http://localhost:9000/blog/usuario/eliminar/' . $nick);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
        $result = curl_exec($curl);

        if ($result === false) {
            die('Error fetching data: ' . curl_error($curl));
        }

  
            return $this->XMLtoMensaje($result);
        
    }

    /* Modificar Usuario
     * PUT	/blog/usuario/modificar/:token 	controllers.Usuario.modificarUsuario(token)
     * validar Usuario
     * POST /blog/usuario/validar controllers.Usuario.validarUsuario()
     * function  function ValidarUsuario($nick, $pass) : Array
     * Valida el nick y la clave del ususario que desea ingresar el sistema destro del arreglo que contendra los datos del ususario
     * trallendo destro de este arreglo el token otorgado al ususario 
     * Entrada: 2 String
     * Salida: Array 
     * 
     */

    function ValidarUsuario($nick, $pass) {

        $prexml = "<usuario>
	<nick>" . $nick . "</nick>
	<clave>" . $pass . "</clave>
</usuario>
";
        
        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, 'http://localhost:9000/blog/usuario/validar');
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
    
        curl_setopt($curl, CURLOPT_POSTFIELDS, $prexml);
        curl_setopt($curl, CURLOPT_HTTPHEADER, array('Content-Type: text/xml', 'Content-Length: ' . strlen($prexml)));
        $result = curl_exec($curl);
        // echo $result;
        if ($result === false) {
            die('Error fetching data: ' . curl_error($curl));
        }
        curl_close($curl);

        if (strpos($result, "Error: ")) {
            return $this->XMLtoMensaje($result);
        } else {
           
            return $this->XMLtoUsuarioToken($result);
        }
    }

//Un Usuario jajajajaa
//GET	/blog/usuario/unico/:nick controllers.Usuario.especifico(nick)


    function BuscarUsuarioEspe($nick) {

        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, 'http://localhost:9000/blog/usuario/unico/' . $nick);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
        $result = curl_exec($curl);
        // echo $result;
        if ($result === false) {
            die('Error fetching data: ' . curl_error($curl));
        }
        curl_close($curl);

        if (strpos($result, "Error: ")) {
            return $this->XMLtoMensaje($result);
        } else {

            return $this->XMLtoUsuario($result);
        }
    }

    public function nickdelid($nick) {
    
        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, 'http://localhost:9000/blog/usuario/datos/' . $nick);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
        $result = curl_exec($curl);
        // echo $result;
        if ($result === false) {
            die('Error fetching data: ' . curl_error($curl));
        }
        curl_close($curl);

        if (strpos($result, "Error: ")) {
            return $this->XMLtoMensaje($result);
        } else {
            echo $result;
            return $result;
        }
    }

         
    
    function ModificarUsuario($id_u, $nombre, $nombres, $apellido, $apellidos, $email, $nick, $pais, $biografia, $sexo, $fecha, $foto, $clave, $token) {
        $prexml = " <usuario>
        <id_u>" . $id_u . "</id_u>
 	<nombre>" . $nombre . "</nombre>
   	<nombres>" . $nombres . "</nombres>
   	<apellido>" . $apellido . "</apellido>
   	<apellidos>" . $apellidos . "</apellidos>
   	<email>" . $email . "</email>
   	<nick>" . $nick . "</nick>
   	<pais>" . $pais . "</pais>
   	<biografia>" . $biografia . "</biografia>
   	<sexo>" . $sexo . "</sexo>
   	<fecha>" . $fecha . "</fecha>
   	<foto>" . $foto . "</foto>
   	<clave>" . $clave . "</clave>
</usuario>
";
        //ahora debo validar al usuario
        

        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, 'http://localhost:9000/blog/usuario/modificar/' . $token);

        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($curl, CURLOPT_POSTFIELDS, $prexml);//si voy a mandar xml
        curl_setopt($curl, CURLOPT_HTTPHEADER, array('Content-Type: text/xml', 'Content-Length: ' . strlen($prexml)));//va cuando mandas xml pa q le de un formato
        $result = curl_exec($curl);
        echo $result;
        if ($result === false) {
            die('Error fetching data: ' . curl_error($curl));
        }
        curl_close($curl);


        if (strpos($result, "Error: ")) {
           
            return $this->XMLtoMensaje($result);
        } else {

            return $result = $this->XMLtoUsuario($result);
        }
    }

    
    
    
    
     function InsertarFoto($id_u, $ruta, $token) {
        $prexml = " <usuario>
        <id_u>" . $id_u . "</id_u>
 	<ruta>" . $ruta . "</ruta>
</usuario>
";
        //ahora debo validar al usuario
        

        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, 'http://localhost:9000/blog/usuario/foto/' . $token);

        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($curl, CURLOPT_POSTFIELDS, $prexml);//si voy a mandar xml
        curl_setopt($curl, CURLOPT_HTTPHEADER, array('Content-Type: text/xml', 'Content-Length: ' . strlen($prexml)));//va cuando mandas xml pa q le de un formato
        $result = curl_exec($curl);
        //echo $result;
        if ($result === false) {
            die('Error fetching data: ' . curl_error($curl));
        }
        curl_close($curl);


       
            return $result = $this->XMLtoMensaje($result);
        
    }
    
    
    function obtenerFoto($nick) {
        
        //ahora debo validar al usuario
        

        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, 'http://localhost:9000/blog/usuario/obtenerFoto/' . $nick);

        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
        //curl_setopt($curl, CURLOPT_POSTFIELDS, $prexml);//si voy a mandar xml
        //curl_setopt($curl, CURLOPT_HTTPHEADER, array('Content-Type: text/xml', 'Content-Length: ' . strlen($prexml)));//va cuando mandas xml pa q le de un formato
        $result = curl_exec($curl);
        //echo $result;
        if ($result === false) {
            die('Error fetching data: ' . curl_error($curl));
        }
        curl_close($curl);


       
            return $result = $this->XMLtoMensaje($result);
        
    }
    
    function obtenerNick($id_u) {
        
        //ahora debo validar al usuario
        

        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, 'http://localhost:9000/blog/usuario/datos/' . $id_u);

        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
        //curl_setopt($curl, CURLOPT_POSTFIELDS, $prexml);//si voy a mandar xml
        //curl_setopt($curl, CURLOPT_HTTPHEADER, array('Content-Type: text/xml', 'Content-Length: ' . strlen($prexml)));//va cuando mandas xml pa q le de un formato
        $result = curl_exec($curl);
        //echo $result;
        if ($result === false) {
            die('Error fetching data: ' . curl_error($curl));
        }
        curl_close($curl);


       
            return $result = $this->XMLtoMensaje($result);
        
    }  
        
        
        
        
    

}
?>


