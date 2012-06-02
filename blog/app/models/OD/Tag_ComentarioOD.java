/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.OD;

/**
 *
 * @author SCOTT
 */
public class Tag_ComentarioOD {
    
    private int id_t;
    private int id_c;

    /**
     * 
     * @param id_c
     * @param id_t
     */
    public Tag_ComentarioOD(int id_c, int id_t) {
        this.id_t = id_t;
        this.id_c = id_c;
    }

    /**
     * 
     */
    public Tag_ComentarioOD() {
		// TODO Auto-generated constructor stub
	}

    /**
     * 
     * @return id_c
     */
	public int getId_c() {
        return id_c;
    }

	/**
	 * 
	 * @param id_c a setear
	 */
    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    /**
     * id del tags
     * @return id_t
     */
    public int getId_t() {
        return id_t;
    }

    /**
     * id del tags
     * @param id_t a setear
     */
    public void setId_t(int id_t) {
        this.id_t = id_t;
    }
    
    
    
    
}
