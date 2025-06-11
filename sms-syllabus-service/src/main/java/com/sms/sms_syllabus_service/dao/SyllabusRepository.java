package com.sms.sms_syllabus_service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SyllabusRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<String> getSyllabusBySubject(String subjectName) {
        String query="SELECT syllabus FROM sub_syll WHERE JSON_UNQUOTE(JSON_EXTRACT(syllabus, '$.subject')) = ? LIMIT 1";
        return jdbcTemplate.queryForList(query,new Object[]{subjectName}, String.class);
    }

}
