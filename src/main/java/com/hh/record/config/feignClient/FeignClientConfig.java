package com.hh.record.config.feignClient;

import com.hh.record.BackendApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackageClasses = BackendApplication.class)
@Configuration
public class FeignClientConfig {

}
