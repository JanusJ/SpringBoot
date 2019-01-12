package com.web.gmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;


@EnableHystrix
@EnableDubbo(scanBasePackages={"com.web.gmall.service.impl"})//开启基于注解的dubbo功能
//@ImportResource(value={"classpath:provider.xml"})
@SpringBootApplication
public class BootUserServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootUserServiceProviderApplication.class, args);
	}
}
