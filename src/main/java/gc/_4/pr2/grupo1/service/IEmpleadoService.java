package gc._4.pr2.grupo1.service;

import java.util.List;

import gc._4.pr2.grupo1.entity.Empleado;

public interface IEmpleadoService {
	
	public List<Empleado> mostrarTodos();
	
	public Empleado mostrarPorId(Long id);
	
	public Empleado guardar(Empleado empleado);
	
	public void eliminarPorId(Long id);
	
	public boolean exists(Long id);
	
	// Define un método para obtener empleados filtrados por el estado "activo".
	// El parámetro "activo" es un booleano que indica si se desean empleados activos (true) o inactivos (false).
	List<Empleado> obtenerEmpleadosActivos(boolean activo);
}