package models.DAO.MongoDB;

import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.OD.AdjuntoOD;
import models.OD.UsuarioOD;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class AdjuntoMongoDB implements models.DAO.AdjuntoDAO{
	
	
	public AdjuntoMongoDB(){
		
	}
	
	
	public DBCollection conectarMongo() {
		Mongo m;
		try {
			m = new Mongo();
			DB db = m.getDB("blog");
			DBCollection coleccionUsuario = db.getCollection("adjunto");
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

	public void insertar(AdjuntoOD Foto) {

		DBCollection coleccionToken = conectarMongo();
		if (coleccionToken != null) {

			BasicDBObject foto = new BasicDBObject();
			foto.put("id_c", Foto.getId_c());
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

	public void eliminar(AdjuntoOD Token) {

		DBCollection coleccionToken = conectarMongo();

		BasicDBObject query = new BasicDBObject();
		query.put("id_c", Token.getId_c());
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
	public AdjuntoOD buscar(AdjuntoOD Token) {

		AdjuntoOD beta = null;
		DBObject obj = null;
		DBCollection coleccionUsuario = conectarMongo();
		BasicDBObject query = new BasicDBObject();
		query.put("id_c", Token.getId_c());
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
	public AdjuntoOD construir(DBObject obj) {
		AdjuntoOD token = new AdjuntoOD();
		token.set_id(obj.get("_id").toString());
		token.setId_c((Integer.parseInt(obj.get("id_c").toString())));
		token.setRuta(obj.get("ruta").toString());
		

		return token;
	}

	/**
	 * se encarga de buscar token por IP
	 * 
	 * @return TokenOD
	 */
	
	
	
    public void insertarModificar(AdjuntoOD Usuario) 
    {
    	DBCollection coleccionUsuario = conectarMongo();
    	if(coleccionUsuario!=null)
    	{
    		BasicDBObject usuario = new BasicDBObject();
    		usuario.put("id_c",Usuario.getId_c());
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
public void eliminarModificar(AdjuntoOD Usuario) 
{
    DBCollection coleccionUsuario = conectarMongo();
    BasicDBObject query = new BasicDBObject();
    query.put("id_c", Usuario.getId_c());
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
public void modificar(AdjuntoOD Nuevo) 
{
	AdjuntoMongoDB beta = new AdjuntoMongoDB();
    beta.eliminarModificar(Nuevo);
    beta.insertarModificar(Nuevo);
}
	
	
	
	
	
	
	

}
