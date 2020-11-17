package com.alperb.yoyocinema.feature.detail

import android.content.Context
import android.os.Bundle
import android.view.View
import com.alperb.yoyocinema.BR
import com.alperb.yoyocinema.R
import com.alperb.yoyocinema.core.BaseFragment
import com.alperb.yoyocinema.core.YoyoCinemaApp
import com.alperb.yoyocinema.core.common.DebouncedSingleJobHandler
import com.alperb.yoyocinema.databinding.FragmentMovieDetailBinding
import com.alperb.yoyocinema.di.ViewModelFactory
import javax.inject.Inject

private const val KEY_MOVIE_ID = "keyMovieId"

private const val FAV_BUTTON_APPEAR_DURATION = 3000L

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

    override fun attachViewModelObservers() {
        super.attachViewModelObservers()
        viewModel.onBottomEvent.observe(viewLifecycleOwner) {
            animateFavoriteButton(collapse = false)
            DebouncedSingleJobHandler.post({
                animateFavoriteButton(collapse = true)
            }, FAV_BUTTON_APPEAR_DURATION)
        }
    }

    private fun animateFavoriteButton(collapse: Boolean) {
        val y = if (collapse) 0f else -binding.fragmentMovieDetailBottomFavoriteButton.height.toFloat()
        binding.fragmentMovieDetailBottomFavoriteButton.animate().translationY(y)
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
