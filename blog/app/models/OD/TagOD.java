/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.OD;

/**
 *
 * @author SCOTT
 */
public class TagOD {
     private int id_t;
     private String nombre;

    /**
     * 
     * @param nombre
     */
    public TagOD(String nombre) {
       
        this.nombre = nombre;
    }

    /**
     * 
     */
    public TagOD() {
	}

    /**
     * id del tag
     * @return id_t
     */
	public int getId_t() {
        return id_t;
    }

	/**
	 * id del tag
	 * @param id_t a setear
	 */

    public void setId_t(int id_t) {
        this.id_t = id_t;
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
}
