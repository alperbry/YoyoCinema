package com.alperb.yoyocinema.model

import com.alperb.yoyocinema.network.model.Cast

class YoyoCast(
    val name: String,
    val character: String,
    val profileImageUrl: String?
) {
    companion object {
        fun newInstance(cast: Cast): YoyoCast? {
            return if (cast.name != null && cast.character != null) {
                YoyoCast(
                    cast.name,
                    cast.character,
                    cast.profile_path
                )
            } else null
        }
    }
}