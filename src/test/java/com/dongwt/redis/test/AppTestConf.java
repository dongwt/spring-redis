package com.dongwt.redis.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value = { "classpath*:spring/spring-mvc.xml", "classpath*:spring/spring-redis.xml" })
public class AppTestConf {

}
