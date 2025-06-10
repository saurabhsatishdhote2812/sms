package com.sms.sms_login_service.util;

public class Utility {
    public static final String USERNAME_VALIDATE = "SELECT count(*) FROM stdnt_rgst WHERE username = ?";
    public static final String PASSWORD_VALIDATE = "SELECT count(*) FROM stdnt_rgst WHERE username = ? and password = ?";
    public static final String USERNAME_VALIDATE_PASS = "PASS";
    public static final String USERNAME_VALIDATE_FAIL = "FAIL";
    public static final String REQUEST_RECEIVED = "Request received for ";
    public static final String RESPONSE_SENT = "Response sent to user";
    public static final String QUERY_EXECUTION_STARTED = "Query execution started...";
    public static final String QUERY_EXECUTION_COMPLETED = "Query execution completed...";
}
