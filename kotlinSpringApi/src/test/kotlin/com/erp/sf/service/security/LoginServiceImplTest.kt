package com.erp.sf.service.security

import cn.hutool.jwt.JWTUtil
import com.erp.sf.JunitService
import com.erp.sf.component.RedisComponent
import com.erp.sf.entity.SysRoleMenu
import com.erp.sf.entity.SysUser
import com.erp.sf.entity.SysUserRole
import com.erp.sf.entity.impl.SysMenuTest
import com.erp.sf.entity.impl.SysRoleTest
import com.erp.sf.entity.impl.SysUserTest
import com.erp.sf.exception.DataException
import com.erp.sf.mapper.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class LoginServiceImplTest : JunitService() {
    @Autowired
    private lateinit var redisUtil: RedisComponent

    @Autowired
    private lateinit var loginService: LoginService

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
        val listSysRoleMenu = mutableListOf<SysRoleMenu>()
        val listSysUserRole = mutableListOf<SysUserRole>()
        for (i in 0 until number) {
            listSysRoleMenu.add(SysRoleMenu(0, sysRoleTest.list[i].id, sysMenuTest.list[i].id))
            listSysUserRole.add(SysUserRole(0, sysUserTest.list[i].id, sysRoleTest.list[i].id))
        }
        sysRoleMenuMapper.insertBatch(listSysRoleMenu)
        sysUserRoleMapper.insertBatch(listSysUserRole)
    }

    @Test
    fun login() {
        for (i in 0 until number) {
            val sysUser = sysUserTest.list[i]
            val login = loginService.login(sysUser)
            //case 1
            Assertions.assertEquals(sysUser.id, login.sysUser.id)

            //case 2
            Assertions.assertEquals(sysMenuMapper.selectPermsByUserId(sysUser.id!!).toString(), login.authority)

            //case 3
            val s = redisUtil["login:${sysUser.id}"]
            Assertions.assertEquals(sysMenuMapper.selectPermsByUserId(sysUser.id!!).toString(), s)

            //case 4
            val authorization = login.authorization
            println(authorization)
            val parseToken = JWTUtil.parseToken(authorization.substring(7))
            val id: Long = parseToken.getPayload("id").toString().toLong()
            Assertions.assertEquals(sysUser.id, id)
        }
    }

    @Test
    fun logout() {
        Assertions.assertThrows(
            NullPointerException::class.java,
            { loginService.logout() },
            "it should throw"
        )
    }

    @Test
    fun register() {
        //case 1
        for (i in 0 until number) {
            Assertions.assertThrows(
                DataException::class.java,
                { loginService.register(sysUserTest.list[i]) },
                "it should throw"
            )
        }

        //case 2
        for (i in 0 until number) {
            val sample = SysUser(0, UUID.randomUUID().toString(), "SF")
            val map = loginService.register(sample)
            val sysUser = map.sysUser
            Assertions.assertEquals(sample.username, sysUser.username)

        }

    }

}