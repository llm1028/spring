package com.example.springdemo1.service.impl;

import com.example.springdemo1.domin.req.ProductInfoQueryReq;
import com.example.springdemo1.domin.res.ProductInfoRes;
import com.example.springdemo1.domin.res.ProductListRes;
import com.example.springdemo1.service.ProductInfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liluming
 * @className: ProductInfo2Serviceimpl
 * @description:
 * @date 2023/2/24 11:08 上午
 */
@Service(value = "product2")
public class ProductInfo2Serviceimpl implements ProductInfoService {
    @Override
    public ProductInfoQueryReq findById(Integer id) {
        ProductInfoQueryReq productInfoQueryVo = new ProductInfoQueryReq();
        productInfoQueryVo.setProductId(199);
        return productInfoQueryVo;
    }

    @Override
    public ProductListRes queryProductList() {
        ProductListRes productListRes = new ProductListRes();
        List<ProductInfoRes> productInfoResList = new ArrayList<>();
        ProductInfoRes productInfoRes = new ProductInfoRes();
        productInfoRes.setProductName("199");
        productInfoResList.add(productInfoRes);
        productListRes.setRecordList(productInfoResList);
        return productListRes;
    }
}
