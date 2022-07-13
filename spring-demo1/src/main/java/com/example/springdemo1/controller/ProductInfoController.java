package com.example.springdemo1.controller;

import com.example.springdemo1.common.*;
import com.example.springdemo1.domin.ProductInfoQueryVo;
import com.example.springdemo1.domin.ProductInfoVo;
import com.example.springdemo1.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author liluming
 * @className: ProductInfoController
 * @description:
 * @date 2022/7/11 5:25 下午
 */
@RestController
@RequestMapping("/product/product-info")
public class ProductInfoController {
    @Autowired
    ProductInfoService productInfoService;

    /**
     * @description:
     * @author liluming
     * @date 2022/7/13 5:19 下午
     */
    @GetMapping("/findById")
    public ProductInfoQueryVo findById(Integer id) {
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
        return productInfoService.findById(id);
    }

    /**
     * @description: 返回结果不需要ResultVo包装，直接走统一返回类
     * @author liluming
     * @date 2022/7/13 5:17 下午
     */
    @PostMapping("/findByVo")
    public ProductInfoQueryVo findByVo(@RequestBody @Validated ProductInfoVo vo) {
        ProductInfoQueryVo productInfo = new ProductInfoQueryVo();
        BeanUtils.copyProperties(vo, productInfo);
        return productInfo;
    }

    /**
     * @description: 需要直接返回字符串
     * @author liluming
     * @date 2022/7/13 5:16 下午
     */
    @GetMapping("/health")
    @NotControllerResponseAdvice
    public String health() {
        return "success";
    }
}
