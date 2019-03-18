package com.eu.servicedrools.service.impl;

import com.eu.servicedrools.model.Car;
import com.eu.servicedrools.model.Customer;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

/**
 * @author yuanjie
 * @date 2019/3/18 18:36
 */
@Service
public class TestServiceImpl {

    public String test() {
        KieServices ks = KieServices.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession-rules");
        kieSession.getAgenda().getAgendaGroup("conditional1").setFocus();
        Customer customer = new Customer();
        customer.setAge(61);
        kieSession.insert(customer);

        Car car = new Car();
        car.setOwner(customer);
        kieSession.insert(car);

        int count = kieSession.fireAllRules();

        kieSession.dispose();
        System.out.println("Fire " + count + " rules!");
        return "ssss";
    }
}
