package com.eu.servicespark.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.receiver.Receiver;

/**
 * 自定义接受streaming类
 */
@Slf4j
public class CustomReceiver extends Receiver<String> {
    private static final long serialVersionUID = 5817531198342629801L;

    public CustomReceiver(StorageLevel storageLevel) {
        super(storageLevel);
    }

    @Override
    public void onStart() {
        new Thread(this::onStart).start();
        log.info("开始启动Receiver...");
        // doStart();
    }

    public void doStart() {
        while (!isStopped()) {
            int value = RandomUtils.nextInt(100);
            if (value < 20) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    log.error("sleep exception", e);
                    restart("sleep exception",e);
                }
            }
            store(String.valueOf(value));
        }
    }

    @Override
    public void onStop() {
        log.info("即将停止Receiver...");
    }
}
