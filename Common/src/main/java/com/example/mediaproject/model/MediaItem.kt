package com.example.mediaproject.model

import com.example.mediaproject.utilities.Constants

data class MediaItem private constructor(
    val id: Int,
    val title: String,
    val genre: String,
    var status: Constants.WatchStatus
) {

    constructor() : this(
        0,
        "",
        "",
        Constants.WatchStatus.NOT_WATCHED
    )

    class Builder(
        private var id: Int = 0,
        private var title: String = "",
        private var genre: String = "",
        private var status: Constants.WatchStatus = Constants.WatchStatus.NOT_WATCHED
    ) {

        fun id(id: Int) = apply {
            this.id = id
        }

        fun title(title: String) = apply {
            this.title = title
        }

        fun genre(genre: String) = apply {
            this.genre = genre
        }

        fun status(status: Constants.WatchStatus) = apply {
            this.status = status
        }

        fun build(): MediaItem {
            return MediaItem(
                id = id,
                title = title,
                genre = genre,
                status = status
            )
        }
    }
}