package com.alperb.yoyocinema.core.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(urlString: String?) {
    urlString?.let {
        Picasso.get().load(urlString).into(this)
    }
}
