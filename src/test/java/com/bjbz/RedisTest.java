package com.bjbz;


import com.bjbz.util.JedisConnectionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;



public class RedisTest {

    @Test
    public void testJedis(){
        //1.连接redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //2.操作redis
//        jedis.set("name", "str");
        String name = jedis.get("name");
        System.out.println(name);
        //3.关闭连接
        jedis.close();
    }

    @Test
    public void testList(){
        Jedis jedis = JedisConnectionFactory.getJedis();
        jedis.lpush("list2", "a" , "b");
        List<String> list2 =  jedis.lrange("list2", 0, -1);
        for(String s : list2){
            System.out.println(s);
        }

        jedis.close();
    }

    @Test
    public void testHash(){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.hset("hash1", "a1", "a1");
        jedis.hset("hash1", "a2", "a2");
        jedis.hset("hash1", "a3", "a3");

        Map<String,String> hash1 = jedis.hgetAll("hash1");
        for(String s: hash1.values()){
            System.out.println(s);
        }
        System.out.println(hash1);
        jedis.close();
    }
}
