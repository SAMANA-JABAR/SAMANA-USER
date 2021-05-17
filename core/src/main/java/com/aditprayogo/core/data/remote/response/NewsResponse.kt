package com.aditprayogo.core.data.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Aditiya Prayogo.
 */
data class Article(
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("source")
    val source: Source?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?
)

data class Source(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)

data class News(
    @SerializedName("articles")
    val articles: List<Article>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Int?
)