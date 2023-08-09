package com.erp.sf.service.impl

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.erp.sf.mapper.MenuDishMapper
import com.erp.sf.entity.MenuDish
import com.erp.sf.service.MenuDishService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MenuDishServiceImpl : ServiceImpl<MenuDishMapper, MenuDish>(), MenuDishService {
    @Autowired
    private lateinit var menuDishMapper: MenuDishMapper
    override fun selectAllDish(): List<MenuDish> = menuDishMapper.selectList(null)

}