package com.testing.university.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import com.testing.university.dao.DepartmentDao;
import com.testing.university.dao.impl.DepartmentDaoImpl;
import com.testing.university.dto.DepartmentStatisticDTO;
import com.testing.university.dto.LectorDTO;
import com.testing.university.entity.Department;

public class DepartmentServiceImplWithDBQueryApproach implements DepartmentService{
    
    private DepartmentDao departmentDao;
    
    public DepartmentServiceImplWithDBQueryApproach() {
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

    @Override
    public String findHeadOfDepartment(Long departmentId) {
        StringBuilder result = new StringBuilder(); 
        
        departmentDao.createEmWithTransaction();
        LectorDTO headOfDepartment = departmentDao.headOfDepartmentName(departmentId);
        departmentDao.closeEmWithTransaction();
        result = result.append("Head of ")
                .append(headOfDepartment.getDepartmentName())
                .append(" department is ")
                .append(headOfDepartment.getName())
                .append(" ")
                .append(headOfDepartment.getSurname());
        
        return result.toString();
    }

    @Override
    public Department selectDepartment() {
        Set<Department> departments = findAll();
        int i = 0;
        for (Department department : departments) {
            System.out.println("Write down a number " + ++i + " for selecting department: "+ department.getDepartmentName());
        }
        int command = 0;
        do{
            System.out.println("You can write down integer numbers from range 1 to " + departments.size() + "(including)");
            command = inputNum();
        } while (!(command > 0 && command <= departments.size())); 
        
        i = 0;
        for (Department department : departments) {
            if (command == ++i){
                return department;
            }
        }
        
        return null;
    }

    @Override
    public String showDepartmentStatistic(Long departmentId) {
        StringBuilder result = new StringBuilder();
        
        departmentDao.createEmWithTransaction();
        List<DepartmentStatisticDTO> depeartmentStatistics = departmentDao.showDepartmentStatistic(departmentId);
        departmentDao.closeEmWithTransaction();
        
        
        for (DepartmentStatisticDTO dto : depeartmentStatistics) {
            result = result.append(dto.getDegree().toString())
                    .append(" - ")
                    .append(dto.getCount())
                    .append("\n");
        }
       
        return result.toString();
    }

    @Override
    public String showAvarageSalaryforDepartment(Long departmentId) {
        StringBuilder result = new StringBuilder();
        departmentDao.createEmWithTransaction();
        double avgSalary = departmentDao.showAvarageSalaryOfDepartment(departmentId);
        departmentDao.closeEmWithTransaction();
        
        result = result.append("The average salary of this department is ")
                .append(avgSalary);
        return result.toString();
    }

    @Override
    public String showCountOfEmployeeForDepartment(Long departmentId) {
        StringBuilder result = new StringBuilder();
        departmentDao.createEmWithTransaction();
        long countOfEmployee = departmentDao.showCountOfEmployees(departmentId);
        departmentDao.closeEmWithTransaction();
        result = result.append(countOfEmployee);
        return result.toString();
    }

    @Override
    public String globalSearchByTemplateThroughDepartmentLectors(String template) {
        StringBuilder result = new StringBuilder();
        
        departmentDao.createEmWithTransaction();
        List<LectorDTO> lectorDTO = departmentDao.globalSearchByTemplateThroughAllDepartmentsLectors(template);
        departmentDao.closeEmWithTransaction();
        
        List <LectorDTO> filteredList = lectorDTO.stream().distinct().collect(Collectors.toList());
        if (filteredList.size()==0){
            result = result.append("Nothing was found in DB");
            return result.toString();
        }
        for (int i = 0; i < filteredList.size(); i++) {
            if (filteredList.size() == i+1){
                result = result.append(lectorDTO.get(i).getName() + " " + lectorDTO.get(i).getSurname());
                break;
            }
            result = result.append(lectorDTO.get(i).getName() + " " + lectorDTO.get(i).getSurname() + ", ");
        }
        
        return result.toString();
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

    
}
