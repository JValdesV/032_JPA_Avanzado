package cl.empresa.tienda.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cl.empresa.tienda.modelo.Producto;

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
	
	//Esta cosnulta se realiza mediante jpql
	//public BigDecimal consultarPrecioPorNombreDeProducto(String nombre) {
	//	String jpql = "SELECT p.precio FROM Producto AS p WHERE p.nombre=:nombre";
	//	return em.createQuery(jpql,BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
	//}
	
	public BigDecimal consultarPrecioPorNombreDeProducto(String nombre) {
		return em.createNamedQuery("Producto.consultaDePrecio", BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
	}
	
	public List<Producto> consultarPorParametros(String nombre, BigDecimal precio, LocalDate fecha){
		StringBuilder jpql = new StringBuilder("SELECT p FROM Producto p WHERE 1=1 ");
			//Construimos la solicitud de parametros dinamica primeramente en jpql
			if(nombre!=null && !nombre.trim().isEmpty()) {
				jpql.append("AND p.nombre=:nombre ");
			}
			if(precio!=null && !precio.equals(new BigDecimal(0))) {
				jpql.append("AND p.precio=:precio ");
			}
			if(fecha!=null) {
				jpql.append("AND p.fechaDeRegistro=:fecha");
			}
			//Interfaz para realizar consultas tipidas en jpa por lo tanto la consulta debe devolver un objeto de tipo Producto
			TypedQuery<Producto> query = em.createQuery(jpql.toString(),Producto.class);
			
			//Creamos la parametrizacion a nivel de objeto
			if(nombre!=null && !nombre.trim().isEmpty()) {
				query.setParameter("nombre", nombre);
			}
			if(precio!=null && !precio.equals(new BigDecimal(0))) {
				query.setParameter("precio", precio);
			}
			if(fecha!=null) {
				query.setParameter("fechaDeRegistro", fecha);
			}
			
			return query.getResultList();
		
	}
	
	public List<Producto> consultarPorParametrosConAPICriteria(String nombre, BigDecimal precio, LocalDate fecha){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Producto> query = builder.createQuery(Producto.class);
		Root<Producto> from = query.from(Producto.class);
		
		Predicate filtro = builder.and();
		if(nombre!=null && !nombre.trim().isEmpty()) {
			filtro=builder.and(filtro,builder.equal(from.get("nombre"), nombre));
		}
		if(precio!=null && !precio.equals(new BigDecimal(0))) {
			filtro=builder.and(filtro,builder.equal(from.get("precio"), precio));
		}
		if(fecha!=null) {
			filtro=builder.and(filtro,builder.equal(from.get("fechaDeRegistro"), fecha));
		}
		
		query = query.where(filtro);
		return em.createQuery(query).getResultList();
		
		
	}
	
	
	
	
	
}
