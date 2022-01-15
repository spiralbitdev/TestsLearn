package com.appsdev.testslearn.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.appsdev.testslearn.getOrAwaitValue
import com.appsdev.testslearn.launchFragmentInHiltContainer
import com.appsdev.testslearn.ui.ShoppingFragment
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@HiltAndroidTest
@SmallTest
class ShoppingDaoTest {

    @get:Rule(order = 0) var hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1) var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject @Named("test_db") lateinit var database: ShoppingItemDatabase
    private lateinit var dao: ShoppingDao

    @Before
    fun setUp() {
        hiltRule.inject()
        dao = database.shoppingDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testLaunchFragmentInHiltContainer() {
        launchFragmentInHiltContainer<ShoppingFragment>() {

        }
    }

    @Test
    fun insert() = runBlockingTest {
        val shoppingItem = ShoppingItem(name = "Banana", amount = 1, price = 1f, imageUrl = "url", id = 1)
        dao.insert(shoppingItem)

        val allShoppingItems = dao.observeAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).contains(shoppingItem)
    }

    @Test
    fun delete() = runBlockingTest {
        val shoppingItem = ShoppingItem(name = "Banana", amount = 1, price = 1f, imageUrl = "url", id = 1)
        dao.insert(shoppingItem)
        dao.delete(shoppingItem)

        val allShoppingItems = dao.observeAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).doesNotContain(shoppingItem)
    }

    @Test
    fun observeTotalPriceSum() = runBlockingTest {
        val shoppingItem1 = ShoppingItem(name = "Banana", amount = 1, price = 1f, imageUrl = "url", id = 1)
        val shoppingItem2 = ShoppingItem(name = "Apple", amount = 4, price = 2f, imageUrl = "url", id = 2)
        val shoppingItem3 = ShoppingItem(name = "Peach", amount = 3, price = 5.5f, imageUrl = "url", id = 3)
        dao.insert(shoppingItem1)
        dao.insert(shoppingItem2)
        dao.insert(shoppingItem3)

        val totalPriceSum = dao.observeTotalPrice().getOrAwaitValue()
        assertThat(totalPriceSum).isEqualTo(1 * 1f + 4 * 2f + 3 * 5.5f)
    }
}