package com.bizvane.utils.redisutils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Micro
 * @Title: Redis 工具类
 * @Package ${package_name}
 * @Description: Redis 操作基本操作
 * @date 2018/6/27 19:06
 */
@Service("redisTemplateService")
public class RedisTemplateServiceImpl<K, V> implements RedisTemplateService<K, V> {
    @Autowired
    private RedisTemplate<K, V> redisTemplate;

    @Override
    public void deleteFromRedis(K key) {
        redisTemplate.delete(key);
    }

    @Override
    public <HK> Boolean hashCheckHxists(K hKey, HK field) {
        return redisTemplate.opsForHash().hasKey(hKey, field);
    }

    @Override
    public <HK> V hashGet(K hKey, HK hashKey) {
        return (V) redisTemplate.opsForHash().get(hKey, hashKey);
    }

    @Override
    public Map<K, V> hashGetAll(K key) {
        return (Map<K, V>) redisTemplate.opsForHash().entries(key);
    }

    @Override
    public <HK> Long hashIncrementLongOfHashMap(K hKey, HK hashKey, Long delta) {
        return redisTemplate.opsForHash().increment(hKey, hashKey, delta);
    }

    @Override
    public <HK> Double hashIncrementDoubleOfHashMap(K hKey, HK hashKey, Double delta) {
        return redisTemplate.opsForHash().increment(hKey, hashKey, delta);
    }

    @Override
    public <HK> void hashPushHashMap(K key, HK hashKey, V value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    public Set<V> hashGetAllHashKey(K key) {
        return (Set<V>) redisTemplate.opsForHash().keys(key);
    }

    @Override
    public Long hashGetHashMapSize(K key) {
        return redisTemplate.opsForHash().size(key);
    }

    @Override
    public List<V> hashGetHashAllValues(K key) {
        return (List<V>) redisTemplate.opsForHash().values(key);
    }

    @Override
    public <HK> Long hashDeleteHashKey(K key, HK... hashKeys) {
        return redisTemplate.opsForHash().delete(key, hashKeys);
    }

    @Override
    public void listLeftPushList(K key, V value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public V listLeftPopList(K key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public Long listSize(K key) {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public List<V> listRangeList(K key, Long start, Long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    @Override
    public Long listRemoveFromList(K key, long i, V value) {
        return redisTemplate.opsForList().remove(key, i, value);
    }

    @Override
    public V listIndexFromList(K key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    @Override
    public void listSetValueToList(K key, long index, V value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    @Override
    public void listTrimByRange(K key, Long start, Long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    @Override
    public void listRightPushList(K key, V value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    @Override
    public V listRightPopList(K key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    @Override
    public Long setAddSetMap(K key, V... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    @Override
    public Long setGetSizeForSetMap(K key) {
        return redisTemplate.opsForSet().size(key);
    }

    @Override
    public Set<V> setGetMemberOfSetMap(K key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public Boolean setCheckIsMemberOfSet(K key, V o) {
        return redisTemplate.opsForSet().isMember(key, o);
    }

    @Override
    public Integer stringAppendString(K key, String value) {
        return redisTemplate.opsForValue().append(key, value);
    }

    @Override
    public V stringGetStringByKey(K key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public String stringGetSubStringFromString(K key, long start, long end) {
        return redisTemplate.opsForValue().get(key, start, end);
    }

    @Override
    public Long stringIncrementLongString(K key, Long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public Double stringIncrementDoubleString(K key, Double delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public void stringSetString(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public V stringGetAndSet(K key, V value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    @Override
    public void stringSetValueAndExpireTime(K key, V value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MILLISECONDS);
    }
}
