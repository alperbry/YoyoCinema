package com.alperb.yoyocinema.model

import android.os.Parcelable
import com.alperb.yoyocinema.network.model.Cast
import kotlinx.android.parcel.Parcelize

@Parcelize
class YoyoCast(
    val name: String,
    val character: String,
    val profileImageUrl: String?
) : Parcelable {
    companion object {
        fun newInstance(cast: Cast?): YoyoCast? {
            return if (cast?.name != null && cast.character != null) {
                YoyoCast(
                    cast.name,
                    cast.character,
                    cast.profile_path
                )
            } else null
        }
    }
}
