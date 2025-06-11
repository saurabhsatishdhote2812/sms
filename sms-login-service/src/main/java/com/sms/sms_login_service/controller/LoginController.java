package com.sms.sms_login_service.controller;

import com.sms.sms_login_service.model.LoginRequest;
import com.sms.sms_login_service.service.UserValidateService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling login requests.
 */
@RestController
@RequestMapping("/sms")
@CrossOrigin(origins = "*")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final UserValidateService userValidateService;

    public LoginController(UserValidateService userValidateService) {
        this.userValidateService = userValidateService;
    }

    /**
     * Handles user login requests.
     *
     * @param loginRequest the login request payload
     * @return ResponseEntity with login result
     */
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest) {
        logger.info("Login attempt for user: {}", loginRequest.getUsername());
        try {
            var result = userValidateService.checkUserAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Login failed for user: {}", loginRequest.getUsername(), e);
            return ResponseEntity.status(500).body("Internal server error");
        }
    }
}