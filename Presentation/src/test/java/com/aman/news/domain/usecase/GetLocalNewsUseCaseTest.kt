package com.aman.news.domain.usecase

import com.aman.domain.entities.NewsSourcesEntity
import com.aman.domain.usecases.GetLocalNewsUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.reactivex.Flowable
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class GetLocalNewsUseCaseTest {

        @MockK
        lateinit var getLocalNewsUseCase: GetLocalNewsUseCase

        @Before
        fun setUp() {
            MockKAnnotations.init(this)
        }

        @Test
        fun testGetNews() {
            val songs = mockk<Flowable<NewsSourcesEntity>>()
            every {
                getLocalNewsUseCase.getNews()
            } returns (songs)
            val songList = getLocalNewsUseCase.getNews()
            Assert.assertEquals(songs, songList)
        }
    }