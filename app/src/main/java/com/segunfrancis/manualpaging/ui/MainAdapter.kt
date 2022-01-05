package com.segunfrancis.manualpaging.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.segunfrancis.manualpaging.R
import com.segunfrancis.manualpaging.databinding.ItemMovieBinding
import com.segunfrancis.manualpaging.ui.model.UiResponse

class MainAdapter : ListAdapter<UiResponse, MainAdapter.MainViewHolder>(MAIN_DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            ItemMovieBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MainViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {  }
        }

        fun bind(response: UiResponse) {
            binding.movieTitle.text = response.title
            binding.movieOverview.text = response.overview
        }
    }

    companion object {
        val MAIN_DIFF_UTIL = object : DiffUtil.ItemCallback<UiResponse>() {
            override fun areItemsTheSame(oldItem: UiResponse, newItem: UiResponse): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UiResponse, newItem: UiResponse): Boolean {
                return oldItem == newItem
            }
        }
    }
}
