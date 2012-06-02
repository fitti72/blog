/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.OD;

/**
 *
 * @author SCOTT
 */
public class TokenOD {
    
    private String _id;
    private int id_u;
    private int token; 
    private String fecha;
    private String ip;

    /**
     * 
     */
    public TokenOD(){}
    
    /**
     * 
     * @param id_u
     * @param token
     * @param fecha
     * @param ip
     */
    public TokenOD(int id_u,int token, String fecha,String ip) {
        this.id_u = id_u;
        this.fecha = fecha;
        this.token = token;
        this.ip = ip;
    
    }
	  /**
	   * 
	   * @param id_u
	   * @param fecha
	   */
    public TokenOD(int id_u, String fecha) {
        this.fecha = fecha;
        this.token = id_u;
    }
    
    /**
     * 
     * @return token
     */
    public int getToken() {
        return token;
    }

    /**
     * 
     * @param token a setear
     */
    public void setToken(int token) {
        this.token = token;
    }

    /**
     * id de mongo 
     * @return _id
     */

    public String getId() {
        return _id;
    }

    /**
     * id que le genera mongo
     * @param _id a setear
     */
    public void setId(String _id) {
        this._id = _id;
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
     * id del usuario
     * @return id_u
     */
    public int getId_u() {
        return id_u;
    }

    /**
     * id del usuario
     * @param id_u a setear
     */
    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    /**
     * 
     * @return ip
     */
    public String getIp() {
        return ip;
    }
    
    /**
     * 
     * @param ip a setear
     */

    public void setIp(String ip) {
    	
        this.ip = ip;
    }
}
