package com.bjbz;

import com.bjbz.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class TestJedisApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void contextLoads() {
    }

    @Test
    void testString(){
        redisTemplate.opsForValue().set("name", "你好");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name=  " + name);
    }

    @Test
    void testUser() throws JsonProcessingException {
        User user = new User("lz",21);
        //手动序列化
        String json = mapper.writeValueAsString(user);
        stringRedisTemplate.opsForValue().set("user:100", json);
        String u =  stringRedisTemplate.opsForValue().get("user:100");
        User user1 = mapper.readValue(u, User.class);
        System.out.println(user1);
    }

}
