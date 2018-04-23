package com.testing.university.dao;

import java.util.Set;

import javax.persistence.EntityManager;

public interface GenericDao <T, ID>{
    
    EntityManager createEmWithTransaction();
    void closeEmWithTransaction();
    Set<T> findAll();
    T findOne(ID id);
    
}
