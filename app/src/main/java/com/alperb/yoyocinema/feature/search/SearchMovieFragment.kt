package com.alperb.yoyocinema.feature.search

import android.content.Context
import android.util.Log
import com.alperb.yoyocinema.BR
import com.alperb.yoyocinema.R
import com.alperb.yoyocinema.core.BaseFragment
import com.alperb.yoyocinema.core.YoyoCinemaApp
import com.alperb.yoyocinema.core.common.UIState
import com.alperb.yoyocinema.databinding.FragmentSearchBinding
import com.alperb.yoyocinema.di.HomeComponent
import javax.inject.Inject

class SearchMovieFragment : BaseFragment<SearchMovieViewModel, FragmentSearchBinding>() {

    lateinit var homeComponent: HomeComponent

    @Inject
    override lateinit var viewModel: SearchMovieViewModel

    override fun getResourceLayoutId() = R.layout.fragment_search

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeComponent = (requireActivity().application as YoyoCinemaApp).appComponent.homeComponent().create()
        homeComponent.inject(this)
    }

    override fun bindVariables() {
        binding.setVariable(BR.viewModel, viewModel)
    }

    override fun attachViewModelObservers() {
        viewModel.movieDetailState.observe(this) {
            if (it is UIState.Success) {
                Log.d("deneme", it.data?.originalTitle.orEmpty())
            }
        }
    }

    companion object {
        fun newInstance(): SearchMovieFragment {
            return SearchMovieFragment()
        }
    }
}