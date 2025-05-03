package com.example.linkedin_clone.utils.Navigation

import android.content.Context

interface navigateProviders {
    fun navigateToAnotherActivity(context: Context, desiredActivity: Class<*>)
    fun navigateMsgToAnotherActivity(context: Context, key: String, value: String, desiredActivity: Class<*>)
}