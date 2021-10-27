package com.example.movieappclean.presentation.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.movieappclean.R
import com.example.movieappclean.core.BaseFragment
import com.example.movieappclean.databinding.FragmentMovieDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment :
    BaseFragment<FragmentMovieDetailsBinding>(R.layout.fragment_movie_details) {


    private val viewModel by viewModels<MovieDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding {
         viewModel=this@MovieDetailsFragment.viewModel
            binding.toolbar.setNavigationOnClickListener {
                   onBack()
               }

        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectStatus()
        observeError()

    }

    private fun collectStatus() {
        viewModel.status.collectWhenStarted { s ->
            viewModel.updateStatus(s)
        }
    }

    private fun observeError() {
        viewModel.error.observe(viewLifecycleOwner, {
            it?.let { e ->
                binding.packageErrorTxt.text=getMessage(e).toString()
            }
        })
    }


}