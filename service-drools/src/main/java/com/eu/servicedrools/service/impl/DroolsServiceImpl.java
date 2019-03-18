package com.eu.servicedrools.service.impl;


import com.eu.servicedrools.domain.Message;
import com.eu.servicedrools.entity.Address;
import com.eu.servicedrools.service.DroolsService;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

/**
 * @author yuanjie
 * @date 2019/3/14 14:38
 */
@Service("droolsService")
public class DroolsServiceImpl implements DroolsService {
    @Override
    public String fireRule() {
        // load up the knowledge base
        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kession-rules");

        // go!
        Message message = new Message();
        message.setMessage("Hello World");
        message.setStatus(Message.HELLO);
        kieSession.insert(message); // 插入
        kieSession.fireAllRules();  // 执行规则
//        kieSession.dispose();

        String s = message.getMessage();
        System.out.println(s);

        KieSession kieSession2 = kieContainer.newKieSession("kession-rules");
        message.setMessage("Good Bye");
        message.setStatus(Message.GOODBYE);
        kieSession2.insert(message); // 插入
        kieSession2.fireAllRules();  // 执行规则
        kieSession2.dispose();

        KieSession kieSession3 = kieContainer.newKieSession("kession-rules");
        Address address = new Address();
        address.setPostcode("99425");
        kieSession3.insert(address); // 插入
        kieSession3.fireAllRules();  // 执行规则

        s = message.getMessage();
        return s;
    }
}
