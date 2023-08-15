package com.erp.sf.service.security.impl

import cn.hutool.jwt.JWTUtil
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.erp.sf.component.PasswordComponent
import com.erp.sf.component.RedisComponent
import com.erp.sf.component.SettingComponent
import com.erp.sf.constant.M
import com.erp.sf.constant.S
import com.erp.sf.entity.SysRole
import com.erp.sf.entity.SysUser
import com.erp.sf.entity.SysUserRole
import com.erp.sf.exception.DataException
import com.erp.sf.mapper.SysMenuMapper
import com.erp.sf.mapper.SysRoleMapper
import com.erp.sf.mapper.SysUserMapper
import com.erp.sf.mapper.SysUserRoleMapper
import com.erp.sf.model.entity.LoginUser
import com.erp.sf.model.sys.SysResponse
import com.erp.sf.model.responseEntity.apiResposne.TokenModel
import com.erp.sf.service.security.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class LoginServiceImpl : LoginService {
    @Autowired
    private lateinit var s: SettingComponent

    @Autowired
    private lateinit var sysRoleMapper: SysRoleMapper

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var redisUtil: RedisComponent

    @Autowired
    private lateinit var sysUserMapper: SysUserMapper

    @Autowired
    private lateinit var sysMenuMapper: SysMenuMapper

    @Autowired
    private lateinit var passwordParser: PasswordComponent

    @Autowired
    private lateinit var sysUserRoleMapper: SysUserRoleMapper

    override fun login(sysUser: SysUser): TokenModel {
        val authenticate =
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(sysUser.username, sysUser.password))
        val loginUser = authenticate.principal as LoginUser
        val userId = loginUser.sysUser.id.toString()
        val auth = loginUser.authorities.toString()
        redisUtil["login:$userId"] = auth
        val jwt = setJwt(userId)
        return TokenModel("Bearer $jwt", auth, loginUser.sysUser)

    }

    override fun logout(): TokenModel {
        val usernamePasswordAuthenticationToken =
            SecurityContextHolder.getContext().authentication as UsernamePasswordAuthenticationToken
        val loginUser = usernamePasswordAuthenticationToken.principal as LoginUser
        redisUtil.del("login:${loginUser.sysUser.id}")
        return TokenModel("", "", loginUser.sysUser)
    }

    override fun register(sysUser: SysUser): TokenModel {
        if (sysUser.password != s.get(S.INVITE_CODE)) {
            throw DataException(SysResponse(M.INVITE_CODE_ERROR))
        }
        if (sysUserMapper.exists(KtQueryWrapper(SysUser::class.java).eq(SysUser::username, sysUser.username))) {
            throw DataException(SysResponse(M.DATA_EXISTS))
        }
        sysUser.password = passwordParser.encode(sysUser.password)
        sysUserMapper.insert(sysUser)
        sysUserRoleMapper.insert(SysUserRole(0, sysUser.id, getInitRoleId()))

        val auth = sysUser.id?.let { sysMenuMapper.selectPermsByUserId(it).toString() } ?: ""
        val jwt = setJwt(sysUser.id.toString())
        redisUtil["login"] = auth
        return TokenModel("Bearer $jwt", auth, sysUser)
    }

    fun setJwt(id: String): String {
        val map = HashMap<String, Any>()
        map["id"] = id
        map["expire"] = System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7
        return JWTUtil.createToken(map, "123".toByteArray())
    }


    fun getInitRoleId(): Long {
        return sysRoleMapper.selectOne(KtQueryWrapper(SysRole::class.java).eq(SysRole::name, s.get(S.INIT_ROLE))).id
            ?: throw DataException(SysResponse(M.INIT_ROLE_NOT_FOUND))
    }
}