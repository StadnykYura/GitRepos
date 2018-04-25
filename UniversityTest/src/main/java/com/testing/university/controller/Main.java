package com.testing.university.controller;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import com.testing.university.entity.Degree;
import com.testing.university.entity.Department;
import com.testing.university.entity.Lector;
import com.testing.university.entity.University;
import com.testing.university.service.DepartmentService;
import com.testing.university.service.DepartmentServiceImplWithBussinessLogic;
import com.testing.university.service.DepartmentServiceImplWithDBQueryApproach;
import com.testing.university.service.UniversityServiceImpl;
import com.testing.university.util.JPAHibernateUtil;
import com.testing.university.util.ScannerUtil;
import com.testing.university.util.SchemaStrings;

public class Main {
    
    public static void openUniversity(){
        EntityManager entityManager = null;
        try {
           entityManager = JPAHibernateUtil.getEntityManagerFactory().createEntityManager();
           entityManager.getTransaction().begin();

           University university = new University("Information Technology University");
           entityManager.persist(university);
           
           entityManager.getTransaction().commit();
           System.out.println("University created successfully");
           System.out.println("Greetings in the " + university.getName());
           
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
       
    public static void main(String[] args) {
        
        openUniversity();
        DepartmentController departmentController = new DepartmentController();
        departmentController.startDepartmentProgram();
           
    }

}
