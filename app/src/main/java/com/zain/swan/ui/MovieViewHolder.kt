package com.zain.swan.ui

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.zain.swan.data.model.Result
import com.zain.swan.databinding.ItemMovieBinding
import com.zain.swan.util.ext.executeWithAction

class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movieItemUiState: MovieItemUiState) {
        binding.executeWithAction {
            this.movieItemUiState = movieItemUiState
        }

    }
}