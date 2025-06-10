package com.sms.sms_login_service.dao;

import com.sms.sms_login_service.util.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserValidate {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public String checkUserAndPass(String username, String password) {
        var result = jdbcTemplate.queryForList(Utility.PASSWORD_VALIDATE, username, password).get(0).get("count(*)").toString();
        log.info("Query result: {}", result);
        return result;
    }

}
