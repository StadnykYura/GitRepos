package com.testing.university.service;

import com.testing.university.entity.Department;

public interface DepartmentService {
    
    
    Department findOne(String name);
    String findHeadOfDepartment(Long departmentId);
    Department selectDepartment();
    
    String showDepartmentStatistic(Long departmentId);
    String showAvarageSalaryforDepartment(Long departmentId);
    String showCountOfEmployeeForDepartment(Long departmentId);
    String globalSearchByTemplateThroughDepartmentLectors(String template);
    

}
