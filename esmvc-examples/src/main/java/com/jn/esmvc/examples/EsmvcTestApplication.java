package com.jn.esmvc.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EsmvcTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(EsmvcTestApplication.class, args);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(60000000);
                }catch (Throwable ex){
                    // ignore
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }
}
