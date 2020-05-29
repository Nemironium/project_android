package io.nemiron.meetgo.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

private fun Activity.hideKeyboard() {
    currentFocus?.let {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun Activity.clearFocus(view: View?) {
    this.hideKeyboard()
    view?.clearFocus()
}
