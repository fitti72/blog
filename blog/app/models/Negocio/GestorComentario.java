package models.Negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import play.Logger;
import models.DAO.AdjuntoDAO;
import models.DAO.ComentarioDAO;
import models.DAO.FotoDAO;
import models.DAO.GustaDAO;
import models.DAO.TagDAO;
import models.DAO.Tag_ComentarioDAO;
import models.DAO.TokenDAO;
import models.DAO.UsuarioDAO;
import models.DAO.MongoDB.AdjuntoMongoDB;
import models.DAO.MongoDB.ComentarioMongoDB;
import models.DAO.MongoDB.FotoMongoDB;
import models.DAO.MongoDB.GustaMongoDB;
import models.DAO.MongoDB.TagMongoDB;
import models.DAO.MongoDB.Tag_ComentarioMongoDB;
import models.DAO.MongoDB.TokenMongoDB;
import models.DAO.MongoDB.UsuarioMongoDB;
import models.OD.AdjuntoOD;
import models.OD.ComentarioOD;
import models.OD.FotoOD;
import models.OD.GustaOD;
import models.OD.TagOD;
import models.OD.Tag_ComentarioOD;
import models.OD.TokenOD;
import models.OD.UsuarioOD;

public class GestorComentario 
{
	 private static GestorComentario instance = new GestorComentario();

	public GestorComentario(){}
	
	 
		/**
		 * 
		 * @return la instancia
		 */
	 public static GestorComentario getInstance() {
	        return instance;
	    }
	
		/**
		 * se encarga de recibir del controlador el comentrio y el token del usuario y aqui es donde se valida si el token es del usuario y si el vakidos
		 * y de ser cierto
		 * @param Comentario
		 * @param token
		 * @return
		 */
		
	public boolean insertar(ComentarioOD Comentario,int token)
	{
		Date fecha = new Date();
		Comentario.setFecha(fecha.toString());
		UsuarioOD Usuario = new UsuarioOD();
		TokenDAO t = new TokenMongoDB();
		UsuarioDAO u = new UsuarioMongoDB();
		ComentarioOD aux = new ComentarioOD();
		GestorToken gestort = new GestorToken();
		TokenOD Token = new TokenOD();
		Token.setToken(token);
		Token = t.buscar(Token); 
		if (Token == null)
		{
			System.out.println("Token = null significa que el token buscado no se encuentra registrado");
			return false;
		}
		
		if (gestort.validartoken(Token))
		{
			ComentarioDAO c = new ComentarioMongoDB();
			//aux.setId_c(Comentario.getId_c());
			System.out.println("estoy seteandole: "+Comentario.getPadre());
			aux.setId_c(Comentario.getPadre());
			aux = c.buscar(aux);
			if (Comentario.getPadre()!= 0)
			{
				System.out.println("1entro: "+Comentario.getPrivacidad());
				if(padreExistePrivasidad(Comentario))
				{ 
					System.out.println("2entro");
					Usuario.setId_u(aux.getId_u());
					Usuario = u.buscarID(Usuario);
					//System.out.println("envio mensaje a:  "+Usuario.getEmail());
					c.insertar(Comentario);
					Logger.info("GestorComentario: Insertando comentario hijo en persistencia "+Comentario.getTexto());
					return true;
				}
				else
				{
					System.out.println("el comentario padre es privado"); 
					Logger.error("GestorComentario: Error: el comentario padre es privado, para el comentario: "+Comentario.getId_c());
					return false;
				}
			}
			else 
			{
				c.insertar(Comentario);
				Logger.info("GestorComentario: Insertando comentario padre en persistencia "+Comentario.getTexto());
				return true;
			}
		}
		else
		{
			System.out.println("token no valido");
			Logger.error("GestorComentario: Token invalido");
			return false;
		}
					 
	}
	
	
	/**
	 * Se encarga de validar el estatus del comentario. Si es privado, no acepta respuestas. Si es publico, si acepta
	 * Si es 0 no acepta respuestas
	 * Si es 1 si acepta respuetas
	 * @param Comentario
	 * @return
	 */
		
