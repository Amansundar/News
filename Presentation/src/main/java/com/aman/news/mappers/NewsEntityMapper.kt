package com.aman.news.mappers

import com.aman.domain.common.Mapper
import com.aman.domain.entities.NewsPublisherEntity
import com.aman.domain.entities.NewsSourcesEntity
import com.aman.news.entities.NewsPublisher
import com.aman.news.entities.NewsSources

class NewsEntityMapper : Mapper<NewsSourcesEntity, NewsSources>() {
    override fun mapFrom(from: NewsSourcesEntity): NewsSources = NewsSources(
        status = from.status,
        articles = mapListArticlesToPresentation(from.articles)
    )

    private fun mapListArticlesToPresentation(articles: List<NewsPublisherEntity>?)
            : List<NewsPublisher> = articles?.map { mapArticleToPresentation(it) }
        ?: emptyList()

    private fun mapArticleToPresentation(response: NewsPublisherEntity): NewsPublisher =
        NewsPublisher(
            id = response.id,
            name = response.name,
            description = response.description,
            url = response.url,
            category = response.category
        )

}