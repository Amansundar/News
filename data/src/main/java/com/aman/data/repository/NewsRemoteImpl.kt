package com.aman.data.repository

import com.aman.data.api.RemoteNewsApi
import com.aman.data.entities.NewsDataEntityMapper
import com.aman.domain.entities.NewsSourcesEntity
import io.reactivex.Flowable

class NewsRemoteImpl constructor(private val api: RemoteNewsApi) : NewsDataStore {

    private val newsMapper = NewsDataEntityMapper()

    override fun getNews(): Flowable<NewsSourcesEntity> {
        return api.getNews().map { newsMapper.mapToEntity(it) }
    }

}