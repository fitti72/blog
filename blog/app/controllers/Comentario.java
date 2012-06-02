package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.DAO.ComentarioDAO;
import models.DAO.TagDAO;
import models.DAO.Tag_ComentarioDAO;
import models.DAO.TokenDAO;
import models.DAO.UsuarioDAO;
import models.DAO.MongoDB.ComentarioMongoDB;
import models.DAO.MongoDB.TagMongoDB;
import models.DAO.MongoDB.Tag_ComentarioMongoDB;
import models.DAO.MongoDB.TokenMongoDB;
import models.DAO.MongoDB.UsuarioMongoDB;
import models.Negocio.GestorComentario;
import models.Negocio.GestorUsuario;

import models.OD.AdjuntoOD;
import models.OD.ComentarioOD;
import models.OD.FotoOD;
import models.OD.TagOD;
import models.OD.Tag_ComentarioOD;
import models.OD.TokenOD;
import models.OD.UsuarioOD;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import play.Logger;
import play.libs.XPath;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Comentario extends Controller {

	/**
	 * Esta funcion que devuelve un boolean: devuelve true si el string pasado
	 * como parametro es un numero y devuelve un false si esta cadena contiene
	 * caracteres no numericos
	 * 
	 * @param cadena
	 * @return
	 */

	private static boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	/**
	 * se encarga de recibir el xml que viene del servico web y generar un
	 * objeto del tipo comentario para luego llamar a la capa de negocio para
	 * insertar el comentatio en persistencia. Primero valida que todas las
	 * etiquetas del xml esten no vacias, luego valida que si el dato esperado
	 * un string, que el dato pasado no contenga caracteres numericos. Tambien
	 * se insertan las etiquetas: si las etiquetas que pasan por el xml ya estan
	 * registradas, entonces se procede a obtener su id e insertar en
	 * tag_comentario, con el id del comentario que se pretende insertar. luego,
	 * si el tag no es existente, se crea, y se insertar en tag_comentario con
	 * el id del comentario correspondiente.
	 * 
	 * @param token
	 * @return
	 */

	public static Result insertarComentario(String token) // si todos los datos
															// que llegan por el
															// xml son
															// verdaderos,
															// entonces si hay
															// un error, es por
															// token falso
	{
		System.out.println("Insertando ComentarioOOOOOOOOO");

		// int tokens = (Integer) Integer.parseInt(token);
		// System.out.println("el token es: "+tokens);
		String texto = XPath.selectText("//texto", request().body().asXml());
		String privacidad = XPath.selectText("//privacidad", request().body()
				.asXml());
		String id_u = XPath.selectText("//id_u", request().body().asXml());
		String padre = XPath.selectText("//padre", request().body().asXml());

		// llego
		System.out.println("la data que llega es: " + texto + "," + privacidad
				+ "," + id_u + "," + padre);
		if (texto == null) {
			return ok("<mensaje>Error: No puedes colocar comentario vacio </mensaje>");
		} else if (privacidad == null) {
			return ok("<mensaje>Error: Falta la privacidad del comentario </mensaje>");
		} else if (id_u == null) {
			return ok("<mensaje>Error: Falta el id del usuario que crea el comentario </mensaje>");
		} else if (padre == null) {
			return ok("<mensaje>Error: Falta el id del comentario padre de este comentario </mensaje>");
		}

		// validaciones de tipo de dato
		else if (isNumeric(token) == false) {
			return ok("<mensaje>Error: El token del usuario excede el numero de digitos permitidos </mensaje>");
		} else if (isNumeric(id_u) == false) {
			return ok("<mensaje>Error: El id del usuario debe ser un numero </mensaje>");
		} else if (isNumeric(padre) == false) {
			return ok("<mensaje>Error: El id del padre debe ser un numero </mensaje>");
		} else if (isNumeric(privacidad) == false) {
			return ok("<mensaje>Error: la privacidad debe ser un numero </mensaje>");
		} else if (privacidad.length() > 1) {
			return ok("<mensaje>Error: la privacidad debe ser un numero de un solo digito </mensaje>");
		} else if (isNumeric(privacidad) == true) {
			if ((Integer.parseInt(privacidad) != 0)&& (Integer.parseInt(privacidad) != 1)) {
				return ok("<mensaje>Error: la privacidad solo puede ser 0 o 1 </mensaje>");
			}
		}
		int tokens = (Integer) Integer.parseInt(token);
		System.out.println("el token del usuario es: " + tokens);

		ComentarioOD comentario = new ComentarioOD(texto, null,
				Integer.parseInt(id_u), Integer.parseInt(padre),
				Integer.parseInt(privacidad), 0, 0);
		GestorComentario beta = new GestorComentario();

		// aki debo comparar si el id_u que esta en el comentario es igual al
		// id_u asociado al token

		int idUsuario = Integer.parseInt(id_u);
		GestorComentario tita = new GestorComentario();
		boolean propiedad = tita.esSuToken(idUsuario, tokens);
		if (propiedad == false) {
			Logger.error("ControladorComentario: ese token no es de quien dice ser: "
					+ tokens);
			return ok("<mensaje>Error: Ese token no pertenece a ese usuario bro </mensaje>");
		} else {
			Logger.info("ControladorComentario: El token es: " + tokens);
			System.out.println("ese token si es tuyo brother");
		}

		System.out.println("1paso");
		// aki esta el error
		int contadorTags = 0;
		for (int i = 0; i <= 200; i++) {
			String tag = XPath
					.selectText("//tag" + i, request().body().asXml());
			if (tag != null) {
				contadorTags++;

			}
		}
		System.out.println("el comentario tiene tags: " + contadorTags);
		System.out.println("1VOYY");

		if (contadorTags == 0) {
			GestorComentario preparador = new GestorComentario();
			ComentarioOD fase1 = preparador.insertarComentarioSintags(
					comentario, tokens);
			if (fase1 != null) {
				Logger.info("ControladorComentario: El comentario ha sido insertado"
						+ comentario.getTexto());
				XStream xstream = new XStream(new DomDriver());
				String xml = xstream.toXML(comentario);
				return ok(xml);
			} else {
				Logger.error("ControladorComentario: Token no valido: "
						+ tokens);
				return ok("<mensaje>Error: error insertando comentario, Token invalido o comentario padre privado</mensaje>");

			}

		}
		boolean flag = false;
		String xml = null;
		String xml2 = null;
		Tag_ComentarioOD alfa = null;
		Tag_ComentarioDAO yema = null;
		String tag = null;
		XStream xstream = null;
		List<String> etiquetas = new ArrayList<String>();
		if (Integer.parseInt(padre) == 0) {
			if (contadorTags != 0) // cuando si tiene tags
			{
				GestorComentario alfino = new GestorComentario();
				boolean flagOficial = alfino.insertarComentarioConTags(
						comentario, tokens);
				int contadorTags2 = 0;
				for (int i2 = 0; i2 <= 200; i2++) {
					tag = XPath.selectText("//tag" + i2, request().body()
							.asXml());
					GestorComentario adminEtiquetas = new GestorComentario();
					if (tag != null)
						flag = adminEtiquetas.manejarEtiquetas(flagOficial,
								tag, etiquetas, comentario, tokens);

					contadorTags2++;
				}
			}
		} else {
			return ok("<mensaje>Error:: el comentario es hijo y no puede llevar tags asociados</mensaje>");
		}

		xstream = new XStream(new DomDriver());
		System.out.println("el comentario viene con: " + comentario.getTexto());

		if (flag == true) {
			xml = xstream.toXML(comentario);
			System.out.println("HASTA AKI VOY BIEN");
			return ok(xml);// si devuelvo xml3 lo pone todo anaranjado y con una
							// nueva etiqueta
		} else
			return ok("<mensaje>Error: error insertando comentario: Token invalido</mensaje>");

	}

	// Este par de funciones comentadas, fueron sustituidas por la funcion
	// insertarComentario, que engloba las funcionalidades de estas dos
	// funciones

	/**
	 * Cuando tengo un comentario creado y deseo agregarle o asignarle un nuevo
	 * tag
	 * @param token
	 * @param id_c
	 * @param etiqueta
	 * @return
	 */
	
	  
	  public static boolean insertarTagAcomentario(String token,int id_c,String etiqueta) 
	  { 
		  System.out.println("pegando etiqueta a comentario");
	  	  GestorComentario nuevo = new GestorComentario(); 
	  	  ComentarioOD tagear = new ComentarioOD (); 
	  	  tagear.setId_c(id_c); 
	  	  boolean tag =nuevo.insertarTag(tagear, etiqueta , token); 
	  	  if(tag ==true) 
	  	  { 
	  		  XStream xstream = new XStream(new DomDriver()); 
	  		  String xml = xstream.toXML(etiqueta); 
	  		  //return ok(xml); 
	  		  return true; 
	  	  }
	  	  else 
	  		  return false; 
	  }
	 

	/**
	 * se encarga de recibir el xml del servicio web para agregarle el tag al
	 * comentario ya creado pasandole el token, el id del comentario y el nombre
	 * de la etiqueta a agregar
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
			 return ok("<mensaje>Error: Indique la etiqueta que desea agregar al comentario </mensaje>");
		 }
	     else if(isNumeric(id_c) == false) 
	     {
	    	 return ok("<mensaje> El id del comentario debe ser un numero </mensaje>"); 
	     }
	     else if(isNumeric(token) == false) 
	     { 
	    	 return ok("<mensaje>Error: El id del usuario debe ser un numero </mensaje>"); 
	     }
		 GestorComentario nuevo = new GestorComentario(); 
		 ComentarioOD tagear = new ComentarioOD (); 
		 tagear.setId_c(Integer.parseInt(id_c));
		 boolean tag=nuevo.insertarTag(tagear, etiqueta , token); 
		 if(tag == true) 
		 { 
			 XStream xstream = new XStream(new DomDriver()); 
			 String xml = xstream.toXML(etiqueta); 
			 //return ok(xml); 
			 return ok("<mensaje> La etiqueta "+etiqueta+"ha sido agregada al comentario "+id_c+" </mensaje>"); 
		 } 
		 else
			 return ok("<mensaje>Error: error insertando tag </mensaje>"); 
		 }
	 

	/**
	 * se encarga de enviar la sulicitud de listar todos los comentarios que se
	 * encuentren en persistencia
	 * 
	 * @return
	 */

	public static Result listart() {
		GestorComentario beta = new GestorComentario();
		List<ComentarioOD> list = beta.listar();
		if(list !=null)
		{
		XStream xstream = new XStream(new DomDriver());
		String xml = xstream.toXML(list);
		return ok(xml);
		}
		else
			return ok("<mensaje>Error: Lista vacia</mensaje>");
	}

	/**
	 * se encarga de listar todos los comentarios hijos con respecto a un
	 * comentario en espeficicio para su busqueda se hace por un id de
	 * coemntario generado por nosotros
	 * 
	 * @param id_c
	 * @return
	 */

	public static Result listarcomentarios(String id_c) {
		GestorComentario beta = new GestorComentario();
		ComentarioOD comentario = new ComentarioOD(Integer.parseInt(id_c),
				null, null, 0, 0, 0);
		List<ComentarioOD> list = beta.listarEspesifico(comentario);
		if(list !=null)
		{
		XStream xstream = new XStream(new DomDriver());
		String xml = xstream.toXML(list);
		return ok(xml);
		}
		else
			return ok("<mensaje>Error: Lista vacia</mensaje>");	}

	/**
	 * Se encaga de listar todos los comentarios de de un usuario en espeficico
	 * pasando el nick name del usuario *
	 * 
	 * @param id_u
	 * @return
	 */

	public static Result listarcomentariosUsuario(String nick) {
		if (nick == null) {
			return ok("<mensaje> Ingrese un nick </mensaje>");
		}

		GestorComentario beta = new GestorComentario();
		UsuarioOD comentariousuario = new UsuarioOD();
		comentariousuario.setNick(nick);
		GestorUsuario buscar = new GestorUsuario();
		comentariousuario = buscar.usuarioespecifico(comentariousuario);
		if (comentariousuario == null)
			return ok("<mensaje>Error: Nick no encontrado: Nick no registrado </mensaje>");
		else {
			ComentarioOD comentario = new ComentarioOD(null, null,
					comentariousuario.getId_u(), 0, 0, 0, 0);
			List<ComentarioOD> list = beta.listarUsuario(comentario);
			if(list !=null)
			{
			XStream xstream = new XStream(new DomDriver());
			String xml = xstream.toXML(list);
			return ok(xml);
			}
			else
				return ok("<mensaje>Error: Lista vacia</mensaje>");	
		}

		
	}

	/**
	 * se encagar de recibir el xml y de darle me gusta o no me gusta a un
	 * comentario en especifico ya creado dandole solo un me gusta o no me gusta
	 * en el xml se pasa el id del comentario, el id del usuario y el like (si
	 * es 1 me gusta , 0 no me gusta)
	 * 
	 * @param token
	 * @return
	 */
	public static Result darLike(String token) {
		System.out.println("Dando like o no like a Comentario con el token: "
				+ token);
		int tokens = (Integer) Integer.parseInt(token);
		System.out.println(tokens);

		String id_c = XPath.selectText("//id_c", request().body().asXml());
		String id_u = XPath.selectText("//id_u", request().body().asXml());
		String like = XPath.selectText("//like", request().body().asXml());
		if (id_c == null) {
			return ok("<mensaje>Error: Id del comentario vacio no esta permitido </mensaje>");
		} else if (id_u == null) {
			return ok("<mensaje>Error: Indique el id del usuario </mensaje>");
		} else if (like == null) {
			return ok("<mensaje>Error: Indique like=1 si le gusta, 0 si no le gusta </mensaje>");
		}
		// validaciones de tipo de dato
		else if (isNumeric(id_c) == false) {
			return ok("<mensaje>Error: El id del comentario debe ser un numero </mensaje>");
		} else if (isNumeric(id_u) == false) {
			return ok("<mensaje>Error: El id del usuario debe ser un numero </mensaje>");
		} else if (isNumeric(like) == false) {
			return ok("<mensaje>Error: El like debe ser un numero </mensaje>");
		} else if (like.length() > 1) {
			return ok("<mensaje>Error: El like debe ser un numero de un solo digito </mensaje>");
		} else if (isNumeric(like) == true) {
			if ((Integer.parseInt(like) != 0) && (Integer.parseInt(like) != 1)) {
				return ok("<mensaje>Error: El like solo puede ser 0 o 1 </mensaje>");
			}
		}

		ComentarioOD comentario = new ComentarioOD(Integer.parseInt(id_c),
				null, null, Integer.parseInt(id_u), 0, 0);
		UsuarioOD usuario = new UsuarioOD();
		usuario.setId_u(Integer.parseInt(id_u));
		usuario.setToken(Integer.parseInt(token));
		GestorComentario beta = new GestorComentario();
		int ellike = Integer.parseInt(like);
		boolean lik = false;

		int idUsuario = comentario.getId_u();
		UsuarioOD jou = new UsuarioOD(idUsuario, null, null, null, null, null,
				null, null, null, null, null, null);
		TokenDAO bethoven = new TokenMongoDB();
		TokenOD quetal = bethoven.buscarPorUsuario(jou);
		if (quetal == null) {
			System.out.println("brother preocupate q stas en problemas");
			return ok("<mensaje>Error:Ese token no pertenece a ese usuario </mensaje>");
		} else
			System.out.println("el token de este pana es: " + quetal.getToken()
					+ " lo comparare con: " + tokens);

		lik = beta.Hacerlike(usuario, comentario, ellike);
		if (lik == true) {
			XStream xstream = new XStream(new DomDriver());
			// String xml =
			// xstream.toXML("<mensaje>Registrado tu punteado al comentario</mensaje>");
			Logger.info("ControladorComentario: Haciendo peticion de Gusta o No me gusta al comentario: "
					+ comentario.getId_c());
			return ok("<mensaje> Registrado tu punteado al comentario</mensaje>");
		} else {
			Logger.error("ControladorComentario: No se puede hacer like al comentario "
					+ comentario.getId_c());
			return ok("<mensaje>Error: no se puedo puntear el comentario</mensaje>");
		}
	}

	
	
	/**
	 * XML
	 * se encarga de eliminar el comentario seleccionado, lo elimina si eres el
	 * dueno del comentario o si eres el padre de ese comentario
	 * Recibe datos en formato XML ecepto el token
	 * @param token
	 * @return
	 */
	public static Result eliminarComentariosXML(String token) {
		System.out.println("1 ENTRO token= " + token);
		
		String id_c = XPath.selectText("//id_c", request().body().asXml());
		String nick = XPath.selectText("//nick", request().body().asXml());
		
		
		
		if (id_c == null) {
			return ok("<mensaje>Error: Id del comentario vacio no esta permitido </mensaje>");
		} else if (nick == null) {
			return ok("<mensaje>Error: Indique el id del usuario </mensaje>");
		} else if (isNumeric(id_c) == false) {
			return ok("<mensaje>Error: El id del comentario debe ser un numero </mensaje>");
		}

		else if (isNumeric(token) == false) {
			return ok("<mensaje>Error: El token debe ser un numero </mensaje>");
		}

		System.out.println("2 ENTRO YA VALIDE TODOS LAS ENTRADAS");
		GestorComentario beta = new GestorComentario();
		ComentarioOD elcomentario = new ComentarioOD();

		UsuarioOD beta2 = new UsuarioOD(nick, null);
		UsuarioDAO beta3 = new UsuarioMongoDB();
		UsuarioOD respuesta = beta3.buscar(beta2);
		if (respuesta != null) {

			int idUsuario = respuesta.getId_u();
			UsuarioOD jou = new UsuarioOD(idUsuario, null, null, null, null,
					null, null, null, null, null, null, null);
			TokenDAO bethoven = new TokenMongoDB();
			TokenOD quetal = bethoven.buscarPorUsuario(jou);
			if (quetal == null) {
				System.out.println("brother preocupate q stas en problemas");
				return ok("<mensaje>Error: Ese token no pertenece a ese usuario </mensaje>");
			} else
				System.out.println("el token de este pana es: "
						+ quetal.getToken() + " lo comparare con: " + token);

		} else
			return ok("<mensaje>Error:Usuario no existe </mensaje>");

		System.out.println("el nick a buscar es: " + nick);
		beta3.buscar(beta2);
		System.out.println("el id del usuario es: " + respuesta.getId_u());

		elcomentario.setId_c(Integer.parseInt(id_c));

		int eltoken = Integer.parseInt(token);
		String respuesta2 = beta.eliminar(elcomentario, eltoken,
				respuesta.getId_u());
		if (respuesta2.equals("good")) {
			Logger.info("ControladorComentario: Comentario eliminado exitosamente: "
					+ id_c);
			return ok("<mensaje> elimine comentarioo</mensaje>");
		} else {
			Logger.error("ControladorComentario: No se pudo eliminar el comentario: "
					+ id_c);
			return ok("<mensaje>Error: Error al eliminar el mensaje</mensaje>");
		}
	}
	
	
	
	/**
	 * se encarga de eliminar el comentario seleccionado, lo elimina si eres el
	 * dueno del comentario o si eres el padre de ese comentario
	 * 
	 * @param token
	 * @param id_c
	 * @param id_u
	 * @return
	 */
	public static Result eliminarComentarios(String token, String id_c,
			String nick) {
		System.out.println("1 ENTRO token= " + token);
		System.out.println("2 ENTRO id_c= " + id_c);
		System.out.println("3 ENTRO nick= " + nick);
		if (id_c == null) {
			return ok("<mensaje>Error: Id del comentario vacio no esta permitido </mensaje>");
		} else if (nick == null) {
			return ok("<mensaje>Error: Indique el id del usuario </mensaje>");
		} else if (isNumeric(id_c) == false) {
			return ok("<mensaje>Error: El id del comentario debe ser un numero </mensaje>");
		}

		else if (isNumeric(token) == false) {
			return ok("<mensaje>Error: El token debe ser un numero </mensaje>");
		}

		System.out.println("2 ENTRO YA VALIDE TODOS LAS ENTRADAS");
		GestorComentario beta = new GestorComentario();
		ComentarioOD elcomentario = new ComentarioOD();

		UsuarioOD beta2 = new UsuarioOD(nick, null);
		UsuarioDAO beta3 = new UsuarioMongoDB();
		UsuarioOD respuesta = beta3.buscar(beta2);
		if (respuesta != null) {

			int idUsuario = respuesta.getId_u();
			UsuarioOD jou = new UsuarioOD(idUsuario, null, null, null, null,
					null, null, null, null, null, null, null);
			TokenDAO bethoven = new TokenMongoDB();
			TokenOD quetal = bethoven.buscarPorUsuario(jou);
			if (quetal == null) {
				System.out.println("brother preocupate q stas en problemas");
				return ok("<mensaje>Error: Ese token no pertenece a ese usuario </mensaje>");
			} else
				System.out.println("el token de este pana es: "
						+ quetal.getToken() + " lo comparare con: " + token);

		} else
			return ok("<mensaje>Error:Usuario no existe </mensaje>");

		System.out.println("el nick a buscar es: " + nick);
		beta3.buscar(beta2);
		System.out.println("el id del usuario es: " + respuesta.getId_u());

		elcomentario.setId_c(Integer.parseInt(id_c));

		int eltoken = Integer.parseInt(token);
		String respuesta2 = beta.eliminar(elcomentario, eltoken,
				respuesta.getId_u());
		if (respuesta2.equals("good")) {
			Logger.info("ControladorComentario: Comentario eliminado exitosamente: "
					+ id_c);
			return ok("<mensaje> elimine comentarioo</mensaje>");
		} else {
			Logger.error("ControladorComentario: No se pudo eliminar el comentario: "
					+ id_c);
			return ok("<mensaje>Error: Error al eliminar el mensaje</mensaje>");
		}
	}

	/**
	 * se encagar a de listar todos los comentarios que esten relacionados con
	 * un tag en especifico
	 * 
	 * @param tags
	 * @return
	 */

	public static Result listartags(String tags) {

		GestorComentario beta = new GestorComentario();
		List<ComentarioOD> list = beta.listartags(tags);

		if (list == null)
			return ok("<mensaje>Error: Tag no existe</mensaje>");
		else {
			XStream xstream = new XStream(new DomDriver());
			String xml = xstream.toXML(list);
			return ok(xml);
		}
	}

	/**
	 * Esta funcion intenta adjuntar un archivo. . .
	 * 
	 * @return
	 */

	public static Result upload() {
		System.out.println(request().queryString());

		request().queryString().get("nick");
		MultipartFormData beta = request().body().asMultipartFormData();
		System.out.println(beta.getFiles().size() + "bodyyyyyyyyyyyyyy");
		for (FilePart p : beta.getFiles()) {
			System.out.println(p.getFilename());
		}
		FilePart picture = beta
				.getFile("C:/Users/Hugo Morelli/Desktop/images/bandera/alemania.jpg");
		System.out.println("2 entro");
		if (beta != null) {

			// System.out.println("3 entro"+beta.getFile("C:/Users/Hugo Morelli/Desktop/images/bandera/alemania.jpg"));
			// String fileName = picture.getFilename();

			// String contentType = picture.getContentType();
			// File file = picture.getFile();
			// System.out.println(picture.getFilename());
			return ok("File uploaded");
		} else {
			return ok("NADA BRO");
		}
	}

	// dado el id de un comentario listar sus tags
	public static Result tagDeUnComentario(String id_c) {
		Tag_ComentarioOD enecimo = new Tag_ComentarioOD();
		enecimo.setId_c(Integer.parseInt(id_c));
		Tag_ComentarioDAO beta = new Tag_ComentarioMongoDB();
		List<String> list = beta.listaTagsPorComentario(enecimo);
		if(list !=null)
		{
		XStream xstream = new XStream(new DomDriver());
		String xml = xstream.toXML(list);
		return ok(xml);
		}
		else
			return ok("<mensaje>Error: Lista vacia</mensaje>");
	}

	public static Result listarTags() {
		GestorComentario beta = new GestorComentario();
		List<TagOD> list = beta.listarTags();if(list !=null)
		{
		XStream xstream = new XStream(new DomDriver());
		String xml = xstream.toXML(list);
		return ok(xml);
		}
		else
			return ok("<mensaje>Error: Lista vacia</mensaje>");	
		}
	
	
	
	public static Result insercionAdjunto(String token){	//todo igual mas las rutas
		System.out.println("Insertando ComentarioOOOOOOOOO");

		// int tokens = (Integer) Integer.parseInt(token);
		// System.out.println("el token es: "+tokens);
		String texto = XPath.selectText("//texto", request().body().asXml());
		String privacidad = XPath.selectText("//privacidad", request().body().asXml());
		String id_u = XPath.selectText("//id_u", request().body().asXml());
		String padre = XPath.selectText("//padre", request().body().asXml());
		String ruta = XPath.selectText("//ruta", request().body().asXml());
		// llego
		System.out.println("la data que llega es: " + texto + "," + privacidad
				+ "," + id_u + "," + padre);
		if (texto == null) {
			return ok("<mensaje>Error: No puedes colocar comentario vacio </mensaje>");
		} else if (privacidad == null) {
			return ok("<mensaje>Error: Falta la privacidad del comentario </mensaje>");
		} else if (id_u == null) {
			return ok("<mensaje>Error: Falta el id del usuario que crea el comentario </mensaje>");
		} else if (padre == null) {
			return ok("<mensaje>Error: Falta el id del comentario padre de este comentario </mensaje>");
		} else if (ruta == null) {
			return ok("<mensaje>Error: Falta la ruta del comentario padre de este comentario </mensaje>");
		}

		// validaciones de tipo de dato
		else if (isNumeric(token) == false) {
			return ok("<mensaje>Error: El token del usuario excede el numero de digitos permitidos </mensaje>");
		} else if (isNumeric(id_u) == false) {
			return ok("<mensaje>Error: El id del usuario debe ser un numero </mensaje>");
		} else if (isNumeric(padre) == false) {
			return ok("<mensaje>Error: El id del padre debe ser un numero </mensaje>");
		} else if (isNumeric(privacidad) == false) {
			return ok("<mensaje>Error: la privacidad debe ser un numero </mensaje>");
		} else if (privacidad.length() > 1) {
			return ok("<mensaje>Error: la privacidad debe ser un numero de un solo digito </mensaje>");
		} else if (isNumeric(privacidad) == true) {
			if ((Integer.parseInt(privacidad) != 0)&& (Integer.parseInt(privacidad) != 1)) {
				return ok("<mensaje>Error: la privacidad solo puede ser 0 o 1 </mensaje>");
			}
		}
		int tokens = (Integer) Integer.parseInt(token);
		System.out.println("el token del usuario es: " + tokens);

		ComentarioOD comentario = new ComentarioOD(texto, null,
				Integer.parseInt(id_u), Integer.parseInt(padre),
				Integer.parseInt(privacidad), 0, 0);
		GestorComentario beta = new GestorComentario();

		// aki debo comparar si el id_u que esta en el comentario es igual al
		// id_u asociado al token

		int idUsuario = Integer.parseInt(id_u);
		GestorComentario tita = new GestorComentario();
		boolean propiedad = tita.esSuToken(idUsuario, tokens);
		if (propiedad == false) {
			Logger.error("ControladorComentario: ese token no es de quien dice ser: "
					+ tokens);
			return ok("<mensaje>Error: Ese token no pertenece a ese usuario bro </mensaje>");
		} else {
			Logger.info("ControladorComentario: El token es: " + tokens);
			System.out.println("ese token si es tuyo brother");
		}

		System.out.println("1paso");
		// aki esta el error
		int contadorTags = 0;
		for (int i = 0; i <= 200; i++) {
			String tag = XPath.selectText("//tag" + i, request().body().asXml());
			if (tag != null) {
				contadorTags++;

			}
		}
		System.out.println("el comentario tiene tags: " + contadorTags);
		System.out.println("1VOYY");

		if (contadorTags == 0) {
			GestorComentario preparador = new GestorComentario();
			ComentarioOD fase1 = preparador.insertarComentarioSintags(comentario, tokens);
			if (fase1 != null) {
				Logger.info("ControladorComentario: El comentario ha sido insertado"+ comentario.getTexto());
				XStream xstream = new XStream(new DomDriver());
				String xml = xstream.toXML(comentario);
				return ok(xml);
			} else 
			{
				Logger.error("ControladorComentario: Token no valido: "+ tokens);
				return ok("<mensaje>Error: error insertando comentario, Token invalido o comentario padre privado</mensaje>");

			}

		}
		boolean flag = false;
		String xml = null;
		String xml2 = null;
		Tag_ComentarioOD alfa = null;
		Tag_ComentarioDAO yema = null;
		String tag = null;
		XStream xstream = null;
		List<String> etiquetas = new ArrayList<String>();
		if (Integer.parseInt(padre) == 0) {
			if (contadorTags != 0) // cuando si tiene tags
			{
				GestorComentario alfino = new GestorComentario();
				boolean flagOficial = alfino.insertarComentarioConTags(comentario, tokens);
				int contadorTags2 = 0;
				for (int i2 = 0; i2 <= 200; i2++) 
				{
					tag = XPath.selectText("//tag" + i2, request().body().asXml());
					GestorComentario adminEtiquetas = new GestorComentario();
					if (tag != null)
						flag = adminEtiquetas.manejarEtiquetas(flagOficial,tag, etiquetas, comentario, tokens);

					contadorTags2++;
				}
			}
		} 
		else
		{
			return ok("<mensaje>Error:: el comentario es hijo y no puede llevar tags asociados</mensaje>");
		}

		xstream = new XStream(new DomDriver());
		System.out.println("el comentario viene con: " + comentario.getTexto());

		if (flag == true) {
			AdjuntoOD fotopa = new AdjuntoOD();
	    	fotopa.setId_c(comentario.getId_c());
	    	fotopa.setRuta(ruta);
			
			
			GestorComentario tramite = new GestorComentario();
	    	boolean llamado = tramite.insertarAdjuntoProfesional(fotopa,tokens);
	    	if (llamado == true)
	    	{
	    		xml = xstream.toXML(comentario);
				System.out.println("HASTA AKI VOY BIEN");
				return ok(xml);// si devuelvo xml3 lo pone todo anaranjado y con una
								// nueva etiqueta
	    	}
	    	else
	    		return ok("<mensaje>Error: no se pudo insertar la foto </mensaje>");
	    	
	    	
			
		} else
			return ok("<mensaje>Error: error insertando comentario: Token invalido</mensaje>");

		
	}
	
	
	public static Result obtenerAdjunto(String id_c)
	{
  		if(id_c == null) 
  		{
    		return ok("<mensaje>Error: Falta el id del comentario </mensaje>");
    	}
  		else if (isNumeric(id_c) == false) {
			return ok("<mensaje>Error: El id del comentario debe ser un numero </mensaje>");
		}
  		int idComentario = Integer.parseInt(id_c);
  		ComentarioOD beta = new ComentarioOD();
  		beta.setId_c(idComentario);
  		//y se lo paso al gestor
  		GestorComentario alfa = new GestorComentario();
  		String mensaje = alfa.obtenerRutaAdjuntoComentario(beta);
  		if (mensaje == "error: ese comentario no tiene adjunto")
  		{
  			Logger.error("ControladorComentario: el comentario no contiene adjuntos");
  			return ok("<mensaje>Error: ese comentario no tiene adjunto</mensaje>"); 	
  		}
  		else 	
  		{
  			Logger.info("ControladorComentario: la ruta del adjunto es: " +mensaje);
  			return ok("<mensaje>La ruta del adjunto es: "+mensaje+"</mensaje>");
  		}
  		
  	}

}
