package com.erp.sf.component.impl

import com.erp.sf.component.PasswordComponent
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordComponentImpl : PasswordComponent {
    private val passwordParserTime = 10
    private val match: Regex = Regex("\\\$2a\\\$${Regex.escape(passwordParserTime.toString())}\\\$.*")
    override fun encode(password: String): String {
        return BCryptPasswordEncoder(passwordParserTime).encode(password)
    }

    override fun check(password: String): Boolean {
        return password.matches(match)
    }

    override fun getPasswordEncode(): PasswordEncoder {
        return BCryptPasswordEncoder(passwordParserTime)
    }

    override fun match(expected: String, actual: String): Boolean {
        return BCryptPasswordEncoder(passwordParserTime).matches(expected,actual)
    }
}