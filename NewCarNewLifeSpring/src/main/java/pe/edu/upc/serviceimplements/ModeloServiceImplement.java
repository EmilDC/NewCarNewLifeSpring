package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Modelo;
import pe.edu.upc.repositories.IModeloRepository;
import pe.edu.upc.serviceinterfaces.IModeloService;

@Service
public class ModeloServiceImplement implements IModeloService{
	
	@Autowired
	private IModeloRepository mR;
	
	@Override
	public List<Modelo> list() {
		// TODO Auto-generated method stub
		return mR.findAll();
	}
	
	@Override
	public boolean insert(Modelo modelo) {
		// TODO Auto-generated method stub
		Modelo rpta= mR.save(modelo);
		if ( rpta == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Modelo listarId(int idModelo) {
		// TODO Auto-generated method stub
		Optional<Modelo> op = mR.findById(idModelo);
		return op.isPresent() ? op.get() : new Modelo();
	}
	
}
