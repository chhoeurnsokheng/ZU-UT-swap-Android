package com.zillennium.utswap.utils

import android.text.TextUtils
import android.util.Patterns

class validate {
    fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

        fun isValidPhoneNumber(number: String): Boolean {
            if (number.length >= 8){
                return !TextUtils.isEmpty(number) && Patterns.PHONE.matcher(number).matches()
            }
            return false
        }
}