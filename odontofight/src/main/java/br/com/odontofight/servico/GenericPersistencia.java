package br.com.odontofight.servico;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.odontofight.entidade.EntidadeGenerica;

/**
 * Classe resolve os métodos basicos de cadastro (CRUD) com API da <code>JPA</code>.
 * 
 */
public abstract class GenericPersistencia<T extends EntidadeGenerica, PK extends Number> {

    /**
     * Classe da entidade, necessario para o método <code>EntityManager.find</code>.
     */
    private Class<T> entityClass;

    public GenericPersistencia(Class<T> entityClass) {

        this.entityClass = entityClass;
    }

    public T save(T e) {
        if (e.getId() != null)
            return getEntityManager().merge(e);
        else {
            getEntityManager().persist(e);
            return e;
        }
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(PK id) {
        return getEntityManager().find(entityClass, id);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<T> findRange(int[] range) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public int count() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List findAll(Class entityClass) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * Exige a definição do <code>EntityManager</code> responsável pelas operações de persistencia.
     */
    protected abstract EntityManager getEntityManager();
}
