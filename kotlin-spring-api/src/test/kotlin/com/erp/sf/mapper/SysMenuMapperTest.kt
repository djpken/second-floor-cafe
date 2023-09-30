package com.erp.sf.mapper

import com.erp.sf.JunitMapper
import com.erp.sf.JunitService
import com.erp.sf.entity.SysRoleMenu
import com.erp.sf.entity.SysUserRole
import com.erp.sf.entity.impl.SysMenuTest
import com.erp.sf.entity.impl.SysRoleTest
import com.erp.sf.entity.impl.SysUserTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class SysMenuMapperTest : JunitService() {



    private lateinit var sysMenuTest: SysMenuTest
    private lateinit var sysUserTest: SysUserTest
    private lateinit var sysRoleTest: SysRoleTest

    @BeforeEach
    override fun beforeEach() {
        sysMenuTest = SysMenuTest(sysMenuMapper, number)
        sysUserTest = SysUserTest(sysUserMapper, number)
        sysRoleTest = SysRoleTest(sysRoleMapper, number)
        for (j in 0 until number) {
            val i= j.toInt()
            sysRoleMenuMapper.insert(SysRoleMenu(0, sysRoleTest.list[i].id, sysMenuTest.list[i].id))
            sysUserRoleMapper.insert(SysUserRole(0, sysUserTest.list[i].id, sysRoleTest.list[i].id))
        }
    }

    @Test
    fun selectPermsByUserId() {
        //case 1
        for (j in 0 until number) {
            val i= j.toInt()
            val selectPermsByUserId = sysMenuMapper.selectPermsByUserId(sysUserTest.list[i].id!!)[0]
            Assertions.assertEquals(sysMenuTest.list[i].perms, selectPermsByUserId)
        }
    }
}