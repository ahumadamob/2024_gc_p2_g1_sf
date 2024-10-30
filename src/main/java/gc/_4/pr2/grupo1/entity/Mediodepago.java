package gc._4.pr2.grupo1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Mediodepago {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	//Ejemplo de encapsulamiento: nombre es private  y no se puede acceder directamente
	private String descripción;
	private int comisiones;
	private String disponibilidad;
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	// ejemplo del metodo getter del atributo encapsulado
	public String getDescripción() {
		return descripción;
	}
	// ejemplo del metodo setter del atributo encapsulado
	public void setDescripción(String descripción) {
		this.descripción = descripción;
	}
	public int getComisiones() {
		return comisiones;
	}
	public void setComisiones(int comisiones) {
		this.comisiones = comisiones;
	}
	public String getDisponibilidad() {
		return disponibilidad;
	}
	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}