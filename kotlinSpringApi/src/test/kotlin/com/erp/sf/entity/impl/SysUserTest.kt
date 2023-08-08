package com.erp.sf.entity.impl

import com.erp.sf.entity.SysMenu
import com.erp.sf.entity.SysUser
import com.erp.sf.mapper.SysMenuMapper
import com.erp.sf.mapper.SysUserMapper
import java.util.*

class SysUserTest(override val dao: SysUserMapper) : BaseTestEntity<SysUser, SysUserMapper>(dao) {
    override fun toList(number: Int) {
        for (i in 0 until number) {
            val sysUser = SysUser(0, UUID.randomUUID().toString())
            dao.insert(sysUser)
            list.add(sysUser)
        }
    }

    override fun before() {
        beforeCount= dao.selectCount(null)
    }


    override fun after() {
        afterCount= dao.selectCount(null)
    }

    override fun init(number: Int): SysUserTest {
        before()
        toList(number)
        after()
        return this
    }
}

