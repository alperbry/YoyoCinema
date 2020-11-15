package com.alperb.yoyocinema.core.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.viewpager2.widget.ViewPager2
import com.alperb.yoyocinema.R
import com.alperb.yoyocinema.feature.home.HomePageType
import com.google.android.material.bottomnavigation.BottomNavigationView

@BindingAdapter("currentPage")
fun ViewPager2.setCurrentPage(homePageType: HomePageType?) {
    homePageType?.let {
        setCurrentItem(it.index, true)
    }
}

@BindingAdapter("currentPage")
fun BottomNavigationView.setItem(homePageType: HomePageType?) {
    homePageType?.let {
        selectedItemId = it.id
        setTag(R.id.bottom_navigation_position_id, it.id)
    }
}

@InverseBindingAdapter(attribute = "currentPage", event = "currentPageAttrChanged")
fun BottomNavigationView.getCurrentPage(): HomePageType? {
    return (getTag(R.id.bottom_navigation_position_id) as? Int)?.let {
        HomePageType.getById(it)
    }
}

@BindingAdapter(value = ["currentPageAttrChanged"], requireAll = false)
fun BottomNavigationView.onCurrentPageChanged(attrChanged: InverseBindingListener) {
    setOnNavigationItemSelectedListener {
        val oldId = getTag(R.id.bottom_navigation_position_id) as? Int
        if (oldId != it.itemId) {
            setTag(R.id.bottom_navigation_position_id, it.itemId)
            attrChanged.onChange()
        }
        true
    }
}
