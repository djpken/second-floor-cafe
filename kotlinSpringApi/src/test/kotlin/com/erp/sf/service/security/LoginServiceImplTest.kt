package com.erp.sf.service.security

import cn.hutool.jwt.JWTUtil
import com.erp.sf.JunitService
import com.erp.sf.entity.SysRoleMenu
import com.erp.sf.entity.SysUser
import com.erp.sf.entity.SysUserRole
import com.erp.sf.entity.impl.SysMenuTest
import com.erp.sf.entity.impl.SysRoleTest
import com.erp.sf.entity.impl.SysUserTest
import com.erp.sf.exception.DataException
import com.erp.sf.mapper.*
import com.erp.sf.util.RedisUtil
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.lang.NullPointerException
import java.util.*

class LoginServiceImplTest : JunitService() {
    @Autowired
    private lateinit var redisUtil: RedisUtil

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
        for (i in 0 until number) {
            val sysRoleMenu = SysRoleMenu(0, sysRoleTest.list[i].id, sysMenuTest.list[i].id)
            val sysUserRole = SysUserRole(0, sysUserTest.list[i].id, sysRoleTest.list[i].id)
            sysRoleMenuMapper.insert(sysRoleMenu)
            sysUserRoleMapper.insert(sysUserRole)
        }
    }

    @Test
    fun login() {
        for (i in 0 until number) {
            val sysUser = sysUserTest.list[i]
            val login = loginService.login(sysUser)
            //case 1
            Assertions.assertEquals(sysUser.id, (login["user"] as SysUser).id)

            //case 2
            Assertions.assertEquals(sysMenuMapper.selectPermsByUserId(sysUser.id!!).toString(), login["authority"])

            //case 3
            val s = redisUtil["login:${sysUser.id}"]
            Assertions.assertEquals(sysMenuMapper.selectPermsByUserId(sysUser.id!!).toString(), s)

            //case 4
            val authorization = login["authorization"].toString()
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
            val sample = SysUser(0, UUID.randomUUID().toString(), UUID.randomUUID().toString())
            val map = loginService.register(sample)
            val sysUser = map["user"] as SysUser
            Assertions.assertEquals(sample.username, sysUser.username)

        }

    }

}