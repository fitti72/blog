package controllers;

import java.util.Date;
import java.util.List;

import models.DAO.ComentarioDAO;
import models.DAO.MongoDB.ComentarioMongoDB;
import models.Negocio.GestorComentario;
import models.Negocio.GestorUsuario;

import models.OD.ComentarioOD;
import models.OD.UsuarioOD;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import play.libs.XPath;
import play.mvc.Controller;
import play.mvc.Result;


public class Comentario  extends Controller {
	
	/**
	 * se encarga de recibir el xml que viene del servico web y generar un objeto del tipo comentario para luego llamar a la capa 
	 * de negocio para insertar el comentatio en persistencia
	 * @param token
	 * @return
	 */
	
	private static boolean isNumeric(String cadena)
	{
		try 
		{
			Integer.parseInt(cadena);
			return true;
		}	catch (NumberFormatException nfe)
		{
			return false;
		}
	}
	
	public static Result insertarComentario(String token) {
		  System.out.println("Insertando Comentario");
		   
		  	int tokens = (Integer) Integer.parseInt(token);
		    System.out.println(tokens);
		  	String texto = XPath.selectText("//texto", request().body().asXml());
		  	String privacidad = XPath.selectText("//privacidad", request().body().asXml());
		  	String id_u = XPath.selectText("//id_u", request().body().asXml());
		  	String padre = XPath.selectText("//padre", request().body().asXml());
		  	
		  	if(texto == null)
		  	{
		    	return ok("<mensaje> No puedes colocar comentario vacio </mensaje>");
		  	}
		  	else if(privacidad == null)
		  	{
		    	return ok("<mensaje> Falta la privacidad del comentario </mensaje>");
		  	}
		  	else if(id_u == null)
		  	{
		    	return ok("<mensaje> Falta el id del usuario que crea el comentario </mensaje>");
		  	}
		  	else if(padre == null)
		  	{
		    	return ok("<mensaje> Falta el id del comentario padre de este comentario </mensaje>");
		  	}
		  	
		  	//validaciones de tipo de dato
		  	else if(isNumeric(id_u) == false)
		  	{
		    	return ok("<mensaje> El id del usuario debe ser un numero </mensaje>");
		  	}
		  	
			else if(isNumeric(padre) == false)
		  	{
		    	return ok("<mensaje> El id del padre debe ser un numero </mensaje>");
		  	}
		  	else if(isNumeric(privacidad) == false)
		  	{
		    	return ok("<mensaje> la privacidad debe ser un numero </mensaje>");
		  	}
		  	else if(privacidad.length()>1)
		  	{
		  		return ok("<mensaje> la privacidad debe ser un numero de un solo digito </mensaje>");
		  	}
		  	else if(isNumeric(privacidad)==true)
		  	{
		  		if((Integer.parseInt(privacidad) != 0) && (Integer.parseInt(privacidad) != 1))
		  		{
		  			return ok("<mensaje> la privacidad solo puede ser 0 o 1 </mensaje>");
		  		}
		  	}
		  	
		  	Date fecha = new Date();
		  	ComentarioOD comentario = new ComentarioOD(texto, fecha.toString() , Integer.parseInt(id_u), Integer.parseInt(padre), Integer.parseInt(privacidad),0 ,0);
		  	GestorComentario beta = new GestorComentario();
		  	boolean modifico = beta.insertar(comentario, tokens);
		  	
		  
		  	if(modifico == true)
		  	{
		  		ComentarioDAO  id_c= new ComentarioMongoDB();
			  	int id_comentario = id_c.Buscarid();
			  	comentario.setId_c(id_comentario);
			  	XStream xstream = new XStream(new DomDriver());
			    String xml = xstream.toXML(comentario);
			    return ok(xml);
			}
		  	else 
		  	{
		  		XStream xstream = new XStream(new DomDriver());
			    String xml = xstream.toXML("No insertar tu comentario ");
		  		return ok(xml);
		  	}
	}
	/**
	 * se encarga de recibir el xml del servicio web para agregarle el tag al comentario ya creado pasandole el token, 
	 * el id del comentario y el nombre de la etiqueta a agregar  
	 * @param token
	 * @return
	 */
	public static Result insertarComentariotag(String token)
	{  
		
		
		System.out.println("Insertando Comentario ");
		String id_c = XPath.selectText("//id_c", request().body().asXml());
		String etiqueta = XPath.selectText("//etiqueta" ,request().body().asXml());
		if(id_c == null)
	  	{
	    	return ok("<mensaje> Id del comentario vacio no esta permitido </mensaje>");
	  	}
	  	else if(token == null)
	  	{
	    	return ok("<mensaje> Sin token no es posible realizar la transaccion </mensaje>");
	  	}
	  	else if(etiqueta == null)
	  	{
	    	return ok("<mensaje> Indique la etiqueta que desea agregar al comentario </mensaje>");
	  	}
		
	  	else if(isNumeric(id_c) == false)
	  	{
	    	return ok("<mensaje> El id del comentario debe ser un numero </mensaje>");
	  	}
	  	else if(isNumeric(token) == false)
	  	{
	    	return ok("<mensaje> El id del usuario debe ser un numero </mensaje>");
	  	}
	  	GestorComentario nuevo = new GestorComentario();
	  	ComentarioOD tagear = new ComentarioOD	();
		tagear.setId_c(Integer.parseInt(id_c));
		boolean tag  =nuevo.insertarTag(tagear, etiqueta , token);
		if(tag ==true)
		{
			XStream xstream = new XStream(new DomDriver());
		    String xml = xstream.toXML(id_c);
			return ok(xml);
		}
		else 
			return ok("<mensaje>" + id_c +" " + etiqueta + "</mensaje>");
	}
	
	
	/**
	 * se encarga de enviar la sulicitud de listar todos los comentarios que se encuentren en persistencia
	 * @return
	 */
	
