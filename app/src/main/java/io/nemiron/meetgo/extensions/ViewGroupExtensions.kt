package io.nemiron.meetgo.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach

fun ViewGroup.disableElements() {
    this.forEach { it.isEnabled = false }
}

fun ViewGroup.enableElements() {
    this.forEach { it.isEnabled = true }
}

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this.context).inflate(layoutId, this, attachToRoot)
}
