package com.eu.servicefeign.feign;

import com.eu.servicefeign.feign.impl.TestFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yuanjie
 * @date 2018/12/12 9:52
 */
@FeignClient(name = "eureka-client", fallback = TestFeignFallback.class)
public interface TestFeign {

    @RequestMapping(value = "/test/info", method = RequestMethod.GET)
    public String info();
}
