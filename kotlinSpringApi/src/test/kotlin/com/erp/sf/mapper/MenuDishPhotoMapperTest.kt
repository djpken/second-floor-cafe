package com.erp.sf.mapper

import cn.hutool.core.io.FileUtil
import com.erp.sf.entity.MenuDishPhoto
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource


@SpringBootTest
class MenuDishPhotoMapperTest {
    @Autowired
    private lateinit var menuDishPhotoMapper: MenuDishPhotoMapper

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        private val logger: Logger = LoggerFactory.getLogger(javaClass)
        private var listResource: List<MenuDishPhoto> = emptyList()
        private const val fileExtension = ".jpg"

        @Autowired
        private lateinit var menuDishPhotoMapper: MenuDishPhotoMapper

        @JvmStatic
        @BeforeAll
        fun beforeEach() {
            val fileDir = ClassPathResource("image").file
            listResource = fileDir.listFiles()!!.map {
                MenuDishPhoto(
                    it.name.substring(0, it.name.length - fileExtension.length).toLong(),
                    it.name.substring(0, it.name.length - fileExtension.length).toLong(),
                    it.name,
                    FileUtil.readBytes(it)
                )
            }
        }
    }

    @Test
    fun selectMenuDishPhotoBySeason() {
        if (menuDishPhotoMapper.selectCount(null) == 0L) {
            listResource.map { menuDishPhotoMapper.insert(it) }
        }

        // case 1
        val actual = menuDishPhotoMapper.selectMenuDishPhotoBySeason(2023).size
        Assertions.assertNotEquals(0,actual)
    }
}
