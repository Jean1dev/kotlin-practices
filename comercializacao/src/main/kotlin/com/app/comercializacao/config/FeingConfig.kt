package com.app.comercializacao.config

import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

@Configuration
class FeingConfig{

    @Bean
    fun messageConverter(): HttpMessageConverters = HttpMessageConverters(MappingJackson2HttpMessageConverter())
}