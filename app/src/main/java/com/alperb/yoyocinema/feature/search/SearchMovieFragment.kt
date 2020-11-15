package com.alperb.yoyocinema.feature.search

import android.content.Context
import com.alperb.yoyocinema.BR
import com.alperb.yoyocinema.R
import com.alperb.yoyocinema.core.BaseFragment
import com.alperb.yoyocinema.core.YoyoCinemaApp
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

    companion object {
        fun newInstance(): SearchMovieFragment {
            return SearchMovieFragment()
        }
    }
}