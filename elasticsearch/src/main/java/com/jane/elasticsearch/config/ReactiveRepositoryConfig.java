package com.jane.elasticsearch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.client.reactive.ReactiveRestClients;
import org.springframework.data.elasticsearch.config.AbstractReactiveElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;

/**
 * Created by jianggc at 2019/12/27.
 */
@Configuration
@EnableReactiveElasticsearchRepositories
public class ReactiveRepositoryConfig extends AbstractReactiveElasticsearchConfiguration {

    @Override
    public ReactiveElasticsearchClient reactiveElasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("192.168.18.128:9200")
                .withSocketTimeout(60000)
                .withConnectTimeout(60000)
                .build();
        ReactiveElasticsearchClient reactiveElasticsearchClient = ReactiveRestClients.create(clientConfiguration);
        System.out.println("reactiveElasticsearchClient is created:"+reactiveElasticsearchClient);
        return reactiveElasticsearchClient;
    }
}
