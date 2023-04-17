package com.example.lottery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 项目启动成功后自动输出项目访问地址
 */
@Component
public class PrintURL implements CommandLineRunner {
    Logger log = LoggerFactory.getLogger(PrintURL.class);

    @Value("${server.port:8080}")
    Integer PORT;

    @Override
    public void run(String... args) throws Exception {
        log.info("项目启动成功，访问地址：http://localhost:{}", PORT);
    }

}
