package com.alperb.yoyocinema.feature.home

import android.content.Context
import android.os.Bundle
import android.view.View
import com.alperb.yoyocinema.BR
import com.alperb.yoyocinema.R
import com.alperb.yoyocinema.core.BaseFragment
import com.alperb.yoyocinema.core.YoyoCinemaApp
import com.alperb.yoyocinema.databinding.FragmentHomeBinding
import com.alperb.yoyocinema.di.HomeComponent
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    lateinit var homeComponent: HomeComponent

    @Inject
    override lateinit var viewModel: HomeViewModel

    override fun getResourceLayoutId() = R.layout.fragment_home

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeComponent = (requireActivity().application as YoyoCinemaApp).appComponent.homeComponent().create()
        homeComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViewPager()
    }

    override fun bindVariables() {
        super.bindVariables()
        binding.setVariable(BR.viewModel, viewModel)
    }

    private fun initializeViewPager() {
        binding.activityHomeViewPager.adapter = HomePagerAdapter(this)
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

}
