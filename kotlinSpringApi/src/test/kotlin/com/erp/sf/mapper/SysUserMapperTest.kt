package com.erp.sf.mapper

import com.baomidou.mybatisplus.core.toolkit.Assert
import com.erp.sf.JunitBase
import com.erp.sf.entity.impl.SysUserTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class SysUserMapperTest : JunitBase() {
    @Autowired
    private lateinit var sysUserMapper: SysUserMapper
    var sysUserTest: SysUserTest? = null;

    @BeforeEach
    fun beforeEach() {
        sysUserTest = SysUserTest(sysUserMapper).init(5)
    }

    @Test
    fun selectPermsByUserId() {
        for (i in 0 until (sysUserTest?.list?.size ?: 0)) {
            val sysUser=sysUserMapper.selectOneByUsername(sysUserTest?.list?.get(i)?.username ?: "")
            Assertions.assertEquals(sysUserTest?.list?.get(i),sysUser)
        }
    }
}