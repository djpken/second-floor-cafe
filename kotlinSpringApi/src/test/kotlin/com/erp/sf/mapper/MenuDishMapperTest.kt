package com.erp.sf.mapper

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.erp.sf.JunitService
import com.erp.sf.entity.MenuDish
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class MenuDishMapperTest : JunitService() {
    @Autowired
    private lateinit var menuDishMapper: MenuDishMapper

    @BeforeEach
    override fun beforeEach() {
    }

    @Test
    public fun test() {
        menuDishMapper.selectList(KtQueryWrapper<MenuDish>(MenuDish::class.java).eq(MenuDish::season, 2023))
    }
}