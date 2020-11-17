package com.alperb.yoyocinema.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.alperb.yoyocinema.BuildConfig
import com.alperb.yoyocinema.R
import com.alperb.yoyocinema.network.model.Cast
import kotlinx.android.parcel.Parcelize

@Parcelize
class YoyoCast(
    val name: String,
    val character: String,
    val profileImageUrl: String?,
    @DrawableRes val placeholderResource: Int? = R.drawable.ic_launcher_background
) : Parcelable {
    companion object {
        fun newInstance(cast: Cast?): YoyoCast? {
            return if (cast?.name != null && cast.character != null) {
                YoyoCast(
                    cast.name,
                    cast.character,
                    "${BuildConfig.BASE_IMAGE_URL}${cast.profile_path}"
                )
            } else null
        }
    }
}
