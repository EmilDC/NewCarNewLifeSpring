package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.Vehiculo;

public interface IVehiculoService {
	
	public boolean insert(Vehiculo vehiculo);
	
	List<Vehiculo> list();
	
	Vehiculo listarId(int idVehiculo);
	
}
