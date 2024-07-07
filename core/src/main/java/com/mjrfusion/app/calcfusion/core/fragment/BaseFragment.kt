package com.mjrfusion.app.calcfusion.core.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.mjrfusion.app.calcfusion.core.activity.BaseActivity

abstract class BaseFragment(@LayoutRes root: Int): Fragment(root) {
    val mLocalTag = this::class.java.canonicalName
    lateinit var baseActivity: BaseActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeThisAttributes()
        initializeAttributes(view)
        setListeners()
    }

    private fun initializeThisAttributes() {
        baseActivity = requireActivity() as BaseActivity
    }

    protected abstract fun initializeAttributes(view: View)

    protected open fun setListeners() {}

    fun replaceFragmentBy(
        @IdRes fragmentContainer: Int,
        fragment: Class<out Fragment>,
        addToBackStack: Boolean = false,
        tag: String? = null
    ) {
        baseActivity.replaceFragmentBy(
            fragmentContainer,
            fragment,
            addToBackStack,
            tag
        )
    }
}