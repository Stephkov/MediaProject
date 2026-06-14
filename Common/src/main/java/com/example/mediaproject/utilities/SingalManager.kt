package com.example.mediaproject.utilities

import android.content.Context
import android.widget.Toast

object SignalManager {

    fun toast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}