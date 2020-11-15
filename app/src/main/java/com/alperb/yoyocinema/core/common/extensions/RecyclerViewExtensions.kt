package com.alperb.yoyocinema.core.common.extensions

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

fun <T> RecyclerView.Adapter<*>.autoNotify(
    currentList: List<T>,
    newList: List<T>
) {
    val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
        override fun getOldListSize() = currentList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return currentList[oldItemPosition] === newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return currentList[oldItemPosition] == newList[newItemPosition]
        }
    })

    diff.dispatchUpdatesTo(this)
}
