package com.eu.servicedrools.config;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * @author yuanjie
 * @date 2019/3/14 14:52
 */
//@Configuration
public class DroolsAutoConfiguration {

    private static final String RULES_PATH = "rules/";

//    @Bean
//    @ConditionalOnMissingBean(KieFileSystem.class)
    public KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = getKieServices().newKieFileSystem();
        for (Resource file : getRuleFiles()) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"));
        }
        return kieFileSystem;
    }

//    @Bean
//    @ConditionalOnMissingBean(KieContainer.class)
    public KieContainer kieContainer() throws IOException {
        final KieRepository kieRepository = getKieServices().getRepository();
        kieRepository.addKieModule(new KieModule() {
            @Override
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });
        KieBuilder kieBuilder = getKieServices().newKieBuilder(kieFileSystem());
        kieBuilder.buildAll();
        return getKieServices().newKieContainer(kieRepository.getDefaultReleaseId());
    }

//    @Bean
//    @ConditionalOnMissingBean(KieBase.class)
    public KieBase kieBase() throws IOException {
        return kieContainer().getKieBase();
    }

//    @Bean
//    @ConditionalOnMissingBean(KieSession.class)
    public KieSession kieSession() throws IOException {
        return kieContainer().newKieSession();
    }

    private Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        final Resource[] resources = resourcePatternResolver.getResources("classpath*:" + RULES_PATH +"**/*.*");
        return resources;
    }


    private KieServices getKieServices() {
        return KieServices.Factory.get();
    }
}
