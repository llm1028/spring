package com.example.springdemo1.service.impl;

import com.example.springdemo1.domin.ProductInfoQueryVo;
import com.example.springdemo1.service.ProductInfoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author liluming
 * @className: ProductInfoServiceimpl
 * @description:
 * @date 2022/7/11 5:28 下午
 */
@Service
public class ProductInfoServiceimpl implements ProductInfoService {
    @Override
    public ProductInfoQueryVo findById(Integer id) {
        ProductInfoQueryVo productInfoQueryVo = new ProductInfoQueryVo();
        productInfoQueryVo.setProductId(1);
        productInfoQueryVo.setProductName("手机");
        productInfoQueryVo.setProductPrice(BigDecimal.valueOf(4900.00));
        productInfoQueryVo.setProductDescription("苹果手机");
        productInfoQueryVo.setProductStatus(0);
        return productInfoQueryVo;
    }

}
