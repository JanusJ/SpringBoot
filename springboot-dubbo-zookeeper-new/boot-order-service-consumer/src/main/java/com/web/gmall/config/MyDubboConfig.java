package com.web.gmall.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.MethodConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.web.gmall.service.UserService;

@Configuration
public class MyDubboConfig {
	
	@Bean
	public ApplicationConfig applicationConfig() {
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName("boot-order-service-consumer");
		return applicationConfig;
	}
	
	//<dubbo:registry protocol="zookeeper" address="127.0.0.1:2181"></dubbo:registry>
	@Bean
	public RegistryConfig registryConfig() {
		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setProtocol("zookeeper");
		registryConfig.setAddress("127.0.0.1:2181");
		return registryConfig;
	}
	
	
	/**
	 *<dubbo:reference interface="com.web.gmall.service.UserService" 
		id="userService" timeout="5000" retries="3" version="*">
		<dubbo:method name="getUserAddressList" timeout="1000" retries="0"></dubbo:method>
	</dubbo:reference>
	 */
	@Bean
	public ReferenceConfig<UserService> userReferenceConfig(){
		ReferenceConfig<UserService> serviceConfig = new ReferenceConfig<>();
		serviceConfig.setInterface(UserService.class);
		serviceConfig.setTimeout(5000);
		serviceConfig.setVersion("*");
		serviceConfig.setRetries(3);
		
		//配置每一个method的信息
		MethodConfig methodConfig = new MethodConfig();
		methodConfig.setName("getUserAddressList");
		methodConfig.setTimeout(1000);
		
		//将method的设置关联到service配置中
		List<MethodConfig> methods = new ArrayList<>();
		methods.add(methodConfig);
		serviceConfig.setMethods(methods);
		
		return serviceConfig;
	}
//	<dubbo:consumer check="false" timeout="5000"></dubbo:consumer>
	@Bean
	public ConsumerConfig consumerConfig() {
		ConsumerConfig consumerConfig = new ConsumerConfig();
		consumerConfig.setCheck(false);;
		consumerConfig.setTimeout(5000);
		return consumerConfig;
	}
//	<dubbo:monitor address="127.0.0.1:7070"></dubbo:monitor>
	@Bean
	public MonitorConfig monitorConfig() {
		MonitorConfig monitorConfig = new MonitorConfig();
		monitorConfig.setAddress("127.0.0.1:7070");
		monitorConfig.setProtocol("registry");
		return monitorConfig;
	}

}
