package cl.empresa.tienda.modelo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

//Estamos estableciendo de que esta entidad va a ser un objeto y no una tabla
//Y al ser serializable puede ser guardada en otros soportes tales como archivo, red, bd y se convierte en una secuencia de bytes.
@Embeddable
public class CategoriaId implements Serializable{

	private String nombre;
	private String codigo;
	
	public CategoriaId() {
		
	}

	public CategoriaId(String nombre, String codigo) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoriaId other = (CategoriaId) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(nombre, other.nombre);
	}

	
	
	
	
}
