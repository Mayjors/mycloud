package com.eu.servicedrools2.service;

import com.eu.servicedrools2.model.Customer;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.io.KieResources;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.springframework.stereotype.Component;

import static org.kie.api.io.ResourceType.DRL;

/**
 * @author yuanjie
 * @date 2019/3/19 16:11
 */
@Component
public class KieFileSystemTest {
    /**
     * 先创建KieModuleModel；
     * 再创建KieBaseModel；
     * 然后创建 KieSessionModel；
     * 创建完成之后可以生产一个xml文件，就是kmodule.xml文件了；
     * 将这个xml文件写入到KieFileSystem中；
     * 然后将规则文件等写入到KieFileSystem中；
     * 最后通过KieBuilder进行构建就将该kmodule加入到KieRepository中了。这样就将自定义的kmodule加入到引擎中了，就可以按照之前的方法进行使用了
     */
    public void test() {
        KieServices kieServices = KieServices.Factory.get();
        KieResources resources = kieServices.getResources();

        KieModuleModel kieModuleModel = kieServices.newKieModuleModel();

        KieBaseModel baseModel = kieModuleModel.newKieBaseModel().addPackage("");
        baseModel.newKieSessionModel("FileSystemKession");
        KieFileSystem fileSystem = kieServices.newKieFileSystem();

        String xml = kieModuleModel.toXML();
        System.out.println(xml);

        fileSystem.write("src/main/resources/com/rules/conditional/conditional1.drl",
                resources.newClassPathResource("kiefilesystem/KieFileSystemTest.drl"));

        KieBuilder kieBuilder = kieServices.newKieBuilder(fileSystem);
        kieBuilder.buildAll();
        if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
            throw new RuntimeException("Build Errors:\n" + kieBuilder.getResults().toString());
        }
        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());

        KieSession kieSession = kieContainer.newKieSession("FileSystemKession");
        kieSession.fireAllRules();
    }

    public void handle() {
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        // 以DRL形式加载规则
        builder.add(ResourceFactory.newByteArrayResource(rule2Drl().getBytes()), DRL);
        KnowledgeBuilderErrors errors = builder.getErrors();
        for (KnowledgeBuilderError error : errors) {
            System.out.println(error.getMessage());
        }
        InternalKnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addPackages(builder.getKnowledgePackages());
        // 获取规则引擎会话session
        KieSession kieSession = knowledgeBase.newKieSession();
//        KieSession kieSession = builder.newKieBase().newKieSession();

        Customer customer = new Customer();
        customer.setAge(61);
        kieSession.insert(customer);
        int count = kieSession.fireAllRules();
        kieSession.dispose();
        System.out.println("Fire " + count + " rules!");
    }

    public String rule2Drl() {
        StringBuilder result = new StringBuilder();
        /* package部分 */
        result.append("package com.rules.conditional\r\n");
        result.append("\r\n");

        /* 导包部分 */
        result.append("import com.eu.servicedrools2.model.Customer;\r\n");
        result.append("import com.eu.servicedrools2.model.Car;\r\n");
        result.append("\r\n");

        /* 规则申明部分 */
        result.append("rule \"conditional1:Give 10% discount to customers older than 60\"\r\n");
        result.append("\r\n");

        /* 规则属性部分 */

        /* 规则条件部分 */
        result.append("\twhen\r\n");
        result.append("\t\t$customer : Customer( age > 60 )\r\n");

        /* 规则结果部分 */
        result.append("\tthen\r\n");
        result.append("\t\tmodify($customer) { setDiscount( 0.1 ) };\r\n");
        result.append("\t\tSystem.out.println(\"Give 10% discount to customers older than 60\");\r\n");

        /* 规则结束 */
        result.append("end\r\n");

        return result.toString();
    }

}
