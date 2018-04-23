package com.testing.university.dao.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import com.testing.university.dao.GenericDao;
import com.testing.university.util.JPAHibernateUtil;

public abstract class GenericDaoImpl <T, ID> implements GenericDao<T, ID>{

    private EntityManager entityManager;
    protected final Class<T> entityClass;
    
    public GenericDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    public EntityManager getEntityManager() {
        return entityManager;
    }


    public EntityManager createEmWithTransaction(){
       
        try{
            entityManager = JPAHibernateUtil.getEntityManagerFactory().createEntityManager();
            entityManager.getTransaction().begin();
        } catch (Exception e) {
            System.out.println("Problem occured while creating entityManager and starting a transaction");            
            System.out.println(e.getMessage());
        }
        return entityManager;
    }
    
    public void closeEmWithTransaction(){
        try{
            getEntityManager().getTransaction().commit();   
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager != null) {
               System.out.println("Transaction is being rolled back.");
               entityManager.getTransaction().rollback();
            }
         } finally {
            if (entityManager != null) {
               entityManager.close();
            }
         }
    }
    
    @Override
    public Set<T> findAll() {
        Set <T> result = new HashSet<>();
        
        CriteriaQuery<T> criteria = 
                getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        criteria.select(criteria.from(entityClass));
        try{
            result = entityManager.createQuery(criteria).getResultList().stream().collect(Collectors.toSet());        
        } catch (Exception e){
            System.out.println("Problem occured during finding Entities in DB");
            System.out.println(e.getMessage());
        }
        
        return result;
    }

    @Override
    public T findOne(ID id) {
        T t = null;
        try{ 
            t = entityManager.find(entityClass, id);
            if (t == null){
                throw new Exception("No entity was found");
            }
        } catch (Exception e){
            System.out.println("Problem occured during finding Entities in DB");
            System.out.println(e.getMessage());
            
        }
        
        return t;
    }

}
