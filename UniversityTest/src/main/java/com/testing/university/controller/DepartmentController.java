package com.testing.university.controller;

import java.util.InputMismatchException;

import com.testing.university.entity.Department;
import com.testing.university.service.DepartmentService;
import com.testing.university.service.DepartmentServiceImplWithDBQueryApproach;
import com.testing.university.util.JPAHibernateUtil;
import com.testing.university.util.ScannerUtil;
import com.testing.university.util.SchemaStrings;

public class DepartmentController {

    private DepartmentService departmentServiceWithDBQueryLogic;
    
    public DepartmentController() {
        departmentServiceWithDBQueryLogic = new DepartmentServiceImplWithDBQueryApproach();
    }
    
    private void pritnInterfaceForUser(){
        System.out.println("Choose a number from 1-5 for doing staff: " + "\n"
                + "1. Take a list of existing departments and find out who is a head of particular department" + "\n"
                + "2. Show a statistic of particular department" + "\n" + "3. Show the avarage salary for particular department"
                + "\n" + "4. Show count of employee for special department" + "\n"
                + "5. Global search with a template" + "\n" + "6. Exit from program");
    }
    
    public void startDepartmentProgram(){
        while (true) {
            try {
                pritnInterfaceForUser();
                int switchNumber = ScannerUtil.getInt();
                switch (switchNumber) {
                    case 1:
                        findHeadOfDepartment();
                        break;
                    case 2:
                        showDepartmentStatistics();
                        break;
                    case 3:
                        showAvgSalary();
                        break;
                    case 4:
                        showCountOfEmployeeForDepartment();
                        break;
                    case 5:
                        globalSearchByTemplateThroughDepartmentLectors();
                        break;
                    case 6:
                        System.out.println("You have closed the program");
                        JPAHibernateUtil.closeEmFactory();
                        System.exit(0);
                        break;
                    default:
                        System.out.println(SchemaStrings.WRONG_NUMBER_OR_CHARACTER);
                        System.out.println();
                        break;
                    }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println(SchemaStrings.WRONG_NUMBER_OR_CHARACTER);
            }
        }
    }
    
    private void findHeadOfDepartment() {
        
        Department currentDepartment = null;
        try {
            currentDepartment = departmentServiceWithDBQueryLogic.selectDepartment();
            
            if (currentDepartment == null){
                System.out.println(SchemaStrings.NOTHING_WAS_CHOSEN);
            } else {
                System.out.println(departmentServiceWithDBQueryLogic.findHeadOfDepartment(currentDepartment.getId()));
            }

        } catch (InputMismatchException | NumberFormatException nfe) {
            System.out.println(SchemaStrings.WRONG_NUMBER_OR_CHARACTER);
            System.out.println();
        }

    }
    
    private void showDepartmentStatistics(){
        Department currentDepartment = departmentServiceWithDBQueryLogic.selectDepartment();
        String response = departmentServiceWithDBQueryLogic.showDepartmentStatistic(currentDepartment.getId());
        System.out.println(response);
    }
    
    
    private void showAvgSalary(){
        Department currentDepartment = departmentServiceWithDBQueryLogic.selectDepartment();
        String response = departmentServiceWithDBQueryLogic.showAvarageSalaryforDepartment(currentDepartment.getId());
        System.out.println(response);
    }
    
    private void showCountOfEmployeeForDepartment(){
        Department currentDepartment = departmentServiceWithDBQueryLogic.selectDepartment();
        String response = departmentServiceWithDBQueryLogic.showCountOfEmployeeForDepartment(currentDepartment.getId());
        System.out.println(response);
    }
    
    private void globalSearchByTemplateThroughDepartmentLectors(){
        String template = null;
        
        do{
            System.out.println(SchemaStrings.WRITE_DOWN_ANY_SEQUENCE);
            template = ScannerUtil.getString();
        } while (template == null);
        
        String responseSearch = departmentServiceWithDBQueryLogic.globalSearchByTemplateThroughDepartmentLectors(template);
        System.out.println(responseSearch);
    }
    
    
}
