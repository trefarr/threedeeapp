package com.threedeeapp.threedeemodel;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableHystrix
public class Application  {

/*	@Autowired
	private ModelController com.threedeeapp.threedeemodel.controller;*/

	public Application() {
	}


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
