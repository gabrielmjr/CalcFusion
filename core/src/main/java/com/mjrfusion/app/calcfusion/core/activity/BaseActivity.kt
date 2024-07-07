package com.mjrfusion.app.calcfusion.core.activity

import android.os.Bundle
import android.os.Handler
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeThisAttributes()
        initializeActivity()
        initializeAttributes()
        setListeners()
    }

    private fun initializeThisAttributes() {
        handler = Handler(mainLooper)
    }

    protected abstract fun initializeActivity()

    protected abstract fun initializeAttributes()

    protected open fun setListeners() {}

    fun replaceFragmentBy(
        @IdRes fragmentContainer: Int,
        fragment: Class<out Fragment>,
        addToBackStack: Boolean = false,
        tag: String? = null
    ) {
        supportFragmentManager.beginTransaction().apply {
            if (addToBackStack) addToBackStack(tag)
            replace(fragmentContainer, fragment, null)
            commit()
        }
    }
}