package com.aman.domain.repositories

import com.aman.domain.entities.NewsSourcesEntity
import io.reactivex.Flowable

interface NewsRepository {

    fun getNews(): Flowable<NewsSourcesEntity>
    fun getLocalNews(): Flowable<NewsSourcesEntity>
    fun getRemoteNews(): Flowable<NewsSourcesEntity>

}