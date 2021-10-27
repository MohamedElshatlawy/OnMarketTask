package com.example.movieappclean.presentation.ui.nowplaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.movieappclean.R
import com.example.movieappclean.core.BaseFragment
import com.example.movieappclean.databinding.FragmentNowPlayingBinding
import com.example.movieappclean.domain.entities.local.NowPlayingLocal
import com.example.movieappclean.presentation.ui.nowplaying.adapter.NowPlayingAdapter
import com.example.movieappclean.presentation.ui.nowplaying.adapter.NowPlayingItemListener
import com.example.movieappclean.presentation.ui.nowplaying.state.NowPlayingViewStateHandler
import com.example.movieappclean.presentation.ui.nowplaying.state.NowPlayingViewStates
import com.example.movieappclean.presentation.util.NavigationUtil.navigateTo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NowPlayingFragment : BaseFragment<FragmentNowPlayingBinding>(R.layout.fragment_now_playing),
    NowPlayingViewStateHandler, NowPlayingItemListener {


    private val viewModel by viewModels<NowPlayingViewModel>()
    private val nowPlayingAdapter by lazy { NowPlayingAdapter(this) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding {
            binding.handler = this@NowPlayingFragment
            binding.viewModel = this@NowPlayingFragment.viewModel
            binding.adapter = this@NowPlayingFragment.nowPlayingAdapter
        }.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stateAdapter()
        submitMovies()
    }

    private fun stateAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            nowPlayingAdapter.loadStateFlow.collectLatest { state ->
                viewModel.setStateList(state, nowPlayingAdapter.itemCount)
            }
        }
    }

    private fun submitMovies() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.data.collectLatest {
                nowPlayingAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
    }

    override fun refresh() {
        nowPlayingAdapter.refresh()
    }

    override fun retry() {
        nowPlayingAdapter.retry()
    }

    override fun isLoading(state: NowPlayingViewStates?): Boolean {
        return state is NowPlayingViewStates.Loading
    }

    override fun isLoaded(state: NowPlayingViewStates?): Boolean {
        return state is NowPlayingViewStates.Loaded

    }

    override fun isError(state: NowPlayingViewStates?): Boolean {
        return state is NowPlayingViewStates.Error

    }

    override fun isEmpty(state: NowPlayingViewStates?): Boolean {
        return state is NowPlayingViewStates.Empty
    }

    override fun hasState(state: NowPlayingViewStates?): Boolean {
        return state != null
    }

    override fun getErrorText(state: NowPlayingViewStates?): String? {
        return (state as? NowPlayingViewStates.Error)?.throwable?.let { getMessage(it) }
    }

    override fun onItemClick(item: NowPlayingLocal) {
        this.navigateTo(id = R.id.movieDetailsFragment, args = Bundle().apply {
            putInt("movie_id", item.id ?: 0)
        })
    }


}
