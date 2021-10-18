package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.Fakultas;

public class FakultasJpaController implements Serializable {

    public FakultasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fakultas fakultas) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(fakultas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFakultas(fakultas.getKode()) != null) {
                throw new PreexistingEntityException("Fakultas " + fakultas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fakultas fakultas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            fakultas = em.merge(fakultas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = fakultas.getKode();
                if (findFakultas(id) == null) {
                    throw new NonexistentEntityException("The fakultas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fakultas fakultas;
            try {
                fakultas = em.getReference(Fakultas.class, id);
                fakultas.getKode();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fakultas with id " + id + " no longer exists.", enfe);
            }
            em.remove(fakultas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fakultas> findFakultasEntities() {
        return findFakultasEntities(true, -1, -1);
    }

    public List<Fakultas> findFakultasEntities(int maxResults, int firstResult) {
        return findFakultasEntities(false, maxResults, firstResult);
    }

    private List<Fakultas> findFakultasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fakultas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Fakultas findFakultas(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fakultas.class, id);
        } finally {
            em.close();
        }
    }

    public int getFakultasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Fakultas> rt = cq.from(Fakultas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
