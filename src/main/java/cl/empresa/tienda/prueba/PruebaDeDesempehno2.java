package cl.empresa.tienda.prueba;

import javax.persistence.EntityManager;

import cl.empresa.tienda.dao.PedidoDAO;
import cl.empresa.tienda.modelo.Pedido;
import cl.empresa.tienda.modelo.Producto;
import cl.empresa.tienda.utils.JPAUtils;

public class PruebaDeDesempehno2 {

	public static void main(String[] args) {

		//Aqui nosotros instaciamos el entitymanager
		EntityManager em = JPAUtils.getEntityManager();
		
		//Aqui buscamos un objeto mediante jpa
		PedidoDAO pedidoDAO = new PedidoDAO(em);
		Pedido consultarPedidoConCliente = pedidoDAO.consultarPedidoConCliente(1l);
		
		em.close();   //LazyInitializationException al cerrar em 
		
		//Observacion al trabajar con atributos que tengan estrategias lazy se generan potenciales problemas de tipo
		//lazy exception, que en el fondo significa que nuestras consultas no pueden ser procesadas por que se 
		//cerro la conexion del entitymanager, asi que el enfoque es aplicar una consulta especifica en la entidad DAO
		//LazyInitializationException
		
		System.out.println(consultarPedidoConCliente.getCliente().getNombre());   // Aqui salta error por los motivos explicados anteriormente
		
		
	}

}
