package com.sergeenko.mylibrary

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

object LibraryInstance {

    val TAG = "LIBRARY_TAG"

    fun initLibrary(ctx: Context){
        val fb = FirebaseMessaging.getInstance()
        fb.token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            Log.d(TAG, token.toString())
            Toast.makeText(ctx, token, Toast.LENGTH_LONG).show()
        })

        fb.subscribeToTopic("weather")
            .addOnCompleteListener { task ->
                Log.d(TAG, task.isSuccessful.toString())
            }
    }

}