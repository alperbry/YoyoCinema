package com.alperb.yoyocinema.core.bindingadapters

import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.alperb.yoyocinema.core.common.DebouncedSingleJobHandler

@BindingAdapter("queryText")
fun SearchView.setQueryText(queryText: String?) {
    queryText?.let {
        if (it != query) {
            setQuery(it, false) //todo check if needed
        }
    }
}

@InverseBindingAdapter(attribute = "queryText", event = "queryTextChanged")
fun SearchView.getQueriedText(): String? {
    return query?.toString()
}

@BindingAdapter(value = ["queryTextChanged"], requireAll = false)
fun SearchView.onQueryTextChanged(attrChanged: InverseBindingListener) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            DebouncedSingleJobHandler.post({
                attrChanged.onChange()
            }, 200)
            return true
        }
    })
}
