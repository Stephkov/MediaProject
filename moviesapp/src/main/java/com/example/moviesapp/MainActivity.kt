package com.example.moviesapp

import android.os.Bundle
import com.example.mediaproject.MainActivity_Base
import com.example.mediaproject.utilities.Constants

class MainActivity : MainActivity_Base() {

    override fun getStorageKey(): String {
        return Constants.SP_KEYS.MOVIES_KEY
    }


}