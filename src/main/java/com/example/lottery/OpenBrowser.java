package com.example.lottery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URI;

@Order(1)   // 优先级，值越小优先级越高
@Component
// 条件注解，只有当openBrowser=false时，才不会加载该类
@ConditionalOnProperty(name = "open.browser", havingValue = "true", matchIfMissing = true)
public class OpenBrowser implements CommandLineRunner {
    Logger log = LoggerFactory.getLogger(OpenBrowser.class);

    @Value("${server.port:8080}")
    Integer PORT;

    @Override
    public void run(String... args) throws Exception {
        URI uri = new URI("http://localhost:" + PORT);
        try {
            // spring boot默认是headless模式，无法打开浏览器，需要手动关闭headless模式
            System.setProperty("java.awt.headless", "false");
            Desktop.getDesktop().browse(uri);
        } catch (Exception e) {
            log.error("打开浏览器失败", e);
            log.info("以上为打开默认浏览器失败的异常信息，可以忽略");
        } finally {
            // 还原headless模式
            System.setProperty("java.awt.headless", "true");
        }
    }
}
