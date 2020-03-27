package io.nemiron.meetgo

import androidx.lifecycle.ViewModel
import io.nemiron.meetgo.core.entities.UserInfo
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userModule = module {
    viewModel { UserSharedViewModel() }
}

class UserSharedViewModel : ViewModel() {
    val userInfo = UserInfo()

}