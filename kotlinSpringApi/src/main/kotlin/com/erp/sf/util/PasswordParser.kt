package com.erp.sf.util

import org.springframework.security.crypto.password.PasswordEncoder

interface PasswordParser {
    fun encode(password: String): String
    fun check(password: String): Boolean
    fun getPasswordEncode(): PasswordEncoder
    fun match(expected: String, actual: String): Boolean
}