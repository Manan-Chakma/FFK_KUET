package com.chakmakuet.kuetfantasy.ui.group

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GroupFactory(private val repository: GroupRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GroupViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GroupViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}