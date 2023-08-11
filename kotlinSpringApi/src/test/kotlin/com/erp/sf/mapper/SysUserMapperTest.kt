package com.erp.sf.mapper

import com.erp.sf.JunitMapper
import com.erp.sf.JunitService
import com.erp.sf.entity.impl.SysUserTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class SysUserMapperTest : JunitService() {
    @Autowired
    private lateinit var sysUserMapper: SysUserMapper
    private lateinit var sysUserTest: SysUserTest
    private val number = 5

    @BeforeEach
    override fun beforeEach() {
        sysUserTest = SysUserTest(sysUserMapper, number)
    }

    @Test
    fun selectOneByUsername() {
        for (i in 0 until number) {
            val sysUser = sysUserMapper.selectOneByUsername(sysUserTest.list[i].username)
            Assertions.assertEquals(sysUserTest.list[i].id, sysUser.id)
        }
    }
}