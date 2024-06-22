package gc._4.pr2.grupo1.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Pedidos {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String FechyHoraDePedido;
	private String FechyHoraDeEntrega;
	private String Estado;
	private String TiempoEstimado;
	@OneToOne(mappedBy="pedidos")
	private Factura factura;
	
	@ManyToMany(mappedBy = "pedidos")
	private Set<Mesa> mesas = new HashSet<>();


	@ManyToMany(mappedBy = "Lista_Pedidos")
	private Set<Productos>  Lista_Productos = new HashSet<>();


    @ManyToOne
    private Empleado empleado;
	private String ListDeProductos;
	
	private String ListDeProductos;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getListDeProductos() {
		return ListDeProductos;
	}
	public void setListDeProductos(String listDeProductos) {
		ListDeProductos = listDeProductos;
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
	
	public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

}
