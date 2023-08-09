package com.erp.sf.mapper

import com.erp.sf.JunitMapper
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired

class MenuDishMapperTest : JunitMapper() {
    @Autowired
    private lateinit var menuDishMapper: MenuDishMapper

    @BeforeEach
    override fun beforeEach() {
        TODO("Not yet implemented")
    }
}