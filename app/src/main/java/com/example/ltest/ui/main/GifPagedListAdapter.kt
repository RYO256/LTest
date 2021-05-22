package com.example.ltest.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ltest.databinding.ItemGifBinding
import com.example.ltest.domain.models.Gif

class GifPagedListAdapter(
        private val context: Context,
        private val onClick: (item: Gif) -> Unit,
) : PagingDataAdapter<Gif, GifPagedListAdapter.ArticleViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
            ArticleViewHolder.from(parent)

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item: Gif? = getItem(position)
        if (item != null) {
            holder.bind(context, item, onClick)
        }
    }

    class ArticleViewHolder(private val binding: ItemGifBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ArticleViewHolder {
                val binding = ItemGifBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ArticleViewHolder(binding)
            }
        }

        fun bind(context: Context, item: Gif, onClick: (item: Gif) -> Unit) {

            Glide.with(context)
                    .load(item.preview)
                    .centerCrop()
                    .into(binding.gifPreview)

            itemView.setOnClickListener {
                onClick(item)
            }
        }

    }

    private class DiffUtilCallBack : DiffUtil.ItemCallback<Gif>() {
        override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean =
                oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: Gif, newItem: Gif): Boolean =
                oldItem.url == newItem.url
    }
}