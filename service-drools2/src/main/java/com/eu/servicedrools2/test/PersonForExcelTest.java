package com.eu.servicedrools2.test;

import com.eu.servicedrools2.model.PersonForExcel;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.junit.Test;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.DecisionTableInputType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author yuanjie
 * @date 2019/3/22 14:45
 */
public class PersonForExcelTest {

    @Test
    public  void excel0() throws FileNotFoundException {
        DecisionTableConfiguration decisionTableConfiguration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
        // 表示执行的是xls，另一种是csv
        decisionTableConfiguration.setInputType(DecisionTableInputType.XLS);
//        String filePath = "F:\\www\\xlsx\\personAge.xlsx";
//        String filePath = PersonForExcelTest.class.getClassLoader().getResource("personAge.xlsx").getPath();
//        String filePath = "com/rules/personAge.xlsx";
//        Resource resource = ResourceFactory.newClassPathResource(filePath);

        File file = new File("F:\\www\\xlsx\\personAge.xlsx");
        InputStream is = new FileInputStream(file);
        Resource resource = ResourceFactory.newInputStreamResource(is, "utf-8");

        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        // xls的标识
        knowledgeBuilder.add(resource, ResourceType.DTABLE, decisionTableConfiguration);
        // 获取base实现方法
        InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        // 将集合添加到KnowledgeBase中
        kbase.addPackages(knowledgeBuilder.getKnowledgePackages());
        StatelessKieSession kieSession = kbase.newStatelessKieSession();
//        Person person = new Person("张三", 30);
        PersonForExcel personForExcel = new PersonForExcel("奥巴马", 61);

        KieSession kieSession1 = kbase.newKieSession();
        kieSession1.insert(personForExcel);
        int count = kieSession1.fireAllRules();
        kieSession1.dispose();
//        kieSession.execute(personForExcel);
        System.out.println(count);
//        System.out.println(personForExcel);
    }

    @Test
    public void test() throws FileNotFoundException {
        DecisionTableConfiguration dtableconfiguration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
        dtableconfiguration.setInputType(DecisionTableInputType.XLS);//枚举  表示执行的是xls，当然还有一种csv的
//        String filePath = ExcelTest.class.getClassLoader().getResource("personAge.xlsx").getPath();
//        String filePath = ExcelTest.class.getClassLoader().getResource("com.drools.excel.table.test1.personAge.xlsx").getPath();
//        String filePath = "com/drools/excel/table/test1/personAge.xlsx";
//        System.out.println("文件路径"+filePath);
//        Resource resource = ResourceFactory      //add方法  *添加一个给定的资源类型的资源,使用ResourceConfiguration提供
//                .newClassPathResource(filePath,  //找到指定目录的xls文件，
//                        ExcelTest.class);
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        File file = new File("F:\\www\\xlsx\\personAge.xlsx");
        InputStream is = new FileInputStream(file);
        Resource resource = ResourceFactory.newInputStreamResource(is, "utf-8");
        kbuilder.add(resource, ResourceType.DTABLE, dtableconfiguration);   //xls的标识
        InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();//获取base实现方法
        kbase.addPackages(kbuilder.getKnowledgePackages());   //将集合添加到KnowledgeBase中
        StatelessKieSession ksession = kbase.newStatelessKieSession();//通过base获取 kession 实现
//        Person person=new Person("张三",30);
        PersonForExcel person = new PersonForExcel("x", 12);
//        person.setName("Tony");
        ksession.execute(person);
    }
}
