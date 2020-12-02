package com.percivalruiz.budget.utils

import java.text.NumberFormat
import java.util.*

fun Double.formatMoney(): String {
    val format = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 4
    format.currency = Currency.getInstance("PHP")
    return format.format(this)
}

fun String.safeToDouble(): Double {
    return if (this.isEmpty()) {
        0.0
    } else {
        this.toDouble()
    }
}