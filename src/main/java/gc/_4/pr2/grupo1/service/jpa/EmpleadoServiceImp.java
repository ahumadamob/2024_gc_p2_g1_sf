package gc._4.pr2.grupo1.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gc._4.pr2.grupo1.entity.Empleado;
import gc._4.pr2.grupo1.repository.EmpleadoRepository;
import gc._4.pr2.grupo1.service.EmpleadoService;

@Service
public class EmpleadoServiceImp implements EmpleadoService {
	
	@Autowired
	private EmpleadoRepository repo;

	@Override
	public List<Empleado> mostrarTodos() {
		
		return repo.findAll();
	}

	@Override
	public Empleado mostrarPorId(Long id) {
		
		return repo.findById(id).orElse(null);
	}

	@Override
	public Empleado guardar(Empleado empleado) {
		
		return repo.save(empleado);
	}

	@Override
	public void eliminarPorId(Long id) {
		
		repo.deleteById(id);;
	}

}
