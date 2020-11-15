package com.alperb.yoyocinema.core.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alperb.yoyocinema.core.common.extensions.autoNotify
import com.alperb.yoyocinema.core.common.recycler.GenericRecyclerViewAdapter
import com.alperb.yoyocinema.core.common.recycler.RecyclerModelWrapper

@BindingAdapter("itemList")
fun RecyclerView.setItemList(itemList: List<RecyclerModelWrapper>?) {
    itemList ?: return
    val currentAdapter = adapter as? GenericRecyclerViewAdapter?

    if (currentAdapter != null) {
        val existingList = currentAdapter.recyclerModelWrapperList
        currentAdapter.recyclerModelWrapperList = itemList
        currentAdapter.autoNotify(existingList, itemList)
    }

    adapter = GenericRecyclerViewAdapter(itemList)
    layoutManager = LinearLayoutManager(context)    //fixme
}
