package com.testing.university.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.testing.university.dao.UniversityDao;
import com.testing.university.dao.impl.UniversityDaoImpl;
import com.testing.university.entity.Department;
import com.testing.university.entity.Lector;
import com.testing.university.entity.University;

public class UniversityServiceImpl implements UniversityService{

    private UniversityDao universityDao;
    
    public UniversityServiceImpl() {
        universityDao = new UniversityDaoImpl();
    }
    
    public String globalSearchByTemplateThroughAllNamesInSystem(String template){
        StringBuilder result = new StringBuilder();
        universityDao.createEmWithTransaction();
        
        //considering that there is only 1 university in the system
        Optional<University> universityOpt = universityDao.findAll().stream().findFirst();
        University university = null;
        if (universityOpt.isPresent()){
            university = universityOpt.get();
        } else {
            System.out.println("There is no University found in DB");
        }
        
        List<Department> departments = university.getDepartments();
        List<Lector> lectors = university.getLectors();
       
        List<String> toPrint = new ArrayList<>();
        for (Lector lector : lectors) {
            if (lector.getFirstName().contains(template) || lector.getSecondName().contains(template)){
                toPrint.add(lector.getFirstName() + " " + lector.getSecondName());
            }
        }
        
        for (Department department : departments) {
            if (department.getDepartmentName().contains(template)){
                toPrint.add(department.getDepartmentName());
            }
        }
        universityDao.closeEmWithTransaction();
        for (int i = 0; i < toPrint.size(); i++) {
            if (toPrint.size() == i+1){
                result = result.append(toPrint.get(i));
                break;
            }
            result = result.append(toPrint.get(i) + ", ");
        }
        
        
        return result.toString();
    }
    
    
    
}
