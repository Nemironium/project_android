package io.nemiron.meetgo.view.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class CoroutineViewModel : ViewModel(), CoroutineScope {
    override val coroutineContext =
        SupervisorJob() + Dispatchers.Main
}