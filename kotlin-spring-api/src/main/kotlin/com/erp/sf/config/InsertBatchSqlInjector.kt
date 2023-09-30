package com.erp.sf.config

import InsertBatch
import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.core.injector.AbstractMethod
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector
import com.baomidou.mybatisplus.core.metadata.TableInfo


class InsertBatchSqlInjector : DefaultSqlInjector() {
    override fun getMethodList(mapperClass: Class<*>?, tableInfo: TableInfo?): List<AbstractMethod> {
        // super.getMethodList() 保留 Mybatis Plus 自带的方法
        val methodList = super.getMethodList(mapperClass, tableInfo)
        // 添加自定义方法：批量插入，方法名为 insertBatchSomeColumn
        methodList.add(InsertBatch { i -> i.fieldFill != FieldFill.UPDATE })
        return methodList
    }
}