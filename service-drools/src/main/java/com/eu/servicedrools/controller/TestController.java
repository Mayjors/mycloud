package com.eu.servicedrools.controller;

import com.eu.servicedrools.entity.Address;
import com.eu.servicedrools.entity.AddressCheckResult;
import com.eu.servicedrools.service.DroolsService;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yuanjie
 * @date 2019/3/14 14:42
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private DroolsService droolsService;

    @GetMapping("/showRules")
    public String showRules() {
        return droolsService.fireRule();
    }

    @Resource
    private KieSession kieSession;

    @ResponseBody
    @RequestMapping("/address")
    public void test(){
        Address address = new Address();
        address.setPostcode("99425");

        AddressCheckResult result = new AddressCheckResult();
        kieSession.insert(address);
        kieSession.insert(result);
        int ruleFiredCount = kieSession.fireAllRules();
        System.out.println("触发了" + ruleFiredCount + "条规则");

        if(result.isPostCodeResult()){
            System.out.println("规则校验通过");
        }

    }
}
