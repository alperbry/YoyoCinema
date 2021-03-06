package com.alperb.yoyocinema.feature.search

import android.content.Context
import com.alperb.yoyocinema.BR
import com.alperb.yoyocinema.R
import com.alperb.yoyocinema.core.BaseFragment
import com.alperb.yoyocinema.core.YoyoCinemaApp
import com.alperb.yoyocinema.core.common.UIState
import com.alperb.yoyocinema.databinding.FragmentSearchBinding
import com.alperb.yoyocinema.di.HomeComponent
import com.alperb.yoyocinema.di.ViewModelFactory
import com.alperb.yoyocinema.feature.detail.MovieDetailFragment
import javax.inject.Inject

class SearchMovieFragment : BaseFragment<SearchMovieViewModel, FragmentSearchBinding>() {

    lateinit var homeComponent: HomeComponent

    @Inject
    override lateinit var viewModelFactory: ViewModelFactory

    override fun getResourceLayoutId() = R.layout.fragment_search

    override fun getViewModelClazz() = SearchMovieViewModel::class.java

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeComponent = (requireActivity().application as YoyoCinemaApp).appComponent.homeComponent().create()
        homeComponent.inject(this)
    }

    override fun bindVariables() {
        binding.setVariable(BR.viewModel, viewModel)
    }

    override fun attachViewModelObservers() {
        //fixme add generic navigation solution
        viewModel.navigate.observe(viewLifecycleOwner) { id ->
            val manager = requireActivity().supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(
                R.id.activityHomeContainer,
                MovieDetailFragment.newInstance(id),
                null
            )
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    companion object {
        fun newInstance(): SearchMovieFragment {
            return SearchMovieFragment()
        }
    }
}