	public static Result listart() 
	{
		GestorComentario beta = new GestorComentario();
		List<ComentarioOD> list = beta.listar();
	      
		XStream xstream = new XStream(new DomDriver());
	    String xml = xstream.toXML(list);
  		return ok(xml);
	}
	/**
	 * se encarga de listar todos los comentarios hijos con respecto a un comentario en espeficicio para su busqueda se hace
	 * por un id de coemntario generado por nosotros
	 * @param id_c
	 * @return
	 */
	
	public static Result listarcomentarios(String id_c) 
	{
		GestorComentario beta = new GestorComentario();
		ComentarioOD comentario = new ComentarioOD(Integer.parseInt(id_c),null,null,0,0,0);
		List<ComentarioOD> list = beta.listarEspesifico(comentario);
	      
		XStream xstream = new XStream(new DomDriver());
	    String xml = xstream.toXML(list);
  		return ok(xml);
	}
	
	/**
	 *Se encaga de listar todos los comentarios de de un usuario en espeficico pasando el nick name del usuario	 * 
	 * @param id_u
	 * @return
	 */
	//cambiar a nickname del usuario
	
	
	public static Result listarcomentariosUsuario(String nick) 
	{
		GestorComentario beta = new GestorComentario();
		UsuarioOD comentariousuario = new UsuarioOD();
		comentariousuario.setNick(nick);
		GestorUsuario buscar = new GestorUsuario();
		comentariousuario = buscar.usuarioespecifico(comentariousuario);
		ComentarioOD comentario = new ComentarioOD(null,null,comentariousuario.getId_u(),0,0,0,0);
		List<ComentarioOD> list = beta.listarUsuario(comentario);
	      
		XStream xstream = new XStream(new DomDriver());
	    String xml = xstream.toXML(list);
  		return ok(xml);
	}
	
	
	/**
	 * se encagar de recibir el xml y de darle me gusta o no me gusta a un coemntario en especifico ya creado dandole solo un me gusta o no me gusta
	 * en el xml se pasa el id del comentario, el id del usuario y el like (si es 1 me gusta , 0 no me gusta)
	 * @param token
	 * @return
	 */
	public static Result darLike(String token)
	{
		System.out.println("Dando like o no like a Comentario" + token);
		int tokens = (Integer) Integer.parseInt(token);
		System.out.println(tokens);
			  	
		String id_c = XPath.selectText("//id_c", request().body().asXml());
		String id_u = XPath.selectText("//id_u", request().body().asXml());
		String like = XPath.selectText("//like", request().body().asXml());
		if(id_c == null)
		{
			return ok("<mensaje> Id del comentario vacio no esta permitido </mensaje>");
		}
		else if(id_u == null)
		{
		    	return ok("<mensaje> Indique el id del usuario </mensaje>");
		}
		else if(like == null)
		{
		  	return ok("<mensaje> Indique like=1 si le gusta, 0 si no le gusta </mensaje>");
		}
		//validaciones de tipo de dato
		else if(isNumeric(id_c) == false)
		{
		  	return ok("<mensaje> El id del comentario debe ser un numero </mensaje>");
		}
		else if(isNumeric(id_u) == false)
		{
		  	return ok("<mensaje> El id del usuario debe ser un numero </mensaje>");
		}
		else if(isNumeric(like) == false)
		{
		  	return ok("<mensaje> El like debe ser un numero </mensaje>");
		}
		else if(like.length()>1)
		{
			return ok("<mensaje> El like debe ser un numero de un solo digito </mensaje>");
		}
		else if(isNumeric(like)==true)
		{
			if((Integer.parseInt(like) != 0) && (Integer.parseInt(like) != 1))
			{
				return ok("<mensaje> El like solo puede ser 0 o 1 </mensaje>");
			}
		}
		ComentarioOD comentario = new ComentarioOD(Integer.parseInt(id_c), null, null, Integer.parseInt( id_u),0,0);
		UsuarioOD usuario = new UsuarioOD();
		usuario.setId_u(Integer.parseInt(id_u));
		usuario.setToken(Integer.parseInt(token));
		GestorComentario beta = new GestorComentario();
		int ellike = Integer.parseInt(like);
		boolean lik  = false;
		lik = beta.Hacerlike(usuario, comentario,ellike);
		if(lik == true )
		{
			XStream xstream = new XStream(new DomDriver());
		    //String xml = xstream.toXML("<mensaje>Registrado tu punteado al comentario</mensaje>");
			return ok("<mensaje>Registrado tu punteado al comentario</mensaje>");
		}
		else 
			return ok("<mensaje>no se puedo puntear el comentario</mensaje>");
	}
	/**
	 * se encarga de eliminar el comentario seleccionado, lo elimina si eres el dueno del comentario o si eres el padre de ese comentario
	 * @param token
	 * @param id_c
	 * @param id_u
	 * @return
	 */
	 public static Result eliminarComentarios(String token, String id_c, String id_u)
	 { 
		 if(id_c == null)
		 {
		  	return ok("<mensaje> Id del comentario vacio no esta permitido </mensaje>");
		 }
		 else if(id_u == null)
		 {
		  	return ok("<mensaje> Indique el id del usuario </mensaje>");
		 }
		 else if(isNumeric(id_c) == false)
		 {
		  	return ok("<mensaje> El id del comentario debe ser un numero </mensaje>");
		 }
		 else if(isNumeric(id_u) == false)
		 {
		  	return ok("<mensaje> El id del usuario debe ser un numero </mensaje>");
		 }
		 else if(isNumeric(token) == false)
		 {
		  	return ok("<mensaje> El token debe ser un numero </mensaje>");
		 }
		 GestorComentario beta = new GestorComentario();
		 ComentarioOD elcomentario = new ComentarioOD();
		 elcomentario.setId_c(Integer.parseInt(id_c));
		 
		 int eltoken = Integer.parseInt(token);
		 int elusuario = Integer.parseInt(id_u); 
		 beta.eliminar(elcomentario, eltoken, elusuario);
		 return ok("<mensaje>elimine comentarioo</mensaje>");
	 }
	 	
	 
	 
	 /**
	  * se encagar a de listar todos los comentarios que esten relacionados con un tag en especifico 
	  * @param tags
	  * @return
	  */
 
	 public static Result listartags(String tags) 
	 {
	 
		GestorComentario beta = new GestorComentario();
		List<ComentarioOD> list = beta.listartags(tags);
	      
		XStream xstream = new XStream(new DomDriver());
	    String xml = xstream.toXML(list);
		return ok(xml);
	}
 
 
 
}

