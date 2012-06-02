package models.DAO;

import models.OD.FotoOD;
import models.OD.UsuarioOD;

public interface FotoDAO {
	public void insertar(FotoOD Foto);
	public void eliminar(FotoOD Token);
	public FotoOD buscar(FotoOD Token);
	public FotoOD buscarPorUsuario(UsuarioOD Usuario);
	public void modificar(FotoOD Nuevo);

}
