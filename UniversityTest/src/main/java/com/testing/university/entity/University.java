package com.testing.university.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.testing.university.util.JPAHibernateUtil;

@Entity
@Table(name="UNIVERSITY")
public class University {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Column(name = "NAME")
    private String name;
    
    @OneToMany(mappedBy = "university", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval=true)
    private List <Department> departments = new ArrayList<>();
    
    @OneToMany( mappedBy = "university", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List <Lector> lectors = new ArrayList<>();

    public University() {
        
        
        // TODO Auto-generated constructor stub
    }
    
    public University(String name) {
           this.name = name;
           
           Lector lector1 = new Lector("Yura", "Stad", 7000, Degree.ASSOCIATE_PROFESSOR);
           Lector lector2 = new Lector("Taras", "Kyrs", 4000, Degree.ASSISTANT);
           Lector lector3 = new Lector("Vova", "Kos", 7000, Degree.ASSOCIATE_PROFESSOR);
           Lector lector4 = new Lector("Orest", "Gogo", 10000, Degree.PROFESSOR);
           Lector lector5 = new Lector("Igor", "Gogo", 9000, Degree.PROFESSOR);
           
           this.addLector(lector1);
           this.addLector(lector2);
           this.addLector(lector3);
           this.addLector(lector4);
           this.addLector(lector5);
           
           Department department1 = new Department("Geograpic");
           Department department2 = new Department("International");
           
           department1.setHeadOfDepartment(lector5);
           department2.setHeadOfDepartment(lector4);
           
           department1.addLector(lector5);
           department1.addLector(lector1);
           department1.addLector(lector2);
           
           department2.addLector(lector4);
           department2.addLector(lector3);
           department2.addLector(lector2);
           
           this.addDepartment(department1);
           this.addDepartment(department2);
    }

    public University(String name, List<Department> departments, List<Lector> lectors) {
        super();
        this.name = name;
        this.departments = departments;
        this.lectors = lectors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Lector> getLectors() {
        return lectors;
    }

    public void setLectors(List<Lector> lectors) {
        this.lectors = lectors;
    }
    
    public void addDepartment(Department department) {
        this.departments.add(department);
        department.setUniversity(this);
    }
 
    public void removeDepartment(Department department) {
        department.setUniversity(null);
        this.departments.remove(department);
    }
    
    public void addLector(Lector lector) {
        this.lectors.add(lector);
        lector.setUniversity(this);
    }
 
    public void removeLector(Lector lector) {
        lector.setUniversity(null);
        this.lectors.remove(lector);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        University other = (University) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

  

    
    

  
}