	public boolean padreExistePrivasidad(ComentarioOD Comentario)
	{
		System.out.println(Comentario.getPrivacidad()+"id de comentario en  existe privasidad");
		ComentarioDAO comentario = new ComentarioMongoDB();
		Comentario.setId_c(Comentario.getPadre());
		ComentarioOD result = comentario.buscar(Comentario);
		System.out.println(result);
		if (result != null)
		{			
			if (result.getPrivacidad() == 1)
			{
				return true;
			}
			else
			{
				System.out.println("Mensaje Privado no acepta respuestas");
				Logger.warn("GestorComentario: El comentario: "+Comentario.getId_c()+" es privado");
				return false;
			}
		}
		else
		{
			System.out.println("Mensaje Padre no existe"+Comentario.getPadre());
			Logger.error("GestorComentario: El comentario: "+Comentario.getPadre()+" no existe");
			return false;
		}
			
	}
	
	
	
	/**
	 * Funcion que elimina comentarios, si no es posible, devuelve un error, especificando la causa del mismo
	 * 
	 * @param Comentario
	 * @param token
	 * @param id_u
	 * @return
	 */
	
	public String eliminar(ComentarioOD Comentario, int token , int id_u)
	{			
		System.out.println(id_u + " id del usuario que quiere eliminar comentario");
		System.out.println(token + " token enviado para validar por usuario y token");
		ComentarioDAO c = new ComentarioMongoDB();
		UsuarioDAO u = new UsuarioMongoDB();
		TokenDAO t = new TokenMongoDB();
		UsuarioOD Usuario = new UsuarioOD();
		Usuario.setId_u(id_u);
		Usuario = u.buscarID(Usuario);
		Usuario.setToken(token);
		System.out.println(Usuario.getNombre() + " Nombre del usuario a buscar para elimiar comentario");
		Comentario = c.buscar(Comentario);
		if(Usuario!=null)
		{
			if(Comentario!=null)
			{							
				GestorToken gestort = new GestorToken();
				TokenOD Token = new TokenOD();
				System.out.println(Usuario.getId_u() + "Id del Usuario por el cual buscare el token");
				Token = t.buscarPorUsuario(Usuario); 
				if (Token == null)
				{
					Logger.error("GestorComentario: El Token que se introdujo no pertenece al dueno del comentario");
					return "El Token no pertenece al usuario";					
				}
				else
				{						
					System.out.println(Token.getToken() + " token buscado por el id de usuario");
					if(Token.getToken() == token)
					{
						if (gestort.validartoken(Token))
						{
							if((Usuario.getId_u()==Comentario.getPadre())||(Usuario.getId_u()==Comentario.getId_u()))
							{
								c.eliminar(Comentario);
								Logger.info("GestorComentario: Comentario eliminado exitosamente: "+Comentario.getTexto());
								return "good";
							}
							else
							{
								System.out.println("el Usuario no tiene los permisos para borrar este mensaje");
								Logger.error("GestorComentario: El usuario: "+Comentario.getId_u()+ " no tiene los permisos para borrar el comentario: "+Comentario.getTexto());
								return "el Usuario no tiene los permisos para borrar este mensaje";
							}
							
						}
						else
						{
							System.out.println("token no valido");
							Logger.error("GestorComentario: El usuario: "+Comentario.getId_u()+ " no tiene un token valido");
							return "token no valido";
						}
						 	
					}
					else
					{
						System.out.println("token distintos");
						Logger.error("GestorComentario: Token Distintos");
						return "token distintos o invalido";
					
					}
				
				}
	
			}
			else 
			{
				System.out.println("no tenemos ese comentario en sistema");
				Logger.error("GestorComentario: El comentario no esta en el sistema");
				return "no tenemos ese comentario en sistema";
				
			}
		}
		else
		{ 
			System.out.println("El usuario del comentario ya no existe");
			Logger.error("GestorComentario: Usuario no valido");
			//return "usuario no valido";
			
			
		}
		return "El usuario del comentario ya no existe";
	}
	
		
	/**
	 * Funcion que devuelve una lista con todos los comentarios existentes	
	 * @return
	 */
	public List<ComentarioOD> listar()
	{		
		ComentarioDAO c = new ComentarioMongoDB();
		List<ComentarioOD> lista = c.listar();
		return lista;
		    
	}
		
	/**
	 * Lista de todos los hijos de un comentario especifico
	 * @param Comentario
	 * @return
	 */
	public List<ComentarioOD> listarEspesifico(ComentarioOD Comentario)
	{		
		ComentarioDAO c = new ComentarioMongoDB();
		List<ComentarioOD> lista = new ArrayList<ComentarioOD>();
		List<ComentarioOD> listaAux = c.listarHijos(Comentario);
		lista.add(c.buscar(Comentario));
		for (ComentarioOD result : listaAux) 
		{
			lista.add(result);
		}	
		return lista;
	}
	
