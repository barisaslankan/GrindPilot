package com.barisaslankan.grindpilot.feature_planning.domain.model

data class Plan(
    val id : String = "",
    val ownerId: String = "",
    val name : String = "",
    val goals : ArrayList<Goal> = arrayListOf(),
    val days : List<String> = emptyList(),
    val planDuration: Double = 0.0
)

enum class DurationType{
    DAYS, WEEKS, MONTHS
}

enum class Day {
    MON, TUE, WED, THU, FRI, SAT, SUN
}
