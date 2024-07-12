package com.atl.order_detail_service;

import com.atl.order_detail_service.order_detail.infrastructure.hash.OrderHash;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Objects;

@Configuration
public class BeanOrderDetailRedisConfiguration {
    @Bean
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory(Environment env) {
        return new LettuceConnectionFactory(Objects.requireNonNull(env.getProperty("spring.data.redis.host")),
                Integer.parseInt(Objects.requireNonNull(env.getProperty("spring.data.redis.port"))));
    }

    @Bean
    public ReactiveHashOperations<String, String, OrderHash> reactiveHashOperations(
            ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        var template = new ReactiveRedisTemplate<>(
                reactiveRedisConnectionFactory,
                RedisSerializationContext.<String, OrderHash>newSerializationContext(new StringRedisSerializer())
                        .hashKey(new GenericToStringSerializer<>(String.class))
                        .hashValue(new Jackson2JsonRedisSerializer<>(OrderHash.class))
                        .build());
        return template.opsForHash();
    }
}
