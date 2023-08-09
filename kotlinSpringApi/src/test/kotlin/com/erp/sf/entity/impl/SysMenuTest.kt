package com.erp.sf.entity.impl

import com.erp.sf.entity.SysMenu
import com.erp.sf.mapper.SysMenuMapper
import java.util.*

class SysMenuTest(dao:SysMenuMapper,number: Int) : BaseTestEntity<SysMenu, SysMenuMapper>(dao,number) {
    override fun toList(number: Int) {
        for (i in 0 until number) {
            val sysMenu = SysMenu(0, UUID.randomUUID().toString())
            dao.insert(sysMenu)
            list.add(sysMenu)
        }
    }




}

