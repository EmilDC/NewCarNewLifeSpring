package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.EstadoTaller;

public interface IEstadoTallerService {

	public Integer insert(EstadoTaller estadotaller);
	
	List<EstadoTaller> list();
}
