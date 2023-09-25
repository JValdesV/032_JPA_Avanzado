package cl.empresa.tienda.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import cl.empresa.tienda.modelo.Cliente;

public class ClienteDAO {
	private EntityManager em;
	
	public ClienteDAO(EntityManager em) {
		this.em = em;
	}
	
	public void guardar(Cliente Cliente) {
		this.em.persist(Cliente);
	}
	
	public Cliente consultaPorId(Long id) {
		return this.em.find(Cliente.class, id);
	}

	public List<Cliente> consultarTodos(){
		//La query que armamos es de acuerdo al nombre del objeto
		//Cuidado con la mayuscula del primer caracter del objeto
		String jpql = "SELECT P FROM Cliente AS P";
		return this.em.createQuery(jpql,Cliente.class).getResultList();
	}
	
	public List<Cliente> consultaPorNombre(String nombre){
		//String jpql = "SELECT P FROM Cliente AS P WHERE P.nombre=:1 AND P.descripcion=:2";
		String jpql = "SELECT P FROM Cliente AS P WHERE P.nombre=:nombre";
		//El primer parametro es de la consulta y el segundo parametro es el parametro del metodo
		return em.createQuery(jpql,Cliente.class).setParameter("nombre", nombre).getResultList();
	}
	
	public List<Cliente> consultaPorNonbreDeCategoria(String nombre){
		String jpql = "SELECT p FROM Cliente AS p WHERE p.categoria.nombre=:nombre";
		return em.createQuery(jpql,Cliente.class).setParameter("nombre", nombre).getResultList();
	}
	
	public BigDecimal consultarPrecioPorNombreDeCliente(String nombre) {
		String jpql = "SELECT p.precio FROM Cliente AS p WHERE p.nombre=:nombre";
		return em.createQuery(jpql,BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
		
		
	}
}
