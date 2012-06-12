package test;

import junit.framework.Assert;
import models.DAO.ComentarioDAO;
import models.DAO.TokenDAO;
import models.DAO.UsuarioDAO;
import models.DAO.MongoDB.ComentarioMongoDB;
import models.DAO.MongoDB.TokenMongoDB;
import models.DAO.MongoDB.UsuarioMongoDB;
import models.Negocio.GestorComentario;
import models.Negocio.GestorToken;
import models.Negocio.GestorUsuario;
import models.OD.ComentarioOD;
import models.OD.TokenOD;
import models.OD.UsuarioOD;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ComentarioTest {
		public ComentarioTest(){
		
		}
		public static GestorComentario gestorcomentario;
		public static GestorUsuario gestorusuario;
		public static UsuarioOD Test2;
		public static ComentarioOD comentario;
		public static GestorToken gestortoken;

			@BeforeClass
			public static void setUpClass()  {
				gestorcomentario = GestorComentario.getInstance();
				gestorusuario = GestorUsuario.getInstance();
				gestortoken = GestorToken.getInstance();
				Test2 = new UsuarioOD();
				comentario = new ComentarioOD();
				
			}
			
		        
		    @AfterClass
			public static void tearDownClass() {
		    	gestorcomentario = null;
				gestorusuario = null;
				gestortoken = null;
				Test2 = null;
				comentario = null;
			}
		 	
		    /**
		     * Esta prueba se encarga de crear un usuario, insertarlo y validar su existencia buscando un token para el, luego, para este usuario, crea un comentario, 
		     * con dos tags, y la prueba verifica que las inserciones sean correctas
		     * falla si hector ya esta creado
		     */
		    
			@Test
			public void InsertarComentarioTest() 
			{
				UsuarioOD beta = new UsuarioOD("Hector", "Alberto", "Sam", "Guevara", "hfsam88@hotmail.com", "sam", "Venezuela", "mr pruebas unitarias2", "M", "21/06/1990", "1234");
				Boolean test = gestorusuario.insertar(beta);
				Assert.assertEquals(true, test.booleanValue());	//valido que se haya insertado correctamente
				
				
				UsuarioOD validar = gestorusuario.Login(beta,"localhost"); 
				Assert.assertNotNull("el usuario exite en el sistema"+validar.getNick());	//chequeo que si este en persistencia el usuario
		
				ComentarioOD comentario = new ComentarioOD();
				comentario.setTexto("Segundo intento de prueba unitaria");
				
				UsuarioDAO yama = new UsuarioMongoDB();
				beta = yama.buscar(beta);
				
				comentario.setId_u(beta.getId_u());
				System.out.println("EPAAAAAAAAAAAAA");
				Assert.assertTrue(gestorcomentario.insertar(comentario, validar.getToken()));
				
				String tag1 = "tagPruebaUnitaria1";
				String tag2 = "tagPruebaUnitaria2";
				
				ComentarioDAO alfa = new ComentarioMongoDB();
				int k = alfa.Buscarid();
				comentario.setId_c(k);
				String tokan = Integer.toString(validar.getToken());
				boolean taguero = gestorcomentario.insertarTag(comentario,tag1,tokan);
				Assert.assertEquals(true,taguero);
				
				
				
				boolean tags = gestorcomentario.insertarTag(comentario,tag2,tokan);
				Assert.assertEquals(true,tags);
				
				
			
				
				TokenDAO betados = new TokenMongoDB();
				TokenOD alfados = new TokenOD();
				alfados.setToken(validar.getToken());
				betados.eliminar(alfados);
				boolean eliminar = gestorusuario.eliminar(beta);
				
			}
			
		  
			
		    
		    /**
		     * Prueba encargada de insertar un nuevo usuario, solicitar un token para el, inserta un nuevo comentario con este usuario, y este 
		     * comentario sera hijo del comentario que en persistencia tenga de id 2 y luego elimina este comentario. Esta prueba posee tres Assert's
			 * -validarUsuario e insertarComentarioHijo y eliminar ese comentario
			 */
			@Test
		    public void insertarComentarioHijoTest(){
				UsuarioOD beta = new UsuarioOD("Hector", "Alberto", "Sam", "Guevara", "hfsam88@hotmail.com", "sam", "Venezuela", "mr pruebas unitarias2", "M", "21/06/1990", "1234");
				Boolean test4 = gestorusuario.insertar(beta);
				
		    	UsuarioOD Test2 = new UsuarioOD();
				Test2.setNick("sam");	
				Test2.setClave("1234");
				UsuarioOD test = gestorusuario.Login(Test2, "localhost");
				Assert.assertEquals("Hector", test.getNombre());
				
				ComentarioOD comentario = new ComentarioOD();
				comentario.setTexto("Comentario a eliminar en la prueba unitaria, hijo de id 2");
				comentario.setPadre(5);
				comentario.setId_u(test.getId_u());
				
				//ahora inserto
				boolean inserto = gestorcomentario.insertar(comentario,test.getToken());
				Assert.assertEquals(true,inserto);
				
				
				ComentarioDAO alfa = new ComentarioMongoDB();
				int k = alfa.Buscarid();
				comentario.setId_c(k);
				String eliminado = gestorcomentario.eliminar(comentario, test.getToken(),test.getId_u());
				Assert.assertEquals("good", eliminado);
				//limpio
				TokenDAO betados = new TokenMongoDB();
				TokenOD alfados = new TokenOD();
				alfados.setToken(test.getToken());
				betados.eliminar(alfados);
				
				boolean eliminar = gestorusuario.eliminar(beta);
			}
		    
		    /**
		     * Prueba que consiste en validar un usuario existente en el sistema, es decir, obtener su token, e intenta eliminar un comentario que no es de el.
		     * La funcion eliminar, devuelve el mensaje "el Usuario no tiene los permisos para borrar este mensaje" entonces lo prueba sera exitosa si este es el
		     * mensaje devuelto
		     * -validar cualquier usuario, y eliminar cualquier comentario que no sea de el
		     */
		    @Test
		    public void eliminarComentarioNoPropietarioTest(){
		    	UsuarioOD Test2 = new UsuarioOD();
				Test2.setNick("juan");	
				Test2.setClave("1234");
				UsuarioOD test = gestorusuario.Login(Test2, "localhost");
				Assert.assertEquals("Juan", test.getNombre());
				
				ComentarioOD comentario = new ComentarioOD();
				comentario.setId_c(48);///////////////////////////////tiene q haber un comentario en Mongo que no haya sido creado por Juan
				String eliminado = gestorcomentario.eliminar(comentario,test.getToken(),test.getId_u());
				Assert.assertEquals("el Usuario no tiene los permisos para borrar este mensaje", eliminado);
				
				
				TokenDAO betados = new TokenMongoDB();
				TokenOD alfados = new TokenOD();
				alfados.setToken(test.getToken());
				betados.eliminar(alfados);
				
		    }
		
		    
		    /**
		     * -tener un padre con varios hijos y eliminar padre
		     */
		    @Test
		    public void eliminarPadreTest(){
				
				ComentarioOD comentario = new ComentarioOD();
				comentario.setTexto("ComentarioHijoPrueba1");
				comentario.setPadre(10);
				
				ComentarioOD comentario2 = new ComentarioOD();
				comentario2.setTexto("ComentarioHijoPrueba2");
				comentario2.setPadre(10);
				
				UsuarioOD Test2 = new UsuarioOD();	//valido a un usuario que sera el que insertara los hijos
				Test2.setNick("juan");	
				Test2.setClave("1234");
				UsuarioOD test = gestorusuario.Login(Test2, "localhost");
				Assert.assertEquals("Juan", test.getNombre());
				
				GestorComentario alfa = new GestorComentario();
				Boolean insertado = alfa.insertar(comentario,test.getToken());
				Assert.assertEquals(true,insertado.booleanValue());
				
				Boolean insertado2 = alfa.insertar(comentario2,test.getToken());
				Assert.assertEquals(true,insertado2.booleanValue());
				
				ComentarioOD padre = new ComentarioOD();
				padre.setId_c(3);
				String eliminado = gestorcomentario.eliminar(padre,test.getToken(),test.getId_u());
				Assert.assertEquals("good", eliminado);
				
				TokenDAO betados = new TokenMongoDB();
				TokenOD alfados = new TokenOD();
				alfados.setToken(test.getToken());
				betados.eliminar(alfados);
				
		    }
			
			
			

}

