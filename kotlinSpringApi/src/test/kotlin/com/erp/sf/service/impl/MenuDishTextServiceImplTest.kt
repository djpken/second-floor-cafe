package com.erp.sf.service.impl

import com.erp.sf.JunitService
import com.erp.sf.entity.impl.MenuDishTest
import com.erp.sf.mapper.MenuDishTextMapper
import com.erp.sf.service.MenuDishTextService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class MenuDishTextServiceImplTest : JunitService() {
    @Autowired
    private lateinit var menuDishTextService: MenuDishTextService

    @Autowired
    private lateinit var menuDishMapper: MenuDishTextMapper
    private lateinit var menuDishTest: MenuDishTest

    @BeforeEach
    override fun beforeEach() {
        menuDishTest = MenuDishTest(menuDishMapper, 5);
    }

    @Test
    fun selectAllDish() {
        val size = menuDishTextService.selectAllMenuDishText().size.toLong()
        Assertions.assertEquals(menuDishTest.beforeCount, size - 5)
        Assertions.assertEquals(menuDishTest.afterCount, size)
    }

    @Test
    fun selectAllMenuDish() {
        val selectMenuDishBySeason = menuDishTextService.selectMenuDishTextBySeason(2023)
        Assertions.assertFalse(selectMenuDishBySeason.isEmpty())
    }
}