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
    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence? {
        return if (source.isEmpty()) {
            // When the source text is empty, we need to remove characters and check the result
            if (mPattern.matcher(dest.removeRange(dstart, dend)).matches()) {
                null
            } else {
                dest.subSequence(dstart, dend)
            }
        } else {
            // Check the result
            if (mPattern.matcher(dest.replaceRange(dstart, dend, source)).matches()) {
                null
            } else {
                // Return nothing
                ""
            }
        }
    }

    init {
        mPattern = Pattern.compile("(\\d{0,$digitsBeforeZero})|(\\d{0,$digitsBeforeZero}\\.\\d{0,$digitsAfterZero})")
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
