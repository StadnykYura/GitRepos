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

public class Main {
    
    public static int getInt()   {
        Scanner sc = new Scanner(System.in);
        int number = 0;
        try{
            number = sc.nextInt();
            if (number < 1 || number > Integer.MAX_VALUE){
                throw new Exception();
            }
        } catch (InputMismatchException e) {
            System.out.println("You wrote not a number");
            return 0;
        } catch (Exception e){
            System.out.println("You wrote wrong number or not a number ");
            return 0;
        }
        return number;
    }
    
    public static String getString(){
        String result;
        Scanner sc = new Scanner(System.in);
        try{
            result = sc.next();
            
            if (result.length() < 1 || result == null || result.equals("")){
                throw new Exception();
            }
            
        } catch (Exception e) {
            System.out.println("You wrote wrong character or not a characters");
            return null;
        }
        return result;
    }
    
    
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
        DepartmentService departmentService = new DepartmentServiceImplWithBussinessLogic();
        DepartmentService departmentServiceWithDBQueryLogic = new DepartmentServiceImplWithDBQueryApproach();
        UniversityServiceImpl universityServiceImpl = new UniversityServiceImpl(); 
        
        while (true) {
            try {
            
                System.out.println("Choose a number from 1-5 for doing staff: " + "\n"
                        + "1. Take a list of existing departments and find out who is a head of particular department" + "\n"
                        + "2. Show a statistic of particular department" + "\n" + "3. Show the avarage salary for particular department"
                        + "\n" + "4. Show count of employee for special department" + "\n"
                        + "5. Global search with a template" + "\n" + "6. Exit from program");
    
                int switchNumber = getInt();
                Department currentDepartment = null;
                String response = null;
                switch (switchNumber) {
                    case 1:
                        try {
                            currentDepartment = departmentServiceWithDBQueryLogic.selectDepartment();
                            
                            if (currentDepartment == null){
                                System.out.println("No department chosen with, pls try again");
                                break;
                            }
        
                        } catch (InputMismatchException | NumberFormatException nfe) {
                            System.out.println("You wrote wrong number or character");
                            System.out.println();
                        }
        
                        System.out.println(departmentServiceWithDBQueryLogic.findHeadOfDepartment(currentDepartment.getId()));
                        System.out.println();
                        break;
        
                    case 2:
        
                        currentDepartment = departmentServiceWithDBQueryLogic.selectDepartment();
                        System.out.println();
                        
                        response = departmentServiceWithDBQueryLogic.showDepartmentStatistic(currentDepartment.getId());
                        System.out.println(response);
                        System.out.println();
                        break;
                    case 3:
        
                        currentDepartment = departmentServiceWithDBQueryLogic.selectDepartment();
                        System.out.println();
                        
                        response = departmentServiceWithDBQueryLogic.showAvarageSalaryforDepartment(currentDepartment.getId());
                        System.out.println(response);
                        System.out.println();
                        break;
                    case 4:
        
                        currentDepartment = departmentServiceWithDBQueryLogic.selectDepartment();
                        System.out.println();
                        
                        response = departmentServiceWithDBQueryLogic.showCountOfEmployeeForDepartment(currentDepartment.getId());
                        System.out.println(response);
                        System.out.println();
                 
                        break;
                    case 5:
                     
                        String template = null;
                        
                        do{
                            System.out.println("Write down any sequence of character to look up a lectors names through all departments by this template");
                            template = getString();
                        } while (template == null);
                        
                        String responseSearch = departmentServiceWithDBQueryLogic.globalSearchByTemplateThroughDepartmentLectors(template);
                        System.out.println(responseSearch);
                        System.out.println();
                        
                        break;
                        
                    case 6:
                        System.out.println("You have closed the program");
                        JPAHibernateUtil.closeEmFactory();
                        System.exit(0);
                        break;
                        
                    default:
                        System.out.println("You choosed wrong number or character");
                        System.out.println();
                        break;
                    }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("You choosed/wrote wrong number/character");
            }
        }
        
        
        
       
           
           
    }

}
