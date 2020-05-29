package io.nemiron.meetgo.extensions

import android.view.ViewGroup
import androidx.core.view.forEach

fun ViewGroup.disableElements() {
    this.forEach { it.isEnabled = false }
}

fun ViewGroup.enableElements() {
    this.forEach { it.isEnabled = true }
}
