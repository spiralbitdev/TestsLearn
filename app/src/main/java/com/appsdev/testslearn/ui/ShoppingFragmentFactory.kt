package com.appsdev.testslearn.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.appsdev.testslearn.adapters.PixabayAdapter
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class ShoppingFragmentFactory @Inject constructor(
    private val pixabayAdapter: PixabayAdapter,
    private val glide: RequestManager
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ImagePickFragment::class.java.name -> ImagePickFragment(pixabayAdapter)
            AddShoppingItemFragment::class.java.name -> AddShoppingItemFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }
}