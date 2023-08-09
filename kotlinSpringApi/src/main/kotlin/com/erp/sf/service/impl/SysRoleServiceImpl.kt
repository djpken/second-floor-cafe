package com.erp.sf.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.erp.sf.mapper.SysRoleMapper
import com.erp.sf.entity.SysRole
import com.erp.sf.service.SysRoleService
import org.springframework.stereotype.Service

@Service
class SysRoleServiceImpl : ServiceImpl<SysRoleMapper, SysRole>(), SysRoleService