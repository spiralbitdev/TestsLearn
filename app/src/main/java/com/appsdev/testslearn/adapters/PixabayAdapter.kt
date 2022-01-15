package com.appsdev.testslearn.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.appsdev.testslearn.databinding.ItemImageBinding
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class PixabayAdapter @Inject constructor(private val glide: RequestManager) : RecyclerView.Adapter<PixabayAdapter.PixabayViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var images: List<String>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixabayViewHolder {
        return PixabayViewHolder(
            ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PixabayViewHolder, position: Int) {
        val currentUrl = images[position]
        holder.bind(currentUrl)
    }

    override fun getItemCount() = images.size

    inner class PixabayViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(url: String) = with(binding) {
            glide.load(url).into(ivShoppingImage)
            itemView.setOnClickListener {
                onItemClickListener?.let { click ->
                    click(url)
                }
            }
        }
    }
}