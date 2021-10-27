package pe.edu.upc.serviceimplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.TelefonoTaller;
import pe.edu.upc.repositories.ITelefonoTallerRepository;
import pe.edu.upc.serviceinterfaces.ITelefonoTallerService;

@Service
public class TelefonoTallerServiceImplement implements ITelefonoTallerService{

	@Autowired
	private ITelefonoTallerRepository ttR;

	@Override
	public List<TelefonoTaller> list() {
		// TODO Auto-generated method stub
		return ttR.findAll();
	}

	@Override
	public Integer insert(TelefonoTaller telefonotaller) {
		// TODO Auto-generated method stub
		int rpta= ttR.buscarTelefonoTaller(telefonotaller.getTelTaller());
		if(rpta==0) {
			ttR.save(telefonotaller);
		}
		return rpta;
	}

	
}
