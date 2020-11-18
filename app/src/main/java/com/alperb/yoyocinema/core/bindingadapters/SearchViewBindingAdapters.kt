package com.alperb.yoyocinema.core.bindingadapters

import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.alperb.yoyocinema.core.common.DebouncedSingleJobHandler

private const val SEARCH_DEBOUNCE_MS = 400L

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
    return query?.let {
        if (it.isEmpty().not()) it.toString() else null
    }
}

@BindingAdapter(value = ["queryTextChanged"], requireAll = false)
fun SearchView.onQueryTextChanged(attrChanged: InverseBindingListener) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            DebouncedSingleJobHandler.post({
                attrChanged.onChange()
            }, SEARCH_DEBOUNCE_MS)
            return true
        }
    })
}
