package com.erp.sf.service.security.impl

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtQueryChainWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.erp.sf.mapper.SysUserMapper
import com.erp.sf.mapper.SysMenuMapper
import com.erp.sf.entity.SysUser
import com.erp.sf.model.LoginUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailServiceImpl : UserDetailsService {
    @Autowired
    private lateinit var sysUserMapper: SysUserMapper

    @Autowired
    private lateinit var sysMenuMapper: SysMenuMapper
    override fun loadUserByUsername(username: String): UserDetails {
        val eq = KtQueryWrapper(SysUser::class.java).eq(SysUser::username, username)
        val sysUser = sysUserMapper.selectOne(eq) ?: throw UsernameNotFoundException("Username not found :${username}")
        val auth = sysMenuMapper.selectPermsByUserId(sysUser.id!!)
        return LoginUser(sysUser, auth)
    }
}