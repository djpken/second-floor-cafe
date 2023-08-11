package com.erp.sf.component

import org.springframework.security.crypto.password.PasswordEncoder

interface PasswordComponent {
    fun encode(password: String): String
    fun check(password: String): Boolean
    fun getPasswordEncode(): PasswordEncoder
    fun match(expected: String, actual: String): Boolean
}