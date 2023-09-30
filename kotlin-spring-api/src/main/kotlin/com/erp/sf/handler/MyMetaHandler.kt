package com.erp.sf.handler

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler
import org.apache.ibatis.reflection.MetaObject
import java.util.*

class MyMetaHandler :MetaObjectHandler{
    override fun insertFill(metaObject: MetaObject) {
        setFieldValByName("createTime",Date(),metaObject)
        setFieldValByName("createTime",Date(),metaObject)
    }

    override fun updateFill(metaObject: MetaObject) {
        setFieldValByName("updateTime",Date(),metaObject)
    }
}