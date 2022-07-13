package com.example.springdemo1.service;

import com.example.springdemo1.domin.ProductInfoQueryVo;

/**
 * @author liluming
 * @className: ProductInfoService
 * @description:
 * @date 2022/7/11 5:26 下午
 */
public interface ProductInfoService {
    ProductInfoQueryVo findById(Integer id);
}
