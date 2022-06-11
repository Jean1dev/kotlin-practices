package com.app.comercializacao.repositories

import com.app.comercializacao.entities.Cambio
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface CambioRepository : MongoRepository<Cambio, ObjectId> {
}