package io.nemiron.meetgo.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import io.nemiron.domain.entities.CommonError
import io.nemiron.meetgo.extensions.SingleLiveEvent
import io.nemiron.meetgo.usecase.TagsInteractor
import io.nemiron.meetgo.view.items.TagItem

class TagsViewModel(private val interactor: TagsInteractor) : ViewModel() {
    data class TagsScreenState(
        val isProgressIndicated: Boolean = false
    )

    private val checkedTags = mutableListOf<TagItem>()

    private val _state = MutableLiveData<TagsScreenState>()
    private val _errorMessage = SingleLiveEvent<CommonError>()
    private val _navigateTo = SingleLiveEvent<Unit>()

    val state: LiveData<TagsScreenState>
        get() = _state.distinctUntilChanged()

    val errorMessage: LiveData<CommonError>
        get() = _errorMessage

    val navigateTo: LiveData<Unit>
        get() = _navigateTo


    interface OnTagSelectedListener {

    }
}