package gc._4.pr2.grupo1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gc._4.pr2.grupo1.entity.Pedidos;


public interface PedidosRepository extends JpaRepository<Pedidos, Long> {
	
	 List<Pedidos> findByUrgente(boolean urgente);
	
	

}
