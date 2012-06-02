
package models.DAO.MongoDB;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DAO.ComentarioDAO;
import models.OD.ComentarioOD;
import models.OD.Tag_ComentarioOD;


/**
 *
 * @author SCOTT
 */
public class ComentarioMongoDB implements models.DAO.ComentarioDAO{

    public ComentarioMongoDB() {
    }
    /**
     * se encarga de establecer la conexion con MongoDB y adicionalmente se encarga de 
     * verificar que la coleccion donde vas a trabajar exista
     * @return DBCollection
     */

    public DBCollection conectarMongo(){
        Mongo m;
        
        try {
            m = new Mongo();
            DB db = m.getDB( "blog" );
            DBCollection coleccionUsuario = db.getCollection("comentario");
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
     * Se encargada de insertar en persistencia (Mongodb) el comentario que le llega a la funcion
     * como parametro conviertiendolo en un objeti tipo mongo para luego insertatlo
     * @param Comentario
     */
    public void insertar(ComentarioOD Comentario){
     
        DBCollection coleccionComentario = conectarMongo();
        
        if(coleccionComentario!=null){
            
            BasicDBObject comentario = new BasicDBObject();
            comentario.put("id_c",crearId());
            comentario.put("texto", Comentario.getTexto());
            comentario.put("id_u", Comentario.getId_u());
            comentario.put("fecha", Comentario.getFecha());
            comentario.put("padre",Comentario.getPadre());
            comentario.put("gusta",Comentario.getGusta());
            comentario.put("nogusta",Comentario.getNogusta());
            comentario.put("privacidad",Comentario.getPrivacidad());
            
            coleccionComentario.insert(comentario);
                 
        }
        else
        {
        	
            System.out.println("coleccion no existente");
           
        }
        
    }
   
    
    /**
     * Se encarga de eliminar los comentarios en persistencia(Mongodb) pasando el un objeto  del tipo comentario en el
     * cual sol
     *
     */
    public void eliminar(ComentarioOD comentario) {
        
        DBCollection coleccionComentario = conectarMongo();
        DBObject obj = null;
        BasicDBObject query = new BasicDBObject();
        query.put("padre", comentario.getId_c());
        DBCursor cur = coleccionComentario.find(query);
        
       
          	   
    	   
       
        while(cur.hasNext()) {  
            obj = cur.next();
            int id_c = Integer.parseInt(obj.get("id_c").toString());
            int padre = Integer.parseInt(obj.get("padre").toString());
            ComentarioOD comentarioR = new ComentarioOD(id_c,null, null, 0,padre,0);
            eliminar(comentarioR);
           
        coleccionComentario.remove(obj); 
            //System.out.println(cur.next());
        }
        eliminarOrigen(comentario);
            
    }
    
    /**
     * se encarga de 
     * @param comentario
     */
    
    public void eliminarOrigen(ComentarioOD comentario) {
        System.out.println(comentario.getId_c());
        DBCollection coleccionComentario = conectarMongo();
        BasicDBObject query = new BasicDBObject();
        query.put("id_c", comentario.getId_c());
        coleccionComentario.remove(query);
         
    }
    
    
    /**
     * se encarga de constuir un objeto manejable para el framwork ya que el manejador devuelve un DBobject y lo convertimos en un 
     * objeto ComentarioOD
     * @param obj
     * @return
     */
    
     public ComentarioOD construir(DBObject obj){
        ComentarioOD comentario = new ComentarioOD();
       // comentario.setObjectid(obj.get("_id").toString());
        comentario.setId_c((Integer.parseInt(obj.get("id_c").toString())));
        comentario.setId_u((Integer.parseInt(obj.get("id_u").toString())));
        comentario.setFecha(obj.get("fecha").toString());
        comentario.setTexto(obj.get("texto").toString());
        comentario.setGusta((Integer.parseInt(obj.get("gusta").toString())));
        comentario.setNogusta((Integer.parseInt(obj.get("nogusta").toString())));
        comentario.setPrivacidad((Integer.parseInt(obj.get("privacidad").toString())));
        comentario.setPadre((Integer.parseInt(obj.get("padre").toString())));
        
    return comentario;
    }
     
     
     /**
      * se encarga de buscar un comentario por el id del comentario
      */
     public ComentarioOD buscar(ComentarioOD comentario){
     	
     	
     	 DBObject obj = null ;
         ComentarioOD beta = null;
         DBCollection coleccionUsuario = conectarMongo();
         BasicDBObject query = new BasicDBObject();
         query.put("id_c",comentario.getId_c());
         DBCursor cur = coleccionUsuario.find(query);
            
         if(cur.count() != 0){
         while(cur.hasNext()){
             
         	obj = cur.next();
         	beta = construir(obj);
         	return beta;
             }  		
      
         return null;
     }else{return null;}
         
   }
     
     /**
      * procedimiento para insertar cuando hacemos el proceso de modificacion o actualizacion de comentarios
      * @param Comentario
      */
     
     public void insertarModificar(ComentarioOD Comentario){
         
         DBCollection coleccionComentario = conectarMongo();
         
         if(coleccionComentario!=null){
             
             BasicDBObject comentario = new BasicDBObject();
             comentario.put("id_c",Comentario.getId_c());
             comentario.put("texto", Comentario.getTexto());
             comentario.put("id_u", Comentario.getId_u());
             comentario.put("fecha", Comentario.getFecha());
             comentario.put("padre",Comentario.getPadre());
             comentario.put("gusta",Comentario.getGusta());
             comentario.put("nogusta",Comentario.getNogusta());
             comentario.put("privacidad",Comentario.getPrivacidad());
             
             coleccionComentario.insert(comentario);    
         }
         else
         {
         	
             System.out.println("coleccion no existente");
            
         }
         
     }
  

 
     /**
      * se encarga de actualizar en persistencia los campos de gusta o no gusta de la peticion realizada sobre el comentario
      */
    public void ActualizaGustar(ComentarioOD Comentario, int gusta){
     
    	int jou = Comentario.getId_c();
    	eliminarOrigen(Comentario);      
    	if(gusta==1){
   
    		Comentario.setGusta(Comentario.getGusta() + 1);
    		insertarModificar(Comentario);
    	}
       
    	if(gusta==0){
    		Comentario.setNogusta(Comentario.getNogusta() + 1);
    		insertarModificar(Comentario);
       
      }
        
    }
    
    
    /**
     * se encarga de crear un id al comentario para su mejor manejo
     * @return
     */
        public int crearId(){
            DBObject obj = null;
            DBCollection coleccionComentario = conectarMongo();
            DBCursor cur = coleccionComentario.find();
            int mayor = 0;
        while(cur.hasNext()) {
            //System.out.println(cur.next());
            obj = cur.next();
            if(Integer.parseInt(obj.get("id_c").toString())>mayor){
            mayor = Integer.parseInt(obj.get("id_c").toString());
            }
        }
        //System.out.println(obj.get("nombre"));
          
          if(obj!=null){
              return  mayor + 1;
          }else{return 1;}
        
        }
        
        /**
         * se encarga de buscar el id del coemntario recien insertado
         */
        public int Buscarid()
        {
            DBObject obj = null;
            DBCollection coleccionComentario = conectarMongo();
            DBCursor cur = coleccionComentario.find();
            int mayor = 0;
            while(cur.hasNext()) 
            {
            	//System.out.println(cur.next());
            	obj = cur.next();
            	if(Integer.parseInt(obj.get("id_c").toString())>mayor)
            	{
            		mayor = Integer.parseInt(obj.get("id_c").toString());
            	}
            }
            //System.out.println(obj.get("nombre"));
          
            if(obj!=null)
            {
            	return  mayor ;
            }
            else
            {
            	return 1;
            }        
        }
        
        /**
         * se encarga de listar todos los comentario en persistencia 
         * @return List<ComentarioOD>
         */
        
        public List<ComentarioOD> listar() {
            
            List<ComentarioOD> lista = new ArrayList<ComentarioOD>();
            DBObject obj = null;
            ComentarioOD beta = new ComentarioOD();
            DBCollection coleccionComentario = conectarMongo();
            DBCursor cur = coleccionComentario.find();
           
            while(cur.hasNext()) {
                //System.out.println(cur.next());
                obj = cur.next();
                beta = construir(obj);
                
                lista.add(beta);
            }
            
            return lista;
        }
        
        /**
         * se encarga de listar todos los comentario hijos con respecto a un comentario en especifico
         * @param Comentario
         * @return List<ComentarioOD>
         */
   
   
        public List<ComentarioOD> listarHijos(ComentarioOD Comentario) {
            
            List<ComentarioOD> lista = new ArrayList<ComentarioOD>();
            DBObject obj = null;
            ComentarioOD beta = new ComentarioOD();
            BasicDBObject query = new BasicDBObject();
            DBCollection coleccionComentario = conectarMongo();
            query.put("padre",Comentario.getId_c());
            DBCursor cur = coleccionComentario.find(query);
          
           
            while(cur.hasNext()) {
                //System.out.println(cur.next());
                obj = cur.next();
                beta = construir(obj);
                lista.add(beta);
            }
            
            return lista;
        }
      
        /**
         * se encarga de listar todos los comentarios creados por un usuario
         * @param Comentario
         * @return List<ComentarioOD>
         */
        public List<ComentarioOD> listarPorUsuario(ComentarioOD Comentario) {
	            
	            List<ComentarioOD> lista = new ArrayList<ComentarioOD>();
	            DBObject obj = null;
	            ComentarioOD beta = new ComentarioOD();
	            BasicDBObject query = new BasicDBObject();
	            DBCollection coleccionComentario = conectarMongo();
	            query.put("id_u",Comentario.getId_u());
	            DBCursor cur = coleccionComentario.find(query);
	          
	           
	            while(cur.hasNext()) {
	                //System.out.println(cur.next());
	                obj = cur.next();
	                beta = construir(obj);
	                lista.add(beta);
	            }
	            
	            return lista;
	        }

     


		@Override
		public void Modificar(ComentarioOD comentario) {
			// TODO Auto-generated method stub
			
		}


}
