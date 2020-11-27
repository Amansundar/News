package com.aman.data.repository

import com.aman.domain.entities.NewsSourcesEntity
import io.reactivex.Flowable


interface NewsDataStore {
    fun getNews(): Flowable<NewsSourcesEntity>
}