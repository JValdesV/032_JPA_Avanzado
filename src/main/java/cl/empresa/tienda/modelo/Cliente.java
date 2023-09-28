package cl.empresa.tienda.modelo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	//Se inyecta la clase DatosPersonales sim crear una tabla en bd
	@Embedded
	private DatosPersonales datosPersonales;
	
	public String getDni() {
		return datosPersonales.getDni();
	}

	public void setDni(String dni) {
		this.datosPersonales.setDni(dni); 
	}

	public Cliente() {
		
	}
	
	public Cliente(String nombre, String dni) {
		this.datosPersonales = new DatosPersonales(nombre,dni);
	}

	public Long getId() {
		return id;
	}
	
	
	public String getNombre() {
		return datosPersonales.getNombre();
	}
	
	public void setNombre(String nombre) {
		this.datosPersonales.setDni(nombre);
	}
	
}
