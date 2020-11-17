package com.alperb.yoyocinema.core.common.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.alperb.yoyocinema.BR

class GenericViewHolder(
    @LayoutRes layoutRes: Int,
    parentView: ViewGroup,
    private val variableResId: Int = BR.viewModel,
    private val binding: ViewDataBinding = DataBindingUtil.inflate(
        LayoutInflater.from(parentView.context),
        layoutRes,
        parentView,
        false
    )
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(presentation: RecyclerComponentPresentation) {
        binding.setVariable(variableResId, presentation)
    }

}
