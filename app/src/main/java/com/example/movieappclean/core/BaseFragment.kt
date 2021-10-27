package com.example.movieappclean.core


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movieappclean.R
import com.example.movieappclean.domain.entities.common.Status
import com.example.movieappclean.domain.entities.exceptions.UnauthorizedExceptions
import com.example.movieappclean.presentation.util.NavigationUtil.clearNavigateStack
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * @author Eramint ( Mohamed Helmy)
 *
 * Base class for fragments that wish to bind content layout with [DataBindingUtil].
 * Provides a [binding] property that extends [ViewDataBinding] from abstract information.
 * The [binding] property ensures to be initialized in [onCreateView].
 *
 * @param T A generic class that extends [ViewDataBinding] and generated by DataBinding on compile time.
 * @property contentLayoutId A content layout Id for inflating as a content view.
 */

abstract class BaseFragment<T : ViewDataBinding> constructor(
    @LayoutRes private val contentLayoutId: Int
) : Fragment() {

    protected val tagString: String = javaClass.simpleName

    /** This interface is generated during compilation to contain getters for all used instance `BindingAdapters`. */
    protected var bindingComponent: DataBindingComponent? = DataBindingUtil.getDefaultComponent()

    /** A backing field for providing an immutable [binding] property.  */
    private lateinit var _binding: T

    /**
     * A data-binding property will be initialized in [onCreateView].
     * And provide the inflated view which depends on [contentLayoutId].
     */
//    @BindingOnly
    protected val binding: T
        get() = _binding

    /**
     * An executable inline binding function that receives a binding receiver in lambda.
     *
     * @param block A lambda block will be executed with the binding receiver.
     * @return T A generic class that extends [ViewDataBinding] and generated by DataBinding on compile time.
     */
//    @BindingOnly
    protected inline fun binding(block: T.() -> Unit): T {
        return binding.apply(block)
    }

    /**
     * Ensures the [binding] property should be executed and provide the inflated view which depends on [contentLayoutId].
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, contentLayoutId, container, false, bindingComponent)
        _binding.lifecycleOwner = viewLifecycleOwner
        _binding.executePendingBindings()
        return _binding.root
    }
    protected inline fun <T> Flow<T>.collectWhenStarted(
        crossinline collector: suspend (T) -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            this@collectWhenStarted.collect {
                collector(it)
            }
        }
    }
    protected fun getMessage(error: Status.Error): String? {
        return when (error) {
            is Status.Error.Message -> error.message ?: getString(R.string.error_unknown)
            is Status.Error.Exceptions -> getMessage(error.ex)
        }
    }
    protected fun getMessage(throwable: Throwable?): String? {
        return when (throwable) {
            is IOException -> getString(R.string.error_io)
            is SocketTimeoutException -> getString(R.string.error_time_out)
            is UnauthorizedExceptions -> getString(R.string.Unauthenticated)
            else -> getString(R.string.error_unknown)
        }
    }

protected fun onBack(){
    findNavController().clearNavigateStack()
}

}
