package com.erp.sf.service.security.impl

import cn.hutool.jwt.JWTUtil
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.erp.sf.entity.SysRole
import com.erp.sf.util.RedisUtil
import com.erp.sf.mapper.SysUserMapper
import com.erp.sf.mapper.SysMenuMapper
import com.erp.sf.entity.SysUser
import com.erp.sf.entity.SysUserRole
import com.erp.sf.exception.DataException
import com.erp.sf.mapper.SysRoleMapper
import com.erp.sf.mapper.SysUserRoleMapper
import com.erp.sf.model.LoginUser
import com.erp.sf.model.SystemResponse
import com.erp.sf.service.security.LoginService
import com.erp.sf.util.PasswordParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import kotlin.collections.HashMap

@Service
class LoginServiceImpl : LoginService {
    @Autowired
    private lateinit var sysRoleMapper: SysRoleMapper

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var redisUtil: RedisUtil

    @Autowired
    private lateinit var sysUserMapper: SysUserMapper

    @Autowired
    private lateinit var sysMenuMapper: SysMenuMapper

    @Autowired
    private lateinit var passwordParser: PasswordParser

    @Autowired
    private lateinit var sysUserRoleMapper: SysUserRoleMapper
    private val initRole = "visitor"
    override fun login(sysUser: SysUser): Map<String, Any> {
        val authenticate =
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(sysUser.username, sysUser.password))
        val loginUser = authenticate.principal as LoginUser
        val userId = loginUser.sysUser.id.toString()
        val auth = loginUser.authorities.toString()
        redisUtil["login:$userId"] = auth
        val jwt = setJwt(userId)
        return getMap(loginUser.sysUser, auth, jwt)

    }

    override fun logout(): Map<String, Any> {
        val usernamePasswordAuthenticationToken =
            SecurityContextHolder.getContext().authentication as UsernamePasswordAuthenticationToken
        val loginUser = usernamePasswordAuthenticationToken.principal as LoginUser
        redisUtil.del("login:${loginUser.sysUser.id}")
        return hashMapOf<String, Any>("message" to "登出成功")
    }

    override fun register(sysUser: SysUser): Map<String, Any> {
        if (sysUserMapper.exists(KtQueryWrapper(SysUser::class.java).eq(SysUser::username, sysUser.username))) {
            throw DataException(SystemResponse.dataFailed("data is exists"))
        }
        sysUser.password = passwordParser.encode(sysUser.password)
        sysUserMapper.insert(sysUser)
        sysUserRoleMapper.insert(SysUserRole(0, sysUser.id, getInitRoleId()))

        val auth = sysUser.id?.let { sysMenuMapper.selectPermsByUserId(it).toString() } ?: return emptyMap()
        val jwt = setJwt(sysUser.id.toString())
        redisUtil["login"] = auth
        return getMap(sysUser, auth, jwt)
    }

    fun setJwt(id: String): String {
        val map = HashMap<String, Any>()
        map["id"] = id
        map["expire"] = System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7
        return JWTUtil.createToken(map, "123".toByteArray())
    }

    fun getMap(sysUser: SysUser, auth: String, jwt: String): HashMap<String, Any> {
        val map = HashMap<String, Any>()
        sysUser.password = ""
        map["user"] = sysUser
        map["authority"] = auth
        map["authorization"] = "Bearer $jwt"
        return map
    }

    fun getInitRoleId(): Long {
        return sysRoleMapper.selectOne(KtQueryWrapper(SysRole::class.java).eq(SysRole::name, initRole)).id
            ?: throw DataException(SystemResponse.dataFailed("Didn't find init role"))
    }
}