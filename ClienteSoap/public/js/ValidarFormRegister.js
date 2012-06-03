/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

// extend the current rules with new groovy ones
	
// this one requires the text "buga", we define a default message, too
$.validator.addMethod("buga", function(value) {
    return value == "buga";
}, 'Please enter "buga"!');
	
// this one requires the value to be the same as the first parameter
$.validator.methods.equal = function(value, element, param) {
    return value == param;
};
	

$(document).ready(function(){
    $("#register").validate({
        
        
        rules: {
            nombre: {
                required: true, 
                minlength: 2
            },
            nombres: {
                required: false, 
                minlength: 2
            },
            apellido: {
                required: true, 
                minlength: 2
            },
            apellidos:{
                required: false, 
                minlength: 2
            },
            email:{
                required: true, 
                email: true
            },
            reemail: {
                required: true,
                email: true,
                equalTo:"#email"
            },
            nick: {
                required: true, 
                minlength: 2
            },
            pass: {
                required: true, 
                minlength: 2
            },
            rpass: {
                required: true, 
                minlength: 2
                
            },
			
            biografia: {
                required: false, 
                minlength: 15, 
                maxlength: 200
            }

			
        },
        messages: {
            nombre:{  
                required:"required name",  
                minlength:"enter at least 3 characters"  
            },  
            apellido:
            {  
                required:"required lastname",  
                minlength:"enter at least 3 characters"  
            },
            email:
            {
                required:"required email",  
                email :"invalid email"
            },
            reemail:
            {
                required:"required email",  
                email :"invalid email",
                equalTo:"diferent email"
            },    
            nick:{  
                required:"required nick",  
                minlength:"enter at least 3 characters"  
            },  
            pass: {  
                required:"required  pass",  
                minlength:"ingrese al menos 3 caracteres"  
            },  
            rpass: { 
                
                required:"required rpass",  
                email :"invalid email",
                
                 
            },  
            biografia:
            {
                minlength: "enter at least 15 characters", 
                maxlength: "enter at most 200 characters"
            }
        
        }
        
        
        
        
    });
	
        
        
});
