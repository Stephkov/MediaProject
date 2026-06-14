package com.example.seriesapp


import com.example.mediaproject.MainActivity_Base
import com.example.mediaproject.utilities.Constants

class MainActivity : MainActivity_Base() {

    override fun getStorageKey(): String {
        return Constants.SP_KEYS.SERIES_KEY
    }


}