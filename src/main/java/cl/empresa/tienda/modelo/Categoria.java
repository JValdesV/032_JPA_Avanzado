package cl.empresa.tienda.modelo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categorias")

public class Categoria {
	//Mapeo de llaves compuestas
	@EmbeddedId
	private CategoriaId categoriaId;
	
	public Categoria() {
		
	}
	
	public Categoria(String nombre,String codigo) {
		this.categoriaId = new CategoriaId(nombre, codigo);
	}
	
	public String getNombre() {
		return categoriaId.getNombre();
	}
	public void setNombre(String nombre) {
		this.categoriaId.setNombre(nombre);;
	}
	
}
