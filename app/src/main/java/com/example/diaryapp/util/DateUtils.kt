package com.example.diaryapp.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
    private val dateTimeFormat = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault())
    private val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    /**
     * 日付を文字列に変換（yyyy/MM/dd）
     */
    fun formatDate(date: Date): String {
        return dateFormat.format(date)
    }

    /**
     * 日時を文字列に変換（yyyy/MM/dd HH:mm）
     */
    fun formatDateTime(date: Date): String {
        return dateTimeFormat.format(date)
    }

    /**
     * 時刻を文字列に変換（HH:mm）
     */
    fun formatTime(date: Date): String {
        return timeFormat.format(date)
    }

    /**
     * 相対時間を表示（今日、昨日、など）
     */
    fun getRelativeTimeString(date: Date): String {
        val now = Calendar.getInstance()
        val targetCal = Calendar.getInstance().apply { time = date }

        val diffDays = (now.get(Calendar.DAY_OF_YEAR) - targetCal.get(Calendar.DAY_OF_YEAR))
        val diffYears = now.get(Calendar.YEAR) - targetCal.get(Calendar.YEAR)

        return when {
            diffYears == 0 && diffDays == 0 -> "今日"
            diffYears == 0 && diffDays == 1 -> "昨日"
            diffYears == 0 && diffDays in 2..6 -> "${diffDays}日前"
            else -> formatDate(date)
        }
    }
}