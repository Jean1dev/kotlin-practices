package com.app.comercializacao.services

import com.app.comercializacao.dtos.CambioDto
import com.app.comercializacao.feign.CambioApiClient
import com.app.comercializacao.mappers.toDomain
import com.app.comercializacao.repositories.CambioRepository
import com.app.comercializacao.service.GerenciadorCambioService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class GerenciadorCambioServiceTest {

    @InjectMockKs
    private lateinit var service: GerenciadorCambioService

    @MockK(relaxed = true)
    private lateinit var repository: CambioRepository

    @MockK(relaxed = true)
    private lateinit var cambioApiClient: CambioApiClient

    @Test
    fun `deve salvar o cambio e adicionar na lista`() {
        val cambio = CambioDto(valor = 0.5)
        val cambioDomain = cambio.toDomain()

        every { repository.save(any()) } answers { cambioDomain }

        assertDoesNotThrow {
            service.upsertCambio(cambio)
        }

        assertEquals(1, service.getHistorico().size)
    }

}