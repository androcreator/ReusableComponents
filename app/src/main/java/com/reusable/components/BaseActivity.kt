package com.reusable.components

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.LayoutInflaterCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.reusable.components.databinding.ActivityBaseBinding

abstract class BaseActivity<VB : ViewDataBinding, VM: BaseViewModel>: AppCompatActivity() {

    protected var viewBinding: VB? = null

    protected var activityBaseBinding: ActivityBaseBinding? = null

    private lateinit var baseViewModel: BaseViewModel

    @LayoutRes
    abstract fun getLayoutId():Int

    abstract fun getViewModel(): VM

    abstract fun getBindingVariables(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseViewModel = getViewModel()

        activityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base)
        activityBaseBinding?.setVariable(BR.viewModel, baseViewModel)
        activityBaseBinding?.executePendingBindings()
        activityBaseBinding?.lifecycleOwner = this


        viewBinding = DataBindingUtil.inflate(layoutInflater,getLayoutId(),activityBaseBinding?.activityContent, false)
        viewBinding?.setVariable(getBindingVariables(), baseViewModel)
        activityBaseBinding?.activityContent?.addView(viewBinding?.root)
    }
}