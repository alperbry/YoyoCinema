package com.alperb.yoyocinema.core.common.loading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.alperb.yoyocinema.R

class LoadingFragment : DialogFragment() {

    private var isShowing = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_loading, container, false)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (isShowing) return
        isShowing = true
        super.show(manager, tag)
    }

    override fun dismiss() {
        if (isShowing.not()) return
        isShowing = false
        super.dismiss()
    }

    companion object {
        const val TAG = "loadingTag"
    }
}
