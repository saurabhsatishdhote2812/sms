package com.sms.sms_syllabus_service.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * Repository for accessing syllabus data from the database.
 */
@Repository
public class SyllabusRepository {

    private static final Logger logger = LoggerFactory.getLogger(SyllabusRepository.class);

    private final JdbcTemplate jdbcTemplate;

    public SyllabusRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Retrieves the syllabus for a given subject.
     *
     * @param subjectName the name of the subject
     * @return list of syllabus items, or empty list if none found
     */
    public List<String> getSyllabusBySubject(String subjectName) {
        String query = "SELECT syllabus FROM sub_syll WHERE JSON_UNQUOTE(JSON_EXTRACT(syllabus, '$.subject')) = ? LIMIT 1";
        logger.info("Executing query to fetch syllabus for subject: {}", subjectName);
        try {
            List<String> result = jdbcTemplate.queryForList(query, new Object[]{subjectName}, String.class);
            if (result.isEmpty()) {
                logger.warn("No syllabus found for subject: {}", subjectName);
                return Collections.emptyList();
            }
            return result;
        } catch (Exception ex) {
            logger.error("Error fetching syllabus for subject {}: {}", subjectName, ex.getMessage());
            return Collections.emptyList();
        }
    }
}