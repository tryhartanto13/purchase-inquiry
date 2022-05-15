package com.th.purchase.inquiry.config;

import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.spring.starter.remote.InfinispanRemoteConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class DataGridConfig {

    @Value(value = "${dg.host}")
    private String host;

    @Value(value = "${dg.port}")
    private int port;

    @Bean
    public InfinispanRemoteConfigurer infinispanRemoteConfigurer() {
        return () -> new ConfigurationBuilder()
                .addServer()
                .host(host)
                .port(port)
                .build();
    }
}
