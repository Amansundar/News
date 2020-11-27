package com.aman.data.entities

import com.aman.domain.entities.NewsPublisherEntity
import com.aman.domain.entities.NewsSourcesEntity
import com.google.gson.annotations.SerializedName

data class NewsSourcesData(
    @SerializedName("status") var status: String? = null,
    @SerializedName("articles") var articles: List<NewsPublisherData> = emptyList()
)

class NewsDataEntityMapper {

    fun mapToEntity(data: NewsSourcesData?): NewsSourcesEntity? = NewsSourcesEntity(
        status = data?.status,
        articles = mapListArticlesToEntity(data?.articles)
    )

    fun mapToEntity(articles: List<NewsPublisherData>?): NewsSourcesEntity? = NewsSourcesEntity(
        articles = mapListArticlesToEntity(articles)
    )

    private fun mapListArticlesToEntity(articles: List<NewsPublisherData>?)
            : List<NewsPublisherEntity> = articles?.map { mapArticleToEntity(it) } ?: emptyList()

    private fun mapArticleToEntity(response: NewsPublisherData): NewsPublisherEntity =
        NewsPublisherEntity(
            id = response.id,
            name = response.name,
            description = response.description,
            url = response.url,
            category = response.category
        )
}


class NewsEntityDataMapper {

    fun mapArticleToEntity(response: NewsPublisherEntity): NewsPublisherData = NewsPublisherData(
        id = response.id,
        name = response.name,
        description = response.description,
        url = response.url,
        category = response.category
    )
}

