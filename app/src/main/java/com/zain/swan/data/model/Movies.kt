package com.zain.swan.data.model

data class Movies(
    val average_rating: Double,
    val backdrop_path: String,
    val comments: Comments,
    val created_by: CreatedBy,
    val description: String,
    val id: Int,
    val iso_3166_1: String,
    val iso_639_1: String,
    val object_ids: ObjectIds,
    val page: Int,
    val poster_path: String,
    val public: Boolean,
    val results: ArrayList<Result>,
    val revenue: Long,
    val runtime: Int,
    val sort_by: String,
    val total_pages: Int,
    val total_results: Int
    )