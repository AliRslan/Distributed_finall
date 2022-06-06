package com.example.rateservice;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
//import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@EnableHystrix
@SpringBootApplication
public class RateServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RateServiceApplication.class, args);
    }

@LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }


//    @Bean
//    public Sampler alwaysSampler() {
//        return Sampler.ALWAYS_SAMPLE;
//    }


    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

}
