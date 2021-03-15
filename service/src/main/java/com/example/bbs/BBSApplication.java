package com.example.bbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BBSApplication {

    public static void main(String[] args) {
        SpringApplication.run(BBSApplication.class, args);
    }

}
/*
 *1.项目结构 每个表对应一个entity，每个entity一个dao（mapper）, 按照功能，划分controller和service
 *
 *2.使用技术 springboot + mybatis
 *
 *3. controller(request mapping) -> service -> dao(extends baseMapper) -> mapper.xml
 *
 * 4. entity(database) <--> DTO <--> VO (html)
 *
 *
 * */
