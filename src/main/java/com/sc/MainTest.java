package com.sc;

import com.sc.utils.ImageHelper;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by hgwang on 2017/12/29.
 */
public class MainTest {
    public static void main(String[] args) {
        String src = "/Users/hgwang/Document/sc-api/data/image/2017/12/9/4d5e31cf716ec2385e0003d5.jpg";
        try {
            ImageHelper.zoomImage(src, src, 686, 326);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
