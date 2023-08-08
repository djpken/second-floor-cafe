package com.erp.sf.entity.impl

import com.erp.sf.entity.SysMenu
import com.erp.sf.entity.SysRole
import com.erp.sf.mapper.SysMenuMapper
import com.erp.sf.mapper.SysRoleMapper
import java.util.*

class SysRoleTest(override val dao: SysRoleMapper) : BaseTestEntity<SysRole, SysRoleMapper>(dao) {
    override fun toList(number: Int) {
        for (i in 0 until number) {
            val sysRole = SysRole(0, UUID.randomUUID().toString())
            dao.insert(sysRole)
            list.add(sysRole)
        }
    }

    override fun before() {
        beforeCount= dao.selectCount(null)
    }


    override fun after() {
        afterCount= dao.selectCount(null)
    }



    override fun init(number: Int): SysRoleTest {
        before()
        toList(number)
        after()
        return this
    }
}

