package com.erp.sf.service.security

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.core.toolkit.Wrappers
import com.erp.sf.JunitService
import com.erp.sf.entity.SysRoleMenu
import com.erp.sf.entity.SysUser
import com.erp.sf.entity.SysUserRole
import com.erp.sf.entity.impl.SysMenuTest
import com.erp.sf.entity.impl.SysRoleTest
import com.erp.sf.entity.impl.SysUserTest
import com.erp.sf.mapper.*
import com.erp.sf.util.PasswordParser
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import java.util.*

class UserDetailServiceImplTest : JunitService() {
    @Autowired
    private lateinit var passwordParser: PasswordParser

    @Autowired
    private lateinit var userDetailService: UserDetailsService

    @Autowired
    private lateinit var sysRoleMapper: SysRoleMapper

    @Autowired
    private lateinit var sysMenuMapper: SysMenuMapper

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
    fun loadUserByUsername() {
        //case 1
        for (i in 0 until number) {
            val loadUserByUsername: UserDetails = userDetailService.loadUserByUsername(sysUserTest.list[i].username)
            println(loadUserByUsername)
            Assertions.assertEquals(sysUserTest.list[i].username, loadUserByUsername.username)
            Assertions.assertTrue(passwordParser.match(sysUserTest.list[i].password, loadUserByUsername.password))
            Assertions.assertEquals(
                sysMenuTest.list[i].perms,
                loadUserByUsername.authorities.toList()[0].toString()
            )
        }

        //case 2
        Assertions.assertThrows(
            UsernameNotFoundException::class.java,
            { userDetailService.loadUserByUsername(UUID.randomUUID().toString()) },
            "it should throw"
        )
    }
}