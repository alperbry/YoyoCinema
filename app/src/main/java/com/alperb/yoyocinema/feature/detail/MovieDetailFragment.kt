package com.alperb.yoyocinema.feature.detail

import android.content.Context
import android.os.Bundle
import com.alperb.yoyocinema.BR
import com.alperb.yoyocinema.R
import com.alperb.yoyocinema.core.BaseFragment
import com.alperb.yoyocinema.core.YoyoCinemaApp
import com.alperb.yoyocinema.databinding.FragmentMovieDetailBinding
import com.alperb.yoyocinema.di.ViewModelFactory
import com.alperb.yoyocinema.model.YoyoMovieDetail
import javax.inject.Inject

private const val KEY_MOVIE_DETAILS = "keyMovieDetails"

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

    companion object {
        fun newInstance(movieDetails: YoyoMovieDetail): MovieDetailFragment {
            return MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_MOVIE_DETAILS, movieDetails)
                }
            }
        }
    }

}
