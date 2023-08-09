package com.erp.sf.service.impl

import com.erp.sf.JunitService
import com.erp.sf.entity.impl.MenuDishTest
import com.erp.sf.mapper.MenuDishMapper
import com.erp.sf.service.MenuDishService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

class MenuDishServiceImplTest : JunitService(){
    @Autowired
    private lateinit var menuDishService: MenuDishService

    @Autowired
    private lateinit var menuDishMapper: MenuDishMapper
    private lateinit var menuDishTest: MenuDishTest
    @BeforeEach
    override fun beforeEach() {
        menuDishTest = MenuDishTest(menuDishMapper, 5);
    }

    @Test
    fun selectAllDish() {
        val size = menuDishService.selectAllDish().size.toLong()
        Assertions.assertEquals(menuDishTest.beforeCount, size - 5)
        Assertions.assertEquals(menuDishTest.afterCount, size)
    }
}