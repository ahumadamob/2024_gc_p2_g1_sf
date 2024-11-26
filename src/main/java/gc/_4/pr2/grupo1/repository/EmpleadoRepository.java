package gc._4.pr2.grupo1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gc._4.pr2.grupo1.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

	// Método personalizado para buscar empleados filtrados por su estado "activo".
	List<Empleado> findByActivo(boolean activo);

}