package com.oladipo.fairmoneytest.viemodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.oladipo.fairmoneytest.utils.CoroutineRule
import com.oladipo.fairmoneytest.utils.getOrAwaitValue
import com.oladipo.fairmoneytest.model.Data
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.Is
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainViewModelTest{

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutine = CoroutineRule()

    @Test()
    fun assertNetworkCall_WasSuccesful(){
        val data =  listOf<Data>()


        coroutine.runBlockingTest {
            val detailViewModel = MainViewModel(ApplicationProvider.getApplicationContext())

            val result = detailViewModel.users.getOrAwaitValue()

            assertThat(result.isEmpty(), Is.`is`(data.isEmpty()))
        }
    }
}