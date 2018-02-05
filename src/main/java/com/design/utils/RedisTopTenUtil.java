package com.design.utils;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * Created by cvter on 2017/5/31.
 */
public class RedisTopTenUtil {
    @Autowired
    private JedisPool jedisPool;

    public double putRedisTopTen(String bookUid) {
        Jedis jedis = jedisPool.getResource();
        double nums = jedis.zincrby(Constants.TOP_TEN_KEY, 1, bookUid);
        jedis.close();
        return nums;
    }

    public Set<String> getInRedisTopTen() {
        Jedis jedis = jedisPool.getResource();

        Set<String> topTen = jedis.zrange(Constants.TOP_TEN_KEY, -10, -1);
        jedis.close();
        return topTen;
    }
}
