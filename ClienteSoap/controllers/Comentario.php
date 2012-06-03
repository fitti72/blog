<?php

class Comentario {

    var $resultado;

    function Comentario() {
        
    }
    
    function XMLtoMensaje_C($result) {

        $xml = simplexml_load_string($result);
        if ($xml === false) {
            die('Error parsing XML');
        }

        foreach ($xml->xpath('//mensaje') as $asunto) {
            $comentario['id_c'] = $asunto;
           
        }
        return $comentario;
    }

    function XMLtoMensaje_T($result) {

        $xml = simplexml_load_string($result);
        if ($xml === false) {
            die('Error parsing XML');
        }
        
        
        print_r($xml);
        foreach ($xml->xpath('//mensaje') as $asunto) {
            $comentario['id_t'] = $asunto;
           
        }
        return $comentario;
    }
    
    function XMLtoListaDeComentarios($result) {

        $xml = simplexml_load_string($result);

        if ($xml === false) {
            die('Error parsing XML');
        }
        print_r($xml);
        $i = 0;
        foreach ($xml->xpath('//id__c') as $asunto) {
            $comentario[$i]['id_c'] = $asunto;
            $i++;
        }
        $i = 0;
        foreach ($xml->xpath('//texto') as $asunto) {
//  echo $asunto."\n";
            $comentario[$i]['texto'] = $asunto;
            $i++;
        }
        $i = 0;
        foreach ($xml->xpath('//fecha') as $asunto) {
// echo $asunto."\n";
            $comentario[$i]['fecha'] = $asunto;
            $i++;
        }
        $i = 0;
        foreach ($xml->xpath('//privacidad') as $asunto) {
//echo $asunto."\n";
            $comentario[$i]['privacidad'] = $asunto;
            $i++;
        }
        $i = 0;
        foreach ($xml->xpath('//id__u') as $asunto) {
// echo $asunto."\n";
            $comentario[$i]['id_u'] = $asunto;
            $i++;
        }
        $i = 0;
        foreach ($xml->xpath('//padre') as $asunto) {
// echo $asunto."\n";
            $comentario[$i]['padre'] = $asunto;
            $i++;
        }
        $i = 0;
        foreach ($xml->xpath('//gusta') as $asunto) {
// echo $asunto."\n";
            $comentario[$i]['gusta'] = $asunto;
            $i++;
        }
        $i = 0;
        foreach ($xml->xpath('//nogusta') as $asunto) {
// echo $asunto."\n";
            $comentario[$i]['nogusta'] = $asunto;
            $i++;
        }

        return $comentario;
    }

    function XMLtoTags($result) {

        $xml = simplexml_load_string($result);

        if ($xml === false) {
            die('Error parsing XML');
        }
        $i = 0;
//        print_r($xml);
        foreach ($xml->xpath('//id__t') as $asunto) {
            $tags[$i]['id_t'] = $asunto;
            $i++;
        }
        $i = 0;
        foreach ($xml->xpath('//nombre') as $asunto) {
//  echo $asunto."\n";
            $tags[$i]['nombre'] = $asunto;
            $i++;
        }

        return $tags;
    }

    function XMLtoContruirTags($result) {

        $xml = simplexml_load_string($result);

        if ($xml === false) {
            die('Error parsing XML');
        }

        foreach ($xml->xpath('//string') as $asunto) {
            $tags['string'] = $asunto;
        }


        return $tags;
    }

//insertar Comentario
//POST /blog/comentario/insertar/:token  controllers.Comentario.insertarComentario(token)
    function InsertarComentario($texto, $priva, $id_u, $padre, $tags, $token) {
echo $texto."////";
echo $id_u."////";
echo $priva."////";
echo $padre."////";
echo $tags."////";
echo $token."////";
echo $ta = $tags[0];


        $i = 1;
      /*  $prexml = "<comentario>
            <texto>" . $texto . "</texto>
            <privacidad>" . $priva . "</privacidad>
            <id_u>" . $id_u . "</id_u>
            <padre>" . $padre . "</padre>
            <tag>";

        foreach ($tags as $value) {
            $prexml = $prexml . "<tag" . $i . ">" . $value . "</tag" . $i . ">";
            $i++;
        }
        $prexml = $prexml . "</tag></comentario>";
        print_r($prexml); "comcatenado final     ".$prexml; */
        
        $prexml = "<comentario>
	<texto>".$texto."</texto>
	<privacidad>". $priva."</privacidad>
	<id_u>".$id_u."</id_u>
	<padre>".$padre."</padre>
	<tags>
		<tag1>".$ta."</tag1>
	</tags>	
</comentario>
";
        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, 'http://localhost:9000/blog/comentario/insertar/' . $token);

        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($curl, CURLOPT_POSTFIELDS, $prexml);
        curl_setopt($curl, CURLOPT_HTTPHEADER, array('Content-Type: text/xml', 'Content-Length: ' . strlen($prexml)));
        $result = curl_exec($curl);
        echo $result;
        if ($result === false) {
            die('Error fetching data: ' . curl_error($curl));
        } else {

            curl_close($curl);

            if (strpos($result, "Error: ")) {
                return $this->XMLtoMensaje_C($result);
            } else {
                return $result = $this->XMLtoListaDeComentarios($result);
            }
        }
    }

