package com.aman.news.domain.usecase

import com.aman.domain.entities.NewsSourcesEntity
import com.aman.domain.usecases.GetNewsUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.reactivex.Flowable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetNewsUseCaseTest {

    @MockK
    lateinit var getNewsUseCase: GetNewsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testGetNews() {
        val songs = mockk<Flowable<NewsSourcesEntity>>()
        every {
            getNewsUseCase.getNews()
        } returns (songs)
        val songList = getNewsUseCase.getNews()
        assertEquals(songs, songList)
    }
}