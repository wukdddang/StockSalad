package com.github.funkyblues.stock_salad

object Constants {
    const val DB_URI_STRING = "mongodb://%s:%s@%s:%d/%s?authSource=%s"

    object Settings {
        const val FILE_NAME = "settings.json"
        object Key {
            const val DEV_MODE = "dev"
            const val PROD_MODE = "prod"
            const val DB_HOST = "db_host"
            const val DB_PORT = "db_port"
            const val DB_NAME = "db_name"
            const val DB_AUTH_SOURCE = "db_auth_source"
            const val DB_USERNAME = "db_username"
            const val DB_PASSWORD = "db_password"
            const val API_KEY = "api_key"
        }
    }

    object News {
        const val QUERY_URL = "https://news.naver.com/main/list.naver?mode=LSD&mid=sec&sid1=001&date=%s&page=%d"
        const val REQUEST_PERIOD = 60 * 1000L
        const val MIN_VALIDITY_THRESHOLD = 24 * 60 * 60 * 1000L
        const val MAX_VALIDITY_THRESHOLD = 7 * 24 * 60 * 60 * 1000L
        const val RECENT_NEWS_COUNT = 10
        const val MAX_PAGE = 2000
        const val SLEEP_TIME = 1000L
        const val DATE_PATTERN = "yyyyMMdd"
        const val MAX_FAIL_COUNT = 5
        const val MIN_NEWS_COUNT_IN_PAGE = 20
    }
}
