package com.erp.sf.entity.impl

import com.baomidou.mybatisplus.core.mapper.BaseMapper

abstract class BaseTestEntity<E, T : BaseMapper<E>>(
    val dao: T,
    val number: Int,
    var beforeCount: Long = 0,
    var afterCount: Long = 0,
    val list: MutableList<E>
) {
    constructor(dao: T, number: Int) : this(dao, number, 0, 0, mutableListOf())

    init {
        before()
        toList(number)
        after()
    }

    fun before() {
        beforeCount = dao.selectCount(null)
    }

    fun after() {
        afterCount = dao.selectCount(null)
    }

    abstract fun toList(number: Int)
}
