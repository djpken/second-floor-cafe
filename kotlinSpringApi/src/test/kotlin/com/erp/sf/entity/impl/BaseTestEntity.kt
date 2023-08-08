package com.erp.sf.entity.impl

import com.baomidou.mybatisplus.core.mapper.BaseMapper

abstract class BaseTestEntity<E, T : BaseMapper<E>>(dao: T){
    abstract val dao: T
    var beforeCount: Long= 0
    var afterCount: Long = 0
    val list = mutableListOf<E>()
    abstract fun before()
    abstract fun after()
    abstract fun init(number: Int): Any
    abstract fun toList(number:Int)
}