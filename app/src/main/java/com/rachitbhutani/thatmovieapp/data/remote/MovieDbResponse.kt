package com.rachitbhutani.thatmovieapp.data.remote

import com.google.gson.annotations.SerializedName

data class MovieDbResponse(
    val results: List<MovieDbItem>?
)

data class MovieDbItem(
    @SerializedName("poster_path")
    val posterPath: String?,
    val id: Int?,
    val title: String?,
    val overview: String?
)

/**
 * {
 *             "backdrop_path": "/yDHYTfA3R0jFYba16jBB1ef8oIt.jpg",
 *             "id": 533535,
 *             "title": "Deadpool & Wolverine",
 *             "original_title": "Deadpool & Wolverine",
 *             "overview": "A listless Wade Wilson toils away in civilian life with his days as the morally flexible mercenary, Deadpool, behind him. But when his homeworld faces an existential threat, Wade must reluctantly suit-up again with an even more reluctant Wolverine.",
 *             "poster_path": "/8cdWjvZQUExUUTzyp4t6EDMubfO.jpg",
 *             "media_type": "movie",
 *             "adult": false,
 *             "original_language": "en",
 *             "genre_ids": [
 *                 28,
 *                 35,
 *                 878
 *             ],
 *             "popularity": 13902.556,
 *             "release_date": "2024-07-24",
 *             "video": false,
 *             "vote_average": 7.9,
 *             "vote_count": 1702
 *         }
 */
