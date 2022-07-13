package com.example.springdemo1.domin;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author liluming
 * @className: ProductInfoVo
 * @description:
 * @date 2022/7/13 3:23 下午
 */
@Data
public class ProductInfoVo {
    // 商品名称
    @NotNull(message = "商品名称不能为空")
    private String productName;
    // 商品价格
    @Min(value = 0, message = "商品价格不允许为负数")
    private BigDecimal productPrice;
    // 上架状态
    private Integer productStatus;
}