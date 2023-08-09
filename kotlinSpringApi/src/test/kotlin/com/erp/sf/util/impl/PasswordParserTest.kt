package com.erp.sf.util.impl

import com.erp.sf.JunitService
import com.erp.sf.mapper.SysUserMapper
import com.erp.sf.util.PasswordParser
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


class PasswordParserTest :JunitService(){
    @Autowired
    private lateinit var sysUserMapper: SysUserMapper

    @Autowired
    private lateinit var passwordParser: PasswordParser

    @BeforeEach
    override fun beforeEach() {
        val selectList = sysUserMapper.selectList(null)
        selectList.filter { !passwordParser.check(it.password) }
            .map {
                it.password = passwordParser.encode(it.password)
                it
            }
            .onEach { sysUserMapper.updateById(it) }
    }
    @Test
    fun getPasswordEncode() {
        Assertions.assertTrue(passwordParser.getPasswordEncode() is BCryptPasswordEncoder)
    }
    @Test
    fun match() {
        val encode = BCryptPasswordEncoder(10).encode("1234")
        Assertions.assertTrue(passwordParser.match("1234",encode))
    }
    @Test
    fun check() {
        val encode = BCryptPasswordEncoder(10).encode("1234")
        passwordParser.check(encode)
    }

    @Test
    fun encode() {
        val encode = passwordParser.encode("1234")
        Assertions.assertTrue(BCryptPasswordEncoder(10).matches("1234", encode))
    }

}

