package com.eu.servicedrools2.test;

import com.eu.servicedrools2.model.Car;
import com.eu.servicedrools2.model.Customer;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Created by zhuzs on 2017/8/4.
 */
public class ConditionalNamedConsequencesTest  {

    @Test
    public void testConditional1() {
        KieSession kieSession = getKieSession("conditional1");

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

    @Test
    public void testConditional2() {
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

    @Test
    public void testConditional3() {
        KieSession kieSession = getKieSession("conditional3");

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

    @Test
    public void testConditional4() {
        KieSession kieSession = getKieSession("conditional4");

        Customer customer = new Customer();
        customer.setAge(61);
        customer.setType("Golden");
        kieSession.insert(customer);

        Car car = new Car();
        car.setOwner(customer);
        kieSession.insert(car);

        int count = kieSession.fireAllRules();

        kieSession.dispose();
        System.out.println("Fire " + count + " rules!");
    }

    @Test
    public void testConditional5() {
        KieSession kieSession = getKieSession("conditional5");

        Customer customer = new Customer();
        customer.setAge(61);
        customer.setType("Silver");
        kieSession.insert(customer);

        Car car = new Car();
        car.setOwner(customer);
        kieSession.insert(car);

        int count = kieSession.fireAllRules();
        System.out.println("Golden : fire " + count + " rules!");
        kieSession.dispose();

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
