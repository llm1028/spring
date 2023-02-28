package com.example.springredisson.event;

import com.example.springredisson.common.req.OrderInfoReq;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * @author liluming
 * @className: MessageEvent
 * @description:
 * @date 2022/6/8 5:54 下午
 */

public class MessageEvent extends ApplicationEvent {
    private OrderInfoReq orderInfoReq;

    public MessageEvent(Object source, OrderInfoReq orderInfoReq) {
        super(source);
        this.orderInfoReq = orderInfoReq;
        System.out.println("自定义事件");
    }

    public OrderInfoReq getOrderInfoReq() {
        return orderInfoReq;
    }

    public void setOrderInfoReq(OrderInfoReq orderInfoReq) {
        this.orderInfoReq = orderInfoReq;
    }
}
