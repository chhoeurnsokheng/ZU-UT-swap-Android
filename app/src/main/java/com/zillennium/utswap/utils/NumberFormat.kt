package com.zillennium.utswap.utils

import android.text.InputFilter
import android.text.Spanned
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.regex.Matcher
import java.util.regex.Pattern

fun groupingSeparator(number: Any): String{
    val symbols = DecimalFormatSymbols()
    symbols.groupingSeparator = ' '
    val df = DecimalFormat("###,##0.00", symbols)
//    val df = DecimalFormat()
//    df.decimalFormatSymbols = symbols
//    df.groupingSize = 3
//    df.maximumFractionDigits = 2

    return df.format(number)
}

fun groupingSeparatorInt(number: Any): String{
    val symbols = DecimalFormatSymbols()
    symbols.groupingSeparator = ' '
    val df = DecimalFormat("###,###", symbols)
//    val df = DecimalFormat()
//    df.decimalFormatSymbols = symbols
//    df.groupingSize = 3
//    df.maximumFractionDigits = 2

    return df.format(number)
}

internal class DecimalDigitsInputFilter(digitsBeforeZero: Int, digitsAfterZero: Int) :
    InputFilter {
    private val mPattern: Pattern
    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        val matcher: Matcher = mPattern.matcher(dest)
        return if (!matcher.matches()) "" else null
    }

    init {
        mPattern =
            Pattern.compile("[0-9]{0," + (digitsBeforeZero - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?")
    }
}
fun groupingSeparatorPhoneNumber(number: Any): String{
    val symbols = DecimalFormatSymbols()
    symbols.groupingSeparator = ' '
    val df = DecimalFormat("### ### ####", symbols)
//    val df = DecimalFormat()
//    df.decimalFormatSymbols = symbols
//    df.groupingSize = 3
//    df.maximumFractionDigits = 2

    return df.format(number)
}
