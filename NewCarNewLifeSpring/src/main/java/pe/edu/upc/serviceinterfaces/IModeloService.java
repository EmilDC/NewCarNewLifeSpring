package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.Modelo;

public interface IModeloService {
	
	public boolean insert(Modelo modelo);
	
	List<Modelo> list();
	
	Modelo listarId(int idModelo);
	
}
