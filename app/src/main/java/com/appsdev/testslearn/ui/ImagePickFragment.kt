package com.appsdev.testslearn.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.appsdev.testslearn.R
import com.appsdev.testslearn.adapters.PixabayAdapter
import com.appsdev.testslearn.databinding.FragmentImagePickBinding
import com.appsdev.testslearn.other.Constants.GRID_SPAN_COUNT
import com.appsdev.testslearn.repositories.DefaultShoppingRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class ImagePickFragment @Inject constructor(
    val imageAdapter: PixabayAdapter
) : Fragment(R.layout.fragment_image_pick) {

    lateinit var viewModel: ShoppingViewModel
    private val binding: FragmentImagePickBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
        setUpClicks()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() = with(binding) {
        rvImages.apply {
            layoutManager = GridLayoutManager(requireContext(), GRID_SPAN_COUNT)
            adapter = imageAdapter
        }
    }

    private fun setUpClicks() = with(binding) {
        imageAdapter.setOnItemClickListener {
            findNavController().popBackStack()
            viewModel.setCurrentImageUrl(it)
        }
    }
}