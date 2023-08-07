package com.erp.sf.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.erp.sf.mapper.SysMenuMapper
import com.erp.sf.entity.SysMenu
import com.erp.sf.service.SysMenuService
import org.springframework.stereotype.Service

@Service
class SysMenuServiceImpl : ServiceImpl<SysMenuMapper, SysMenu>(),SysMenuService