package gc._4.pr2.grupo1.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gc._4.pr2.grupo1.entity.Factura;
import gc._4.pr2.grupo1.repository.FacturaRepository;
import gc._4.pr2.grupo1.service.FacturaService;


@Service
public class FacturaServiceImp implements FacturaService {

	@Autowired
	private FacturaRepository repo;

	@Override
	public List<Factura>mostrarTodos() {
		
		return repo.findAll();
	}

	@Override
	public Factura mostrarPorId(Long id) {
	
		return repo.findById(id).orElse(null);
	}

	@Override
	public Factura guardar(Factura factura) {
		
		return repo.save(factura);
	}

	@Override
	public void eliminarPorId(Long id) {
		repo.deleteById(id);
		
	}

	
	
}
