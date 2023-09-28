package cl.empresa.tienda.prueba;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import cl.empresa.tienda.dao.CategoriaDAO;
import cl.empresa.tienda.dao.ProductoDAO;
import cl.empresa.tienda.modelo.Categoria;
import cl.empresa.tienda.modelo.Producto;
import cl.empresa.tienda.utils.JPAUtils;

public class PruebaDeRegistroProducto3 {

	public static void main(String[] args) {
		
		//La logica de la transaccion para trabajar con llaves compuestas es un poco más dificil de enteder a primeras
		//pero hay que llevar a cabo cierta logica
		
		//Se define el entitymanager y las clases dao con las que se requiere trabajar
		EntityManager em = JPAUtils.getEntityManager();
		ProductoDAO productoDAO = new ProductoDAO(em);
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		
		//Se inicia la transaccion		
		em.getTransaction().begin();	
		
		//Se crea la cateogia a nivel de objeto y despues se trasnfiere al dao
		//Si no creamos la categoria antes del producto hay excepciones de tipo transiest porque estamos instanciando
		//un objeto que no esxiste en la bd
		Categoria categoria = new Categoria("MASCOTAS", "50-230");
		categoriaDAO.guardar(categoria);
		
		//Teniendo nuestra categoria en bd podemos crear una instancia nueva con los datos anteriores
		//y se los facilitamos a la entidad producto
		
		Producto producto = new Producto("Comida Gato 10KG", "Comida Gato", new BigDecimal(24990), new Categoria("MASCOTAS", "50-230"));
		productoDAO.guardar(producto);
		//Si se requiere la busqueda de la categoria con llave compuesta
		//Categoria categoria = em.find(Categoria.class, new Categoria("MASCOTAS", "50-230"));		
		
		//Se confirma la trnasaccion
		//esto queire decir que los datos en soporte de objetos son almacenados por jpa en bd
		em.getTransaction().commit();
		//Se cierra la transaccion y todos los recursos soslicitados
		em.close();
		
		//Si aparecen errores en las otras clases de pruebas es por la modificacion del objeto categoria a utilizar llaves
		//compuestas
		
		
	}
	
}
