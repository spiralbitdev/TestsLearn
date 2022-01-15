package com.appsdev.testslearn.repositories

import androidx.lifecycle.LiveData
import com.appsdev.testslearn.data.local.ShoppingItem
import com.appsdev.testslearn.data.remote.responses.ImageResponse
import com.appsdev.testslearn.other.Resource

interface IShoppingRepository {

    suspend fun insert(shoppingItem: ShoppingItem)

    suspend fun delete(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}