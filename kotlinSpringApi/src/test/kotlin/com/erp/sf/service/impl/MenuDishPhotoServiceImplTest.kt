package com.erp.sf.service.impl

import com.erp.sf.JunitService
import com.erp.sf.entity.impl.MenuDishTest
import com.erp.sf.mapper.MenuDishTextMapper
import com.erp.sf.service.MenuDishPhotoService
import com.erp.sf.service.MenuDishTextService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class MenuDishPhotoServiceImplTest : JunitService() {
    @Autowired
    private lateinit var menuDishPhotoService: MenuDishPhotoService

    @BeforeEach
    override fun beforeEach() {
    }

    @Test
    fun selectAllDish() {
    }

    @Test
    fun selectAllMenuDish() {
    }
}