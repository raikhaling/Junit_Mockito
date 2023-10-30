package com.example.testing.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserCacheConfig {
    @Bean
    public Config cachConfig(){
        return new Config()
                .setInstanceName("hazel-instance")
                .addMapConfig(new MapConfig()
                        .setName("user-cache")
                        .setTimeToLiveSeconds(3000)
                        .setMaxIdleSeconds(1200)

                );
    }
}
