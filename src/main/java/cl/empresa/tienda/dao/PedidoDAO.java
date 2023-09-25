package cl.empresa.tienda.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import cl.empresa.tienda.modelo.Pedido;

public class PedidoDAO {
	
	private EntityManager em;
	
	public PedidoDAO(EntityManager em) {
		this.em = em;
	}
	
	public void guardar(Pedido Pedido) {
		this.em.persist(Pedido);
	}
	
	public Pedido consultaPorId(Long id) {
		return this.em.find(Pedido.class, id);
	}

	public List<Pedido> consultarTodos(){
		//La query que armamos es de acuerdo al nombre del objeto
		//Cuidado con la mayuscula del primer caracter del objeto
		String jpql = "SELECT P FROM Pedido AS P";
		return this.em.createQuery(jpql,Pedido.class).getResultList();
	}
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) from Pedido p";
		return em.createQuery(jpql,BigDecimal.class).getSingleResult();
	}
	
	public Double valorPromedioVendido() {
		String jpql = "SELECT avg(p.valorTotal) from Pedido p";
		return em.createQuery(jpql,Double.class).getSingleResult();
	}
	
	public List<Object[]> relatorioDeVentas(){
		//Consulta creada a partir de las entidades y alias basados en jpa
		//Pedido es la entidad | pedido es el alias | se crea un alias con Pedido.items como item | 
		String jpql = "SELECT producto.nombre, SUM(item.cantidad), MAX(pedido.fecha) FROM Pedido pedido JOIN pedido.items item JOIN item.producto producto GROUP BY producto.nombre ORDER BY item.cantidad DESC";
		return em.createQuery(jpql,Object[].class).getResultList();
	}
	
	
}
