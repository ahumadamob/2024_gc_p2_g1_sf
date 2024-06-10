package gc._4.pr2.grupo1.entity;



import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.*;

@Entity
public class Productos {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Productos_id;
	
	private String nombre;
	private String Descripción;
	private int Precio;
	private String Disponibilidad;
	private String categoría;
	private String Ingredientes;
	

	@ManyToMany
	@JoinTable(name = "Productos_Pedidos",	
			joinColumns = {@JoinColumn(name = "Pedidos_id")},
			inverseJoinColumns = {@JoinColumn(name = "Productos_id")})
	private Set<Pedidos> Lista_Pedidos = new HashSet<>();

	
	public Long getProductos_id() {
		return Productos_id;
	}
	public void setProductos_id(Long productos_id) {
		Productos_id = productos_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripción() {
		return Descripción;
	}
	public void setDescripción(String descripción) {
		Descripción = descripción;
	}
	public int getPrecio() {
		return Precio;
	}
	public void setPrecio(int precio) {
		Precio = precio;
	}
	public String getDisponibilidad() {
		return Disponibilidad;
	}
	public void setDisponibilidad(String disponibilidad) {
		Disponibilidad = disponibilidad;
	}
	public String getCategoría() {
		return categoría;
	}
	public void setCategoría(String categoría) {
		this.categoría = categoría;
	}
	public String getIngredientes() {
		return Ingredientes;
	}
	public void setIngredientes(String ingredientes) {
		Ingredientes = ingredientes;
	}
	
	
}
