package com.example.superheroescertificacion

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.superheroescertificacion.data.local.HeroeDao
import com.example.superheroescertificacion.data.local.HeroeDataBase
import com.example.superheroescertificacion.data.local.HeroeEntity
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class RoomDataBaseTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var heroeDao: HeroeDao
    private lateinit var db: HeroeDataBase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, HeroeDataBase::class.java).build()
        heroeDao = db.getHeroeDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertHeroe_empty() = runBlocking {
        // Given
        val heroeList = listOf<HeroeEntity>()

        // When
        heroeDao.insertALLHeroe(heroeList)

        // Then A
        val it = heroeDao.getALLHeroe().getOrAwaitValue()
        assertThat(it).isNotNull() //assertNotEquals(it, null) Son equivalentes
        assertThat(it).isEmpty() //assertEquals(it.size, 0) Son equivalentes

        // Then B
        heroeDao.getALLHeroe().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

    @Test
    fun insertHeroe_happyCase_1element() = runBlocking {
        // Given
        val heroeList = listOf(HeroeEntity(1,"aquaman", "Atlantida","www.url.com","respira bajo el agua",1980))

        // When
        heroeDao.insertALLHeroe(heroeList)

        // Then
        heroeDao.getALLHeroe().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun insertHeroe_happyCase_3elements() = runBlocking {
        // Given
        val heroeList = listOf(
            HeroeEntity(1,"aquaman", "Atlantida","www.url.com","respira bajo el agua",1980),
            HeroeEntity(2,"aquaman", "Atlantida","www.url.com","respira bajo el agua",1980),
            HeroeEntity(3,"aquaman", "Atlantida","www.url.com","respira bajo el agua",1980))

        // When
        heroeDao.insertALLHeroe(heroeList)

        // Then
        heroeDao.getALLHeroe().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(3)
        }
    }
}

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}