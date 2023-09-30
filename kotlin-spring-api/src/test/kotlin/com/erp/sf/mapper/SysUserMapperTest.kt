package com.erp.sf.mapper

import com.erp.sf.JunitMapper
import com.erp.sf.JunitService
import com.erp.sf.entity.impl.SysUserTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class SysUserMapperTest : JunitService() {
    private lateinit var sysUserTest: SysUserTest

    @BeforeEach
    override fun beforeEach() {
        sysUserTest = SysUserTest(sysUserMapper, number)
    }

    @Test
    fun selectOneByUsername() {
        for (j in 0 until number) {
            val i = j.toInt()
            val sysUser = sysUserMapper.selectOneByUsername(sysUserTest.list[i].username)
            Assertions.assertEquals(sysUserTest.list[i.toInt()].id, sysUser.id)
        }
    }
}