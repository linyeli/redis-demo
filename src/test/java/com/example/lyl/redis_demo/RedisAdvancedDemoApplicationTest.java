package com.example.lyl.redis_demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class RedisAdvancedDemoApplicationTest {

    @Autowired
    @Qualifier("stringObjectRedisTemplate")
    private RedisTemplate redisTemplate;

    @Test
    public void redisGeoTest() {
        Map<String, Point> map = new HashMap<>();
        double x = 13.361389;
        double y = 38.115556;
        for(int i=0;i<5;i++){
            x+=i;
            y+=i;
            Point point = new Point(x,y);
            map.put("position"+i, point);
        }
        long count = redisTemplate.opsForGeo().add("positionKey",map);
        System.out.println(count);
    }

    @Test
    public void redisBitMapTest() {
        redisTemplate.opsForHyperLogLog().add("hyperKey", 1,2,3,4,5,6,6,5,3,3,333,2,1);
        long size = redisTemplate.opsForHyperLogLog().size("hyperKey");
        System.out.println(size);
    }
}
