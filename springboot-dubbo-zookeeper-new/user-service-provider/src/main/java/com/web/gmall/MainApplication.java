package com.web.gmall;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApplication {
	
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("provider.xml");
		ioc.start();
		//阻塞等待读入字节
		System.in.read();
	}

}
