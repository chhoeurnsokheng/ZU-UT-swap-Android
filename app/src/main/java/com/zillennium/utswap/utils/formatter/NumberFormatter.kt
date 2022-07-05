package com.zillennium.utswap.utils.formatter

import java.text.DecimalFormat
import java.util.*

/**
 * @author chhoeurnsokheng
 * Created 5/7/22 at 3:16 PM
 * By Mac
 */
object NumberFormatter {

    fun formatNumber(value: Any, minDigits: Int = 0): String {
        val formatter = DecimalFormat()
        formatter.minimumFractionDigits = minDigits
        return formatter.format(value)
    }

    fun formatPrice(
        value: Double?,
        currencyCode: String = "USD",
        digits: Int = 0
    ): String {
        return formatPriceValue(value ?: 0.0, currencyCode, digits)
    }

    private fun formatPriceValue(value: Any, currencyCode: String, digits: Int): String {
        val formatter = DecimalFormat.getCurrencyInstance()
        formatter.currency = Currency.getInstance(currencyCode)
        formatter.minimumFractionDigits = digits
        return formatter.format(value)
    }
}