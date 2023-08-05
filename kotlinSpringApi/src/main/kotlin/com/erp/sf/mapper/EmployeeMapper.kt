package com.erp.sf.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.erp.sf.model.Employee
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface EmployeeMapper:BaseMapper<Employee> {
    fun selectOneByUsername(@Param("username") username:String):Employee
}