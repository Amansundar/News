package com.aman.data.repository

import com.aman.data.db.ArticlesDao
import com.aman.data.db.NewsDatabase
import com.aman.data.entities.NewsDataEntityMapper
import com.aman.data.entities.NewsEntityDataMapper
import com.aman.domain.entities.NewsSourcesEntity
import io.reactivex.Flowable

class NewsCacheImpl(private val database: NewsDatabase,
                    private val entityToDataMapper: NewsEntityDataMapper,
                    private val dataToEntityMapper: NewsDataEntityMapper) : NewsDataStore {

    private val dao: ArticlesDao = database.getArticlesDao()

    override fun getNews(): Flowable<NewsSourcesEntity> {
        return dao.getAllArticles().map { it ->
            dataToEntityMapper.mapToEntity(it)
        }
    }

    fun saveArticles(it: NewsSourcesEntity) {
        dao.clear()
        dao.saveAllArticles(it.articles.map { articles -> entityToDataMapper.mapArticleToEntity(articles) })
    }

}