	/**
	 * Lista comentarios de un usuario
	 * @param Comentario
	 * @return
	 */
	public List<ComentarioOD> listarUsuario(ComentarioOD Comentario)
	{
		ComentarioDAO c = new ComentarioMongoDB();
		List<ComentarioOD> lista = new ArrayList<ComentarioOD>();
		List<ComentarioOD> listaAux = c.listarPorUsuario(Comentario);
		lista.add(c.buscar(Comentario));
		for (ComentarioOD result : listaAux) 
		{
			lista.add(result);
		}	
		return lista;
	}


		
	/**
	 * Esta funcion es para hacer me gusta o no me gusta a un comentario	
	 * @param Usuario
	 * @param Comentario
	 * @param like
	 * @return
	 */
	public boolean Hacerlike(UsuarioOD Usuario,ComentarioOD Comentario,int like)
	{
		System.out.println("hola esto es el token que llega en el usuario de gestorComentario "+Usuario.getToken());
		GustaDAO g = new GustaMongoDB();
		ComentarioDAO c = new ComentarioMongoDB();
		Comentario = c.buscar(Comentario);
		if (Comentario== null)
		{
			Logger.error("GestorComentario: El comentario no existe");
			return false;
		}
			
		System.out.println(Comentario.getPrivacidad()+"comentario actualizado esta todo");
		if(Comentario!=null)
		{
			GestorToken gestort = new GestorToken();
			TokenOD Token = new TokenOD();
			Token.setToken(Usuario.getToken()); 
			System.out.println("hola esto es el token de gestorComentario "+Token.getFecha());
			if (gestort.validartoken(Token))
			{
				System.out.println("Comentario get padre en gestorComentario = "+ Comentario.getPadre());
				GustaOD Gusta = new GustaOD(Comentario.getId_c(),like,Usuario.getId_u());
				GustaOD GustaAux = new GustaOD();
				GustaAux = g.buscar(Gusta);
				//(Gusta.getId_u()==GustaAux.getId_u())&&(Gusta.getId_c()==GustaAux.getId_c())
				if(GustaAux==null)
				{
					System.out.println("AAAAAAAAAAAAAAAAAAAA: "+Comentario.getId_c());
					c.ActualizaGustar(Comentario, like);							  
					g.insertar(Gusta);
					Logger.info("GestorComentario: Haciendo peticion de like: " +like+" al comentario: "+Comentario.getTexto());
					return true;
				}
				else 
				{
					System.out.println("el usuario no puede repetir"); 
					Logger.warn("GestorComentario: El usuario: "+Usuario.getNick()+ " ya realizo una peticion de gusto a este comentario: "+Comentario.getTexto());
					return false;
				}
			}
			else
			{
				System.out.println("token no valido");
				Logger.error("GestorComentario: Token invalido");
				return false;
			}
				 
		}
		else
		{
			System.out.println("el comentario NO EXISTE"); 
			Logger.error("GestorComentario: El comentario: "+Comentario.getTexto()+" no existe");
			return false;
		}
	}

	
	/**
	 * Esta funcion es para asignar un tag a un comentario
	 * 
	 * @param Comentario
	 * @param tags
	 * @param token
	 * @return
	 */

	public boolean insertarTag(ComentarioOD Comentario,String tags,String token)
	{
		ComentarioDAO c = new ComentarioMongoDB();
		Comentario = c.buscar(Comentario);
		Tag_ComentarioDAO tc = new Tag_ComentarioMongoDB();
		if(Comentario!=null)
		{
			if(Comentario.getPadre()==0)
			{
				TagOD nodo = new TagOD();
				nodo.setNombre(tags);
				
				TagDAO t = new TagMongoDB();
				//nodo = t.buscar(nodo);
				GestorToken gestort = new GestorToken();
				TokenOD Token = new TokenOD();
				Token.setToken(Integer.parseInt(token));
				
				if (gestort.validartoken(Token))
				{
					if(t.buscar(nodo)==null)
					{
						
						System.out.println(nodo.getNombre()+"nombre del tag antes de insertar");
						t.insertar(nodo);
						nodo = t.buscar(nodo);
						Tag_ComentarioOD Tag_c = new Tag_ComentarioOD(Comentario.getId_c(), nodo.getId_t());
						tc.insertar(Tag_c);
					}
					else
					{
						nodo = t.buscar(nodo);
						Tag_ComentarioOD Tag_c = new Tag_ComentarioOD(Comentario.getId_c(), nodo.getId_t());
						tc.insertar(Tag_c);
					}
				}
				else
				{
					System.out.println("Token vencido o no valido"); 
					return false;
				}
			}
			else
			{
				System.out.println("solo se pueden etiquetar comentarios padres"); 
				return false;
			}
		}
		else 
		{
			System.out.println("no esxite el comentario que quieres");
			return false;
		}
		return true;
	}

	
	
