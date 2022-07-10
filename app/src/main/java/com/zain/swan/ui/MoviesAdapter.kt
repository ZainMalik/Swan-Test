package com.zain.swan.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.zain.swan.R
import com.zain.swan.databinding.ItemMovieBinding
import javax.inject.Inject

class MoviesAdapter @Inject constructor() :
    PagingDataAdapter<MovieItemUiState, MovieViewHolder>(Comparator) {

    private var onItemClickListener: onMovieItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: onMovieItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    interface onMovieItemClickListener {
        fun onClick(position: Int, item: MovieItemUiState?) //pass your object types.
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { movieItemUiState -> holder.bind(movieItemUiState) }
        holder.itemView.setOnClickListener{
            onItemClickListener?.onClick(position, getItem(position));
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val binding = inflate<ItemMovieBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )

        return MovieViewHolder(binding)
    }

    object Comparator : DiffUtil.ItemCallback<MovieItemUiState>() {
        override fun areItemsTheSame(oldItem: MovieItemUiState, newItem: MovieItemUiState): Boolean {
            return oldItem.getName() == newItem.getName()
        }

        override fun areContentsTheSame(
            oldItem: MovieItemUiState,
            newItem: MovieItemUiState
        ): Boolean {
            return oldItem == newItem
        }
    }

}