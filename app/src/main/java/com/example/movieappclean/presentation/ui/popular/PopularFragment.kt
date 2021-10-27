package com.example.movieappclean.presentation.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.movieappclean.R
import com.example.movieappclean.core.BaseFragment
import com.example.movieappclean.databinding.FragmentPopularBinding
import com.example.movieappclean.presentation.ui.popular.adapter.PopularAdapter
import com.example.movieappclean.presentation.ui.popular.adapter.PopularItemListener
import com.example.movieappclean.presentation.ui.popular.state.PopularViewStateHandler
import com.example.movieappclean.presentation.ui.popular.state.PopularViewStates
import com.example.movieappclean.data.dataSource.remote.models.PopularResponseResult
import com.example.movieappclean.domain.entities.local.PopularLocal
import com.example.movieappclean.presentation.util.NavigationUtil.navigateTo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularFragment : BaseFragment<FragmentPopularBinding>(R.layout.fragment_popular),
    PopularViewStateHandler, PopularItemListener {


    private val viewModel by viewModels<PopularViewModel>()
    private val popularAdapter by lazy { PopularAdapter(this) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding {
            binding.handler=this@PopularFragment
            binding.viewModel=this@PopularFragment.viewModel
            binding.adapter=this@PopularFragment.popularAdapter
        }.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stateAdapterPopular()
        submitMovies()
    }

    private fun stateAdapterPopular() {
        viewLifecycleOwner.lifecycleScope.launch {
            popularAdapter.loadStateFlow.collectLatest { state ->
                viewModel.setStateList(state, popularAdapter.itemCount)
            }
        }
    }

    private fun submitMovies() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.data.collectLatest {
                popularAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
    }

    override fun refresh() {
        popularAdapter.refresh()
    }

    override fun retry() {
        popularAdapter.retry()
    }

    override fun isLoading(state: PopularViewStates?): Boolean {
        return state is PopularViewStates.Loading
    }

    override fun isLoaded(state: PopularViewStates?): Boolean {
        return state is PopularViewStates.Loaded

    }

    override fun isError(state: PopularViewStates?): Boolean {
        return state is PopularViewStates.Error

    }

    override fun isEmpty(state: PopularViewStates?): Boolean {
        return state is PopularViewStates.Empty
    }

    override fun hasState(state: PopularViewStates?): Boolean {
        return state!=null
    }

    override fun getErrorText(state: PopularViewStates?): String? {
        return (state as? PopularViewStates.Error)?.throwable?.let { getMessage(it) }
    }

    override fun onItemClick(item: PopularLocal) {
        this.navigateTo(id=R.id.movieDetailsFragment,args=Bundle().apply {
            putInt("movie_id",item.id?:0)
        })
    }


}