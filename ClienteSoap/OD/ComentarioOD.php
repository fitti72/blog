<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of ComentarioOD
 *
 * @author SCOTT
 */
class ComentarioOD {
    var $id_c;
    var $texto;
    var $fecha;
    var $privacidad;
    var $id_u;
    var $padre;
    var $gusta;
    var $nogusta;
    

  function __construct($id_c,$texto,$fecha,$privacidad,$id_u,$padre,$gusta,$nogusta) {
      
       $this->id_u = $id_c;
       $this->texto = $texto;
       $this->fecha = $fecha;
       $this->privacidad = $privacidad;
       $this->padre = $padre;
       $this->id_c = $id_u;
       $this->gusta = $gusta;
       $this->nogusta = $nogusta;
  
   }
    
  
   function getId_c ($id_c){
       $this->id_c = $id_c;
   }
    function setId_c(){
        return $this->id_c;
    }
   
   
   
   
   
   
}

?>
