package cl.empresa.tienda.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import cl.empresa.tienda.Producto;

public class ProductoDAO {
	
	private EntityManager em;
	
	public ProductoDAO(EntityManager em) {
		this.em = em;
	}
	
	public void guardar(Producto producto) {
		this.em.persist(producto);
	}
	
	public Producto consultaPorId(Long id) {
		return this.em.find(Producto.class, id);
	}

	public List<Producto> consultarTodos(){
		//La query que armamos es de acuerdo al nombre del objeto
		//Cuidado con la mayuscula del primer caracter del objeto
		String jpql = "SELECT P FROM Producto AS P";
		return this.em.createQuery(jpql,Producto.class).getResultList();
	}
	
	public List<Producto> consultaPorNombre(String nombre){
		//String jpql = "SELECT P FROM PRODUCTO AS P WHERE P.nombre=:1 AND P.descripcion=:2";
		String jpql = "SELECT P FROM Producto AS P WHERE P.nombre=:nombre";
		//El primer parametro es de la consulta y el segundo parametro es el parametro del metodo
		return em.createQuery(jpql,Producto.class).setParameter("nombre", nombre).getResultList();
	}
	
	public List<Producto> consultaPorNonbreDeCategoria(String nombre){
		String jpql = "SELECT p FROM Producto AS p WHERE p.categoria.nombre=:nombre";
		return em.createQuery(jpql,Producto.class).setParameter("nombre", nombre).getResultList();
	}
	
	public BigDecimal consultarPrecioPorNombreDeProducto(String nombre) {
		String jpql = "SELECT p.precio FROM Producto AS p WHERE p.nombre=:nombre";
		return em.createQuery(jpql,BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
		
		
	}
	
	
	
}
