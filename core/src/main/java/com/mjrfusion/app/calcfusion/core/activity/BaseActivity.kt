package com.mjrfusion.app.calcfusion.core.activity

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeActivity()
        initializeAttributes()
    }

    protected abstract fun initializeActivity()

    protected abstract fun initializeAttributes()

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