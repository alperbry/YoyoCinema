package com.alperb.yoyocinema.feature.favorite

import android.content.Context
import androidx.fragment.app.Fragment
import com.alperb.yoyocinema.BR
import com.alperb.yoyocinema.R
import com.alperb.yoyocinema.core.BaseFragment
import com.alperb.yoyocinema.core.YoyoCinemaApp
import com.alperb.yoyocinema.databinding.FragmentFavoriteBinding
import com.alperb.yoyocinema.di.ViewModelFactory
import com.alperb.yoyocinema.feature.detail.MovieDetailFragment
import javax.inject.Inject

class FavoriteMoviesFragment : BaseFragment<FavoriteMoviesViewModel, FragmentFavoriteBinding>() {

    @Inject
    override lateinit var viewModelFactory: ViewModelFactory

    override fun getResourceLayoutId() = R.layout.fragment_favorite

    override fun getViewModelClazz() = FavoriteMoviesViewModel::class.java

    override fun bindVariables() {
        super.bindVariables()
        binding.setVariable(BR.viewModel, viewModel)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val homeComponent = (requireActivity().application as YoyoCinemaApp).appComponent.homeComponent().create()
        homeComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()
        viewModel.onAppearOnScreen()
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
        fun newInstance(): Fragment {
            return FavoriteMoviesFragment()
        }
    }
}