package com.example.bucket.configuration;

import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "lark.bucket")
@Setter
@Getter
public class TestBucketConfiguration {

    private Integer fillCountPerTime;
    private Long fillPeriod;
    private Integer initialCount;

    @Bean
    public Bucket getBucket() {
        Refill refill = Refill.intervally(fillCountPerTime, Duration.ofSeconds(fillPeriod));
        Bandwidth limit = Bandwidth.classic(initialCount, refill);
        return Bucket.builder().addLimit(limit).build();
    }

}
