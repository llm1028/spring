package com.example.springdemo1.domin.res;

import com.example.springdemo1.domin.common.PageInfo;
import com.example.springdemo1.domin.res.ProductInfoRes;
import lombok.Data;

import java.util.List;

/**
 * @author liluming
 * @className: ProductList
 * @description:
 * @date 2022/7/14 4:07 下午
 */
@Data
public class ProductListRes {
    PageInfo pageInfo;
    List<ProductInfoRes> recordList;
}
