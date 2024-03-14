package com.brainstation.BrainstationWebAPI.config;
import com.brainstation.BrainstationWebAPI.constant.AppConstant;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topic(){
     return TopicBuilder.name(AppConstant.USER_CRUD).build();
    }
}
