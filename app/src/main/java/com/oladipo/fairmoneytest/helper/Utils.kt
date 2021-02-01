package com.oladipo.fairmoneytest.helper

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class Utils{

    companion object{

        fun isConnectionAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
        }

        fun useSnackBar(view: View, message: String){
            Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
        }

        fun toastMessage(context: Context, message: String){
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}