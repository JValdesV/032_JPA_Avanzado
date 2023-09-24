package cl.empresa.tienda.dao;

import javax.persistence.EntityManager;

import cl.empresa.tienda.modelo.Categoria;

public class CategoriaDAO {
	
	private EntityManager em;
	
	public CategoriaDAO(EntityManager em) {
		this.em = em;
	}
	
	public void guardar(Categoria categoria) {
		this.em.persist(categoria);
	}
	
	public void actualizar(Categoria categoria) {
		this.em.merge(categoria);
	}
	
	public void remover(Categoria categoria) {
		//Se fusiona una entidad desconectada con una entidad gestiona por entitymanager
		categoria = this.em.merge(categoria);
		this.em.remove(categoria);
	}
	

}
