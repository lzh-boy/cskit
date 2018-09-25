package com.bizvane.utils.redisutils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Micro
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/27 17:59
 */
public interface RedisTemplateService<K, V> {

    //================================================Hash================================================

    /**
     * TODO 删除键为key的缓存(hash/set/list/String)
     *
     * @param key
     */
    void deleteFromRedis(K key);

    /**
     * TODO 查看哈希表 hKey 中，给定域 hashKey 是否存在。
     *
     * @param hKey    哈希表名称
     * @param hashKey 域的hashKey
     * @return 如果哈希表含有给定域，返回 1 。如果哈希表不含有给定域，或 hashKey 不存在，返回 0 。
     */
    <HK> Boolean hashCheckHxists(K hKey, HK hashKey);

    /**
     * TODO 查询哈希表 hKey 中给定域 hashKey 的值。
     *
     * @param hKey
     * @param hashKey
     * @return 给定域的值。当给定域不存在或是给定 key 不存在时，返回 nil 。
     */
    <HK> V hashGet(K hKey, HK hashKey);

    /**
     * TODO 获取所有的散列值
     *
     * @param key
     * @return
     */
    Map<K, V> hashGetAll(K key);

    /**
     * TODO 哈希表 hKey 中的域 hashKey 的值加上增量 delta 。
     * <p>
     * 增量也可以为负数，相当于对给定域进行减法操作。  如果 key 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。
     * 如果域 field 不存在，那么在执行命令前，域的值被初始化为 0 。  对一个储存字符串值的域 field 执行 HINCRBY 命令将造成一个错误。
     *
     * @param hKey
     * @param hashKey
     * @param delta
     * @return 执行 HINCRBY 命令之后，哈希表 hKey 中域 hashKey 的值。
     */
    <HK> Long hashIncrementLongOfHashMap(K hKey, HK hashKey, Long delta);

    /**
     * TODO 哈希表 hKey 中的域 hashKey 的值加上浮点值 增量 delta 。
     *
     * @param hKey
     * @param hashKey
     * @param delta
     * @return 执行 HINCRBY 命令之后，哈希表 hKey 中域 hashKey 的值。
     */
    <HK> Double hashIncrementDoubleOfHashMap(K hKey, HK hashKey, Double delta);

    /**
     * TODO 添加键值对到哈希表key中
     *
     * @param hKey
     * @param hashKey
     * @param value
     */
    <HK> void hashPushHashMap(K hKey, HK hashKey, V value);

    /**
     * TODO 获取哈希表key中的所有域
     *
     * @param key
     * @return
     */
    Set<V> hashGetAllHashKey(K key);

    /**
     * TODO 获取散列中的字段数量
     *
     * @param key
     * @return
     */
    Long hashGetHashMapSize(K key);

    /**
     * TODO 获取哈希中的所有值
     *
     * @param key
     * @return
     */
    List<V> hashGetHashAllValues(K key);

    /**
     * TODO 删除一个或多个哈希字段
     *
     * @param key
     * @param hashKeys
     * @return 返回值为被成功删除的数量
     */
    <HK> Long hashDeleteHashKey(K key, HK... hashKeys);

    //================================================List================================================

    /**
     * TODO 从右向左存压栈
     *
     * @param key
     * @param value
     */
    void listRightPushList(K key, V value);

    /**
     * TODO 从右出栈
     *
     * @param key
     * @return
     */
    V listRightPopList(K key);

    /**
     * TODO 从左向右存压栈
     *
     * @param key
     * @param value
     */
    void listLeftPushList(K key, V value);

    /**
     * TODO 从左出栈
     *
     * @param key
     * @return
     */
    V listLeftPopList(K key);

    /**
     * TODO 集合list的长度
     *
     * @param key
     * @return
     */
    Long listSize(K key);

    /**
     * TODO 查询列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    List<V> listRangeList(K key, Long start, Long end);

    /**
     * TODO 移除key中值为value的i个,返回删除的个数；如果没有这个元素则返回0
     *
     * @param key
     * @param i
     * @param value
     * @return
     */
    Long listRemoveFromList(K key, long i, V value);

    /**
     * TODO 根据下标查询list中某个值
     *
     * @param key
     * @param index
     * @return
     */
    V listIndexFromList(K key, long index);

    /**
     * TODO 根据下标设置value
     *
     * @param key
     * @param index
     * @param value
     */
    void listSetValueToList(K key, long index, V value);

    /**
     * TODO 裁剪(删除), 删除 除了[start,end]以外的所有元素
     *
     * @param key
     * @param start
     * @param end
     */
    void listTrimByRange(K key, Long start, Long end);

    //================================================Set================================================

    /**
     * TODO 将一个或多个 value 元素加入到集合 key 当中，已经存在于集合的 value 元素将被忽略。
     *
     * @param key
     * @param values
     * @return 被添加到集合中的新元素的数量，不包括被忽略的元素。
     */
    Long setAddSetMap(K key, V... values);

    /**
     * TODO 获取set集合的大小
     *
     * @param key
     * @return
     */
    Long setGetSizeForSetMap(K key);

    /**
     * TODO 获取set集合中的元素
     *
     * @param key
     * @return
     */
    Set<V> setGetMemberOfSetMap(K key);

    /**
     * TODO 检查元素是不是set集合中的
     *
     * @param key
     * @param o
     * @return
     */
    Boolean setCheckIsMemberOfSet(K key, V o);

    //================================================String================================================

    /**
     * TODO 如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。
     * 如果 key 不存在， APPEND 就简单地将给定 key 设为 value ，就像执行 SET key value 一样。
     *
     * @param key
     * @param value
     * @return 追加 value 之后， key 中字符串的长度
     */
    Integer stringAppendString(K key, String value);

    /**
     * TODO 获取指定键的值
     *
     * @param key
     * @return
     */
    V stringGetStringByKey(K key);

    /**
     * TODO 获取存储在键上的字符串的子字符串
     *
     * @param key
     * @param start
     * @param end
     * @return 截取后的子字符串
     */
    String stringGetSubStringFromString(K key, long start, long end);

    /**
     * TODO 将键的整数值按给定的长整型数值增加
     *
     * @param key
     * @param delta
     * @return 返回增长后的结果值
     */
    Long stringIncrementLongString(K key, Long delta);

    /**
     * TODO 将键的整数值按给定的浮点型数值增加
     *
     * @param key
     * @param delta
     * @return 返回增长后的结果值
     */
    Double stringIncrementDoubleString(K key, Double delta);

    /**
     * TODO 设置指定键的值
     *
     * @param key
     * @param value
     */
    void stringSetString(K key, V value);

    /**
     * TODO 设置键的字符串值并返回其旧值
     *
     * @param key
     * @param value
     * @return
     */
    V stringGetAndSet(K key, V value);

    /**
     * TODO 使用键和到期时间来设置值,时间单位默认为毫秒
     *
     * @param key
     * @param value
     * @param timeout
     * @return
     */
    void stringSetValueAndExpireTime(K key, V value, long timeout);
}
