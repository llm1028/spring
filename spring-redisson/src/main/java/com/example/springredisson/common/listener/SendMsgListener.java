package com.example.springredisson.common.listener;

import com.example.springredisson.event.MessageEvent;
import lombok.SneakyThrows;
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
public class SendMsgListener implements ApplicationListener<MessageEvent> {

    @SneakyThrows
    @Override
    @Async
    public void onApplicationEvent(MessageEvent event) {
        Thread.sleep(5000);
        System.out.println("发送短信:"+event.getOrderInfoReq().getMobile());
    }
}
