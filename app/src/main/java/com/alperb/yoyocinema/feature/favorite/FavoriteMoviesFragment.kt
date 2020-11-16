package com.alperb.yoyocinema.feature.favorite

import android.content.Context
import androidx.fragment.app.Fragment
import com.alperb.yoyocinema.R
import com.alperb.yoyocinema.core.BaseFragment
import com.alperb.yoyocinema.core.YoyoCinemaApp
import com.alperb.yoyocinema.databinding.FragmentFavoriteBinding
import com.alperb.yoyocinema.di.ViewModelFactory
import javax.inject.Inject

class FavoriteMoviesFragment : BaseFragment<FavoriteMoviesViewModel, FragmentFavoriteBinding>() {

    @Inject
    override lateinit var viewModelFactory: ViewModelFactory

    override fun getResourceLayoutId() = R.layout.fragment_favorite

    override fun getViewModelClazz() = FavoriteMoviesViewModel::class.java

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val homeComponent = (requireActivity().application as YoyoCinemaApp).appComponent.homeComponent().create()
        homeComponent.inject(this)
    }

    companion object {
        fun newInstance(): Fragment {
            return FavoriteMoviesFragment()
        }
    }
}