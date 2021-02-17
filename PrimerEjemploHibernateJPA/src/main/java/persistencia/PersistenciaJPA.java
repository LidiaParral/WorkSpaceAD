package persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.NonUniqueResultException;

import model.Autor;
import model.Libro;

public class PersistenciaJPA {
	
	EntityManagerFactory emf;
	
	public PersistenciaJPA() {
		emf = Persistence.createEntityManagerFactory("PrimerEjemploHibernateJPA");
	}
		
	public long consultarIdLibro() {
		EntityManager em = emf.createEntityManager();
		long id = 0;
				
		try {
			Query q = em.createQuery("from Libro l order by l.id desc");
			if (q.getResultList().size() > 0) {
				id = ((Libro) q.getResultList().get(0)).getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return id;
	}
	
	public long consultarIdAutor() {
		EntityManager em = emf.createEntityManager();
		long id = 0;
		
		try {
			Query q = em.createQuery("from Autor a order by a.id desc");
			if (q.getResultList().size() > 0) {
				id = ((Autor) q.getResultList().get(0)).getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return id;
	}
	
	private long consultarIdAutor(EntityManager em) {
		long id = 0;
		
		try {
			Query q = em.createQuery("from Autor a order by a.id desc");
			if (q.getResultList().size() > 0) {
				id = ((Autor) q.getResultList().get(0)).getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return id;
	}

	public String insertarLibro(Libro libro) {
		EntityManager em = emf.createEntityManager();
		Autor autor;
		String res = "";
		
		try {
			
			autor = comprobarExisteAutor(em, libro.getAutor());
				
			em.getTransaction().begin();
				
			if (autor != null) {
				//libro.setAutor(autor);
				autor.addLibro(libro);
				
			} else {
				//libro.getAutor().addLibro(libro);
				long idA = consultarIdAutor(em);
				libro.getAutor().setId(idA+1);
				em.persist(libro.getAutor());
				
			}
			em.persist(libro);	
			res = "\\nLa inserción se ha realizado con éxito";
			
			em.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			res = "\\nLa inserción no se ha podido realizar";
		} finally {
			em.close();
		}
		
		return res;
	}
	
	/*
	public String insertarLibro(Libro libro) {
		EntityManager em = emf.createEntityManager();
		Autor autor;
		String res = "";
		
		try {
			if (!comprobarExiteLibro(em, libro)) {
				// obtener id del libro
				long idL = consultarIdLibro();
				libro.setId(idL + 1);
				autor = comprobarExisteAutor(em, libro.getAutor());
				
				em.getTransaction().begin();
				
				if (autor != null && autor.getId() != 0) {
					//libro.setAutor(autor);
					autor.addLibro(libro);
					
				} else {
					long idA = consultarIdAutor();
					libro.getAutor().setId(idA+1);
					libro.getAutor().addLibro(libro);
					em.persist(libro.getAutor());
					
				}
				em.persist(libro);	
				res = "\\nLa inserción se ha realizado con éxito";
				
				em.getTransaction().commit();
			} else {
				res = "\nYa existe el libro indicado";
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = "\\nLa inserción no se ha podido realizar";
		} finally {
			em.close();
		}
		
		return res;
	}
	 */
	
	private Autor comprobarExisteAutor(EntityManager em, Autor autor) throws Exception {
		
		Query q = em.createQuery("from Autor a where a.nombre = :n and a.anioNac = :i");
		q.setParameter("n", autor.getNombre());
		q.setParameter("i", autor.getAnioNac());
		
		try { 
			autor = (Autor) q.getSingleResult();
		} catch (Exception ex) {
			autor = null;
		}
		/*catch (NoResultException ex) {
			ex.printStackTrace();
			//System.out.println("No hay ningún autor con esos datos");
			autor = null;
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
			//System.out.println("Hay varios autores con esos datos");
			autor = null;
		}*/
		
		return autor;
	}
	
	private boolean comprobarExiteLibro(EntityManager em, Libro libro) throws Exception {
		Query q = em.createQuery("from Libro l where l.titulo = :t and l.isbn = :i");
		q.setParameter("t", libro.getTitulo());
		q.setParameter("i", libro.getIsbn());
		ArrayList<Libro> listaLibros = (ArrayList<Libro>) q.getResultList();
		
		if (listaLibros.size() > 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean comprobarExiteLibro(Libro libro) throws Exception {
		EntityManager em = emf.createEntityManager();
		boolean existe = false;
		
		Query q = em.createQuery("from Libro l where l.titulo = :t and l.isbn = :i");
		q.setParameter("t", libro.getTitulo());
		q.setParameter("i", libro.getIsbn());
		ArrayList<Libro> listaLibros = (ArrayList<Libro>) q.getResultList();
		
		if (listaLibros.size() > 0) {
			existe = true;
		} 

		em.close();
		
		return existe;
		
	}
	
	public ArrayList<Libro> consultarLibros() {
		EntityManager em = emf.createEntityManager();
		ArrayList<Libro> listaLibros = new ArrayList<Libro>();
		try {
			Query q = em.createQuery("from Libro l");
			listaLibros = (ArrayList<Libro>) q.getResultList();
			
			for (Libro libro : listaLibros) {
				libro.getAutor().getNombre();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return listaLibros;
	}
	
	public ArrayList<Autor> consultarAutores() {
		EntityManager em = emf.createEntityManager();
		ArrayList<Autor> listaAutoresQ = new ArrayList<Autor>();
		ArrayList<Autor> listaAutores = new ArrayList<Autor>();
		//Autor autor;
		try {
			Query q = em.createQuery("from Autor a", Autor.class);
			listaAutores = (ArrayList<Autor>) q.getResultList();
			/*listaAutoresQ = (ArrayList<Autor>) q.getResultList();
			
			for (Autor autor : listaAutoresQ) {
				autor = em.find(Autor.class, autor.getId());
				autor.getLibros().size();
				listaAutores.add(autor);
			}*/
			
			List<Libro> listaLibros;
			for (Autor autor : listaAutores) {
				listaLibros = autor.getLibros();
				listaLibros.size();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return listaAutores;
	}

	public String borrarLibro(long id) {
		EntityManager em = emf.createEntityManager();
		String res = "";
		Libro libro;
		try {
			libro = em.find(Libro.class, id);
			
			if (libro != null) {
				em.getTransaction().begin();
				em.remove(libro);	
				em.getTransaction().commit();
				res = "Se ha borrado el libro con éxito";
			} else {
				res = "No existe el libro indicado";
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = "No se ha podido borrar el libro";
		} finally {
			em.close();
		}
		
		return res;
	}
	
	public String modificarLibro(long id, Libro libroModif) {
		EntityManager em = emf.createEntityManager();
		String res = "";
		Libro libro;
		try {
			libro = em.find(Libro.class, id);
		
			if (libro != null) {
				libro.setTitulo(libroModif.getTitulo());
				libro.setIsbn(libroModif.getIsbn());
				libro.setCategoria(libroModif.getCategoria());
				libro.setAnioPublic(libroModif.getAnioPublic());
				
				em.getTransaction().begin();
				em.merge(libro);	
				em.getTransaction().commit();
				res = "Se ha modificado el libro con éxito";
			} else {
				res = "No existe el libro indicado";
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = "No se ha podido modificar el libro";
		} finally {
			em.close();
		}
		
		return res;
	}

	public Libro consultarLibroId(long id) {
		EntityManager em = emf.createEntityManager();
		
		Libro libro = em.find(Libro.class, id);
		
		em.close();
		
		return libro;
	}
	
}
