package com.arditb.cryptotodate.utils

import android.widget.Spinner

fun getIndex(spinner: Spinner, myString: String): Int {
    for (i in 0 until spinner.count) {
        if (spinner.getItemAtPosition(i).toString().equals(myString, ignoreCase = true)) {
            return i
        }
    }
    return 0
}