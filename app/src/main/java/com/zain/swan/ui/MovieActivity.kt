package com.zain.swan.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.zain.swan.R
import com.zain.swan.common.FooterAdapter
import com.zain.swan.databinding.ActivityMovieBinding
import com.zain.swan.util.ext.collect
import com.zain.swan.util.ext.collectLast
import com.zain.swan.util.ext.executeWithAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import android.widget.Toast

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding
    private val viewModel: MovieViewModel by viewModels()

    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setListener()
        setAdapter()
        collectLast(viewModel.movieItemsUiStates, ::setMovies)
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
    }

    private fun setListener() {
        binding.btnRetry.setOnClickListener { moviesAdapter.retry() }
    }


    private fun setAdapter() {
        collect(flow = moviesAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setMoviesUiState
        )
        binding.rvMovies.adapter = moviesAdapter.withLoadStateFooter(FooterAdapter(moviesAdapter::retry))

        moviesAdapter.setOnItemClickListener(object : MoviesAdapter.onMovieItemClickListener {
            override fun onClick(position: Int, item: MovieItemUiState?) {
//                Toast.makeText(applicationContext, "" + position + item, Toast.LENGTH_LONG).show()
                val intent = Intent(this@MovieActivity, DetailsActivity::class.java)
                intent.putExtra("image", item?.getImageUrl())
                intent.putExtra("name", item?.getName())
                intent.putExtra("overview", item?.getOverview())
                intent.putExtra("date", item?.getDate())
                startActivity(intent)
            }
        })

    }

    private fun setMoviesUiState(loadState: LoadState) {
        binding.executeWithAction {
            moviesUiState = MovieUiState(loadState)
        }
    }

    private suspend fun setMovies(movieItemsPagingData: PagingData<MovieItemUiState>) {
        moviesAdapter.submitData(movieItemsPagingData)
    }

}