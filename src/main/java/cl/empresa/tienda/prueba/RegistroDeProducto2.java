package cl.empresa.tienda.prueba;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import cl.empresa.tienda.dao.CategoriaDAO;
import cl.empresa.tienda.dao.ClienteDAO;
import cl.empresa.tienda.dao.PedidoDAO;
import cl.empresa.tienda.dao.ProductoDAO;
import cl.empresa.tienda.modelo.Categoria;
import cl.empresa.tienda.modelo.Cliente;
import cl.empresa.tienda.modelo.ItemsPedido;
import cl.empresa.tienda.modelo.Pedido;
import cl.empresa.tienda.modelo.Producto;
import cl.empresa.tienda.utils.JPAUtils;
import cl.empresa.tienda.vo.RelatorioDeVenta;

public class RegistroDeProducto2 {

	public static void main(String[] args) {
		registrarProducto();
		//consultarEspecifico();
		//consultarTodos();
		//consultarPorNombre();
		//consultaPorNombreDeCategoria();
		//consultaDePrecioPorNombreProducto();
		
		EntityManager em = JPAUtils.getEntityManager();
		
		ProductoDAO productoDAO = new ProductoDAO(em);
		Producto producto = productoDAO.consultaPorId(1l);
		
		PedidoDAO pedidoDAO = new PedidoDAO(em);
		
		ClienteDAO clienteDAO = new ClienteDAO(em);
		Cliente cliente = new Cliente("Juan", "12300500-6");
		
		Pedido pedido = new Pedido(cliente);
		pedido.agregarItems(new ItemsPedido(5,producto,pedido));
		
		em.getTransaction().begin();
		
		clienteDAO.guardar(cliente);
		pedidoDAO.guardar(pedido);
		
		em.getTransaction().commit();
		
		BigDecimal valorTotal = pedidoDAO.valorTotalVendido();
		System.out.println("Valor total: "+valorTotal);
		
		//Alternativa generica 
		
		//Se crea una lista de objetos de tipo array llamada relatorio para crear un informe acerca de las ventas
		//List<Object[]> relatorio = pedidoDAO.relatorioDeVentas();
		//Se secuencia la lista con la finalidad de iterar la lista y sacar los elementos 
		/*
		for(Object[] elemento: relatorio) {
			System.out.println(elemento[0]);
			System.out.println(elemento[1]);
			System.out.println(elemento[2]);
		}
		*/
		//Alternativa con ValueObject
		List<RelatorioDeVenta> relatorioVO = pedidoDAO.relatorioDeVentasVO();
		relatorioVO.forEach(x->System.out.println(x.toString()));
		
	}

	private static void consultaDePrecioPorNombreProducto() {
		//Resumen importar entitymanager 
		//Crear DAO -> asociar dao jpautils
		//crear objeto -> dao.consulta()
		//iterar resultado

		EntityManager em = JPAUtils.getEntityManager();
		ProductoDAO productoDAO = new ProductoDAO(em);
		BigDecimal precioProducto = productoDAO.consultarPrecioPorNombreDeProducto("Samsung");
		System.out.println(precioProducto);
	}

	private static void consultaPorNombreDeCategoria() {
		EntityManager em = JPAUtils.getEntityManager();
		ProductoDAO productoDAO = new ProductoDAO(em);
		List<Producto> productos = productoDAO.consultaPorNonbreDeCategoria("CELULARES");
		productos.forEach(producto->System.out.println(producto.getDescripcion()));
	}

	private static void consultarPorNombre() {
		EntityManager em = JPAUtils.getEntityManager();
		ProductoDAO productoDAO = new ProductoDAO(em);
		List<Producto> productos = productoDAO.consultaPorNombre("Samsung");
		productos.forEach(producto->System.out.println("Lambda: "+producto.getNombre()));
	}

	private static void consultarTodos() {
		EntityManager em = JPAUtils.getEntityManager();
		ProductoDAO productoDAO = new ProductoDAO(em);
		
		List<Producto> productos = productoDAO.consultarTodos();
		productos.forEach(producto->System.out.println(producto.getNombre()));
	}

	private static void consultarEspecifico() {
		EntityManager em = JPAUtils.getEntityManager();
		ProductoDAO productoDAO = new ProductoDAO(em);
		Producto producto = productoDAO.consultaPorId(1L);
		System.out.println(producto.getNombre());
	}

	private static void registrarProducto() {
		//Creamos una nueva categoria asociada a nuestra tabla categoria
		Categoria celulares = new Categoria("CELULARES");
		
		//Creamos un objeto de tipo producto nombrado como celular
		//Asociamos a nuestro objeto celular su respectiva categoria
		Producto celular = new Producto("Samsung","telefono usado",new BigDecimal(1000),celulares);
		//celular.setNombre("Samsung");
		//celular.setDescripcion("Telefono Usado");
		//celular.setPrecio(new BigDecimal("1000"));
		
		//EStablecemos las configuraciones del Entity para usar el recurso en el archivo xml 
		//EntityManagerFactory factory = Persistence.createEntityManagerFactory("tienda");
		//Establecemos el entity manger pasandole la unidad de persistencia
		EntityManager em = JPAUtils.getEntityManager();
		
		//Incorporamos los Objetos para la transaccion a ejecutar
		//delegandolos a las clases que interactuan con el acceso a la base de datos
		//y se les envia por parametro al constructor su EntityMAnager que actua como unidad de persistencia JPA
		ProductoDAO productoDAO = new ProductoDAO(em);		
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		
		
		//Obtenemos las transacciones y comenzamos a operar
		em.getTransaction().begin();
		
		//Guardamos el objeto celular creando anteriomente
		//em.persist(celular);
		categoriaDAO.guardar(celulares);
		productoDAO.guardar(celular);
		
		
		//Enviamos los valores hacia la base de datos
		em.getTransaction().commit();
		
		//Cerramos la transaccion
		em.close();
	}

}
