package com.erp.sf.entity.impl

import com.erp.sf.entity.Setting
import com.erp.sf.entity.SysUser
import com.erp.sf.mapper.SettingMapper
import com.erp.sf.mapper.SysUserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

class SettingTest(dao: SettingMapper, number: Long) : BaseTestEntity<Setting, SettingMapper>(dao, number) {
    override fun toList(number: Long) {
        for (i in 0 until number) {
            val random = UUID.randomUUID().toString()
            val setting =
                Setting(0, random, random)
            dao.insertSetting(setting)
            list.add(setting)
        }
    }

}

