package models.Negocio;

import java.awt.List;

import play.Logger;

//import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import models.DAO.FotoDAO;
import models.DAO.TokenDAO;
import models.DAO.UsuarioDAO;
import models.DAO.MongoDB.FotoMongoDB;
import models.DAO.MongoDB.TokenMongoDB;
import models.DAO.MongoDB.UsuarioMongoDB;
import models.OD.FotoOD;
import models.OD.TokenOD;
import models.OD.UsuarioOD;

public class GestorUsuario {
	
	
	private static GestorUsuario instance = new GestorUsuario();

	public GestorUsuario(){}	 
		/**
		 * 
		 * @return la instancia
		 */
	 public static GestorUsuario getInstance() {
	        return instance;
	    }
	
	
	
	
	//funciones basicas	
	/**
	 * Se encarga de insertar el usuario en persistencia
	 * @param Usuario
	 * @return
	 */
	
	public boolean insertar(UsuarioOD Usuario)
	{
		if (validarNick(Usuario))
		{
			System.out.print(Usuario.getNick());
			UsuarioDAO Persona = new UsuarioMongoDB();
			Persona.insertar(Usuario);
			Logger.info("GestorUsuario: Usuario Insertado en sistema "+ Usuario.getNick());
			return true; 
		}
		else 
		{
			System.out.print("validar nick dijo que no");
			Logger.error("GestorUsuario: Nick ya existente");
			return false;
		}		
	}


	/**
	 * se encarga de buscar un usuario en especifico por  
	 * @param Usuario
	 * @return
	 */
	
	public UsuarioOD usuarioespecifico (UsuarioOD Usuario)
	{
		UsuarioDAO usuario = new UsuarioMongoDB();
		UsuarioOD result = usuario.buscar(Usuario);
		if (result != null)
		{
			return result;
		}
		else
		{
			return null;
		}

	}
	
	
	
	/**
	 * se encarga de eliminar  un usuaio en persistencia
	 * @param usuario
	 * @return
	 */
	public boolean eliminar(UsuarioOD usuario)
	{
		boolean estado = false;
		UsuarioDAO c = new UsuarioMongoDB();
	    c.eliminar(usuario);
	    
	    
	    Logger.info("GestorUsuario: Usuario Eliminado");
	    estado = true;
	    return estado;
	}

	/**
	 * se encarga de modificar los datos del usuario en persistencia
	 * @param Usuario
	 * @return
	 */
	public boolean modificar(UsuarioOD Usuario)
	{
		GestorToken gestort = new GestorToken();
		TokenOD Token = new TokenOD();
		Token.setToken(Usuario.getToken());
		if (gestort.validartoken(Token))
		{
			UsuarioDAO usuario = new UsuarioMongoDB();
			usuario.modificar(Usuario);//entonces primero busco y despues mando los dos //id_u debe ser el mismo
			Logger.info("GestorUsuario: Ha sido modificaco el usuario "+ Usuario.getNick());
			return true;
		}
		else 
		{
			Logger.error("GestorUsuario: No se puedo modificar el usuario, token vencido o no valido");
			return false;
		}
	 
}
	
	
	//funciones de validacion
	/**
	 * se encarga de validar el nick verificando si existe en persistencia
	 * @param Usuario
	 * @return
	 */
	public boolean validarNick(UsuarioOD Usuario)
	{	
		UsuarioDAO usuario = new UsuarioMongoDB();
		UsuarioOD result = usuario.buscar(Usuario);		
		if (result == null)//entonces el nick es nuevo
		{
			Logger.info("GestorUsuario: Nick valido: "+Usuario.getNick());
			return true;				
		}
		else
		{
			Logger.error("GestorUsuario: Nick no valido");
			return false;
		}
	
	}
	
	/**
	 * se encarga de validar si la contrasena del usuario es valida
	 * @param Usuario
	 * @return
	 */

	public boolean validarPass(UsuarioOD Usuario)
	{		
		UsuarioDAO usuario = new UsuarioMongoDB();
		UsuarioOD result = usuario.buscar(Usuario);
		if ((result != null)&&(result.getClave().equals(Usuario.getClave())))
		{
			Logger.info("GestorUsuario: Contrasena valida");
			return true;				
		}
		else
		{
			Logger.error("GestorUsuario: Contrasena invalida");
			return false;
		}
	
	}


