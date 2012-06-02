/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.OD;

/**
 *
 * @author SCOTT
 */
public class GustaOD {
    private int id_c;
    private int gusta;
    private int id_u;

    /**
     * 
     * @param id_c
     * @param gusta
     * @param id_u
     */
    public GustaOD(int id_c, int gusta, int id_u) {
        this.id_c = id_c;
        this.gusta = gusta;
        this.id_u = id_u;
    }

    public GustaOD() {
		
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
     * id del usuario
     * @param id_u a setear
     */
    public void setId_u(int id_u) {
        this.id_u = id_u;
    }
}
