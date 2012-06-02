/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.DAO.MongoDB;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.DAO.ComentarioDAO;
import models.OD.ComentarioOD;
import models.OD.TagOD;
import models.OD.Tag_ComentarioOD;

/**
 *
 * @author SCOTT
 */
public class Tag_ComentarioMongoDB implements models.DAO.Tag_ComentarioDAO{
    
        /**
         * 
         */
     public Tag_ComentarioMongoDB(){
    }

     /**
      * se encarga de establecer la conexion con MongoDB y adicionalmente se encarga de 
      * verificar que la coleccion donde vas a trabajar exista tag_comentario
      * @return DBCollection
      */

    public DBCollection conectarMongo(){
        Mongo m;
        try {
            m = new Mongo();
            DB db = m.getDB( "blog" );
            DBCollection coleccionUsuario = db.getCollection("tag_comentario");
        return coleccionUsuario;
        } catch (UnknownHostException ex) {
            Logger.getLogger(UsuarioMongoDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (MongoException ex) {
            Logger.getLogger(UsuarioMongoDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    /**
     * se encarga de insertar en persisntencia la relacion del comentario con el tag elegido
     * 
     */
    public void insertar(Tag_ComentarioOD Tag_Coemntario) {
        
        
        DBCollection coleccionTG = conectarMongo();
        
        if(coleccionTG!=null){
            
            BasicDBObject tag_comentario = new BasicDBObject();
            tag_comentario.put("id_c", Tag_Coemntario.getId_c());
            tag_comentario.put("id_t", Tag_Coemntario.getId_t());
            
            coleccionTG.insert(tag_comentario);
            
        }
        else
        {
            System.out.println("coleccion no existente");
        }
    }
    
    /**
     * se encarga de eliminar la relacion del comentario  elegido   
     * @param comentario 
     */
    public void eliminarComentario(ComentarioOD comentario) {
        
        
        DBCollection coleccionTG = conectarMongo();
        
        BasicDBObject query = new BasicDBObject();
        query.put("id_c", comentario.getId_c());
        DBCursor cur = coleccionTG.find(query);
        
        while(cur.hasNext()) {
            coleccionTG.remove(cur.next());
            //System.out.println(cur.next());
        } 
    }
    
        /**
         * se encarga de eliminar el tag de persistencia
         * @param tag
         */
        public void eliminarTag(TagOD tag) {
        
        DBCollection coleccionTG = conectarMongo();
        
        BasicDBObject query = new BasicDBObject();
        query.put("id_t", tag.getId_t());
        DBCursor cur = coleccionTG.find(query);
        
        while(cur.hasNext()) {
            coleccionTG.remove(cur.next());
            //System.out.println(cur.next());
        } 
    }
       
        /**
         * se encarga de transformar el objeto de mongo BDObject en un objeto tipo Tag_Comentario para poderlo manejar
         * @param obj
         * @return
         */

        public Tag_ComentarioOD construirtag(DBObject obj){
		 
        	Tag_ComentarioOD tag = new  Tag_ComentarioOD();
		    tag.setId_c((Integer.parseInt(obj.get("id_c").toString())));
		    tag.setId_t((Integer.parseInt(obj.get("id_t").toString())));
        		
        		    
    		return tag;
		}
        
        /**
         * se encarga de listar todos los comentarios por un tag espesicifo
         */
        public List<ComentarioOD> listarPortag(int id_t){
        	
			System.out.println("listar comentario por tag en comentario mongo db " + id_t);
		    List<ComentarioOD> lista = new ArrayList<ComentarioOD>();
		    DBObject obj = null;  
		    Tag_ComentarioOD beta = new Tag_ComentarioOD();
		    BasicDBObject query = new BasicDBObject();
		    DBCollection coleccionComentario = conectarMongo();
		    query.put("id_t",id_t);
		    System.out.println(query);
		    DBCursor cur = coleccionComentario.find(query);
		  
		   
		    while(cur.hasNext()) {
		        obj = cur.next();
		        beta = construirtag(obj);
		        System.out.println(beta.getId_c()+" id del comentario a buscar para menter en la lista");
		        ComentarioOD buscar = new ComentarioOD();
		        buscar.setId_c(beta.getId_c());
		        ComentarioDAO traer = new ComentarioMongoDB();
		        buscar = traer.buscar(buscar);
		        
		        lista.add(buscar);
		    }
		    
		    return lista;
		
		}
        
        /**
         * funcion que dado un DBObject osea el que devuelve mongo, lo convierto a un objeto de dominio
         * @param obj
         * @return
         */
        public Tag_ComentarioOD construir(DBObject obj)
        {
        	Tag_ComentarioOD enecimo = new Tag_ComentarioOD();           
        	enecimo.setId_c((Integer.parseInt(obj.get("id_c").toString())));
        	enecimo.setId_t((Integer.parseInt(obj.get("id_t").toString())));
        	return enecimo;
        }
        
        
        /**
         * funcion que en una lista inserta, todos los tags de un comentario 
         * @param enecimo
         * @return
         */
        
        public List<String> listaTagsPorComentario(Tag_ComentarioOD enecimo) {
            
            List<String> lista = new ArrayList<String>();
            DBObject obj = null;
            Tag_ComentarioOD beta = new Tag_ComentarioOD();
            BasicDBObject query = new BasicDBObject();
            DBCollection coleccionEnecimo = conectarMongo();
            query.put("id_c",enecimo.getId_c());
            DBCursor cur = coleccionEnecimo.find(query);          
           
            while(cur.hasNext()) 
            {
                //System.out.println(cur.next());
                obj = cur.next();
                beta = construir(obj);
                System.out.println("un tag del comentario: "+enecimo.getId_c()+" es: "+beta.getId_t());
                int peter = beta.getId_t();
                TagOD max = new TagOD();
                max.setId_t(peter);
                TagMongoDB epa = new TagMongoDB();
                TagOD nuevo = epa.buscarPorId(max);
                lista.add(nuevo.getNombre());
            }
            
            return lista;
        }
}
