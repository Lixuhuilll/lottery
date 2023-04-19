package com.example.lottery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URI;

/**
 * 项目启动成功后自动输出项目访问地址
 */
@Component
public class PrintURL implements CommandLineRunner {
    Logger log = LoggerFactory.getLogger(PrintURL.class);

    @Value("${server.port:8080}")
    Integer PORT;

    // 是否打开浏览器
    @Value("${open.browser:true}")
    Boolean openBrowser;

    @Override
    public void run(String... args) throws Exception {
        URI uri = new URI("http://localhost:" + PORT);
        if (!openBrowser) {
            // 宁愿多写一行，也要控制缩进，后续代码的缩进已经太深了
            log.info("本地项目地址：{}", uri);
            return;
        }
        try {
            // spring boot默认是headless模式，无法打开浏览器，需要手动关闭headless模式
            System.setProperty("java.awt.headless", "false");
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    desktop.browse(uri);
                }
            }
        } catch (Exception e) {
            log.error("打开浏览器失败", e);
            log.info("以上为打开默认浏览器失败的异常信息，可以忽略");
        } finally {
            // 还原headless模式
            System.setProperty("java.awt.headless", "true");
            // 我希望本地项目地址最后输出，保证不会被错误信息挡住
            log.info("本地项目地址：{}", uri);
        }
    }

}
