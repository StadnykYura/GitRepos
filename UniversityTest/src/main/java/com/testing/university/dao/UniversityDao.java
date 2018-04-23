package com.testing.university.dao;

import com.testing.university.entity.University;

public interface UniversityDao extends GenericDao<University, Long>{

    University globalSearch(String template);
    
}
