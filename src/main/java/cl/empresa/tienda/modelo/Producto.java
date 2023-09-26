package cl.empresa.tienda.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="productos")
@NamedQuery(name="Producto.consultaDePrecio", query="SELECT p.precio FROM Producto AS p WHERE p.nombre=:nombre")
public class Producto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	//@Column(name="nombre") -> Si el nombre del parametro del objeto difiere del atributo de la tabla 
	
	private String nombre;
	
	private String descripcion;
	
	private BigDecimal precio;
	
	private LocalDate fechaDeRegistro = LocalDate.now();
	
	@ManyToOne
	private Categoria categoria;
	
	public Producto() {
		
	}
	
	public Producto(String nombre, String descripcion, BigDecimal precio, Categoria categoria) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.categoria = categoria;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public BigDecimal getPrecio() {
		return precio;
	}


	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}


	public LocalDate getFechaDeRegistro() {
		return fechaDeRegistro;
	}


	public void setFechaDeRegistro(LocalDate fechaDeRegistro) {
		this.fechaDeRegistro = fechaDeRegistro;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
	
	
	
	

}
