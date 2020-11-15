package com.alperb.yoyocinema.core.bindingadapters

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["imageUrl", "placeholderResource"], requireAll = false)
fun ImageView.setImageUrl(urlString: String?, @DrawableRes placeholderResource: Int? = null) {
    urlString?.let { url ->
        var requestCreator = Picasso.get().load(url)
        if (placeholderResource != null) {
            requestCreator = requestCreator.placeholder(placeholderResource)
        }
        requestCreator.into(this)
    }
}
