package com.erp.sf.service.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.erp.sf.entity.MenuDishText
import com.erp.sf.mapper.MenuDishTextMapper
import com.erp.sf.service.MenuDishTextService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MenuDishTextServiceImpl : ServiceImpl<MenuDishTextMapper, MenuDishText>(), MenuDishTextService {
    @Autowired
    private lateinit var menuDishTextMapper: MenuDishTextMapper
    override fun selectAllMenuDishText(): List<MenuDishText> = menuDishTextMapper.selectList(null)
    override fun selectMenuDishTextBySeason(season: Int): List<MenuDishText> {
        val eq = KtQueryWrapper(MenuDishText::class.java).eq(MenuDishText::season, season)
        return menuDishTextMapper.selectList(eq) ?: emptyList()
    }

    override fun updateMenuDishText(menuDishText: MenuDishText): MenuDishText {
        menuDishTextMapper.updateById(menuDishText)
        return menuDishText
    }

    override fun insertListMenuDishText(list: List<MenuDishText>): List<MenuDishText> {
        menuDishTextMapper.insertBatch(list)
        list.drop(1).forEachIndexed { index, score ->
            score.id = list[0].id!! + index + 1
        }
        return list
    }

}