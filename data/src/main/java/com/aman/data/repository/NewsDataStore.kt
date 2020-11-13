package com.aman.data.repository

import com.aman.domain.entities.NewsSourcesEntity
import io.reactivex.Flowable
import io.reactivex.Single


interface NewsDataStore{
    fun getNews(): Flowable<NewsSourcesEntity>
}