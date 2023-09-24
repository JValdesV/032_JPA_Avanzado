package cl.empresa.tienda.prueba;

import javax.persistence.EntityManager;

import cl.empresa.tienda.modelo.Categoria;
import cl.empresa.tienda.utils.JPAUtils;

public class RegistroDeCategoria2 {

	public static void main(String[] args) {
		//Aqui en esta clase se trabaja con el ciclo de vida de una entidad en jpa
		//Se crea un objeto de tipo categoria
		Categoria categoria = new Categoria("SEGURIDAD");
		//Se establece una nueva instancia entitymanager mediante la clase auxiliar JPAUtils
		EntityManager em = JPAUtils.getEntityManager();
		//Se inicia el comienzo de transacciones
		em.getTransaction().begin();
		//Se establece la persistencia del objeto categoria
		em.persist(categoria);
		//Se vuelcan los datos en la bd
		em.flush();
		//Se limpia el entity manager
		em.clear();
		//Se busca una categoria ya creada
		//y se reasigna al objeto cateogria
		categoria = em.find(Categoria.class, 1L);
		//Se cambia la propiedad categoria de la clase en cuestion
		categoria.setNombre("CASA");
		//Se aplica un merge desde el objeto categoria que se encontraba desconectado del entity manager
		em.merge(categoria);
		//Se confirma la operacion desde el entitymanager hacia la base de datos con un commit
		em.getTransaction().commit();
		
		
		
		
		
		
	}

}
