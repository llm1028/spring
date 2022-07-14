package com.example.springdemo1.service.impl;

import com.example.springdemo1.common.ApiException;
import com.example.springdemo1.common.AppCode;
import com.example.springdemo1.common.ResultCode;
import com.example.springdemo1.domin.ProductInfoQueryVo;
import com.example.springdemo1.domin.ProductInfoVo;
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

        try {
            // 测试接口错误，默认statuscode里的code, message
            if (1 == id) {
                throw new ApiException(ResultCode.VALIDATE_ERROR);
            }
            // 测试接口错误，需要自定义message的业务场景
            if (2 == id) {
                throw new ApiException(AppCode.PRICE_ERROR, "该商品价格异常 "+id);
            }
            // 测试接口错误，需要带返回对象的业务场景
            if (3 == id) {
                throw new ApiException(AppCode.PRICE_ERROR, true);
            }
            // 测试异常情况
            if (4 == id) {
                int a = 4/0;
            }
            // 测试异常情况
            if (5 == id) {
                ProductInfoVo productInfoVo = null;
                System.out.println(productInfoVo.getProductPrice());
            }

            productInfoQueryVo.setProductId(1);
            productInfoQueryVo.setProductName("手机");
            productInfoQueryVo.setProductPrice(BigDecimal.valueOf(4900.00));
            productInfoQueryVo.setProductDescription("苹果手机");
            productInfoQueryVo.setProductStatus(0);
        } catch (Exception e) {
            throw new ApiException();
        }
        return productInfoQueryVo;
    }

}
