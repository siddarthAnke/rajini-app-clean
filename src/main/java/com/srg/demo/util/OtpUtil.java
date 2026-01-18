package com.srg.demo.util;

import java.util.Random;

import org.hibernate.annotations.Comment;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
@Configuration
public class OtpUtil {

    public static String generateOtp() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }
}
