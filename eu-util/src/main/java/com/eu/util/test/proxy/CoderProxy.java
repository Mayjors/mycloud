package com.eu.util.test.proxy;

/**
 * 静态代理
 * @author yuanjie
 * @date 2019/1/8 14:20
 */
public class CoderProxy implements ICoder {

    private ICoder coder;

    public CoderProxy(ICoder coder) {
        this.coder = coder;
    }

    @Override
    public void implDemands(String demandName) {
        coder.implDemands(demandName);
    }
}
