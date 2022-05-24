package com.zillennium.utswap.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

fun groupingSeparator(number: Number): String{
    val symbols = DecimalFormatSymbols()
    symbols.groupingSeparator = ' '
    val df = DecimalFormat("###,##0.00", symbols)
//    val df = DecimalFormat()
//    df.decimalFormatSymbols = symbols
//    df.groupingSize = 3
//    df.maximumFractionDigits = 2

    return df.format(number)
}