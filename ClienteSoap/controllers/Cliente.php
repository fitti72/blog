<?php

$prexml = "<usuario>
	<nick>scott746</nick>
	<clave>540019</clave>
</usuario>";

$curl = curl_init();
curl_setopt($curl, CURLOPT_URL, 'http://localhost:9000/blog/usuario/validar');
curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
curl_setopt($curl, CURLOPT_POSTFIELDS, $prexml);
curl_setopt($curl, CURLOPT_HTTPHEADER, array('Content-Type: text/xml', 'Content-Length: ' . strlen($prexml)));
$result = curl_exec($curl);

if ($result === false) {
    die('Error fetching data: ' . curl_error($curl));
}
curl_close($curl);
echo $result;
//we can at this point echo the XML if you want
//echo $result;
//parse xml string into SimpleXML objects
$xml = simplexml_load_string($result);

if ($xml === false) {
    die('Error parsing XML');
}

echo $xml; /* $url = "http://localhost:9000/blog/comentario/listart"; 

  $ch = curl_init($url);
  $result = curl_exec($ch);
  curl_close($ch);

  echo $result; */



/* $ch = curl_init();

  $file = file_get_contents('http://localhost:9000/blog/comentario/listart');

  echo $file;
  curl_setopt($ch, CURLOPT_URL, 'http://localhost:9000/blog/comentario/listart');
  curl_setopt($ch, CURLOPT_HEADER, false);
  curl_setopt ($ch, CURLOPT_RETURNTRANSFER, true);
  $resultado = curl_exec($ch);

  $xml = simplexml_load_string($file);
  //$error = curl_error($ch);
  curl_close($ch);
  echo $resultado;
  echo "<br




  /> el XML deberia estar aqui:    ->";
  echo $xml; */
















/*


  require_once('../OD/ComentarioOD.php');

  $curl = curl_init();
  curl_setopt ($curl, CURLOPT_URL, 'http://localhost:9000/blog/comentario/listart');
  curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
  $result = curl_exec ($curl);

  if ($result === false) {
  die('Error fetching data: ' . curl_error($curl));
  }
  curl_close ($curl);

  //we can at this point echo the XML if you want
  //echo $result;

  //parse xml string into SimpleXML objects
  $xml = simplexml_load_string($result);

  if ($xml === false) {
  die('Error parsing XML');
  }





  //print_r($xml);



  foreach ($xml as $asunto){



  $i = 0;
  foreach ($xml->xpath('//id__c') as $asunto){
  $comentario[$i]['id__c'] = $asunto;
  $i++;

  }
  $i = 0;
  foreach ($xml->xpath('//texto') as $asunto){
  //  echo $asunto."\n";
  $comentario[$i]['texto'] = $asunto;
  $i++;
  }
  $i = 0;
  foreach ($xml->xpath('//fecha') as $asunto){
  // echo $asunto."\n";
  $comentario[$i]['fecha'] = $asunto;
  $i++;
  }
  $i = 0;
  foreach ($xml->xpath('//privacidad') as $asunto){
  //echo $asunto."\n";
  $comentario[$i]['privacidad'] = $asunto;
  $i++;
  }
  $i = 0;
  foreach ($xml->xpath('//id__u') as $asunto){
  // echo $asunto."\n";
  $comentario[$i]['id__u'] = $asunto;
  $i++;
  }
  $i = 0;
  foreach ($xml->xpath('//padre') as $asunto){
  // echo $asunto."\n";
  $comentario[$i]['padre'] = $asunto;
  $i++;
  }
  $i = 0;
  foreach ($xml->xpath('//gusta') as $asunto){
  // echo $asunto."\n";
  $comentario[$i]['gusta'] = $asunto;
  $i++;
  }
  $i = 0;
  foreach ($xml->xpath('//nogusta') as $asunto){
  // echo $asunto."\n";
  $comentario[$i]['nogusta'] = $asunto;
  $i++;
  }


  }
  echo $comentario[0]['id__c']."            ".$comentario[1]['id__c']."                 ".$comentario[2]['id__c'];

 */
?>
