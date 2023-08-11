package com.erp.sf.mapper

import com.erp.sf.JunitMapper
import com.erp.sf.JunitService
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired

class MenuDishMapperTest : JunitService() {
    @Autowired
    private lateinit var menuDishMapper: MenuDishMapper

    @BeforeEach
    override fun beforeEach() {
        TODO("Not yet implemented")
    }
}