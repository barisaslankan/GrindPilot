package com.barisaslankan.grindpilot.feature_planning.data.util.mappers

import com.barisaslankan.grindpilot.feature_planning.domain.model.Day

object DayMapper {
    fun mapStringToDay(dayString: String): Day {
        return when (dayString.uppercase()) {
            "MON" -> Day.MON
            "TUE" -> Day.TUE
            "WED" -> Day.WED
            "THU" -> Day.THU
            "FRI" -> Day.FRI
            "SAT" -> Day.SAT
            "SUN" -> Day.SUN
            else -> throw IllegalArgumentException("Invalid day string: $dayString")
        }
    }
}
