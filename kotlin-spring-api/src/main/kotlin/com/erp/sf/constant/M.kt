package com.erp.sf.constant

//Message
enum class M(val message: String) {
    LOGIN_FAILED("Login is failed"),
    REGISTER_FAILED("Register is failed"),
    TOKEN_FAILED("Token is failed"),
    INVITE_CODE_ERROR("Invite code is error"),
    DATA_EXISTS("Data is exists"),
    LOGOUT_SUCCESS("successfully logged out"),
    LOGOUT_FAILED("logged out failed"),
    INIT_ROLE_NOT_FOUND("Didn't find init role"),
    SETTING_NULL("Setting is null"),
}
