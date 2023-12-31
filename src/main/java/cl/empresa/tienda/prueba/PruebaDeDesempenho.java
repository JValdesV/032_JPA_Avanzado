package cl.empresa.tienda.prueba;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import cl.empresa.tienda.dao.PedidoDAO;
import cl.empresa.tienda.modelo.Pedido;
import cl.empresa.tienda.modelo.Producto;
import cl.empresa.tienda.utils.JPAUtils;

public class PruebaDeDesempenho {

	public static void main(String[] args) {
		
		//Aqui nosotros instaciamos el entitymanager
		EntityManager em = JPAUtils.getEntityManager();
		
		//Aqui buscamos un objeto mediante jpa
		Pedido pedido = em.find(Pedido.class, 1l);
		
		em.close();   //LazyInitializationException al cerrar em 
		
		//Si la fehca del producto no es nulo
		//Presente los datos
		
		//El objetivo es visualizar en la consola la sintaxis sql
		//Para percatarnos si al aplicar la estrategia en los manytone
		//elabora las instrucciones mas simplificadas
		if(pedido.getFecha()!= null) {
			System.out.println("Tiene fecha");
			System.out.println(pedido.getFecha());
		}else {
			System.out.println("No tiene fecha");
		}
		
		//Aqui vamos a ver si la entidad trae mas informacion; porque la estamos solicitando
		System.out.println(pedido.getItems().size());
		
		System.out.println(pedido.getCliente().getNombre());
		
		
		//Observacion al trabajar con atributos que tengan estrategias lazy se generan potenciales problemas de tipo
		//lazy exception, que en el fondo significa que nuestras consultas no pueden ser procesadas por que se 
		//cerro la conexion del entitymanager, asi que el enfoque es aplicar una consulta especifica en la entidad DAO
		//LazyInitializationException
		
		System.out.println(pedido.getCliente().getNombre());   // Aqui salta error por los motivos explicados anteriormente
		
		
		
		
		
	}
	
}
