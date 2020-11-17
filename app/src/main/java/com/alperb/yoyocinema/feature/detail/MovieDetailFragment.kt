package com.alperb.yoyocinema.feature.detail

import android.content.Context
import android.os.Bundle
import android.view.View
import com.alperb.yoyocinema.BR
import com.alperb.yoyocinema.R
import com.alperb.yoyocinema.core.BaseFragment
import com.alperb.yoyocinema.core.YoyoCinemaApp
import com.alperb.yoyocinema.databinding.FragmentMovieDetailBinding
import com.alperb.yoyocinema.di.ViewModelFactory
import javax.inject.Inject

private const val KEY_MOVIE_ID = "keyMovieId"

class MovieDetailFragment : BaseFragment<MovieDetailViewModel, FragmentMovieDetailBinding>() {

    @Inject
    override lateinit var viewModelFactory: ViewModelFactory

    override fun getResourceLayoutId() = R.layout.fragment_movie_detail

    override fun getViewModelClazz() = MovieDetailViewModel::class.java

    override fun bindVariables() {
        super.bindVariables()
        binding.setVariable(BR.viewModel, viewModel)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val homeComponent = (requireActivity().application as YoyoCinemaApp).appComponent.homeComponent().create()
        homeComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initialize(arguments?.getInt(KEY_MOVIE_ID))
    }

    override fun onStop() {
        super.onStop()
        viewModel.onScreenClosed()
    }

    companion object {
        fun newInstance(movieId: Int): MovieDetailFragment {
            return MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_MOVIE_ID, movieId)
                }
            }
        }
    }

}
