package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Marca;
import pe.edu.upc.repositories.IMarcaRepository;
import pe.edu.upc.serviceinterfaces.IMarcaService;

@Service
public class MarcaServiceImplement implements IMarcaService{

	@Autowired
	private IMarcaRepository mR;
	
	@Override
	public Integer insert(Marca marca) {
		// TODO Auto-generated method stub
		int rpta= mR.buscarMarca(marca.getNameMarca());
		if(rpta==0) {
			mR.save(marca);
		}
		return rpta;
	}

	@Override
	public List<Marca> list() {
		// TODO Auto-generated method stub
		return mR.findAll();
	}

	@Override
	public void eliminar(int idMarca) {
		// TODO Auto-generated method stub
		mR.deleteById(idMarca);
	}

	@Override
	public Optional<Marca> listId(int idMarca) {
		// TODO Auto-generated method stub
		return mR.findById(idMarca);
	}

	@Override
	public List<Marca> findByNameMarca(String nombre) {
		// TODO Auto-generated method stub
		return mR.findByNameMarca(nombre);
	}

	
}
