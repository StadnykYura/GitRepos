package com.testing.university.dao;

import java.util.List;

import com.testing.university.dto.DepartmentStatisticDTO;
import com.testing.university.dto.LectorDTO;
import com.testing.university.entity.Department;

public interface DepartmentDao extends GenericDao<Department, Long>{

    Department findOne(String name);
    LectorDTO headOfDepartmentName(Long id);
    List<DepartmentStatisticDTO> showDepartmentStatistic(Long id);
    double showAvarageSalaryOfDepartment(Long id);
    long showCountOfEmployees(Long id);
    List <LectorDTO> globalSearchByTemplateThroughAllDepartmentsLectors(String template);
    
}
