package com.aman.news.di

import androidx.room.Room
import com.aman.data.api.RemoteNewsApi
import com.aman.data.db.NewsDatabase
import com.aman.data.entities.NewsDataEntityMapper
import com.aman.data.entities.NewsEntityDataMapper
import com.aman.data.repository.NewsCacheImpl
import com.aman.data.repository.NewsRemoteImpl
import com.aman.data.repository.NewsRepositoryImpl
import com.aman.domain.repositories.NewsRepository
import com.aman.domain.usecases.GetNewsUseCase
import com.aman.news.NewsViewModel
import com.aman.news.common.AsyncFlowableTransformer
import com.aman.news.mappers.NewsEntityMapper
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

val mRepositoryModules = module {
    single(name = "remote") { NewsRemoteImpl(api = get(API)) }
    single(name = "local") {
        NewsCacheImpl(
            database = get(DATABASE), entityToDataMapper = NewsEntityDataMapper(),
            dataToEntityMapper = NewsDataEntityMapper()
        )
    }
    single { NewsRepositoryImpl(remote = get("remote"), cache = get("local")) as NewsRepository }
}

val mUseCaseModules = module {
    factory(name = "getNewsUseCase") {
        GetNewsUseCase(
            transformer = AsyncFlowableTransformer(),
            repositories = get()
        )
    }
}

val mNetworkModules = module {
    single(name = RETROFIT_INSTANCE) { createNetworkClient(BASE_URL) }
    single(name = API) { (get(RETROFIT_INSTANCE) as Retrofit).create(RemoteNewsApi::class.java) }
}

val mLocalModules = module {
    single(name = DATABASE) {
        Room.databaseBuilder(
            androidApplication(),
            NewsDatabase::class.java,
            "news_articles"
        ).build()
    }
}

val mViewModels = module {
    viewModel {
        NewsViewModel(getNewsUseCase = get(GET_NEWS_USECASE), mapper = NewsEntityMapper())
    }
}

private const val BASE_URL = "https://newsapi.org/v2/"
private const val RETROFIT_INSTANCE = "Retrofit"
private const val API = "Api"
private const val GET_NEWS_USECASE = "getNewsUseCase"
private const val DATABASE = "database"