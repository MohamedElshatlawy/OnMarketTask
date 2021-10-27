package com.example.movieappclean.presentation.ui.toprated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.movieappclean.R
import com.example.movieappclean.core.BaseFragment
import com.example.movieappclean.data.dataSource.remote.models.TopRatedResponseResult
import com.example.movieappclean.databinding.FragmentTopRatedBinding
import com.example.movieappclean.domain.entities.local.TopRatedLocal
import com.example.movieappclean.presentation.ui.toprated.adapter.TopRatedAdapter
import com.example.movieappclean.presentation.ui.toprated.adapter.TopRatedItemListener
import com.example.movieappclean.presentation.ui.toprated.state.TopRatedViewStateHandler
import com.example.movieappclean.presentation.ui.toprated.state.TopRatedViewStates
import com.example.movieappclean.presentation.util.NavigationUtil.navigateTo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class TopRatedFragment : BaseFragment<FragmentTopRatedBinding>(R.layout.fragment_top_rated),
    TopRatedViewStateHandler, TopRatedItemListener {


    private val viewModel by viewModels<TopRatedViewModel>()
    private val topRatedAdapter by lazy { TopRatedAdapter(this) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding {
            binding.handler = this@TopRatedFragment
            binding.viewModel = this@TopRatedFragment.viewModel
            binding.adapter = this@TopRatedFragment.topRatedAdapter
        }.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stateAdapter()
        submitMovies()
    }

    private fun stateAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            topRatedAdapter.loadStateFlow.collectLatest { state ->
                viewModel.setStateList(state, topRatedAdapter.itemCount)
            }
        }
    }

    private fun submitMovies() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.data.collectLatest {
                topRatedAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
    }

    override fun refresh() {
        topRatedAdapter.refresh()
    }

    override fun retry() {
        topRatedAdapter.retry()
    }

    override fun isLoading(state: TopRatedViewStates?): Boolean {
        return state is TopRatedViewStates.Loading
    }

    override fun isLoaded(state: TopRatedViewStates?): Boolean {
        return state is TopRatedViewStates.Loaded

    }

    override fun isError(state: TopRatedViewStates?): Boolean {
        return state is TopRatedViewStates.Error

    }

    override fun isEmpty(state: TopRatedViewStates?): Boolean {
        return state is TopRatedViewStates.Empty
    }

    override fun hasState(state: TopRatedViewStates?): Boolean {
        return state != null
    }

    override fun getErrorText(state: TopRatedViewStates?): String? {
        return (state as? TopRatedViewStates.Error)?.throwable?.let { getMessage(it) }
    }

    override fun onItemClick(item: TopRatedLocal) {
        this.navigateTo(id=R.id.movieDetailsFragment,args=Bundle().apply {
            putInt("movie_id",item.id?:0)
        })
    }


}
