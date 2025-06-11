package com.sms.sms_login_service.dao;

import com.sms.sms_login_service.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 * DAO for user validation.
 */
@Repository
public class UserValidate {

    private static final Logger logger = LoggerFactory.getLogger(UserValidate.class);
    private final JdbcTemplate jdbcTemplate;

    public UserValidate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Checks if the username and password are valid.
     *
     * @param username the username
     * @param password the password
     * @return "1" if valid, "0" otherwise
     */
    public String checkUserAndPass(String username, String password) {
        if (username == null || password == null) {
            logger.warn("Username or password is null");
            return "0";
        }
        try {
            var resultList = jdbcTemplate.queryForList(Utility.PASSWORD_VALIDATE, username, password);
            if (resultList.isEmpty() || resultList.get(0).get("count(*)") == null) {
                logger.info("No user found for username: {}", username);
                return "0";
            }
            String result = resultList.get(0).get("count(*)").toString();
            logger.info("User validation result for {}: {}", username, result);
            return result;
        } catch (EmptyResultDataAccessException e) {
            logger.info("No user found for username: {}", username);
            return "0";
        } catch (Exception e) {
            logger.error("Error validating user: {}", username, e);
            return "0";
        }
    }
}