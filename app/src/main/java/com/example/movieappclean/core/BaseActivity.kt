package com.example.movieappclean.core

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding> constructor(
    @LayoutRes private val contentLayoutId: Int
) : AppCompatActivity() {
    protected val tag: String = javaClass.simpleName

    private lateinit var _binding: T

    protected val binding: T
        get() = _binding


    protected inline fun binding(block: T.() -> Unit): T {
        return binding.apply(block)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        super.onCreate(savedInstanceState)
        _binding =DataBindingUtil.setContentView<T>(this@BaseActivity, contentLayoutId)
        _binding.executePendingBindings()
        _binding.lifecycleOwner = this


    }

}

