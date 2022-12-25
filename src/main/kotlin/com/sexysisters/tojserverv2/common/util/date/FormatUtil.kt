package com.sexysisters.tojserverv2.common.util.date

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FormatUtil {
    companion object {
        private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")

        fun format(date: LocalDateTime): String = date.format(formatter)
    }
}