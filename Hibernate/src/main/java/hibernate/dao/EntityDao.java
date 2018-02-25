package hibernate.dao;


import java.util.List;

import hibernate.model.BaseEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public abstract class EntityDao<T extends BaseEntity>{
	
	protected Session currentSession;
    protected Transaction currentTransaction;

    public EntityDao() {
    }

    protected abstract Class<T> getResponseClass();

    public T persist(T entity) {
        createCurrentSessonWithOpenTransaction();
        currentSession.save(entity);
        closeCurrentSessionCommitingTransactions();
        return entity;
    }

    public void update(T entity) {
        createCurrentSessonWithOpenTransaction();
        currentSession.update(entity);
        closeCurrentSessionCommitingTransactions();
    }

    public void delete(T entity) {
        createCurrentSessonWithOpenTransaction();
        currentSession.delete(entity);
        closeCurrentSessionCommitingTransactions();
    }

    public T findById(long id) {
        createCurrentSessonWithOpenTransaction();
        T entity = currentSession.get(getResponseClass(), id);
        closeCurrentSessionCommitingTransactions();
        return entity;
    }

    public List<T> findAll() {
        createCurrentSessonWithOpenTransaction();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(getResponseClass());
        query.from(getResponseClass());
        List<T> resultList = currentSession.createQuery(query).getResultList();
        closeCurrentSessionCommitingTransactions();
        return resultList;
    }

    protected void () {
        currentTransaction.commit();closeCurrentSessionCommitingTransactions
        currentSession.close();
    }

    protected void createCurrentSessonWithOpenTransaction() {
        currentSession = HibernateSessionFactory.getSessionfactory().openSession();
        currentTransaction = currentSession.beginTransaction();
    }
	
}
