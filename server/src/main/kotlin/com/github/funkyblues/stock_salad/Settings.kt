package com.github.funkyblues.stock_salad

import java.io.File
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object Settings {
    private const val currentMode = Constants.Settings.Key.DEV_MODE
    private val settings: JsonNode

    init {
        val settingsFile = File(Constants.Settings.FILE_NAME)
        val objectMapper = jacksonObjectMapper()
        this.settings = objectMapper.readTree(settingsFile)
    }

    private val currentSettings: JsonNode
        get() = settings[currentMode]

    val dbHost: String
        get() = currentSettings[Constants.Settings.Key.DB_HOST].asText()

    val dbPort: Int
        get() = currentSettings[Constants.Settings.Key.DB_PORT].asInt()

    val dbUserName: String
        get() = currentSettings[Constants.Settings.Key.DB_USERNAME].asText()

    val dbPassword: String
        get() = currentSettings[Constants.Settings.Key.DB_PASSWORD].asText()

    val dbName: String
        get() = currentSettings[Constants.Settings.Key.DB_NAME].asText()

    val dbAuthSource: String
        get() = currentSettings[Constants.Settings.Key.DB_AUTH_SOURCE].asText()

    val apiKey: String
        get() = currentSettings[Constants.Settings.Key.API_KEY].asText()
}
