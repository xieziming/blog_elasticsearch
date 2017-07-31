/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Suny on 7/8/17.
 */
@Configuration
public class ElasticsearchConfig {
    @Bean
    Client elasticSearchClient() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "com.xieziming.cluster")
                .put("xpack.security.user", "elastic:changeme")
                .build();
        return new PreBuiltXPackTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("tools.xieziming"), 9300));
    }
}
