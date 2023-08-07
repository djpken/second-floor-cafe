package com.erp.sf.util

import org.springframework.stereotype.Component

interface RedisUtil {
    fun expire(key: String?, time: Long): Boolean

    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    fun getExpire(key: String?): Long

    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    fun hasKey(key: String?): Boolean

    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    fun del(vararg key: String?)
    // ============================String=============================
    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    operator fun get(key: String?): Any?

    /**
     * 普通缓存放入
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    operator fun set(key: String?, value: Any): Boolean

    /**
     * 普通缓存放入并设置时间
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    operator fun set(key: String?, value: Any, time: Long): Boolean

    /**
     * 递增
     * @param key   键
     * @param delta 要增加几(大于0)
     */
    fun incr(key: String?, delta: Long): Long

    /**
     * 递减
     * @param key   键
     * @param delta 要减少几(小于0)
     */
    fun decr(key: String?, delta: Long): Long
    // ================================Map=================================
    /**
     * HashGet
     * @param key  键 不能为null
     * @param item 项 不能为null
     */
    fun hget(key: String?, item: String?): Any?
    /**
     * 获取hashKey对应的所有键值
     * @param key 键
     * @return 对应的多个键值
     */
    fun hmget(key: String?): Map<Any, Any>

    /**
     * HashSet
     * @param key 键
     * @param map 对应多个键值
     */
    fun hmset(key: String?, map: Map<String, Any>?): Boolean

    /**
     * HashSet 并设置时间
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    fun hmset(key: String?, map: Map<String, Any>?, time: Long): Boolean

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    fun hset(key: String?, item: String, value: Any): Boolean

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    fun hset(key: String?, item: String, value: Any, time: Long): Boolean

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    fun hdel(key: String?, vararg item: Any?)
    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    fun hHasKey(key: String?, item: String?): Boolean
    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     */
    fun hincr(key: String?, item: String, by: Double): Double
    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     */
    fun hdecr(key: String?, item: String, by: Double): Double
    // ============================set=============================
    /**
     * 根据key获取Set中的所有值
     * @param key 键
     */
    fun sGet(key: String?): Set<Any>?

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    fun sHasKey(key: String?, value: Any?): Boolean

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    fun sSet(key: String?, vararg values: Any?): Long

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    fun sSetAndTime(key: String?, time: Long, vararg values: Any?): Long

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     */
    fun sGetSetSize(key: String?): Long

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    fun setRemove(key: String?, vararg values: Any?): Long
    // ===============================list=================================
    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     */
    fun lGet(key: String?, start: Long, end: Long): List<Any>?

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     */
    fun lGetListSize(key: String?): Long

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     */
    fun lGetIndex(key: String?, index: Long): Any?

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     */
    fun lSet(key: String?, value: Any): Boolean

    /**
     * 将list放入缓存
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     */
    fun lSet(key: String?, value: Any, time: Long): Boolean

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    fun lSet(key: String?, value: List<Any>?): Boolean

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    fun lSet(key: String?, value: List<Any>?, time: Long): Boolean

    fun lUpdateIndex(key: String?, index: Long, value: Any): Boolean

    fun lRemove(key: String?, count: Long, value: Any?): Long
    fun fuzzyQueryKeys(key: String): Set<String?>
}