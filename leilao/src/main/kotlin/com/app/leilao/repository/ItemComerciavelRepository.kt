package com.app.leilao.repository

import com.app.leilao.entities.ItemComerciavel
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface ItemComerciavelRepository: MongoRepository<ItemComerciavel, UUID> {
}