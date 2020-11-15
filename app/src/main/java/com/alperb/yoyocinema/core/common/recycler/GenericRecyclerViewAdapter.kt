package com.alperb.yoyocinema.core.common.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GenericRecyclerViewAdapter(
    var recyclerModelWrapperList: List<RecyclerModelWrapper>
) : RecyclerView.Adapter<GenericViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return recyclerModelWrapperList[position].getLayoutId()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        return GenericViewHolder(viewType, parent)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.bind(recyclerModelWrapperList[position].presentation)
    }

    override fun getItemCount() = recyclerModelWrapperList.size

}
