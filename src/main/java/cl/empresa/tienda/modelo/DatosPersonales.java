package cl.empresa.tienda.modelo;

import javax.persistence.Embeddable;

//Clase que va ha ser inyectada en la clase Cliente
@Embeddable
public class DatosPersonales {

	private String nombre;
	private String dni;
	
	public DatosPersonales() {
	}
	
	public DatosPersonales(String nombre, String dni) {
		this.nombre = nombre;
		this.dni = dni;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	
}