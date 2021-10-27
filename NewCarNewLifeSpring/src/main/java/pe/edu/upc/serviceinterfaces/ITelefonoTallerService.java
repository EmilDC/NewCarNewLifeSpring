package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.TelefonoTaller;

public interface ITelefonoTallerService {

	public Integer insert(TelefonoTaller telefonotaller);
	
	List<TelefonoTaller> list();
}
