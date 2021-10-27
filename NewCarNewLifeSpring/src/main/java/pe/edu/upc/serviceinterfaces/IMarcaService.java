package pe.edu.upc.serviceinterfaces;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Marca;

public interface IMarcaService {

	public Integer insert(Marca marca);
	
	List<Marca> list();
	
	public void eliminar(int idMarca);
	
	Optional<Marca> listId(int idMarca);
	
	List<Marca> findByNameMarca(String nombre);
	
}
