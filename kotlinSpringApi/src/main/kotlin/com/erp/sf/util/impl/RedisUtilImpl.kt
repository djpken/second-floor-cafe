package com.erp.main.utils

import com.erp.sf.util.RedisUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import org.springframework.util.CollectionUtils
import java.util.concurrent.TimeUnit

@Component
class RedisUtilImpl: RedisUtil {
    @Autowired
    private val redisTemplate: RedisTemplate<String, Any>? = null
    override fun expire(key: String?, time: Long): Boolean {
        return try {
            if (time > 0) {
                redisTemplate!!.expire(key!!, time, TimeUnit.SECONDS)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    override fun getExpire(key: String?): Long {
        return redisTemplate!!.getExpire(key!!, TimeUnit.SECONDS)
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    override fun hasKey(key: String?): Boolean {
        return try {
            redisTemplate!!.hasKey(key!!)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    override fun del(vararg key: String?) {
        if (key != null && key.size > 0) {
            if (key.size == 1) {
                redisTemplate!!.delete(key[0]!!)
            } else {
                redisTemplate!!.delete((CollectionUtils.arrayToList(key) as Collection<String?>))
            }
        }
    }
    // ============================String=============================
    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    override operator fun get(key: String?): Any? {
        return if (key == null) null else redisTemplate!!.opsForValue()[key]
    }

    /**
     * 普通缓存放入
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    override operator fun set(key: String?, value: Any): Boolean {
        return try {
            redisTemplate!!.opsForValue()[key!!] = value
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 普通缓存放入并设置时间
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    override operator fun set(key: String?, value: Any, time: Long): Boolean {
        return try {
            if (time > 0) {
                redisTemplate!!.opsForValue()[key!!, value, time] = TimeUnit.SECONDS
            } else {
                set(key, value)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 递增
     * @param key   键
     * @param delta 要增加几(大于0)
     */
    override fun incr(key: String?, delta: Long): Long {
        if (delta < 0) {
            throw RuntimeException("递增因子必须大于0")
        }
        return redisTemplate!!.opsForValue().increment(key!!, delta)!!
    }

    /**
     * 递减
     * @param key   键
     * @param delta 要减少几(小于0)
     */
    override fun decr(key: String?, delta: Long): Long {
        if (delta < 0) {
            throw RuntimeException("递减因子必须大于0")
        }
        return redisTemplate!!.opsForValue().increment(key!!, -delta)!!
    }
    // ================================Map=================================
    /**
     * HashGet
     * @param key  键 不能为null
     * @param item 项 不能为null
     */
    override fun hget(key: String?, item: String?): Any? {
        return redisTemplate!!.opsForHash<Any, Any>()[key!!, item!!]
    }

    /**
     * 获取hashKey对应的所有键值
     * @param key 键
     * @return 对应的多个键值
     */
    override fun hmget(key: String?): Map<Any, Any> {
        return redisTemplate!!.opsForHash<Any, Any>().entries(key!!)
    }

    /**
     * HashSet
     * @param key 键
     * @param map 对应多个键值
     */
    override fun hmset(key: String?, map: Map<String, Any>?): Boolean {
        return try {
            redisTemplate!!.opsForHash<Any, Any>().putAll(key!!, map!!)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * HashSet 并设置时间
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    override fun hmset(key: String?, map: Map<String, Any>?, time: Long): Boolean {
        return try {
            redisTemplate!!.opsForHash<Any, Any>().putAll(key!!, map!!)
            if (time > 0) {
                expire(key, time)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    override fun hset(key: String?, item: String, value: Any): Boolean {
        return try {
            redisTemplate!!.opsForHash<Any, Any>().put(key!!, item, value)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    override fun hset(key: String?, item: String, value: Any, time: Long): Boolean {
        return try {
            redisTemplate!!.opsForHash<Any, Any>().put(key!!, item, value)
            if (time > 0) {
                expire(key, time)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    override fun hdel(key: String?, vararg item: Any?) {
        redisTemplate!!.opsForHash<Any, Any>().delete(key!!, *item)
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    override fun hHasKey(key: String?, item: String?): Boolean {
        return redisTemplate!!.opsForHash<Any, Any>().hasKey(key!!, item!!)
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     */
    override fun hincr(key: String?, item: String, by: Double): Double {
        return redisTemplate!!.opsForHash<Any, Any>().increment(key!!, item, by)
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     */
    override fun hdecr(key: String?, item: String, by: Double): Double {
        return redisTemplate!!.opsForHash<Any, Any>().increment(key!!, item, -by)
    }
    // ============================set=============================
    /**
     * 根据key获取Set中的所有值
     * @param key 键
     */
    override fun sGet(key: String?): Set<Any>? {
        return try {
            redisTemplate!!.opsForSet().members(key!!)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    override fun sHasKey(key: String?, value: Any?): Boolean {
        return try {
            redisTemplate!!.opsForSet().isMember(key!!, value!!)!!
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    override fun sSet(key: String?, vararg values: Any?): Long {
        return try {
            redisTemplate!!.opsForSet().add(key!!, *values)!!
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    override fun sSetAndTime(key: String?, time: Long, vararg values: Any?): Long {
        return try {
            val count = redisTemplate!!.opsForSet().add(key!!, *values)
            if (time > 0) expire(key, time)
            count!!
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     */
    override fun sGetSetSize(key: String?): Long {
        return try {
            redisTemplate!!.opsForSet().size(key!!)!!
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    override fun setRemove(key: String?, vararg values: Any?): Long {
        return try {
            val count = redisTemplate!!.opsForSet().remove(key!!, *values)
            count!!
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }
    // ===============================list=================================
    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     */
    override fun lGet(key: String?, start: Long, end: Long): List<Any>? {
        return try {
            redisTemplate!!.opsForList().range(key!!, start, end)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     */
    override fun lGetListSize(key: String?): Long {
        return try {
            redisTemplate!!.opsForList().size(key!!)!!
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     */
    override fun lGetIndex(key: String?, index: Long): Any? {
        return try {
            redisTemplate!!.opsForList().index(key!!, index)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     */
    override fun lSet(key: String?, value: Any): Boolean {
        return try {
            redisTemplate!!.opsForList().rightPush(key!!, value)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 将list放入缓存
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     */
    override fun lSet(key: String?, value: Any, time: Long): Boolean {
        return try {
            redisTemplate!!.opsForList().rightPush(key!!, value)
            if (time > 0) expire(key, time)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    override fun lSet(key: String?, value: List<Any>?): Boolean {
        return try {
            redisTemplate!!.opsForList().rightPushAll(key!!, value!!)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    override fun lSet(key: String?, value: List<Any>?, time: Long): Boolean {
        return try {
            redisTemplate!!.opsForList().rightPushAll(key!!, value!!)
            if (time > 0) expire(key, time)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun lUpdateIndex(key: String?, index: Long, value: Any): Boolean {
        return try {
            redisTemplate!!.opsForList()[key!!, index] = value
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun lRemove(key: String?, count: Long, value: Any?): Long {
        return try {
            val remove = redisTemplate!!.opsForList().remove(key!!, count, value!!)
            remove!!
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }
    override fun fuzzyQueryKeys(key: String): Set<String?> {
        return redisTemplate!!.keys("$key*")
    }
}