	/**
	 * 
	 * Funcion que devuelve una lista con todos los comentario correspondientes a un tag
	 * @param nombre
	 * @return
	 */
	public List<ComentarioOD> listartags(String nombre)
	{	
		Tag_ComentarioDAO c = new Tag_ComentarioMongoDB();
		
		System.out.println("Listar tags en gestor comentario" + nombre);
		TagOD buscar = new TagOD();
		buscar.setNombre(nombre.toLowerCase());
		TagDAO tags = new TagMongoDB();
		buscar = tags.buscar(buscar);
		
		if (buscar == null)
			return null;
		else
		{
			List<ComentarioOD> lista = c.listarPortag(buscar.getId_t());
			return lista;
		}
		
    
	}

	/**
	 * Funcion que recibe un comentario y un token y llama a la funcion insertar, de esta misma clase que se encarga de validar el token y e insertar el comentario
	 * @param comentario
	 * @param tokens
	 * @return
	 */
	
	public ComentarioOD insertarComentarioSintags(ComentarioOD comentario, int tokens)
	{
		System.out.println("a insertar sin tags");
		boolean modifico = insertar(comentario, tokens);
		if(modifico == true)
	 	{
	 		ComentarioDAO  id_c= new ComentarioMongoDB();
	 		int id_comentario = id_c.Buscarid();
	 		comentario.setId_c(id_comentario);			    
			return comentario;
		}
	  	else 
	  	{
	  		comentario = null;
	 		//XStream xstream = new XStream(new DomDriver());
		    //String xml = xstream.toXML("No insertar tu comentario ");
	  		//comentario.setTexto("<mensaje> error insertando comentario: Token invalido</mensaje>");
	  		return comentario;
	  	}	
		
	}
	
	
	/**
	 * Funcion encargada de insertar comentarios que no posean tags, llama a insertar, que valida el token y si todo es correcto, va contra persistencia
	 * 
	 * @param comentario
	 * @param tokens
	 * @return
	 */
	public boolean insertarComentarioConTags(ComentarioOD comentario,int tokens)
	{
		System.out.println("1VOYY");
		//ahora busco de cada tag su id
		boolean modifico = insertar(comentario, tokens);
		if (modifico == true)
			return true;
		else
			return false;
	}
	
	
	/**
	 * Funcion muy importante para la insercion de comentarios que posean n tags, se encarga de ir contra persistencia, es decir, si los tags son nuevos los almacena
	 * y registra en tag_comentario la relacion con el comentario, y si el tag es viejo, simplemente, insertar en la n a n 
	 * @param modifico
	 * @param tag
	 * @param etiquetas
	 * @param comentario
	 * @param tokens
	 * @return
	 */
	public boolean manejarEtiquetas(boolean modifico,String tag,List<String> etiquetas,ComentarioOD comentario,int tokens)
	{
		boolean flag = false;
		Tag_ComentarioOD alfa = null;
		Tag_ComentarioDAO yema = null;
		
							
			System.out.println("La etiqueta es:->"+tag+"<-");
			etiquetas.add(tag);
		
 			System.out.println("2VOYY");
  			//aki tengo el tag  			
  			TagDAO beta2 = new TagMongoDB();
  			TagOD Tag = new TagOD(tag.toLowerCase());
  			TagOD respuesta = null;
  			respuesta = beta2.buscar(Tag);
  			System.out.println("VOYY");
  			if (respuesta == null)		//si no lo encontro entonces lo inserta (el tag) extraigo su id y luego inserto el comentario y luego la n_n
  			{
  				TagDAO beta3 = new TagMongoDB();
	  			TagOD Tag3 = new TagOD(tag.toLowerCase());
	  			beta3.insertar(Tag3);
	  			TagOD respuesta3 = null;
	  			respuesta3 = beta2.buscar(Tag3);
	  			System.out.println("Inserto el nuevo tag y su id es: "+respuesta3.getId_t()); 	
	  			
		  		if(modifico == true)
			  	{
			  		ComentarioDAO  id_c= new ComentarioMongoDB();
				  	int id_comentario = id_c.Buscarid();
				  	comentario.setId_c(id_comentario);
				  	//xstream = new XStream(new DomDriver());
				    //xml = xstream.toXML(comentario);		 
				    System.out.println("El id del comentario es: "+id_comentario); 	
				    Logger.info("GestorComentario: El comentario ha sido insertado"+comentario.getTexto());
				    alfa = new Tag_ComentarioOD(id_comentario, respuesta3.getId_t());
				    yema = new Tag_ComentarioMongoDB();
				    
					yema.insertar(alfa);
					
					Logger.info("GestorComentario: Asignando el tag: "+respuesta3.getNombre()+"al comentario "+comentario.getId_c());
					flag = true;
					return flag;
				}
				else 
				{
					//xstream = new XStream(new DomDriver());
				    //xml = xstream.toXML("No insertar tu comentario ");
				    Logger.error("GestorComentario: Token vencido o invalido "+tokens);
					flag = false;
					return flag;
				} 	
	  				
	  				
  			}
	  		else 	//si si lo encontro entonces inserta el comentario, extraigo su id y luego en la n_n tag_comentario 
	  		{
	  			System.out.println(respuesta.getId_t()); //id del tag
	  			System.out.println("Si lo encontro");
	  			//contadorTags2++;
	  			//boolean modifico = beta.insertar(comentario, tokens);		//inserto el comentario
				if(modifico == true)
			  	{
			  		ComentarioDAO  id_c= new ComentarioMongoDB();
				  	int id_comentario = id_c.Buscarid();
				  	comentario.setId_c(id_comentario);
				  	//xstream = new XStream(new DomDriver());
				    //xml = xstream.toXML(comentario);		 
				    System.out.println("El id del comentario es: "+id_comentario); 	
				    alfa = new Tag_ComentarioOD(id_comentario, respuesta.getId_t());
					yema = new Tag_ComentarioMongoDB();
					yema.insertar(alfa);			
					Logger.info("GestorComentario: Asignando el tag: "+respuesta.getNombre()+"al comentario "+comentario.getId_c());
				    flag = true;
				    return flag;
					//return ok(xml);
				}
				else 
				{
					//xstream = new XStream(new DomDriver());
					//xml = xstream.toXML("No insertar tu comentario ");
					Logger.error("GestorComentario: Token invalido");
					flag = false;
					return flag;
				  	//return ok("<mensaje> error insertando comentario: Token invalido</mensaje>");
				}				  		
	  		}	
	  	
		
  		
	}
	
	
	/**
	 * Funcion creada para validar que un token pertenezca a un usuario
	 * @param idUsuario
	 * @param tokens
	 * @return
	 */
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
	
	
	
	
	public List<TagOD> listarTags()
	{		
		TagDAO c = new TagMongoDB();
		List<TagOD> lista = c.listar();
		return lista;
		    
	}
	
	
	
	
	public boolean insertarAdjuntoProfesional(AdjuntoOD foto,int token)
	{
		GestorToken gestort = new GestorToken();
		TokenOD Token = new TokenOD();
		Token.setToken(token);
		if (gestort.validartoken(Token))
		{
			boolean flag = false;
			AdjuntoDAO beta = new AdjuntoMongoDB();
			beta.insertar(foto);
			flag = true;
			Logger.info("Archivo adjuntado exitosamente");
			return flag;
		}
		else 
		{
			Logger.error("GestorUsuario: No se puede modificar el usuario, token vencido o no valido");
			return false;
		}		
	}
	
	
	public String obtenerRutaAdjuntoComentario(ComentarioOD comentario)
	{
		AdjuntoOD buscado = new AdjuntoOD();
		buscado.setId_c(comentario.getId_c());
		AdjuntoDAO bethoven = new AdjuntoMongoDB();
		buscado = bethoven.buscar(buscado);
		if (buscado == null)
		{
			Logger.warn("GestorComentario: EL comentario indicado no posee adjunto");
			return "error: ese comentario no tiene adjunto";
		}
		else
		{
			Logger.info("GestorComentario: ruta del adjunto buscada exitosamente: "+buscado.getRuta());
			return buscado.getRuta();
		}
	}
}