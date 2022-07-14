package com.example.springdemo1.domin.req;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author liluming
 * @className: ProductInfoVo
 * @description:
 * @date 2022/7/11 5:27 下午
 */
@Data
public class ProductInfoQueryReq {

    private Integer productId;
    private String productName;
    private BigDecimal productPrice;
    private String productDescription;
    private Integer productStatus;
}
