package com.testing.university.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LECTOR")
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LECTOR_ID")
    private Long id;
    
    @Column(name = "FIRST_NAME", nullable=false)
    private String firstName;
    
    @Column(name = "SECOND_NAME", nullable=false)
    private String secondName;
    
    @Column(name = "SALARY")
    private double salary;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "DEGREE")
    private Degree degree;
    
    @OneToOne
    @JoinColumn(name="headOfdepartment_id")
    private Department departmentsHead;
    
    @ManyToMany(mappedBy="lectors", fetch = FetchType.LAZY)
    private Set<Department> departments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "university_id", nullable=false)
    private University university;
    
    public Lector() {
        // TODO Auto-generated constructor stub
    }
    
    public Lector(String name, String surname, double salary, Degree degree) {
        super();
        this.firstName = name;
        this.secondName = surname;
        this.salary = salary;
        this.degree = degree;
    }

    
    
    public Lector(String firstName, String secondName, double salary, Degree degree, Set<Department> departments,
            University university) {
        super();
        this.firstName = firstName;
        this.secondName = secondName;
        this.salary = salary;
        this.degree = degree;
        this.departments = departments;
        this.university = university;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public double getSalary() {
        return salary;
    }
    
    

    public Department getDepartmentsHead() {
        return departmentsHead;
    }

    public void setDepartmentsHead(Department departmentHead) {
        this.departmentsHead = departmentHead;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((secondName == null) ? 0 : secondName.hashCode());
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
        Lector other = (Lector) obj;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (secondName == null) {
            if (other.secondName != null)
                return false;
        } else if (!secondName.equals(other.secondName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Lector [id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", salary=" + salary
                + ", degree=" + degree + ", departmentsHead=" + departmentsHead + ", university=" + university + "]";
    }

   
    
    

  
   
    
    
}
