package com.jane.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

/**
 * Created by jianggc at 2019/12/24.
 */
//@Configuration
public class ElasticsearchConfig {
//    @Bean
    RestHighLevelClient client() {

        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("192.168.18.128:9200")
//                .connectedTo("192.168.18.128:9300", "localhost:9301")
                .build();
        RestHighLevelClient restHighLevelClient = RestClients.create(clientConfiguration).rest();
        System.out.println(restHighLevelClient);

        return restHighLevelClient;
    }
}
