package com.jacksafblaze.newshub.presentation.adapter

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object TimeAgo {

    fun publishedAtToTimeAgo(publishedAt: String): String {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val publishedAtLDT = LocalDateTime.parse(publishedAt, dateFormat)
        val nowLDT = LocalDateTime.now()
        val difInYears = nowLDT.year - publishedAtLDT.year
        val difInMonths = nowLDT.monthValue - publishedAtLDT.monthValue
        val difInDays = nowLDT.dayOfMonth - publishedAtLDT.dayOfMonth
        val difInWeeks = difInDays / 7
        val difInHours = nowLDT.hour - publishedAtLDT.hour
        val difInMinutes = nowLDT.minute - publishedAtLDT.minute
        val timeAgo: String = if (difInYears > 0) {
            if (difInYears == 1) {
                "$difInYears year ago"
            } else {
                "$difInYears years ago"
            }
        } else if (difInMonths > 0) {
            if (difInMonths == 1) {
                "$difInMonths month ago"
            } else {
                "$difInMonths months ago"
            }
        } else if (difInWeeks > 0) {
            if (difInWeeks == 1) {
                "$difInWeeks week ago"
            } else {
                "$difInWeeks weeks ago"
            }
        } else if (difInDays > 0) {
            if (difInDays == 1) {
                "$difInDays day ago"
            } else {
                "$difInDays days ago"
            }
        } else if (difInHours > 0) {
            if (difInHours == 1) {
                "$difInHours month ago"
            } else {
                "$difInHours months ago"
            }
        } else if (difInMinutes > 0) {
            if (difInMinutes == 1) {
                "$difInMinutes minute ago"
            } else {
                "$difInMinutes minutes ago"
            }
        } else {
            "less than a minute ago"
        }
        return timeAgo
    }

}