package gc._4.pr2.grupo1.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Pedidos {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Pedidos_id;
	
	private String FechyHoraDePedido;
	private String FechyHoraDeEntrega;
	private String Estado;
	private String TiempoEstimado;


	@ManyToMany(mappedBy = "Lista_Pedidos")
	private Set<Productos>  Lista_Productos = new HashSet<>();


	
	public Long getPedidos_id() {
		return Pedidos_id;
	}
	public void setPedidos_id(Long pedidos_id) {
		Pedidos_id = pedidos_id;
	}
	public String getFechyHoraDePedido() {
		return FechyHoraDePedido;
	}
	public void setFechyHoraDePedido(String fechyHoraDePedido) {
		FechyHoraDePedido = fechyHoraDePedido;
	}
	public String getFechyHoraDeEntrega() {
		return FechyHoraDeEntrega;
	}
	public void setFechyHoraDeEntrega(String fechyHoraDeEntrega) {
		FechyHoraDeEntrega = fechyHoraDeEntrega;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	public String getTiempoEstimado() {
		return TiempoEstimado;
	}
	public void setTiempoEstimado(String tiempoEstimado) {
		TiempoEstimado = tiempoEstimado;
	}



}
