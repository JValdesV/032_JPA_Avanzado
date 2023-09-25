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
	
	public List<Pedido> consultaPorNombre(String nombre){
		//String jpql = "SELECT P FROM Pedido AS P WHERE P.nombre=:1 AND P.descripcion=:2";
		String jpql = "SELECT P FROM Pedido AS P WHERE P.nombre=:nombre";
		//El primer parametro es de la consulta y el segundo parametro es el parametro del metodo
		return em.createQuery(jpql,Pedido.class).setParameter("nombre", nombre).getResultList();
	}
	
	public List<Pedido> consultaPorNonbreDeCategoria(String nombre){
		String jpql = "SELECT p FROM Pedido AS p WHERE p.categoria.nombre=:nombre";
		return em.createQuery(jpql,Pedido.class).setParameter("nombre", nombre).getResultList();
	}
	
	public BigDecimal consultarPrecioPorNombreDePedido(String nombre) {
		String jpql = "SELECT p.precio FROM Pedido AS p WHERE p.nombre=:nombre";
		return em.createQuery(jpql,BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
		
		
	}
}
