package com.alperb.yoyocinema.core.bindingadapters

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import com.alperb.yoyocinema.core.common.ToolbarModel

@BindingAdapter("model")
fun Toolbar.setModel(model: ToolbarModel) {
    val activity = context as AppCompatActivity
    activity.setSupportActionBar(this)
    activity.supportActionBar?.setDisplayHomeAsUpEnabled(model.navIcon != null)
    setNavigationOnClickListener { activity.onBackPressed() }

}
