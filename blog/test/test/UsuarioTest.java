package test;

import junit.framework.Assert;
import models.DAO.TokenDAO;
import models.DAO.MongoDB.TokenMongoDB;
import models.Negocio.GestorUsuario;
import models.OD.TokenOD;
import models.OD.UsuarioOD;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class UsuarioTest {
	public static GestorUsuario gestorUsuario;
	public UsuarioTest() {
	}

	@BeforeClass
	public static void setUpClass() {
		gestorUsuario = GestorUsuario.getInstance();
	}

	@AfterClass
	public static void tearDownClass() {
		gestorUsuario = null;
	}

	
	
	/**
	 * Prueba para insertar nuevos usuarios, si todo el proceso es correcto la funcion insertar debe devolver true. 
	 * La funcion insertar corre la funcion validarNick. Si el nick es nuevo, lo inserta y la prueba verifica que 
	 * si este insertado con la funcion usuarioespecifico. Si el nick ya esta en el sistema, la prueba fallara
	 */
/*
	@Test 
	public void validarInsertarTest() {
		UsuarioOD beta = new UsuarioOD("Renato", "Gianfranco", "Ferrante", "Ghersi", "fitipaldi_6272@hotmail.com", "fitiPrueba", "Venezuela", "mr pruebas unitarias2", "M", "21/06/1990", "1234");
		System.out.println("A1111111111111111111111111111: "+beta.getNick());
		Boolean test = gestorUsuario.insertar(beta);
		Assert.assertEquals(true, test.booleanValue());
		//ahora debo verificar que si esta en persistencia
		UsuarioOD alfa = gestorUsuario.usuarioespecifico(beta);
		Assert.assertEquals("Renato", alfa.getNombre());
		//boolean eliminar = gestorUsuario.eliminar(beta);		
	}

*/	

	
	
	/**
	 * Esta prueba se basa en la validacion de los usuarios, creamos un usuario de nick fiti y password 1234 que ya debe estar registrado en nuestra base de datos,
	 * si alteramos nick o password la prueba falla, si todos mis datos son verdaderos, entonces al hacer la prueba la primera vez todo estara bien, pero a la segunda
	 * como el usuario ya tienen un token asignado, si el token no esta vencido, entonces la prueba fallara. 
	 * En esta prueba se estan verificando varias funciones del GestorUsuario como: validarNick, validarPass y validarToken
	 */
/*
	@Test
	public void validarTokenUsuarioTest() {
		UsuarioOD Test2 = new UsuarioOD();
		Test2.setNick("fitiPrueba");	
		Test2.setClave("1234");
		UsuarioOD test = gestorUsuario.Login(Test2, "localhost");
		Assert.assertEquals("Renato", test.getNombre());	//sabemos que el nick fiti pertenece al usuario de nombre Renato
	}
*/
	
	
	/**
	 * Prueba que consiste en buscar los datos del usuario creado en el primer test, buscar su token, y modificar sus datos
	 */
/*
	@Test
	public void validarModificarUsuarioTest(){	
		System.out.println("ENTROOOOOO");		
		UsuarioOD beta = new UsuarioOD();
		beta.setNick("fitiPrueba");
		beta.setClave("1234");
		
		UsuarioOD buscado = gestorUsuario.usuarioespecifico(beta);
		System.out.println("1AKI BUSKE Y ME TRAJE A: "+buscado.getNombre());	
		Assert.assertEquals(buscado.getNombre(), "Renato");
		
		
		//necesito el token, lo busco
		UsuarioOD jou = new UsuarioOD(buscado.getId_u(),null,null,null,null,null,null,null,null,null,null,null);
		TokenDAO bethoven = new TokenMongoDB();
		TokenOD quetal = bethoven.buscarPorUsuario(jou);
		System.out.println("2ENTRO AL TEST NUEVO "+quetal.getToken());
		Assert.assertNotNull(quetal.getToken());
		
		
		UsuarioOD usuario = new UsuarioOD(buscado.getId_u(),"Estos", "son", "mis", "datos", "de@prueba", "para", "la", "defensa", "F", "25/05/1980", "123",quetal.getToken());
		Boolean alfa = gestorUsuario.modificar(usuario);
		System.out.println("ESTA devuelve: "+alfa.booleanValue());
		Assert.assertEquals(true,alfa.booleanValue());		
		//Assert.assertEquals(5,5);
		
	}
*/	
	
	/**
	 * Prueba que se encarga de eliminar el usuario que insertamos en la prueba 1, que validamos en la prueba 2 y que modificamos en la prueba 3
	 */
/*
	@Test
	public void validarEliminarUsuarioTest(){
		UsuarioOD beta = new UsuarioOD();
		beta.setNick("para");
		beta.setClave("123");
		UsuarioOD buscadoEspecial = gestorUsuario.usuarioespecifico(beta);
		Assert.assertEquals(buscadoEspecial.getNombre(), "Estos");
		System.out.println("2AKI BUSKE Y ME TRAJE A: "+buscadoEspecial.getNombre());	
		Boolean eliminado = gestorUsuario.eliminar(buscadoEspecial);
		Assert.assertTrue(true);
		
	}
	

*/
	

}
