package com.example.movieappclean.presentation.util


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController


object NavigationUtil {
    private const val tag = "NavigationUtil"
    fun Fragment.navigateTo(
        id: Int,
        args: Bundle? = null,
        navOptions: NavOptions? = null,
        extras: Navigator.Extras? = null
    ) {
        safeNavigationTask { findNavController().navigate(id, args, navOptions, extras) }
    }


    fun NavController.clearNavigateStack(destinationId: Int? = null) {
        if (destinationId != null)
            safeNavigationTask { popBackStack(destinationId, false) }
        else
            safeNavigationTask { popBackStack() }
    }

    private fun safeNavigationTask(task: () -> Unit) = try {
        task.invoke()
    } catch (t: Throwable) {
        Log.e(tag,"$t")
    }


}
