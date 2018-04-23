package com.testing.university.dao.impl;

import com.testing.university.dao.UniversityDao;
import com.testing.university.entity.University;

public class UniversityDaoImpl extends GenericDaoImpl<University, Long> implements UniversityDao{

    public UniversityDaoImpl() {
        super(University.class);
    }

    @Override
    public University globalSearch(String template) {
        
        return null;
    }

}
