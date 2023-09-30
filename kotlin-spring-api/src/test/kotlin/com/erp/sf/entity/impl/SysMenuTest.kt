package com.erp.sf.entity.impl

import com.erp.sf.entity.SysMenu
import com.erp.sf.mapper.SysMenuMapper
import java.util.*

class SysMenuTest(dao:SysMenuMapper,number: Long) : BaseTestEntity<SysMenu, SysMenuMapper>(dao,number) {
    override fun toList(number: Long) {
        for (i in 0 until number) {
            val sysMenu = SysMenu(0, UUID.randomUUID().toString())
            dao.insert(sysMenu)
            list.add(sysMenu)
        }
    }




}

