package com.example.springredisson.common.listener;

import com.example.springredisson.event.MessageEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author liluming
 * @className: SendMailListener
 * @description:
 * @date 2022/6/8 6:14 下午
 */
@Component
public class SendMailListener implements ApplicationListener<MessageEvent> {

    @Override
    @Async
    public void onApplicationEvent(MessageEvent event) {
        System.out.println("发送邮件:" + event.getOrderInfoReq().getOrderId());
    }
}
