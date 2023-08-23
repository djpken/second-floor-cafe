package com.erp.sf.mapper

import com.erp.sf.JunitService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class MenuDishPhotoMapperTest :JunitService(){
    @Autowired
    private lateinit var menuDishPhotoMapper:MenuDishPhotoMapper
    @BeforeEach
    override fun beforeEach()
    {
    }
    @Test
    fun selectMenuDishPhotoBySeason(){
        menuDishPhotoMapper.selectMenuDishPhotoBySeason(2023)
    }

}