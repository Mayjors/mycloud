package com.eu.servicedrools2.service;

import com.eu.servicedrools2.model.Car;
import com.eu.servicedrools2.model.Customer;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Results;
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

    public void kessionTest() {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write("","");
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(org.kie.api.builder.Message.Level.ERROR)) {
            System.out.println(results.getMessages());
            throw new IllegalStateException("### errors ###");
        }
        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        KieSession kieSession = kieContainer.newKieSession();

        kieSession.insert("");
        kieSession.fireAllRules();
    }
}
