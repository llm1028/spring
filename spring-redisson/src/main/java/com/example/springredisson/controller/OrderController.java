package com.example.springredisson.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @author liluming
 * @className: OrderController
 * @description:
 * @date 2022/3/18 5:02 下午
 */

@RestController
public class OrderController {

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/initStock")
    public void initStock(HttpServletRequest request, HttpServletResponse response, String productId) {
        String stockKey = "product:"+productId;
        redisTemplate.opsForValue().set(stockKey, 5);
        System.out.println(redisTemplate.opsForValue().get(stockKey));
    }

    @RequestMapping("/toOrder")
    public void toOrder() {
        // RLock rLock = redissonClient.getLock("product:"+"1001");
        // rLock.lock();
        // try {
        //     System.out.println("下单成功");
        // } catch (Exception e) {
        //     e.printStackTrace();
        // } finally {
        //     if (rLock != null) {
        //         rLock.unlock();
        //     }
        // }
    }

}
