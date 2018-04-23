package com.testing.university.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import com.testing.university.dao.DepartmentDao;
import com.testing.university.dto.DepartmentStatisticDTO;
import com.testing.university.dto.LectorDTO;
import com.testing.university.entity.Department;

public class DepartmentDaoImpl extends GenericDaoImpl<Department, Long> implements DepartmentDao{
    
    public DepartmentDaoImpl() {
        super(Department.class);
    }

    //using jpql
    @Override
    public Department findOne(String name) {
        Department result = null;
        String qlString = "select d from Department d where d.departmentName = :name";
       
        try{
            result = (Department) super.getEntityManager().createQuery(qlString).setParameter("name", name).getSingleResult();
        } catch (NoResultException nre){
            System.out.println("No " + Department.class.toString() + "found with this name " + name);
        } catch (Exception e){
            System.out.println("Problem occured during finding Entities in DB");
            System.out.println(e.getMessage());
        }
               
        return result;
    }

    @Override
    public LectorDTO headOfDepartmentName(Long id) {
        LectorDTO lectorDto = null;
        try{
            lectorDto = (LectorDTO)getEntityManager()
                    .createQuery("SELECT new com.testing.university.dto.LectorDTO( "
                            + "d.headOfDepartment.firstName, d.headOfDepartment.secondName, d.departmentName ) FROM Department d WHERE d.id = :id")
                    .setParameter("id", id).getSingleResult();
        }catch (PersistenceException pe) {
            System.out.println("Problem occured during querying Entities in DB");
            System.out.println(pe.getMessage());
        } catch (Exception e) {
            System.out.println("Exception occured in " + this.getClass().toString());
            System.out.println(e.getMessage());
        }
                
        return lectorDto;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DepartmentStatisticDTO> showDepartmentStatistic(Long id) {
        List<DepartmentStatisticDTO> result = new ArrayList<>();
        try{
            result = (List<DepartmentStatisticDTO>) getEntityManager()
                    .createQuery("SELECT new com.testing.university.dto.DepartmentStatisticDTO( "
                            + "COUNT(dl), dl.degree ) FROM Department d LEFT JOIN d.lectors dl WHERE d.id = :id GROUP BY dl.degree")
                    .setParameter("id", id).getResultList();

        } catch (Exception e) {
            System.out.println("Problem occured during querying Entities in DB");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public double showAvarageSalaryOfDepartment(Long id) {
        double result = 0;
        try{
            result = (Double)getEntityManager()
                    .createQuery("SELECT AVG(dl.salary) FROM Department d LEFT JOIN d.lectors dl WHERE d.id = :id")
                    .setParameter("id", id).getSingleResult();            
        } catch (Exception e) {
            System.out.println("Problem occured during querying Entities in DB");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public long showCountOfEmployees(Long id) {
        
        long result = 0;
        try{
            result = (Long)getEntityManager()
                    .createQuery("SELECT COUNT(dl) FROM Department d LEFT JOIN d.lectors dl WHERE d.id = :id")
                    .setParameter("id", id).getSingleResult();            
        } catch (Exception e) {
            System.out.println("Problem occured during querying Entities in DB");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List <LectorDTO> globalSearchByTemplateThroughAllDepartmentsLectors(String template) {
        List <LectorDTO> result = null;
        template = "%"+template+"%";
        try{
            result = (List<LectorDTO>) getEntityManager()
                    .createQuery("SELECT new com.testing.university.dto.LectorDTO( "
                            + "dl.firstName, dl.secondName ) FROM Department d LEFT JOIN d.lectors dl "
                            + "WHERE dl.firstName LIKE :template OR dl.secondName LIKE :template")
                    .setParameter("template", template).getResultList();

        } catch (Exception e) {
            System.out.println("Problem occured during querying Entities in DB");
            System.out.println(e.getMessage());
        }
        
        
        
        return result;
    }

    
    
}
