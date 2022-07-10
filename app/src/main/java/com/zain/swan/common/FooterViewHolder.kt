package com.zain.swan.common

import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.zain.swan.databinding.ItemPagingFooterBinding
import com.zain.swan.util.ext.executeWithAction

class FooterViewHolder(
    private val binding: ItemPagingFooterBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnRetry.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        binding.executeWithAction {
            footerUiState = FooterUiState(loadState)
        }
    }
}

