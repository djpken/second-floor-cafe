package com.erp.sf.util.impl

import com.erp.sf.JunitService
import com.erp.sf.util.RedisUtil
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class RedisUtilImplTest : JunitService() {
    @Autowired
    private lateinit var redisUtil: RedisUtil

    @BeforeEach
    override fun beforeEach() {
    }

    @Test
    fun test() {
        redisUtil["123"] = "1234"
        val any = redisUtil["123"]
        Assertions.assertEquals("1234", any)
    }
}