package com.oladipo.fairmoneytest.viemodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.oladipo.fairmoneytest.utils.CoroutineRule
import com.oladipo.fairmoneytest.db.UserDetails
import com.oladipo.fairmoneytest.utils.getOrAwaitValue
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailViewModelTest{

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutine = CoroutineRule()



    @Test
    fun assertCallToSave_WasSuccessful() {

        val user = UserDetails(
            "123", "Ola", "123455", "23-77-1234", "qwerty@jmail.com", "Lagos",
            "Olaa", "Male", "image.png", "23/45/1234", "Mr"
        )

        val id = argumentCaptor<String>()
//        val apikey = argumentCaptor<String>()

        coroutine.runBlockingTest {
            val detailViewModel = DetailViewModel(ApplicationProvider.getApplicationContext())

            val result = detailViewModel.getUserDetailFromLocal(id.toString()).getOrAwaitValue()

            assertThat(result, `is`(user))
        }
    }
}