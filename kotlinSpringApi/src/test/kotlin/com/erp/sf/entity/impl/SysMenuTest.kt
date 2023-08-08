package com.erp.sf.entity.impl

import com.erp.sf.entity.SysMenu
import com.erp.sf.mapper.SysMenuMapper
import java.util.*

class SysMenuTest(override val dao: SysMenuMapper) : BaseTestEntity<SysMenu, SysMenuMapper>(dao) {
    override fun toList(number: Int) {
        for (i in 0 until number) {
            val sysMenu = SysMenu(0, UUID.randomUUID().toString())
            dao.insert(sysMenu)
            list.add(sysMenu)
        }
    }

    override fun before() {
        beforeCount= dao.selectCount(null)
    }


    override fun after() {
        afterCount= dao.selectCount(null)
    }



    override fun init(number: Int): SysMenuTest {
        before()
        toList(number)
        after()
        return this
    }
}

