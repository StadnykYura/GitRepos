package com.testing.university.dto;

import com.testing.university.entity.Degree;

public class DepartmentStatisticDTO {

    private long count;
    private Degree degree;
    
    public DepartmentStatisticDTO() {
    }

    public DepartmentStatisticDTO(long count, Degree degree) {
        super();
        this.count = count;
        this.degree = degree;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    
    
    
    
    
}
