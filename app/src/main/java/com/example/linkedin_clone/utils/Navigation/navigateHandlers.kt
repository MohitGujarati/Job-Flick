package com.example.linkedin_clone.utils.Navigation

import android.content.Context
import android.content.Intent

class navigateHandlers :navigateProviders {
    override fun navigateToAnotherActivity(context: Context, desiredActivity: Class<*>) {
        val intent = Intent(context, desiredActivity)
        context.startActivity(intent)
    }

    override fun navigateMsgToAnotherActivity(context: Context, key: String, value: String, desiredActivity: Class<*>) {
        val intent = Intent(context, desiredActivity)
        intent.putExtra(key, value)
        context.startActivity(intent)
    }
}