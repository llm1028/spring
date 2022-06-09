package com.example.springredisson.common.req;

import lombok.Data;

/**
 * @author liluming
 * @className: User
 * @description:
 * @date 2022/6/8 6:19 下午
 */

@Data
public class OrderInfoReq {
    private String userId;
    private String mobile;
    private String orderId;
}
