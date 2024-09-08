package com.mjrfusion.app.calcfusion.core.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.mjrfusion.app.calcfusion.core.activity.BaseActivity

abstract class BaseFragment : Fragment() {
    val mLocalTag = this::class.java.canonicalName
    private lateinit var baseActivity: BaseActivity
    lateinit var handler: Handler

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initializeThisAttributes()
        val view = onCreateView(layoutInflater)
        initializeAttributes(view!!)
        initializeAttributes(view, savedInstanceState)
        setListeners()
        return view
    }

    protected open fun onCreateView(layoutInflater: LayoutInflater): View? {
        return null
    }

    private fun initializeThisAttributes() {
        baseActivity = requireActivity() as BaseActivity
        handler = baseActivity.handler
    }

    protected open fun initializeAttributes(view: View) {}

    protected open fun initializeAttributes(view: View, savedInstanceState: Bundle?) {}

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