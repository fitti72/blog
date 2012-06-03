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
import models.OD.UsuarioOD;

/**
 *
 * @author SCOTT
 */
public class UsuarioMongoDB implements models.DAO.UsuarioDAO{

	/**
	 * 
	 */
    public UsuarioMongoDB(){
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
            DBCollection coleccionUsuario = db.getCollection("usuario");
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
     * se encarga de insert el usuario en persistencia
     * @param Usuario
     */
    public void insertar(UsuarioOD Usuario) {
        
        
        DBCollection coleccionUsuario = conectarMongo();
        
        if(coleccionUsuario!=null){
            
            BasicDBObject usuario = new BasicDBObject();
            usuario.put("id_u",crearId());
            usuario.put("nombre", Usuario.getNombre());
            usuario.put("nombres", Usuario.getNombres());
            usuario.put("apellido", Usuario.getApellido());
            usuario.put("apellidos", Usuario.getApellidos());
            usuario.put("email", Usuario.getEmail());
            usuario.put("fecha", Usuario.getFecha());
            usuario.put("nick", Usuario.getNick());
            usuario.put("pais", Usuario.getPais());
            usuario.put("biografia", Usuario.getBiografia());
            usuario.put("sexo", Usuario.getSexo());
            usuario.put("clave", Usuario.getClave());
            usuario.put("token",0);
            coleccionUsuario.insert(usuario);
            
        }
        else
        {
            System.out.println("coleccion no existente");
        }
    }

    /**
     * se encarga de eliminar a los usuario de persistencia se hace usando un cursor buscando el usuario una vez encontrado lo elimina
     */
    
    public void eliminar(UsuarioOD Usuario) {
        
        DBCollection coleccionUsuario = conectarMongo();
        BasicDBObject query = new BasicDBObject();
        query.put("nick", Usuario.getNick());
        DBCursor cur = coleccionUsuario.find(query);
        
        while(cur.hasNext()) {
            coleccionUsuario.remove(cur.next());
            //System.out.println(cur.next());
        } 
    }
    
    /**
     * se encarga de transformar el objeto que nos devuelve mongo DBobject en un objeto manejable para nuestra aplicacion
     * @return UsuarioOD
     */
    
     public UsuarioOD construir(DBObject obj){
        UsuarioOD usuario = new UsuarioOD();
        usuario.setObjectid(obj.get("_id").toString());
        usuario.setId_u((Integer.parseInt(obj.get("id_u").toString())));
        usuario.setNombre(obj.get("nombre").toString());
        usuario.setNombres(obj.get("nombres").toString());
        usuario.setApellido(obj.get("apellido").toString());
        usuario.setApellidos(obj.get("apellidos").toString()); 
        usuario.setEmail(obj.get("email").toString());
        usuario.setFecha(obj.get("fecha").toString());
        usuario.setNick(obj.get("nick").toString());
        usuario.setPais(obj.get("pais").toString());
        usuario.setBiografia(obj.get("biografia").toString());
        usuario.setSexo(obj.get("sexo").toString());
        usuario.setClave(obj.get("clave").toString());
        usuario.setToken((Integer.parseInt(obj.get("token").toString())));
        
        
        return usuario;
    }
     
    

     /**
      * se encarga de transformar el objeto que nos devuelve mongo DBobject en un objeto manejable para nuestra aplicacion
      * @return UsuarioOD
      */
     public UsuarioOD construirToken(DBObject obj){
         UsuarioOD usuario = new UsuarioOD();
         usuario.setObjectid(obj.get("_id").toString());
         usuario.setId_u((Integer.parseInt(obj.get("id_u").toString())));
         usuario.setNombre(obj.get("nombre").toString());
         usuario.setNombres(obj.get("nombres").toString());
         usuario.setApellido(obj.get("apellido").toString());
         usuario.setApellidos(obj.get("apellidos").toString()); 
         usuario.setEmail(obj.get("email").toString());
         usuario.setFecha(obj.get("fecha").toString());
         usuario.setNick(obj.get("nick").toString());
         usuario.setPais(obj.get("pais").toString());
         usuario.setBiografia(obj.get("biografia").toString());
         usuario.setSexo(obj.get("sexo").toString());
         usuario.setClave(obj.get("clave").toString());
         System.out.println("AKI IMPRIMIRE EL TOKEN: "+Integer.parseInt(obj.get("token").toString()));
         usuario.setToken((Integer.parseInt(obj.get("token").toString())));
         
     return usuario;
     }
     
    
     /**
      *  se encarga de buscar un usuario por nick de usuario
      *  @return UsuarioOD
      */
    public UsuarioOD buscar(UsuarioOD Usuario)
    {
    	DBObject obj = null ;
        UsuarioOD beta = null;
        DBCollection coleccionUsuario = conectarMongo();
        BasicDBObject query = new BasicDBObject();
        query.put("nick",Usuario.getNick());
        DBCursor cur = coleccionUsuario.find(query);       
        if(cur.count() != 0)
        {
        	while(cur.hasNext())
        	{
               	obj = cur.next();
               	beta = construir(obj);
               	return beta;
            }  		
     
        	return null;
        }
        else
        {
        	return null;
        }
    }
    
    
    /**
     * se encarga de listar todos los usuario que se encuentren en persistencia
     * @return List<UsuarioOD> lista de usuarios
     */
    public List<UsuarioOD> listar() {
        
        List<UsuarioOD> lista = new ArrayList<UsuarioOD>();
        DBObject obj = null;
        UsuarioOD beta = new UsuarioOD(null, null, null, null, null, null, null, null, null, null, null);
        DBCollection coleccionUsuario = conectarMongo();
        DBCursor cur = coleccionUsuario.find();
       
        while(cur.hasNext()) 
        {
            System.out.println("LLENANDO LISTA BRO");
            obj = cur.next();
            beta = construir(obj);
            lista.add(beta);
        }
        
        return lista;
    }

    
    /**
     * se encarga de generar un id para los usuario a insertar para su mejor manejo ya que los id que genera mongo son un poco mas tediosos
     * @return id 
     */
        public int crearId()
        {
            DBObject obj = null;
            DBCollection coleccionUsuario = conectarMongo();
            DBCursor cur = coleccionUsuario.find();
            int mayor = 0;
        
            while(cur.hasNext()) 
            {
            	//System.out.println(cur.next());
            	obj = cur.next();
            	if(Integer.parseInt(obj.get("id_u").toString())>mayor)
            	{
            		mayor = Integer.parseInt(obj.get("id_u").toString());
            	}
            }
            //System.out.println(obj.get("nombre"));
            if(obj!=null)
            {
            	return  mayor + 1;
            }
            else
            {
            	return 1;
            }
        
        }


    	/**
    	 * se encarga de modificar la informacion del usuario
    	 */
	    public void insertarModificar(UsuarioOD Usuario) 
	    {
	    	DBCollection coleccionUsuario = conectarMongo();
	    	if(coleccionUsuario!=null)
	    	{
	    		BasicDBObject usuario = new BasicDBObject();
	    		usuario.put("id_u",Usuario.getId_u());
	    		usuario.put("nombre", Usuario.getNombre());
	    		usuario.put("nombres", Usuario.getNombres());
	    		usuario.put("apellido", Usuario.getApellido());
	    		usuario.put("apellidos", Usuario.getApellidos());
	    		usuario.put("email", Usuario.getEmail());
	    		usuario.put("fecha", Usuario.getFecha());
	    		usuario.put("nick", Usuario.getNick());
	    		usuario.put("pais", Usuario.getPais());
	    		usuario.put("biografia", Usuario.getBiografia());
	    		usuario.put("sexo", Usuario.getSexo());
	    		usuario.put("clave", Usuario.getClave());
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
	public void eliminarModificar(UsuarioOD Usuario) 
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
    public void modificar(UsuarioOD Nuevo) 
    {
    	UsuarioMongoDB beta = new UsuarioMongoDB();
        beta.eliminarModificar(Nuevo);
        beta.insertarModificar(Nuevo);
    }

    
    /**
     * se encarga de buscar los usuario por el ID
     * @return UsuarioOD
     */
    public UsuarioOD buscarID(UsuarioOD Usuario){
    	
    	
    	DBObject obj = null ;
        UsuarioOD beta = null;
        DBCollection coleccionUsuario = conectarMongo();
        BasicDBObject query = new BasicDBObject();
        query.put("id_u",Usuario.getId_u());
        DBCursor cur = coleccionUsuario.find(query);
           
        
        while(cur.hasNext()){
            
        	obj = cur.next();
        	beta = construir(obj);
        	return beta;
            }  		
     
        return null;
    }


}




    
    
    
 