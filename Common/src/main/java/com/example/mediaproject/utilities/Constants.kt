package com.example.mediaproject.utilities

class Constants {
    enum class WatchStatus {
        NOT_WATCHED,
        WATCHED
    }


    object SP_KEYS {
        const val DATA_FILE = "MEDIA_DATA"
        const val MOVIES_KEY = "MOVIES_KEY"
        const val SERIES_KEY = "SERIES_KEY"


    }
    object IntentKeys {
        const val EXTRA_STORAGE_KEY = "EXTRA_STORAGE_KEY"
        const val EXTRA_MEDIA_TYPE = "EXTRA_MEDIA_TYPE"
    }

}