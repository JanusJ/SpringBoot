package com.web.gmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

@EnableHystrix
@EnableDubbo(scanBasePackages={"com.web.gmall.service.impl"})//开启基于注解的dubbo功能
@SpringBootApplication
public class BootUserServiceProvider20881Application {

	public static void main(String[] args) {
		SpringApplication.run(BootUserServiceProvider20881Application.class, args);
	}
}
