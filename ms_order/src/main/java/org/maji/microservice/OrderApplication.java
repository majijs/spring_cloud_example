package org.maji.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author maji
 * @create 2017-12-21
 */
@EnableFeignClients //启用feign
@EnableHystrix //启用hystrix
@EnableDiscoveryClient //申明是eureka客户端
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Bean
    @LoadBalanced //Ribbon负载均衡
    public RestTemplate restTemplate(){
        //使用okHttp
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }
}
