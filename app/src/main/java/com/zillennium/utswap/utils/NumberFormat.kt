package com.zillennium.utswap.utils

import android.text.InputFilter
import android.text.Spanned
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
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


fun formatThreeDigitValue(value: Number, formatString: String): String? {
   val DECIMAL_FORMAT = "###,###.#"
    val formatSymbols = DecimalFormatSymbols(Locale.ENGLISH)
    formatSymbols.decimalSeparator = '.'
    formatSymbols.groupingSeparator = ' '
    val formatter = DecimalFormat(formatString, formatSymbols)
    return formatter.format(value)
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

        return if (source.isEmpty()){
            if (mPattern.matcher(dest.removeRange(dstart, dend)).matches()) {
                // No changes to source
                null
            } else {
                // Don't delete characters, return the old subsequence
                dest.subSequence(dstart, dend)
            }
        } else {
            // Check the result
            if (mPattern.matcher(dest.replaceRange(dstart, dend, source)).matches()) {
                // No changes to source
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
