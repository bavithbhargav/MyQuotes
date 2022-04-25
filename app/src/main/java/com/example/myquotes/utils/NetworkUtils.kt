package com.example.myquotes.utils

import android.content.Context
import android.net.ConnectivityManager




class NetworkUtils {

    companion object{
        fun isConnectingToInternet(mContext: Context): Boolean {
            val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return if (cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected == true) {
                true
            } else false
        }
    }
}