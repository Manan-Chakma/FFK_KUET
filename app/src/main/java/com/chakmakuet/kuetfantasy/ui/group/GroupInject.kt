package com.chakmakuet.kuetfantasy.ui.group

import android.content.Context
import androidx.lifecycle.ViewModelProvider

object GroupInject {

    private fun provideGithubRepository(context: Context): GroupRepo {
        return GroupRepo(context)
    }


    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return GroupFactory(provideGithubRepository(context))
    }
}