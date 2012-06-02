/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.OD;

import java.util.Date;
import java.util.List;
import models.DAO.MongoDB.UsuarioMongoDB;
import models.DAO.UsuarioDAO;

/**
 *
 * @author SCOTT
 */
public class UsuarioOD {
    
    private int id_u;
    private String objectid;
    private String nombre; 
    private String nombres;
    private String apellido;
    private String apellidos;
    private String email;
    private String nick;
    private String pais;
    private String biografia;
    private String sexo;
    private String fecha;
    private String clave;
    private int    token;
   
    /**
     * 
     */
    public UsuarioOD(){}

    /**
     * 
     * @param nombre
     * @param nombres
     * @param apellido
     * @param apellidos
     * @param email
     * @param nick
     * @param pais
     * @param biografia
     * @param sexo
     * @param fecha
     * @param clave
     */
    public UsuarioOD(String nombre, String nombres, String apellido, String apellidos, String email, String nick, String pais, String biografia, String sexo,String fecha, String clave) {
        this.objectid = "";
        this.nombre = nombre;
        this.nombres = nombres;
        this.apellido = apellido;
        this.apellidos = apellidos;
        this.email = email;
        this.nick = nick;
        this.pais = pais;
        this.biografia = biografia;
        this.sexo = sexo;
        this.fecha = fecha;
        this.clave = clave;
    }
    
    /**
     * 
     * @param id_u
     * @param nombre
     * @param nombres
     * @param apellido
     * @param apellidos
     * @param email
     * @param nick
     * @param pais
     * @param biografia
     * @param sexo
     * @param fecha
     * @param clave
     */
    
    
    public UsuarioOD(int id_u, String nombre, String nombres, String apellido, String apellidos, String email, String nick, String pais, String biografia, String sexo,String fecha, String clave) {
        this.objectid = "";
        this.id_u = id_u;
        this.nombre = nombre;
        this.nombres = nombres;
        this.apellido = apellido;
        this.apellidos = apellidos;
        this.email = email;
        this.nick = nick;
        this.pais = pais;
        this.biografia = biografia;
        this.sexo = sexo;
        this.fecha = fecha;
        this.clave = clave;
    }

    
    /**
     * 
     * @param id_u
     * @param nombre
     * @param nombres
     * @param apellido
     * @param apellidos
     * @param email
     * @param nick
     * @param pais
     * @param biografia
     * @param sexo
     * @param fecha
     * @param clave
     * @param token
     */
    public UsuarioOD(int id_u, String nombre, String nombres, String apellido, String apellidos, String email, String nick, String pais, String biografia, String sexo,String fecha, String clave, int token) {
        this.objectid = "";
        this.id_u = id_u;
        this.nombre = nombre;
        this.nombres = nombres;
        this.apellido = apellido;
        this.apellidos = apellidos;
        this.email = email;
        this.nick = nick;
        this.pais = pais;
        this.biografia = biografia;
        this.sexo = sexo;
        this.fecha = fecha;
        this.clave = clave;
        this.token = token;
    }
    /**
     * consturctors que se usa para validar el usuario
     * @param nick
     * @param clave
     */
     public UsuarioOD(String nick, String clave)
     {
     this.nick = nick;
     this.clave = clave;
     }
    /**
     * 
     * @return objectid
     */
    public String getObjectid() {
        return objectid;
    }
    /**
     * 
     * @param objectid a setear
     */

    public void setObjectid(String objectid) {
        this.objectid = objectid;
    }
	/**
	 * 
	 * @return clave
	 */
    public String getClave() {
        return clave;
    }
	/**
	 * 
	 * @param clave a setear
	 */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * 
     * @return apellido 
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * 
     * @param apellido a setear
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * 
     * @return segundo apellido
     */
    public String getApellidos() {
        return apellidos;
    }

   /**
    * 
    * @param segundo apellido a setear
    */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * 
     * @return biografia
     */
    public String getBiografia() {
        return biografia;
    }

    /**
     * 
     * @param biografia a setear
     */
    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    /**
     * 
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email a setear
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return nick
     */
    public String getNick() {
        return nick;
    }

    /**
     * 
     * @param nick a setear
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * 
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @param nombre a setear
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     * @return segudno nombre
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * 
     * @param nombres segundo nombre a setear
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * 
     * @return pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * 
     * @param pais a setear
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * 
     * @return sexo
     */
    public String getSexo() {
        return sexo;
    }

	/**
	 * 
	 * @param sexo a setear
	 */
    public void setSexo(String sexo) {
        this.sexo = sexo;
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
       
}
