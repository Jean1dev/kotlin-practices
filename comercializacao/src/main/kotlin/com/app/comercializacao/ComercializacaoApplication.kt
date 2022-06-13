package com.app.comercializacao

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class ComercializacaoApplication

fun main(args: Array<String>) {
	runApplication<ComercializacaoApplication>(*args)
}
