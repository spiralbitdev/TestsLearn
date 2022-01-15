package com.appsdev.testslearn.repositories

import androidx.lifecycle.LiveData
import com.appsdev.testslearn.data.local.ShoppingDao
import com.appsdev.testslearn.data.local.ShoppingItem
import com.appsdev.testslearn.data.remote.PixabayAPI
import com.appsdev.testslearn.data.remote.responses.ImageResponse
import com.appsdev.testslearn.other.Resource
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayAPI: PixabayAPI
) : IShoppingRepository {

    override suspend fun insert(shoppingItem: ShoppingItem) {
        shoppingDao.insert(shoppingItem)
    }

    override suspend fun delete(shoppingItem: ShoppingItem) {
       shoppingDao.delete(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImage(imageQuery)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(data = it)
                } ?: Resource.error(msg = "An unknown error occured", data = null)
            } else {
                Resource.error(msg = "An unknown error occured", data = null)
            }
        } catch (e: Exception) {
            return Resource.error(msg = "Couldn't reach the server, check your internet connection", data = null)
        }
    }
}