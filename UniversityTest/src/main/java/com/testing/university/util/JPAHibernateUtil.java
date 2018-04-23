package com.testing.university.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAHibernateUtil {

    public static final String PERSISTENCE_UNIT = "PERSISTENCE_UNIT";
    private static EntityManagerFactory emFactory;
    
    public static EntityManagerFactory getEntityManagerFactory(){
        if (emFactory == null){
            emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        return emFactory;
    }
    
    public static void closeEmFactory(){
        if (emFactory != null){
            emFactory.close();
        }
    }
}