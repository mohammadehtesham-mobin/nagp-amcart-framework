package com.nagp.amcart.security.constant;

public class SecurityConstant {
    private SecurityConstant() {

    }
    public static final String INVALID_TOKEN = "INVALID_TOKEN";
    public static final String INVALID_TOKEN_ERROR_MESSAGE = "Invalid Token. Please refresh token and try again";
    public static final String COGNITO_GROUPS_CLAIMS = "cognito:groups";
    public static final String CLIENT_ID_CLAIMS = "client_id";
    public static final String ROLE_PREFIX = "ROLE_";
    public static final String USERNAME = "username";
}