//listar todos Comentarios
//GET /blog/comentario/listart  controllers.Comentario.listart() 
    function ListarComentario() {

        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, 'http://localhost:9000/blog/comentario/listart');
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
        $result = curl_exec($curl);

        if ($result === false) {
            die('Error fetching data: ' . curl_error($curl));
        }
        curl_close($curl);

        if (strpos($result, "Error: ")) {
            return $this->XMLtoMensaje_C($result);
        } else {
            return $result = $this->XMLtoListaDeComentarios($result);
        }
    }

//REVISAR
//listar Comentarios especificos Cambiar token por id solo en las rutas
//GET /blog/comentario/listar/:id_c  controllers.Comentario.listarcomentarios(token)
    function ListarComentarioEspe($id_c) {


        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, 'http://localhost:9000/blog/comentario/listar/' . $id_c);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
        $result = curl_exec($curl);

        if ($result === false) {
            die('Error fetching data: ' . curl_error($curl));
        }
        curl_close($curl);
        if (strpos($result, "Error: ")) {
            return $this->XMLtoMensaje_C($result);
        } else {

            return $result = $this->XMLtoListaDeComentarios($result);
        }
    }

//hace like comentario
//POST /blog/comentario/like/:token controllers.Comentario.darLike(token)
    function HacerLike($id_c, $id_u, $like, $token) {


        $prexml = "<comentario>
	<id_c>" . $id_c . "</id_c>
	<id_u>" . $id_u . "</id_u>
	<like>" . $like . "</like>
</comentario>";

        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, 'http://localhost:9000/blog/comentario/like/' . $token);

        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
// curl_setopt($curl,CURLOPT_HTTPGET,78447730);
        curl_setopt($curl, CURLOPT_POSTFIELDS, $prexml);
        curl_setopt($curl, CURLOPT_HTTPHEADER, array('Content-Type: text/xml', 'Content-Length: ' . strlen($prexml)));
        $result = curl_exec($curl);
// echo $result;


        if ($result === false) {
            die('Error fetching data: ' . curl_error($curl));
        }
        curl_close($curl);
        return $result = $this->XMLtoMensaje_C($result);
    }

//REVISAR
//eliminar comentario por xml 
//POST /blog/comentario/eliminar/:token controllers.Comentario.eliminarComentariosXML(token)
    function eliminarComentario($token,$id_c,$nick) {



        $prexml = "<comentario>
	<id_c>".$id_c."</id_c>
	<nick>".$nick."</nick>
</comentario>";

        
        
        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, 'http://localhost:9000/blog/comentario/eliminar/'.$token);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($curl, CURLOPT_POSTFIELDS, $prexml);
        curl_setopt($curl, CURLOPT_HTTPHEADER, array('Content-Type: text/xml', 'Content-Length: ' . strlen($prexml)));
        $result = curl_exec($curl);
        
        if ($result === false) {
            die('Error fetching data: ' . curl_error($curl));
        }
        curl_close($curl);
        
        return $result = $this->XMLtoMensaje_C($result);
       
    }

//listar Comentario por Usuario
//GET /blog/comentario/listaru/:nick  controllers.Comentario.listarcomentariosUsuario(nick) 
    function ListarCoemntarioUsuario($nick) {


        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, 'http://localhost:9000/blog/comentario/listaru/' . $nick);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
        $result = curl_exec($curl);

        if ($result === false) {
            die('Error fetching data: ' . curl_error($curl));
        }

        if (strpos($result, "Error: ")) {
            return $result = $this->XMLtoMensaje_C($result);
        } else {
//print_r($xml);

            return $result = $this->XMLtoListaDeComentarios($result);
        }
    }

