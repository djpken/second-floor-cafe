package com.erp.sf.mapper

import com.erp.sf.JunitMapper
import com.erp.sf.entity.SysRoleMenu
import com.erp.sf.entity.SysUserRole
import com.erp.sf.entity.impl.SysMenuTest
import com.erp.sf.entity.impl.SysRoleTest
import com.erp.sf.entity.impl.SysUserTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class SysMenuMapperTest : JunitMapper(){
    @Autowired
    private lateinit var sysMenuMapper: SysMenuMapper

    @Autowired
    private lateinit var sysRoleMapper: SysRoleMapper

    @Autowired
    private lateinit var sysUserMapper: SysUserMapper

    @Autowired
    private lateinit var sysUserRoleMapper: SysUserRoleMapper

    @Autowired
    private lateinit var sysRoleMenuMapper: SysRoleMenuMapper


    private lateinit var sysMenuTest: SysMenuTest
    private lateinit var sysUserTest: SysUserTest
    private lateinit var sysRoleTest: SysRoleTest
    private val number = 3

    @BeforeEach
    override fun beforeEach() {
        sysMenuTest = SysMenuTest(sysMenuMapper, number)
        sysUserTest = SysUserTest(sysUserMapper, number)
        sysRoleTest = SysRoleTest(sysRoleMapper, number)
        for (i in 0 until number) {
            val sysRoleMenu = SysRoleMenu(0, sysRoleTest.list[i].id, sysMenuTest.list[i].id)
            val sysUserRole = SysUserRole(0, sysUserTest.list[i].id, sysRoleTest.list[i].id)
            sysRoleMenuMapper.insert(sysRoleMenu)
            sysUserRoleMapper.insert(sysUserRole)
        }
    }

    @Test
    fun selectPermsByUserId() {
        //case 1
        for (i in 0 until number) {
            val selectPermsByUserId = sysMenuMapper.selectPermsByUserId(sysUserTest.list[i].id!!)[0]
            Assertions.assertEquals(sysMenuTest.list[i].perms, selectPermsByUserId)
        }
    }
}