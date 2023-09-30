package com.erp.sf.entity.impl

import com.erp.sf.entity.SysUser
import com.erp.sf.mapper.SysUserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

class SysUserTest(dao: SysUserMapper, number: Long) : BaseTestEntity<SysUser, SysUserMapper>(dao, number) {
    override fun toList(number: Long) {
        for (i in 0 until number) {
            val random = UUID.randomUUID().toString()
            val sysUser =
                SysUser(0, UUID.randomUUID().toString(), BCryptPasswordEncoder(10).encode(random))
            dao.insert(sysUser)
            sysUser.password = random
            list.add(sysUser)
        }
    }

}

