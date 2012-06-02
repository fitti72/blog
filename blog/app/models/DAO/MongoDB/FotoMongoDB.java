/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.DAO.MongoDB;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import play.db.ebean.Model;
import models.OD.FotoOD;
import models.OD.UsuarioOD;

/**
 * 
 * @author SCOTT
 */
public class FotoMongoDB implements models.DAO.FotoDAO {

	/**
     * 
     */
	public FotoMongoDB() {
	}

	/**
	 * se encarga de establecer la conexion con MongoDB y adicionalmente se
	 * encarga de verificar que la coleccion donde vas a trabajar exista token
	 * 
	 * @return DBCollection
	 */

	public DBCollection conectarMongo() {
		Mongo m;
		try {
			m = new Mongo();
			DB db = m.getDB("blog");
			DBCollection coleccionUsuario = db.getCollection("foto");
			return coleccionUsuario;
		} catch (UnknownHostException ex) {
			Logger.getLogger(UsuarioMongoDB.class.getName()).log(Level.SEVERE,
					null, ex);
			return null;
		} catch (MongoException ex) {
			Logger.getLogger(UsuarioMongoDB.class.getName()).log(Level.SEVERE,
					null, ex);
			return null;
		}

	}

	/**
	 * se encargar de insertar en persistencia el token
	 * 
	 * @param Token
	 *            objeto TokenOD
	 */

	public void insertar(FotoOD Foto) {

		DBCollection coleccionToken = conectarMongo();
		if (coleccionToken != null) {

			BasicDBObject foto = new BasicDBObject();
			foto.put("id_u", Foto.getId_u());
			foto.put("ruta", Foto.getRuta());
			
			coleccionToken.insert(foto);

		} else {
			System.out.println("coleccion no existente");
		}
	}

	/**
	 * se encarga de elimianr el token de persistenca
	 * 
	 * @param Token
	 *            objeto tipo TokenOD
	 */

	public void eliminar(FotoOD Token) {

		DBCollection coleccionToken = conectarMongo();

		BasicDBObject query = new BasicDBObject();
		query.put("id_u", Token.getId_u());
		DBCursor cur = coleccionToken.find(query);

		while (cur.hasNext()) {
			coleccionToken.remove(cur.next());
			// System.out.println(cur.next());
		}
	}

	/**
	 * se encarga de buscar token en persistencia
	 * 
	 * @param Token
	 *            objeto tipo TokenOD
	 * @return TokenOD
	 */
	public FotoOD buscar(FotoOD Token) {

		FotoOD beta = null;
		DBObject obj = null;
		DBCollection coleccionUsuario = conectarMongo();
		BasicDBObject query = new BasicDBObject();
		query.put("id_u", Token.getId_u());
		DBCursor cur = coleccionUsuario.find(query);

		if (cur.count() != 0) {

			while (cur.hasNext()) {

				obj = cur.next();

			}
			beta = construir(obj);
			// System.out.println(beta.getFecha());

			return beta;
		} else {
			return null;
		}
	}

	/**
	 * se encarga de buscar el ultimo token que tiene el usuario
	 * 
	 * @param Usuario
	 *            objeto tipo UsuarioOD
	 * @return TokenOD
	 */
	public FotoOD buscarPorUsuario(UsuarioOD Usuario) {

		FotoOD beta = null;
		DBObject obj = null;
		DBCollection coleccionUsuario = conectarMongo();
		BasicDBObject query = new BasicDBObject();
		query.put("id_u", Usuario.getId_u());
		DBCursor cur = coleccionUsuario.find(query);

		if (cur.count() != 0) {

			while (cur.hasNext()) {

				obj = cur.next();

			}
			beta = construir(obj);
			// System.out.println(beta.getFecha());

			return beta;
		} else {
			return null;
		}
	}

	/**
	 * se encarga de construir un token del formato de mongo a un objeto tipo
	 * TokenOD
	 * 
	 * @param obj
	 * @return TokenOD
	 */
	public FotoOD construir(DBObject obj) {
		FotoOD token = new FotoOD();
		token.set_id(obj.get("_id").toString());
		token.setId_u((Integer.parseInt(obj.get("id_u").toString())));
		token.setRuta(obj.get("ruta").toString());
		

		return token;
	}

	/**
	 * se encarga de buscar token por IP
	 * 
	 * @return TokenOD
	 */
	
	
	
    public void insertarModificar(FotoOD Usuario) 
    {
    	DBCollection coleccionUsuario = conectarMongo();
    	if(coleccionUsuario!=null)
    	{
    		BasicDBObject usuario = new BasicDBObject();
    		usuario.put("id_u",Usuario.getId_u());
    		usuario.put("ruta", Usuario.getRuta());
    	
    		coleccionUsuario.insert(usuario);
        
    	}
    	else
    	{
    		System.out.println("coleccion no existente");
    	}
    }
/**
 * se encarga de eliminar el usuario que queremos modificar
 */
public void eliminarModificar(FotoOD Usuario) 
{
    DBCollection coleccionUsuario = conectarMongo();
    BasicDBObject query = new BasicDBObject();
    query.put("id_u", Usuario.getId_u());
    DBCursor cur = coleccionUsuario.find(query);        
    while(cur.hasNext()) 
    {
        coleccionUsuario.remove(cur.next());  
    } 
}

/**
 * se encarga de insertar el usuario modificado en persistencia ya que el update de la libreria de mongo no nos funciono eliminamos el usuaruo ya insertado e insertamos el nuevo con su mismi 
 * id generado por nosotros
 */
public void modificar(FotoOD Nuevo) 
{
	FotoMongoDB beta = new FotoMongoDB();
    beta.eliminarModificar(Nuevo);
    beta.insertarModificar(Nuevo);
}
	

}