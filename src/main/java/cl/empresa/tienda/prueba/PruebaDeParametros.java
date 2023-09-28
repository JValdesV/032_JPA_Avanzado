package cl.empresa.tienda.prueba;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import cl.empresa.tienda.dao.CategoriaDAO;
import cl.empresa.tienda.dao.ProductoDAO;
import cl.empresa.tienda.modelo.Categoria;
import cl.empresa.tienda.modelo.Producto;
import cl.empresa.tienda.utils.JPAUtils;

public class PruebaDeParametros {

	public static void main(String[] args) {
		//cargarProductos();
		
		EntityManager em = new JPAUtils().getEntityManager();
		
		ProductoDAO productoDAO = new ProductoDAO(em);
		//List<Producto> productos = productoDAO.consultarPorParametros("Samsung s7", null, null);
		List<Producto> productos = productoDAO.consultarPorParametrosConAPICriteria("Samsung s7", null, null);
		productos.forEach(x->System.out.println(x));
		
		

	}

	private static void cargarProductos() {
		
		//Creacion de categorias
		Categoria smartphone = new Categoria("SMARTPHONE");
		Categoria consola = new Categoria("CONSOLA");
		Categoria electrodomestico = new Categoria("ELECTRODOMESTICO");
		Categoria electronicos = new Categoria("ELECTRONICOS");
		
		//Creacion de categorias
		
		Producto samsungs7 = new Producto("Samsung s7","Smartphone Samsung",new BigDecimal(1000),smartphone);
		Producto ps5 = new Producto("PS5","Consola Sony",new BigDecimal(1000),consola);
		Producto hervidor = new Producto("Hervidor 1.5lts Ursus Trutter","Calentador de agua",new BigDecimal(1000),electrodomestico);
		Producto audifonos = new Producto("Audifonos con microfono stereo 3.5mm 60cm","Audifonos stereo celular conector jack 3.5mm",new BigDecimal(20),electronicos);
		
		//Se crea una instancia del entitymanager
		EntityManager em = JPAUtils.getEntityManager();
		
		//Se crean las clases dao para interactuar con la base de datos mediante jpa
		ProductoDAO productoDAO = new ProductoDAO(em);
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		
		//Si tenemos excepciones de tipo transient en hibernate es porque no hemos guardados las entidades intermedias
		//Que son las categorias
		//Inicio de transaccion
		em.getTransaction().begin();
		
		//almacenando categorias
		
		categoriaDAO.guardar(smartphone);
		categoriaDAO.guardar(consola);
		categoriaDAO.guardar(electrodomestico);
		categoriaDAO.guardar(electronicos);
		
		//Almacenamiento de productos con las cateogias creadas
		
		productoDAO.guardar(samsungs7);
		productoDAO.guardar(ps5);
		productoDAO.guardar(hervidor);
		productoDAO.guardar(audifonos);
		
		//Termino de transaccion
		em.getTransaction().commit();
		em.close();
		
	}
	
	
	

}
