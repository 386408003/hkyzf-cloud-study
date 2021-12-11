package top.hkyzf.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 朱峰
 * @date 2021-12-7 23:57
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConsumerMemberApplication {
    public static void main(String[] args) {
        // 启动报错 Failed to rename context [nacos] as [logback]，不影响使用，解决方案如下。
        // 原因：Alibaba Nacos的Logback先加载完成后，再加载项目本身的Logback时就出现了冲突。
        // 一个项目中其context_name只能定义一次。
        System.setProperty("nacos.logging.default.config.enabled", "false");

        SpringApplication.run(ConsumerMemberApplication.class, args);
    }
}
