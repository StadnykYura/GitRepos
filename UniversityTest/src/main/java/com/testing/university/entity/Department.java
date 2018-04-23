package com.testing.university.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.JoinColumn;

@Entity
@Table(name = "DEPARTMENT")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "NAME")
    private String departmentName;
    
    @OneToOne(mappedBy="departmentsHead", cascade=CascadeType.ALL)
    private Lector headOfDepartment;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinTable(name = "DEPARTMENT_LECTOR", 
    joinColumns = { @JoinColumn(name = "DEP_ID") }, 
    inverseJoinColumns = { @JoinColumn(name = "LEC_ID") })
    private Set<Lector> lectors = new HashSet<>();
    
    @ManyToOne
    @JoinColumn(name="university_id", nullable=false)
    private University university;
    
    public Department() {
        // TODO Auto-generated constructor stub
    }
    
    
    
    public Department(String departmentName) {
        super();
        this.departmentName = departmentName;
    }



    public Department(String departmentName, Lector headOfDepartment, Set<Lector> lectors, University university) {
        super();
        this.departmentName = departmentName;
        this.headOfDepartment = headOfDepartment;
        this.lectors = lectors;
        this.university = university;
    }

    public Department(String departmentName, Lector headOfDepartment, University university) {
        super();
        this.departmentName = departmentName;
        this.headOfDepartment = headOfDepartment;
        this.university = university;
    }

    
    
    public Long getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Lector getHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(Lector headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
        headOfDepartment.setDepartmentsHead(this);
    }

    public Set<Lector> getLectors() {
        return lectors;
    }

    public void setLectors(Set<Lector> lectors) {
        this.lectors = lectors;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
    
    public void addLector(Lector lector) {
        lectors.add(lector);
        lector.getDepartments().add(this);
    }
 
    public void removeLector(Lector lector) {
        lectors.remove(lector);
        lector.getDepartments().remove(this);
    }

    public void removeHeadOfDepartment(){
        if(headOfDepartment != null){
            headOfDepartment.setDepartmentsHead(null);
        }
        this.headOfDepartment = null;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((departmentName == null) ? 0 : departmentName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Department other = (Department) obj;
        if (departmentName == null) {
            if (other.departmentName != null)
                return false;
        } else if (!departmentName.equals(other.departmentName))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }



    @Override
    public String toString() {
        return "Department [id=" + id + ", departmentName=" + departmentName + ", headOfDepartment=" + headOfDepartment
                + ", lectors=" + lectors + ", university=" + university + "]";
    }

  
    
   
    
  
    
    
    
}
