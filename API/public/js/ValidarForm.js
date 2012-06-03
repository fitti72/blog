/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
	$("#formlogin").validate({
		rules: {
			nick: "required",
			pass: "required"
			
		},
		messages: {
			nick: "Please enter your Nick",
			pass: "Please enter your Password"
		}
	});
			
});
