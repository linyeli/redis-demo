package com.example.lyl.redis_demo;

import com.example.lyl.redis_demo.pojo.User;
import com.example.lyl.redis_demo.service.RedisBasicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;


@SpringBootTest
class RedisDemoApplicationTests {

    @Autowired
    private RedisBasicService redisBasicService;

    @Autowired
    @Qualifier("stringObjectRedisTemplate")
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void stringRedisTest(){
        System.out.println("redis string set key");
        redisBasicService.setString("stringKey", "stringValue");
        System.out.println("redis string get key");
        Object stringValue = redisBasicService.getString("stringKey");
        System.out.println(stringValue);
        Object stringValue2 = redisBasicService.getString("1");
        System.out.println(stringValue2);
    }

    @Test
    public void hashRedisTest() {
        System.out.println("redis hash set key");
        Map<String, User> map = new HashMap<>();
        for(int i=0;i<6;){
            User user = new User();
            user.setId(i);
            user.setName("xiaoming"+i);
            user.setGender("male");
            user.setAge(5+i);
            map.put("hashkey"+i, user);
            i++;
        }
        redisBasicService.setHash("hashKey",map);
        System.out.println("redis hash get key");
        Map<Object, Object>  resultMap = redisBasicService.getHash("hashKey");
        System.out.println(resultMap);
        System.out.println("redis hash get single Key");
        Object singleValue = redisBasicService.getHash("hashKey", "hashkey2");
        System.out.println(singleValue);
    }

    @Test
    public void listRedisTest() {
//        List<User> list = new ArrayList<>();
//        for(int i=0;i<6;i++){
//            User user = new User();
//            user.setId(i);
//            user.setName("xiaoming"+i);
//            user.setGender("male");
//            user.setAge(5+i);
//            list.add(user);
//        }
//        redisTemplate.opsForList().rightPushAll("listKey2", list);
//        redisTemplate.opsForList().rightPush("listKey", list);
//        redisTemplate.opsForList().rightPopAndLeftPush("listKey2", "listKey", 3, TimeUnit.SECONDS);
//        redisTemplate.opsForList().rightPush("listKey2","2");
//        redisTemplate.opsForList().rightPush("listKey2","2");
//        redisTemplate.opsForList().rightPush("listKey2","3");
        redisTemplate.opsForList().remove("listKey2", 2, "2");
    }

    @Test
    public void setRedisTest(){
//        redisTemplate.opsForSet().add("setKey2", "2", "3", "4", "5","6","7", "8");
//        Set<Object> diff =  redisTemplate.opsForSet().difference(Stream.of("setKey", "setKey2").collect(Collectors.toList()));
//        Set<Object> diff = redisTemplate.opsForSet().difference("setKey2", "setKey");
//        System.out.println(diff);
//        Set<Object> intersect = redisTemplate.opsForSet().intersect("setKey", "setKey2");
//        System.out.println(intersect);
//        Set<Object> union = redisTemplate.opsForSet().union("setKey", "setKey2");
//        System.out.println(union);
//        redisTemplate.opsForSet().differenceAndStore(Stream.of("setKey", "setKey2").collect(Collectors.toList()),"setKey3");
    }

    @Test
    public void zsetRedisTest(){
//        redisTemplate.opsForZSet().add("zsetKey", "zsetValue1", 1d);
//        redisTemplate.opsForZSet().add("zsetKey", "zsetValue2", 2d);
//        redisTemplate.opsForZSet().add("zsetKey", "zsetValue3", 3d);
//        redisTemplate.opsForZSet().add("zsetKey", "zsetValue4", 4d);
//        redisTemplate.opsForZSet().add("zsetKey", "zsetValue5", 4d);
//        Set<Object> result = redisTemplate.opsForZSet().rangeByLex("zsetKey", range);
//       Set<Object> result = redisTemplate.opsForZSet().range("zsetKey", 1 , 5);
//        System.out.println(result);
        long count = redisTemplate.opsForZSet().rank("zsetKey", "zsetValue3");
        System.out.println(count);
//        Set<Object> result = redisTemplate.opsForZSet().reverseRange("zsetKey", 0, 5);
//        System.out.println(result);
    }

    /**
     * 12.按照字典顺序给value排序（可以参考redis命令ZRANGEBYLEX）
     * rangeByLex
     * 注意：（1）排序的value，其socre必须一致相同
     *      （2）rangeByLex的作用参考redis命令ZRANGEBYLEX
     */
    @Test
    public void zsetRangeByLex(){
//        redisTemplate.delete("zset1");
//        redisTemplate.boundZSetOps("zset1").add("a", 1.0);
//        redisTemplate.boundZSetOps("zset1").add("f", 1.0);
//        redisTemplate.boundZSetOps("zset1").add("s", 1.0);
//        redisTemplate.boundZSetOps("zset1").add("d", 1.0);
//        redisTemplate.boundZSetOps("zset1").add("b", 1.0);
//        redisTemplate.boundZSetOps("zset1").add("c", 1.0);

        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        range.gt("a"); //从a开始，但是不包括a
//        range.lt("s"); //s结束，但是不包括s

        System.out.println(redisTemplate.boundZSetOps("zset1").rangeByLex(range));  //获得所有元素与ZSET辞典编纂的排序键和一个值

        RedisZSetCommands.Limit limit = new RedisZSetCommands.Limit();
        limit.offset(0); //从坐标0开始
        limit.count(3); //取出3个
        System.out.println(redisTemplate.boundZSetOps("zset1").rangeByLex(range, limit)); //获得所有元素与ZSET辞典编纂的排序键和一个值,限制个数
    }


}
