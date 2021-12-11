package top.hkyzf.cloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * 网关配置类
 *
 * @author 朱峰
 * @date 2021-12-10 16:22
 */
@Configuration
public class GatewayConfig {

    /**
     * 通过配置类配置路由的示例
     *
     * @param routeLocatorBuilder 路由定位生成器
     * @return 路由定位器
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        return routes.route("baidu_root", r -> r.path("/").uri("http://news.baidu.com/"))
                .route("baidu_guonei", r -> r.path("/guonei").uri("http://news.baidu.com/guonei"))
                .route("baidu_guoji", r -> r.path("/guoji").uri("http://news.baidu.com/guoji")).build();
    }

    /**
     * 网关统一配置允许跨域
     *
     * @return 跨域访问过滤器
     */
    @Bean
    public CorsWebFilter crosWebFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 1、配置跨域
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许所有头进行跨域
        corsConfiguration.addAllowedHeader("*");
        // 允许所有请求方式进行跨域
        corsConfiguration.addAllowedMethod("*");
        // 允许所有请求来源进行跨域
        corsConfiguration.addAllowedOrigin("*");
        // 允许携带 cookie 进行跨域
        corsConfiguration.setAllowCredentials(true);
        // 2、任意路径都允许第 1 步配置的跨域
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(source);
    }
}

