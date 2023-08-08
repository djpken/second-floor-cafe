package com.erp.sf.mapper

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest
import com.erp.sf.JunitBase
import com.erp.sf.entity.SysMenu
import com.erp.sf.entity.impl.SysMenuTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

class MenuMapperTest : JunitBase() {
    @Autowired
    private lateinit var sysMenuMapper: SysMenuMapper

    @Test
    fun selectPermsByUserId() {
        val sysMenuTest = SysMenuTest(sysMenuMapper).init(5)
        println(sysMenuTest.beforeCount)
        println(sysMenuTest.afterCount)
        println(sysMenuTest.list)
        println(sysMenuMapper.selectPermsByUserId(2L))

    }
}