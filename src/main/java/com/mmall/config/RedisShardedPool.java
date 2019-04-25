package com.mmall.config;

import com.mmall.utils.PropertiesUtil;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2019-04-26 上午6:53
 **/
public class RedisShardedPool {
    private static ShardedJedisPool pool;
    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getProperty("redis.max.total","20"));
    private static Integer maxIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.max.idle","20"));
    private static Integer minIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.min.idle","20"));
    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.borrow","true"));
    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.return","true"));
    private static String redis1Ip = PropertiesUtil.getProperty("redis1.ip");
    private static Integer redis1Port = Integer.parseInt(PropertiesUtil.getProperty("redis1.port"));
    private static String redis2Ip = PropertiesUtil.getProperty("redis2.ip");
    private static Integer redis2Port = Integer.parseInt(PropertiesUtil.getProperty("redis2.port"));

    static{
        initPool();
    }

    private static void initPool(){
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        config.setBlockWhenExhausted(true);

        JedisShardInfo info1 = new JedisShardInfo(redis1Ip,redis1Port,1000*2);

        JedisShardInfo info2 = new JedisShardInfo(redis2Ip,redis2Port,1000*2);

        List<JedisShardInfo> jedisShardInfoList = new ArrayList<JedisShardInfo>(2);

        jedisShardInfoList.add(info1);
        jedisShardInfoList.add(info2);
        pool = new ShardedJedisPool(config,jedisShardInfoList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);
    }

    public static ShardedJedis getJedis(){
        return pool.getResource();
    }
    public static void returnBrokenResource(ShardedJedis jedis){
        pool.returnBrokenResource(jedis);
    }
    public static void returnResource(ShardedJedis jedis){
        pool.returnResource(jedis);
    }
}
