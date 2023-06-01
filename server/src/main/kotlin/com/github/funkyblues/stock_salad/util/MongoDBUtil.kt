package com.github.funkyblues.stock_salad.util

import com.mongodb.client.MongoClients
import com.github.funkyblues.stock_salad.Settings
import com.github.funkyblues.stock_salad.Constants
import org.springframework.data.mongodb.core.MongoTemplate

object MongoDBUtil {
    private val lock = Any()
    private fun getMongoDBURI(): String {
        return Constants.DB_URI_STRING.format(
            Settings.dbUserName,
            Settings.dbPassword,
            Settings.dbHost,
            Settings.dbPort,
            Settings.dbName,
            Settings.dbAuthSource
        )
    }

    fun getMongoTemplate(): MongoTemplate {
        synchronized(lock) {
            val mongoClient = MongoClients.create(getMongoDBURI())
            return MongoTemplate(mongoClient, Settings.dbName)
        }
    }
}