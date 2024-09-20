package com.artemklymenko.sneakersapp.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

fun getTimeAgo(isoString: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault())
    val pastDate = ZonedDateTime.parse(isoString, formatter)

    val currentDate = ZonedDateTime.now()

    val months = ChronoUnit.MONTHS.between(pastDate, currentDate)
    val weeks = ChronoUnit.WEEKS.between(pastDate, currentDate)
    val days = ChronoUnit.DAYS.between(pastDate, currentDate)

    return when {
        months > 0 -> "$months month${if (months > 1) "s" else ""} ago"
        weeks > 0 -> "$weeks week${if (weeks > 1) "s" else ""} ago"
        days > 0 -> "$days day${if (days > 1) "s" else ""} ago"
        else -> "Today"
    }
}