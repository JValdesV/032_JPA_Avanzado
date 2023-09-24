package cl.empresa.tienda.prueba;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import cl.empresa.tienda.Producto;
import cl.empresa.tienda.dao.CategoriaDAO;
import cl.empresa.tienda.dao.ProductoDAO;
import cl.empresa.tienda.modelo.Categoria;
import cl.empresa.tienda.utils.JPAUtils;

public class RegistroDeCategoria {
	
	public static void main(String[] args) {
		
		//Tenemos que entender que el ciclo de vida un entidad JPA es el que prima
		
		//Aqui el objeto Categoria se encuentra en un estado new o transcient
		Categoria seguridad = new Categoria("SEGURIDAD");
		//Aqui nos aseguramos de tener el EntityMAnager asociado a nuestro contexto de uso
		EntityManager em = JPAUtils.getEntityManager();
		//Aqui solicitamos la obtencion de una transacion e iniciamos las operaciones con nuestro EntityManager
		em.getTransaction().begin();
		//Aqui aplicamos persistencia al objeto creado y lo pasamos al entitymanger en nuestra transacion activa
		em.persist(seguridad);
		//Modificamos el valor de nuestro objeto durante el isntante de la transacion
		seguridad.setNombre("LIBROS");
		//Aqui operamos en el sentido de volcar los datos en nuestra BD
		em.flush();
		//Aqui operamos con el aislamiento de la entidad desde nuestro entitymanager
		//lo que significa que cualquier alteracion del objeto la estamos realizando en memoria
		//y no en nuestra bd
		em.clear();
		//Aqui tomamos una entidad desconectada la cual modificamos y la volvemos a reintegrar en nuestro entitymanager
		//para que los cambios se reflejen en la bd
		seguridad = em.merge(seguridad);
		//Aqui modificamos el atributo nombre de nuestra entidad
		seguridad.setNombre("ARTE");
		//Aqui volcamos los cambios en la bd
		em.flush();
		//Aqui eliminamos la entidad en la bd
		em.remove(seguridad);
		//Aqui volcamos los datos en la bd
		em.flush();
		
		//El contexto de lo anterior es cuando la transaccion se encuentra activa
		
		//em.getTransaction().commit();
		
		
		
	}
	
	
	

}
