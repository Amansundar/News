package com.aman.news.mappers

import com.aman.domain.common.Mapper
import com.aman.domain.entities.NewsPublisherEntity
import com.aman.domain.entities.NewsSourcesEntity
import com.aman.news.entities.NewsPublisher
import com.aman.news.entities.NewsSources

class NewsEntityMapper : Mapper<NewsSourcesEntity, NewsSources>() {
    override fun mapFrom(data: NewsSourcesEntity): NewsSources = NewsSources(
        status = data?.status,
        articles = mapListArticlesToPresetation(data?.articles)
    )

    private fun mapListArticlesToPresetation(articles: List<NewsPublisherEntity>?)
            : List<NewsPublisher> = articles?.map { mapArticleToPresentation(it) }
            ?: emptyList()

    private fun mapArticleToPresentation(response: NewsPublisherEntity): NewsPublisher = NewsPublisher(
            id = response.id,
            name = response.name,
            description = response.description,
            url = response.url,
            category = response.category
    )

}