/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(function(){

    // Datepicker
    $('#fecha').datepicker({
        inline: true,
        buttonImage: 'calendar.gif', //Indicamos el icono del botón 
        firstDay: 1,
        changeMonth: true,
        minDate: "-30Y", maxDate: "+1M +10D",
        changeYear: true
    });
});