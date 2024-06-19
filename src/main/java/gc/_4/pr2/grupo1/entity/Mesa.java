package gc._4.pr2.grupo1.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Mesa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private int capacidad;
	private boolean estado;
	
	@ManyToMany
	@JoinTable (
			name = "Mesa_Pedidos",
			joinColumns = @JoinColumn(name = "Mesa_Id"),
			inverseJoinColumns = @JoinColumn(name = "Pedidos_Id")		
			)
	private Set<Pedidos> pedidos = new HashSet<>(); 
	
	
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	

}
