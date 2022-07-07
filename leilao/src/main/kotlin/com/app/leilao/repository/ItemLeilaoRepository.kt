package com.app.leilao.repository

import com.app.leilao.entities.ItemLeilao
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface ItemLeilaoRepository: MongoRepository<ItemLeilao, UUID> {
}