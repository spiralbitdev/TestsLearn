package com.appsdev.testslearn.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsdev.testslearn.data.local.ShoppingItem
import com.appsdev.testslearn.data.remote.responses.ImageResponse
import com.appsdev.testslearn.other.Constants
import com.appsdev.testslearn.other.Event
import com.appsdev.testslearn.other.Resource
import com.appsdev.testslearn.repositories.IShoppingRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShoppingViewModel @ViewModelInject constructor(
    private val repository: IShoppingRepository
) : ViewModel() {

    val shoppingItems = repository.observeAllShoppingItems()
    val totalPrice = repository.observeTotalPrice()

    private val _images = MutableLiveData<Event<Resource<ImageResponse>>>()
    val images: LiveData<Event<Resource<ImageResponse>>> get() = _images

    private val _currentImageUrl = MutableLiveData<String>()
    val currentImageUrl: LiveData<String> get() = _currentImageUrl

    private val _insertShoppingItemStatus = MutableLiveData<Event<Resource<ShoppingItem>>>()
    val insertShoppingItemStatus: LiveData<Event<Resource<ShoppingItem>>> get() = _insertShoppingItemStatus

    fun setCurrentImageUrl(url: String) {
        _currentImageUrl.postValue(url)
    }

    fun insertInDB(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.insert(shoppingItem)
    }

    fun insert(name: String, amountString: String, priceString: String) {
        if (name.isEmpty() or amountString.isEmpty() or priceString.isEmpty()) {
            _insertShoppingItemStatus.postValue(Event(Resource.error(msg = "The fields must not be empty", data = null)))
            return
        }
        if (name.length > Constants.MAX_NAME_LENGTH) {
            _insertShoppingItemStatus.postValue(
                Event(
                    Resource.error(
                        msg = "The name of the item must not exceed ${Constants.MAX_NAME_LENGTH} characters",
                        data = null
                    )
                )
            )
            return
        }
        if (priceString.length > Constants.MAX_PRICE_LENGTH) {
            _insertShoppingItemStatus.postValue(
                Event(
                    Resource.error(
                        msg = "The price of the item must not exceed ${Constants.MAX_NAME_LENGTH} characters",
                        data = null
                    )
                )
            )
            return
        }
        val amount = try {
            amountString.toInt()
        } catch (e: Exception) {
            _insertShoppingItemStatus.postValue(Event(Resource.error(msg = "Please enter a valid amount", data = null)))
            return
        }
        val shoppingItem =
            ShoppingItem(name = name, amount = amount, price = priceString.toFloat(), imageUrl = _currentImageUrl.value ?: "")
        insertInDB(shoppingItem)
        setCurrentImageUrl("")
        _insertShoppingItemStatus.postValue(Event(Resource.success(data = shoppingItem)))
    }

    fun delete(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.delete(shoppingItem)
    }

    fun searchForImage(imageQuery: String) {
        if (imageQuery.isEmpty()) return
        _images.value = Event(Resource.loading(data = null))
        viewModelScope.launch {
            val response = repository.searchForImage(imageQuery)
            _images.value = Event(response)
        }
    }
}