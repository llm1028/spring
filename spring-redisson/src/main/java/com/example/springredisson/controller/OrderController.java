package com.example.springredisson.controller;

import com.example.springredisson.common.ResponseModel;
import com.example.springredisson.common.req.OrderInfoReq;
import com.example.springredisson.event.MessageEvent;
import com.gome.architect.idgnrt.IDGenerator;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author liluming
 * @className: OrderController
 * @description:
 * @date 2022/3/18 5:02 下午
 */

@RestController
public class OrderController {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    RedissonClient redissonClient;

    @Resource
    private RedisTemplate redisTemplate;

    // @Autowired
    // private IDGenerator idGenerator;


    @RequestMapping("/initStock")
    public void initStock(HttpServletRequest request, HttpServletResponse response, String productId) {
        String stockKey = "product:" + productId;
        System.out.println(stockKey);
        redisTemplate.opsForValue().set(stockKey, 5, 10, TimeUnit.MINUTES);
        System.out.println(redisTemplate.opsForValue().get(stockKey));
    }

    @RequestMapping("/toOrder")
    public ResponseModel toOrder(HttpServletRequest request, HttpServletResponse response, String productId) {

        ResponseModel<Object> responseModel = new ResponseModel<>();
        String stockKey = "product:" + productId;
        String lockStockKey = "product:lock_" + productId;

        RLock rLock = redissonClient.getLock(lockStockKey);
        rLock.lock();
        try {
            Integer stock = (Integer) redisTemplate.opsForValue().get(stockKey);
            if (null != stock && stock > 0) {
                redisTemplate.opsForValue().increment(stockKey, -1);
                responseModel.setCode("100");
                responseModel.setMsg("下单成功，剩余库存：" + redisTemplate.opsForValue().get(stockKey));
                System.out.println("下单成功，剩余库存：" + redisTemplate.opsForValue().get(stockKey));
            } else {
                responseModel.setMsg("库存不够");
                System.out.println("库存不够");
            }
            return responseModel;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rLock != null) {
                rLock.unlock();
            }
        }
        return ResponseModel.buildSuccessResponseModel("aa");
    }

    @RequestMapping("/register")
    public ResponseModel register(HttpServletRequest request, HttpServletResponse response) {
        OrderInfoReq orderInfoReq = new OrderInfoReq();
        orderInfoReq.setOrderId("20221212X123");
        orderInfoReq.setMobile("13261607121");
        orderInfoReq.setUserId("1001");
        MessageEvent messageEvent = new MessageEvent(this, orderInfoReq);
        applicationContext.publishEvent(messageEvent);
        return ResponseModel.buildSuccessResponseModel("成功");
    }

    @RequestMapping("/testId")
    public ResponseModel testId(HttpServletRequest request, HttpServletResponse response) {
        IDGenerator generator = applicationContext.getBean("idGenerator", IDGenerator.class);
        for (int i = 0; i < 100; i++) {
            System.out.println(generator.next());
        }
        return ResponseModel.buildSuccessResponseModel("成功");

    }

}
