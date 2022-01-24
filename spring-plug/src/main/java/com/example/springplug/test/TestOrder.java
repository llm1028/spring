package com.example.springplug.test;

import com.example.springplug.test.order.A;
import com.example.springplug.test.order.B;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liluming
 * @className: TestOrder
 * @description:
 * @date 2021/12/17 10:52 上午
 */
public class TestOrder {
    public static void main(String[] args) {
        A a = new A(); // order=3
        B b = new B(); // order=2

        AnnotationAwareOrderComparator comparator = new AnnotationAwareOrderComparator();
        System.out.println(comparator.compare(a, b)); // 1

        List<Object> list = new ArrayList<>();
        list.add(a);
        list.add(b);

        // 按order值升序排序
        list.sort(comparator);

        System.out.println(list); // B，A
    }
}
