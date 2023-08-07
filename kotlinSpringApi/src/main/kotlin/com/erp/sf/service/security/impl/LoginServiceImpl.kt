package com.erp.sf.service.security.impl

import cn.hutool.jwt.JWTUtil
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
import com.erp.sf.util.RedisUtil
import com.erp.sf.mapper.SysUserMapper
import com.erp.sf.mapper.SysMenuMapper
import com.erp.sf.entity.SysUser
import com.erp.sf.model.LoginUser
import com.erp.sf.service.security.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import kotlin.collections.HashMap

@Service
class LoginServiceImpl : LoginService {
    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var redisUtil: RedisUtil

    @Autowired
    private lateinit var employeeMapper: SysUserMapper

    @Autowired
    private lateinit var sysMenuMapper: SysMenuMapper


    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    override fun login(employee: SysUser): Map<String, Any> {
        val usernamePasswordAuthenticationToken =
            UsernamePasswordAuthenticationToken(employee.username, employee.password)
        val authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken)
        val loginUser = authenticate.principal as LoginUser
        val userId = loginUser.employee.id.toString()
        val auth = loginUser.authorities.toString()
        redisUtil["login:$userId"] = auth
        val setJwt = setJwt(userId)
        val map = HashMap<String, Any>()
        map["user"] = loginUser.employee
        map["authority"] = auth
        map["Authorization"] = "Bearer $setJwt"
        return map

    }

    override fun logout(): Map<String, Any> {
        val usernamePasswordAuthenticationToken =
            SecurityContextHolder.getContext().authentication as UsernamePasswordAuthenticationToken
        val loginUser = usernamePasswordAuthenticationToken.principal as LoginUser
        redisUtil.del("login:${loginUser.employee.id}")
        return hashMapOf<String ,Any>("message" to "登出成功")
    }

    override fun register(employee: SysUser): Map<String, Any> {
        val lambdaQueryWrapper = LambdaQueryWrapper<SysUser>().eq(SysUser::username, employee.username)
        if (employeeMapper.exists(lambdaQueryWrapper)) {
            return emptyMap()
        }
        employee.password = passwordEncoder.encode(employee.username)
        employeeMapper.insert(employee)
        employeeMapper.selectOneByUsername(employee.username)

        val sysEmployeeRole = SysEmployeeRole(employee.id, 6L)
        sysEmployeeRoleMapper.insert(sysEmployeeRole)
        val userId = employee.id
        val auth = userId?.let { sysMenuMapper.selectPermsByUserId(it).toString() } ?: return emptyMap()

        redisUtil["login"] = auth
        val jwt = setJwt(userId.toString())

        val map = HashMap<String, Any>()
        map["user"] = employee
        map["authority"] = auth
        map["Authorization"] = "Bearer $jwt"
        return map
    }

    fun setJwt(id: String): String {
        val map = HashMap<String, Any>()
        map["id"] = id
        map["expire"] = System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7
        return JWTUtil.createToken(map, "123".toByteArray())
    }
}