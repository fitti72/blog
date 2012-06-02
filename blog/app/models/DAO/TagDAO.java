/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.DAO;

import java.util.List;

import models.OD.TagOD;

/**
 *
 * @author SCOTT
 */
public interface TagDAO {
    public void insertar(TagOD Tag);
    public TagOD buscar(TagOD Tag);
    public TagOD buscarPorId(TagOD Tag);
    public List<TagOD> listar();
    
}
