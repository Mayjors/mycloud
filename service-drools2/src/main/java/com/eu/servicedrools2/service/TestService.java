package com.eu.servicedrools2.service;

import com.eu.servicedrools2.model.Car;
import com.eu.servicedrools2.model.Customer;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

/**
 * @author yuanjie
 * @date 2019/3/18 20:38
 */
@Service
public class TestService {

    public void test() {
        KieSession kieSession = getKieSession("conditional2");

        Customer customer = new Customer();
        customer.setAge(61);
        kieSession.insert(customer);

        Car car = new Car();
        car.setOwner(customer);
        kieSession.insert(car);

        int count = kieSession.fireAllRules();

        kieSession.dispose();
        System.out.println("Fire " + count + " rules!");
    }

    protected KieSession getKieSession(String agendaGroup){
        KieSession kieSession = getKieSession();
        kieSession.getAgenda().getAgendaGroup(agendaGroup).setFocus();
        return kieSession;
    }

    protected KieSession getKieSession(){
        KieServices kieServices = KieServices.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("all-rules");
        return kieSession;
    }
}
