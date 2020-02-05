package com.eslam.callkt.util

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation


/**
 * Performs a navigation to given destination using [androidx.navigation.NavController]
 * found via {@param view}. Catches [IllegalArgumentException] that may occur due to
 * The navigation must work as intended.
 *
 * @param view        the view to search from
 * @param destination destination id
 */
fun navigate(view: View, destination: NavDirections) {
    try {
        Navigation.findNavController(view).navigate(destination)
    } catch (e: IllegalArgumentException) {
        Log.e("navigate : ", "Multiple navigation attempts handled.")
    }

}

/**
 * @param fragment        the Fragment to search from
 * @param destination destination id
 */
fun navigate(fragment: Fragment, destination: NavDirections) {
    navigate(fragment.view!!, destination)
}

fun navigate(view: View, id: Int, args: Bundle) {
    try {
        Navigation.findNavController(view).navigate(id, args)
    } catch (e: IllegalArgumentException) {
        Log.e("navigate : ", "Multiple navigation attempts handled.")
    }

}