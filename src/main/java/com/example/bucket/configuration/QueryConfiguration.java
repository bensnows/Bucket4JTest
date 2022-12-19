package com.example.bucket.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "")
@Getter
@Setter
public class QueryConfiguration {

    private Integer partitionCount;

}
