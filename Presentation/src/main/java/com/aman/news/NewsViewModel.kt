package com.aman.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.aman.domain.common.Mapper
import com.aman.domain.entities.NewsSourcesEntity
import com.aman.domain.usecases.GetNewsUseCase
import com.aman.news.common.BaseViewModel
import com.aman.news.entities.Data
import com.aman.news.entities.Error
import com.aman.news.entities.NewsSources
import com.aman.news.entities.Status

class NewsViewModel(
    private val getNewsUseCase: GetNewsUseCase,
    private val mapper: Mapper<NewsSourcesEntity, NewsSources>
) : BaseViewModel() {

    companion object {
        private val TAG = "viewmodel"
    }

    var mNews = MutableLiveData<Data<NewsSources>>()

    fun fetchNews() {
        val disposable = getNewsUseCase.getNews()
            .flatMap { mapper.Flowable(it) }
            .subscribe({ response ->
                Log.d(TAG, "On Next Called")
                mNews.value = Data(responseType = Status.SUCCESSFUL, data = response)
            }, { error ->
                Log.d(TAG, "On Error Called")
                mNews.value = Data(responseType = Status.ERROR, error = Error(error.message))
            }, {
                Log.d(TAG, "On Complete Called")
            })

        addDisposable(disposable)
    }

    fun getNewsLiveData() = mNews
}