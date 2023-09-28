package cl.empresa.tienda.modelo;

import javax.persistence.Entity;

@Entity

public class Libros extends Producto {
	
	private String autor;
	private int pagina;
	
	
	public Libros() {
		
	}
	
	public Libros(String autor, int pagina) {
		super();
		this.autor = autor;
		this.pagina = pagina;
	}
	
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getPagina() {
		return pagina;
	}
	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
	
	

}
