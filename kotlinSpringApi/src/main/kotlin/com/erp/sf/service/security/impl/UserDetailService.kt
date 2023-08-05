package com.erp.sf.service.security.impl

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
import com.erp.sf.mapper.EmployeeMapper
import com.erp.sf.mapper.SysMenuMapper
import com.erp.sf.model.Employee
import com.erp.sf.model.LoginUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailService :UserDetailsService {
    @Autowired
    private lateinit var employeeMapper: EmployeeMapper
    @Autowired
    private lateinit var sysMenuMapper: SysMenuMapper
    override fun loadUserByUsername(username: String?): UserDetails {
        val query= LambdaQueryWrapper<Employee>().eq(Employee::username, username)
        val employee=employeeMapper.selectOne(query)
        val id = employee.id?:throw UsernameNotFoundException("Username not found :${username}")
        val auth = sysMenuMapper.selectPermsByUserId(id)
        return LoginUser(employee,auth)
    }
}