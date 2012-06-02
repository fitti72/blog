/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.OD;



/**
 *
 * @author SCOTT
 */
public class ComentarioOD {
    
    private int id_c;
    private String objectid;
    private String texto; 
    private String fecha;
    private int privacidad;
    private int id_u;
    private int padre;
    private int gusta;
    private int nogusta;



    /**
     * 
     */
    
    public ComentarioOD(){}

    /**
     * 
     * @param texto
     * @param fecha
     * @param id_u
     * @param padre
     * @param privacidad
     * @param gusta
     * @param nogusta
     */
    public ComentarioOD( String texto, String fecha, int id_u,int padre,int privacidad,int gusta, int nogusta) {
        
        this.texto = texto;
        this.fecha = fecha;
        this.id_u = id_u;
        this.padre = padre;
        this.gusta = 0;
        this.nogusta = 0;
        this.privacidad = privacidad;
    }
    
    /**
     * 
     * @param id_c
     * @param texto
     * @param fecha
     * @param id_u
     * @param padre
     * @param privacidad
     */
    public ComentarioOD(int id_c,String texto, String fecha, int id_u,int padre,int privacidad) {
        this.id_c = id_c;
        this.texto = texto;
        this.fecha = fecha;
        this.id_u = id_u;
        this.padre = padre;
        this.gusta = 0;
        this.nogusta = 0;
        this.privacidad = privacidad;
    }

    /**
     * 
     * @return privacidad
     */
    public int getPrivacidad() {
        return privacidad;
    }

    /**
     * 
     * @param privacidad a setear 
     */
    public void setPrivacidad(int privacidad) {
        this.privacidad = privacidad;
    }
    
    

    /**
     * padre del comentario
     * @return padre
     */
    public int getPadre() {
        return padre;
    }
    
    /**
     * 
     * @param padre a setear
     */

    public void setPadre(int padre) {
        this.padre = padre;
    }
    
    /**
     * 
     * @return fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * 
     * @param fecha a setear 
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * id del comentario
     * @return id_c
     */
    public int getId_c() {
        return id_c;
    }

    /**
     * id del comentario
     * @param id_c
     */
    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    /**
     * id del usuario
     * @return id_u
     */
    public int getId_u() {
        return id_u;
    }

    /**
     * 
     * @param id_u a setear
     */
    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    /**
     * objectid que genera mongo
     * @return objectid
     */
    public String getObjectid() {
        return objectid;
    }
    /**
     * objectid que genera mongo
     * @param objectid a setear
     */

    public void setObjectid(String objectid) {
        this.objectid = objectid;
    }

    /**
     * 
     * @return texto
     */
    public String getTexto() {
        return texto;
    }
    
    /**
     * 
     * @param texto a setear
     */

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    /**
     * 
     * @return gusta
     */
     public int getGusta() {
        return gusta;
    }

     /**
      * 
      * @param gusta a setear
      */
    public void setGusta(int gusta) {
        this.gusta = gusta;
    }

    /**
     * 
     * @return no gusta
     */
    public int getNogusta() {
        return nogusta;
    }
    
    /**
     * 
     * @param nogusta a setear
     */

    public void setNogusta(int nogusta) {
        this.nogusta = nogusta;
    }

}
