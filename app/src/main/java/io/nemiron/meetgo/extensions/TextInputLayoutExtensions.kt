package io.nemiron.meetgo.extensions

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout
import io.nemiron.meetgo.R

fun TextInputLayout.setDefaultColor() {
    setBoxStrokeColorStateList(ContextCompat.getColorStateList(context, R.color.text_default_input_box_stroke)!!)
    defaultHintTextColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPrimary))
}

fun TextInputLayout.setErrorColor() {
    setBoxStrokeColorStateList(ContextCompat.getColorStateList(context, R.color.text_invalid_input_box_stroke)!!)
    defaultHintTextColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorError))
}

fun TextInputLayout.setAcceptColor() {
    defaultHintTextColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorAccept))
    setBoxStrokeColorStateList(ContextCompat.getColorStateList(context, R.color.text_accept_input_box_stroke)!!)
}