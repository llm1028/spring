package com.example.springdemo1.domin.common;

import lombok.Data;

/**
 * @author liluming
 * @className: PageInfo
 * @description:
 * @date 2022/7/14 2:23 下午
 */
@Data
public class PageInfo {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private Integer total;
    private Integer totalPage;
}
