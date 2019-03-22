package com.eu.servicedrools2.config;

import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.kie.internal.io.ResourceFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author yuanjie
 * @date 2019/3/22 11:37
 */
public class KieSessionConfig {

    public void checkDrl() throws FileNotFoundException {
        File file = new File("E:\\GitHub\\respo\\drools-lesson\\src\\main\\resources\\decision.xls");
        InputStream is = new FileInputStream(file);
        SpreadsheetCompiler compiler = new SpreadsheetCompiler();
        String drl = compiler.compile(is, InputType.XLS);
        System.out.println(drl);
    }

    public void checkDrl2() throws FileNotFoundException {
        SpreadsheetCompiler compiler = new SpreadsheetCompiler();
        String drl = compiler.compile(ResourceFactory.newClassPathResource("com/decision/decision.xls"), InputType.XLS);
        System.out.println(drl);
    }
}
