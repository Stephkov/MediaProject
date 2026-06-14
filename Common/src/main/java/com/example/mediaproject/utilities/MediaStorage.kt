package com.example.mediaproject.utilities

import com.example.mediaproject.model.MediaItem
import com.example.mediaproject.model.MediaList
import com.google.gson.Gson
object MediaStorage {

    fun loadMediaList(key: String): MediaList {
        val json = SharedPreferencesManager
            .getInstance()
            .getString(key, "")

        if (json.isBlank()) {
            return MediaList(emptyList())
        }

        return Gson().fromJson(json, MediaList::class.java)
    }

    fun saveMediaList(key: String, mediaList: MediaList) {
        val json = Gson().toJson(mediaList)

        SharedPreferencesManager
            .getInstance()
            .putString(key, json)
    }

    fun loadItems(key: String): MutableList<MediaItem> {
        return loadMediaList(key).items.toMutableList()
    }

    fun saveItems(key: String, items: List<MediaItem>) {
        saveMediaList(key, MediaList(items))
    }
}