package com.testing.university.service;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import com.testing.university.dao.DepartmentDao;
import com.testing.university.dao.impl.DepartmentDaoImpl;
import com.testing.university.entity.Degree;
import com.testing.university.entity.Department;
import com.testing.university.entity.Lector;

public class DepartmentServiceImplWithBussinessLogic implements DepartmentService {

    private DepartmentDao departmentDao;
    
    public DepartmentServiceImplWithBussinessLogic() {
        departmentDao = new DepartmentDaoImpl();
    }
    
    @Override
    public Department findOne(String name) {
        departmentDao.createEmWithTransaction();
        Department department = departmentDao.findOne(name);
        departmentDao.closeEmWithTransaction();
        return department;
    }
    
    public Set<Department> findAll(){
        departmentDao.createEmWithTransaction();
        Set<Department> departments = departmentDao.findAll();
        departmentDao.closeEmWithTransaction();
        return departments;
    }
    
    private Department findOneWithoutTransaction(String name){
        return departmentDao.findOne(name);
    }
    
    private Set<Department> findAllWithoutTransaction(){
        return departmentDao.findAll();
    }
    
    public String findHeadOfDepartment(Long id){
        StringBuilder result = new StringBuilder();
        departmentDao.createEmWithTransaction();
        Department department = departmentDao.findOne(id);
        Lector headOfDepartment = department.getHeadOfDepartment();
        departmentDao.closeEmWithTransaction();
        result = result.append("Head of ")
                .append(department.getDepartmentName())
                .append(" department is ")
                .append(headOfDepartment.getFirstName())
                .append(" ")
                .append(headOfDepartment.getSecondName());
        return result.toString();
    }
    
    public Department selectDepartment(){
        
        Set<Department> departments = findAll();
        int i = 0;
        for (Department department : departments) {
            System.out.println("Write down a number " + ++i + " for selecting department: "+ department.getDepartmentName());
        }
        int command = 0;
        do{
            System.out.println("You can write down integer numbers from range 1 to " + departments.size() + "(including)");
            command = inputNum();
        } while (!(command > 0 && command < departments.size())); 
        
        i = 0;
        for (Department department : departments) {
            if (command == ++i){
                return department;
            }
        }
        
        return null;
    }
    
    
    private int inputNum(){
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



    @Override
    public String showDepartmentStatistic(Long departmentId) {
        StringBuilder result = new StringBuilder();
        departmentDao.createEmWithTransaction();
        Department department = departmentDao.findOne(departmentId);
        Set<Lector> lectors = department.getLectors();
     
        
        int assistantCount = 0;
        int associateProffesorCount = 0;
        int proffesorCount = 0;
        
        for (Lector lector : lectors) {
            if (lector.getDegree().toString() == Degree.ASSISTANT.toString()){
                assistantCount++;
            } else if (lector.getDegree().toString() == Degree.ASSOCIATE_PROFESSOR.toString()){
                associateProffesorCount++;
            } else if (lector.getDegree().toString() == Degree.PROFESSOR.toString()){
                proffesorCount++;
            }
        }
        departmentDao.closeEmWithTransaction();
        
        
        result = result.append("assistans - ").append(assistantCount)
                .append("\n")
                .append("associate professors - ").append(associateProffesorCount)
                .append("\n")
                .append("professors - ").append(proffesorCount);
        
        return result.toString();
    }



    @Override
    public String showAvarageSalaryforDepartment(Long departmentId) {
        StringBuilder result = new StringBuilder();
        departmentDao.createEmWithTransaction();
        Department department = departmentDao.findOne(departmentId);
        Set<Lector> lectors = department.getLectors();
        
        
        double sumOfAllSalary = 0;
        for (Lector lector : lectors) {
            sumOfAllSalary += lector.getSalary();
        }
        double avarageSalary = sumOfAllSalary/lectors.size();
        departmentDao.closeEmWithTransaction();
        
        result = result.append("The average salary of ")
                .append(department.getDepartmentName())
                .append(" is ")
                .append(avarageSalary);
        
        return result.toString();
    }



    @Override
    public String showCountOfEmployeeForDepartment(Long departmentId) {
        StringBuilder result = new StringBuilder();
        departmentDao.createEmWithTransaction();
        Department department = departmentDao.findOne(departmentId);
        Set<Lector> lectors = department.getLectors();
        
        int countOfEmployee = lectors.size();
        
        departmentDao.closeEmWithTransaction();
             
        result = result.append("Count of employees for ")
                .append(department.getDepartmentName())
                .append(" is ")
                .append(countOfEmployee);
        
        return result.toString();
    }

    @Override
    public String globalSearchByTemplateThroughDepartmentLectors(String template) {
        // TODO Auto-generated method stub
        return null;
    }



    
    
    
}
