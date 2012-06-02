/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.DAO;

import models.OD.AdjuntoOD;

/**
 *
 * @author SCOTT
 */
public interface AdjuntoDAO {
	public AdjuntoOD buscar(AdjuntoOD Token);
	public void insertar(AdjuntoOD Foto);
	public void eliminar(AdjuntoOD Token);
	public void insertarModificar(AdjuntoOD Usuario);
	public void eliminarModificar(AdjuntoOD Usuario);
	public void modificar(AdjuntoOD Nuevo) ;
    
}