//REVISAR
//Insertar a Comentario Tag  No se que trae y no funciona bien el servicio 
//POST /blog/comentario/tag/:token controllers.Comentario.insertarComentariotag(token) 
    function InsertarTagAlComentario($id_c, $tag, $token) {

        $prexml = "<tag>
	<id_c>" . $id_c . "</id_c>
	<etiqueta>" . $tag . "</etiqueta>
</tag>";

        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, 'http://localhost:9000/blog/comentario/tag/' . $token);

        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
// curl_setopt($curl,CURLOPT_HTTPGET,78447730);
        curl_setopt($curl, CURLOPT_POSTFIELDS, $prexml);
        curl_setopt($curl, CURLOPT_HTTPHEADER, array('Content-Type: text/xml', 'Content-Length: ' . strlen($prexml)));
        $result = curl_exec($curl);
// echo $result;
        
        
        if ($result === false) {
            die('Error fetching data: ' . curl_error($curl));
        }
       return $result = $this->XMLtoMensaje_T($result);
    }

//Listar Comentarios por Tags
//GET  /blog/comentario/listar/tag/:tags     controllers.Comentario.listartags(tags) 
    function ListarComentarioTag($tag) {


        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, 'http://localhost:9000/blog/comentario/listar/tag/' . $tag);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
        $result = curl_exec($curl);

        if ($result === false) {
            die('Error fetching data: ' . curl_error($curl));
        }

        if (strpos($result, "Error: ")) {
            return $result = $this->XMLtoMensaje_T($result);
        } else {
            return $result = $this->XMLtoListaDeComentarios($result);
        }
    }
//Insertar Comentario con Adjunto
//POST	/blog/comentario/insertarAdjunto	controllers.Comentario.upload()
//Listar tags de un comentario
//GET	/blog/comentario/tags/:id_c			controllers.Comentario.tagDeUnComentario(id_c)
    function ListarTagComentario($id_c) {


        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, 'http://localhost:9000/blog/comentario/tags/' . $id_c);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
        $result = curl_exec($curl);

        if ($result === false) {
            die('Error fetching data: ' . curl_error($curl));
        }

        if (strpos($result, "Error: ")) {
            return $result = $this->XMLtoMensaje_T($result);;
        } else {

            return $result = $this->XMLtoListaDeComentarios($result);
        }
    }

    function ListarTag() {


        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, 'http://localhost:9000/blog/tags/listar');
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
        $result = curl_exec($curl);

        if ($result === false) {
            die('Error fetching data: ' . curl_error($curl));
        }

        if (strpos($result, "Error: ")) {
            return $result = $this->XMLtoMensaje_T($result);
        } else {

            return $result = $this->XMLtoTags($result);
        }
    }
    
 function InsertarAdjunto($texto, $priva, $id_u, $padre, $tags, $token,$ruta) {
echo $texto."////";
echo $id_u."////";
echo $priva."////";
echo $padre."////";
echo $tags."////";
echo $token."////";
echo $ta = $tags[0]."////";
echo $ruta;


        $i = 1;
      /*  $prexml = "<comentario>
            <texto>" . $texto . "</texto>
            <privacidad>" . $priva . "</privacidad>
            <id_u>" . $id_u . "</id_u>
            <padre>" . $padre . "</padre>
            <tag>";

        foreach ($tags as $value) {
            $prexml = $prexml . "<tag" . $i . ">" . $value . "</tag" . $i . ">";
            $i++;
        }
        $prexml = $prexml . "</tag></comentario>";
        print_r($prexml); "comcatenado final     ".$prexml; */
        
        $prexml = "<comentario>
	<texto>".$texto."</texto>
	<privacidad>". $priva."</privacidad>
	<id_u>".$id_u."</id_u>
	<padre>".$padre."</padre>
	<tags>
		<tag1>".$ta."</tag1>
	</tags>	
        <ruta>".$ruta."</ruta>
</comentario>
";
        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, 'http://localhost:9000/blog/comentario/insertarAdjunto/' . $token);

        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($curl, CURLOPT_POSTFIELDS, $prexml);
        curl_setopt($curl, CURLOPT_HTTPHEADER, array('Content-Type: text/xml', 'Content-Length: ' . strlen($prexml)));
        $result = curl_exec($curl);
        echo $result;
        if ($result === false) {
            die('Error fetching data: ' . curl_error($curl));
        } else {

            curl_close($curl);

            if (strpos($result, "Error: ")) {
                return $this->XMLtoMensaje_C($result);
            } else {
                return $result = $this->XMLtoListaDeComentarios($result);
            }
        }
    }

    
    
    
    
    
    
    
}

?>
