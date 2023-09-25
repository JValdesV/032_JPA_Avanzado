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
import javax.persistence.Table;

@Entity
@Table(name="items_pedido")
public class ItemsPedido {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	//@Column(name="nombre") -> Si el nombre del parametro del objeto difiere del atributo de la tabla 
	
	private int cantidad;
	
	private BigDecimal precioUnitario;
	
	@ManyToOne
	private Producto producto;
	
	@ManyToOne
	private Pedido pedido;
	
	public ItemsPedido() {
		
	}

	public ItemsPedido(int cantidad, Producto producto, Pedido pedido) {
		this.cantidad = cantidad;
		this.producto = producto;
		this.pedido = pedido;
		this.precioUnitario = producto.getPrecio();
	}

	public ItemsPedido(Long id, int cantidad, BigDecimal precioUnitario, Producto producto, Pedido pedido) {
		this.id = id;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.producto = producto;
		this.pedido = pedido;
		this.precioUnitario = producto.getPrecio();
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getValor() {
		return this.precioUnitario.multiply(new BigDecimal(this.cantidad));
	}

	
	
	
	
	
	
	
	

}
