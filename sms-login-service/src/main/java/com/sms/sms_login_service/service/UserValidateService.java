package com.sms.sms_login_service.service;

import com.sms.sms_login_service.dao.UserValidate;
import com.sms.sms_login_service.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service for validating user credentials.
 */
@Service
public class UserValidateService {

    private static final Logger logger = LoggerFactory.getLogger(UserValidateService.class);
    private final UserValidate userValidate;

    public UserValidateService(UserValidate userValidate) {
        this.userValidate = userValidate;
    }

    /**
     * Checks if the provided username and password are valid.
     *
     * @param username the username
     * @param password the password
     * @return validation result string
     */
    public String checkUserAndPassword(String username, String password) {
        if (username == null || password == null) {
            logger.warn("Username or password is null");
            return Utility.USERNAME_VALIDATE_FAIL;
        }
        boolean isValid = "1".equals(userValidate.checkUserAndPass(username, password));
        String result = isValid ? Utility.USERNAME_VALIDATE_PASS : Utility.USERNAME_VALIDATE_FAIL;
        logger.info("User validation for {}: {}", username, result);
        return result;
    }
}