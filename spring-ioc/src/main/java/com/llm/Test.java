package com.llm;

import com.llm.service.UserInterface;
import com.llm.service.UserService;
import com.spring.LlmApplicationContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liluming
 * @className: Test
 * @description:
 * @date 2021/12/9 6:01 下午
 */
public class Test {
    public static void main(String[] args) {
        // 创建单例bean
        LlmApplicationContext applicationContext = new LlmApplicationContext(AppConfig.class);
        UserInterface userService = (UserInterface) applicationContext.getBean("userService");
        // 测试单例
        userService.test();
        // 测试格式化
        Double longNum = 39.0;
        String stringNum = String.format("%.2f", longNum);
        stringNum = stringNum.replaceFirst("\\.00", "");
        System.out.println(stringNum);

        // Double price = 0.00;
        //
        // if (Long.parseLong(price).equals(0L)) {
        //     System.out.println(11);
        // } else {
        //     System.out.println(22);
        // }

        // 计算时间
        // String regex = "/^1(3\\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$/";
        // String mobile = "17333615510";
        // 身份证正则
        // String regex = "^(13[0-9]|14[5-9]|15[012356789]|166|17[0-8]|18[0-9]|19[8-9])[0-9]{8}$";
        // String mobile = "132616071212";
        // // String mobile = "18611349677";
        // if (mobile.matches(regex)) {
        //     System.out.println("通过11");
        // } else {
        //     System.out.println("失败22");
        // }
        // String idCard = "";
        // System.out.println(idCard.replace("x","X"));
        //
        // // System.out.println((byte)(-128 >>> 1));
        // System.out.println((byte)(-128 >>> 1));

        String name = "李哈李哈李•哈李哈李哈李哈李哈李哈李哈李哈李哈李哈李李李李李李哈李哈李哈李哈李哈李哈李哈李哈李哈李哈李李李李李李哈李哈李哈李哈李哈李哈李哈李哈李哈李哈李李李李李李哈李哈李哈李哈李哈李哈李哈李哈李哈李哈李李李李李李";
        if (name.contains("·") || name.contains("•")) {
            if (name.matches("^[\\u4e00-\\u9fa5]+[·•][\\u4e00-\\u9fa5]{2,25}")) {
                System.out.println("true1");
            } else {
                System.out.println("false11");
            }
        } else {
            if (name.matches("^[\\u4e00-\\u9fa5]{2,25}")) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }
    }


}
