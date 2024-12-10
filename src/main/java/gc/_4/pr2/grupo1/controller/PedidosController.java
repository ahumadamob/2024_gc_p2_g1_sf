package gc._4.pr2.grupo1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gc._4.pr2.grupo1.dto.ResponseDTO;
import gc._4.pr2.grupo1.entity.Pedidos;
import gc._4.pr2.grupo1.service.IPedidosService;



@RestController
public class PedidosController {
	@Autowired
	private IPedidosService service;
	
	@GetMapping("/pedidos")
	public ResponseDTO <List<Pedidos>> mostrarTodosPedidos(){
		
	 List<Pedidos>ListaPedidos;
	 ListaPedidos= service.mostrarTodos();
	 ResponseDTO <List<Pedidos>> dto;
	 dto=new ResponseDTO <List<Pedidos>>();
	 
	 if(ListaPedidos.isEmpty()){
		 dto.setStatus(false);
		 dto.setMessage("No hay Pedido disponible");
		 dto.setData(null);
		 
	 }else {
		 dto.setStatus(true);
		 dto.setMessage("Se encontraron los Pedido");
		 dto.setData(ListaPedidos);  
	 }
	 return dto;
	}
	
	@GetMapping("/pedidos/{id}")
	public ResponseDTO <Pedidos> mostrarPedidosId(@PathVariable("id") Long id){
		
		//ejemplo de instancia de objeto.
		ResponseDTO <Pedidos> dto;
		//ejemplo de inicializacion de objeto.
		dto=new ResponseDTO <Pedidos>();
		
		if(service.exists(id)) {
			
			Pedidos pedidos= new Pedidos();
			pedidos=service.mostrarPorId(id);
			dto.setStatus(true);
			dto.setMessage("Pedido encontrado");
			dto.setData(pedidos);
		
		}else {
			dto.setStatus(false);
			dto.setMessage("Pedido no encontrado por ID " + id);
			dto.setData(null);
			
		}
		return dto;
	}
	
	@PostMapping("/pedidos")
	public ResponseDTO <Pedidos> crearNuevoPedidos(@RequestBody Pedidos pedidosDesdePostman){
		ResponseDTO<Pedidos> dto=new ResponseDTO<>();
		
		if(service.exists(pedidosDesdePostman.getId())){
			dto.setStatus(false);
			dto.setMessage("Error! El Pedido con "+ pedidosDesdePostman);
			dto.setData(null);
		}else {
			dto.setStatus(true);
			dto.setMessage("Pedido creado exitosamente.");
			dto.setData(service.guardar(pedidosDesdePostman));
		}
		return dto;
	}
	
	@PutMapping("/pedidos")
	public ResponseDTO <Pedidos> actualizarNuevoPedidos(@RequestBody Pedidos pedidosDesdePostman){
		ResponseDTO<Pedidos> dto= new ResponseDTO<>();
		
		if(service.exists(pedidosDesdePostman.getId())) {
			dto.setStatus(true);
			dto.setMessage("Pedido realizado exitosamente. ");
			dto.setData(service.guardar(pedidosDesdePostman));
			return dto;
		}else {
			dto.setStatus(false);
			dto.setMessage("Error! No se puede actualizar el pedido por Id "+ pedidosDesdePostman.getId().toString() + " no existe. ");
			dto.setData(null);
		}
		
		return dto;
	}
	
	@DeleteMapping("/pedidos/{ideliminar}")
	public ResponseDTO<?> borrarPedidos(@PathVariable("ideliminar") Long id){
		ResponseDTO<?> dto = new ResponseDTO<>();
		
		if(service.exists(id)){
			dto.setStatus(true);
			dto.setMessage("Pedido eliminado con exito. ");
			dto.setData(null);
			
			service.eliminarPorId(id);
		}else {
			dto.setStatus(false);
			dto.setMessage("Error! no se encontró Pedido por ID: " + id);
			dto.setData(null);
		}
		
		return dto;
	}
	
	@GetMapping("/pedidos/existe/{id}")
	public ResponseDTO<Pedidos> existePedidos (@PathVariable("id")Long id){
		ResponseDTO<Pedidos> dto= new ResponseDTO<>();
		
		if(service.exists(id)) {
			Pedidos pedidos = new Pedidos();
			dto.setStatus(true);
			dto.setMessage("El pedido con el id: "+ id + " existe.");
			dto.setData(null);
		}else {
			dto.setStatus(false);
			dto.setMessage("El pedido con el id: "+ id + " no existe.");
			dto.setData(null);
		}
		
		return dto;
	}
	
	@PostMapping("/api/pedidos")
	public ResponseDTO <Pedidos> crearPedidos(@RequestBody Pedidos pedidosDesdePostman){
		ResponseDTO<Pedidos> dto=new ResponseDTO<>();
		
		if(service.exists(pedidosDesdePostman.getId())){
			dto.setStatus(false);
			dto.setMessage("La cantidad de productos solicitados debe ser mayor a 0 ,");
			dto.setData(null);
		}else {
			dto.setStatus(true);
			dto.setMessage("Pedido creado exitosamente.");
			dto.setData(service.guardar(pedidosDesdePostman));
		}
		return dto;
	}
		
}
