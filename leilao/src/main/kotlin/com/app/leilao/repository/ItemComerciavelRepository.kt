package com.app.leilao.repository

import com.app.leilao.entities.ItemComerciavel
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface ItemComerciavelRepository: MongoRepository<ItemComerciavel, ObjectId> {
}