	/**
	 * se encarga de validar el nick y la contrasena del usuario sean  validas y que no se conecte desde la misma computadora 2 veces, la unica forma de volverte a conectar 
	 * es que el token este vencido
	 * @param Usuario
	 * @param IP
	 * @return
	 */
	public UsuarioOD Login(UsuarioOD Usuario,String IP)
	{
		if (!validarNick(Usuario))		//la funcion validarNick devuelve false si si encuenta al nick en la base de datos
		{
			if (validarPass(Usuario))
			{
				GestorToken gestort = new GestorToken();
				UsuarioDAO usuario = new UsuarioMongoDB();
				UsuarioOD result = usuario.buscar(Usuario);
				TokenOD Token = new TokenOD();
				System.err.println("este es el ip que llega "+IP);
				Token.setIp(IP);
				System.err.println("este es el ip que manda a encontrar "+Token.getIp());
				Token = gestort.BuscarIP(Token);
				// System.err.println(Token.getFecha());
				  
				if(!(Token!=null)||(!gestort.validartoken(Token)))//si el tojen es igual null o validar devuelve falso
				{
					TokenOD token  = gestort.insertar(result,IP);
					Usuario = result;
					Usuario.setToken(token.getToken());
					Logger.info("GestorUsuario: Asignandole un token: "+ Usuario.getToken() + " al usuario: " +Usuario.getNick());
					return Usuario;				  
				 }
				else 
				{
					//System.out.print("No se puede Iniciar Sesion desde la misma pc"); 
					Usuario.setNombre("error");
					Usuario.setNombres("No se puede Iniciar Sesion desde la misma pc");
					Logger.error("GestorUsuario: Error debido a  estar conectado desde la misma pc");
					return Usuario;
				}
			}
			else
			{
				//System.out.print("validar pass dijo que no"); 
				Usuario.setNombre("error");
				Usuario.setNombres("Clave incorrecta");
				Logger.error("GestorUsuario: Contrasena invalida");
				return Usuario;
			}
		}
		else 
		{
			//System.out.print("validar nick dijo que no");  
			Usuario.setNombre("error");
			Usuario.setNombres("Nick incorrecto");
			Logger.error("GestorUsuario: Nick Invalido");
			return Usuario;
		}
		  
		
	}
		/**
		 * se encarga de buscar los usuario por id_u
		 * @param Usuario
		 * @return
		 */
	public UsuarioOD Buscar(UsuarioOD Usuario)
	{
		 UsuarioDAO usuario = new UsuarioMongoDB();
		 UsuarioOD result = usuario.buscarID(Usuario);
		 Usuario = result;
		 return result;
	}
	
	
	public boolean esSuToken(int idUsuario,int tokens)
	{
		UsuarioOD jou = new UsuarioOD(idUsuario,null,null,null,null,null,null,null,null,null,null,null);
		TokenDAO bethoven = new TokenMongoDB();
		TokenOD quetal = bethoven.buscarPorUsuario(jou);
		if (quetal == null)
		{
			System.out.println("brother preocupate q stas en problemas");
			Logger.error("GestorUsuario: el token no es tuyo");
			return false;
		}
		else
		{
			if (quetal.getToken() != tokens)
			{
				Logger.error("GestorUsuario: El token es tuyo pero viejo");
				return false;
			}
			else
			{
				Logger.info("Token en perfecto estado");
				return true;
			}
		}
	}
	
	public boolean insertarFoto(FotoOD foto,int token)
	{
		GestorToken gestort = new GestorToken();
		TokenOD Token = new TokenOD();
		Token.setToken(token);
		if (gestort.validartoken(Token))
		{
			boolean flag = false;
			FotoDAO beta = new FotoMongoDB();
			beta.insertar(foto);
			Logger.info("GestorUsuario: foto insertada exitosamente");
			flag = true;
			return flag;
		}
		else 
		{
			Logger.error("GestorUsuario: No se puedo insertar la foto, token vencido o no valido");
			return false;
		}		
	}
	
	public String obtenerRutaFotoUsuario(UsuarioOD usuario)
	{
		FotoOD enlace = new FotoOD();//primero busco el id del usuario
		UsuarioDAO bita = new UsuarioMongoDB();
		UsuarioOD quetal = bita.buscar(usuario);
		if (quetal == null)
		{
			Logger.error("GestoUsuario: nick no encontrado en el sistema");
			return "error: nick no encontrado";
		}
		else
		{
			enlace.setId_u(quetal.getId_u());
			FotoDAO bam = new FotoMongoDB();
			FotoOD verdadero = bam.buscar(enlace);	
			if (verdadero == null)
			{
				Logger.warn("GestorUsuario: el usuario no ha cargado foto");
				return "error: ese usuario no ha cargado foto de perfil";
			}
			else
			{
				Logger.info("GestorUsuario: se ha cargado la ruta de la foto de perfil exitosamente");
				return verdadero.getRuta();
			}
		}
	}
	
	
	
}
	
	
	
	
	
