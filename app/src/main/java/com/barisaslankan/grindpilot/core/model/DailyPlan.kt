package com.barisaslankan.grindpilot.core.model

data class DailyPlan(
    val id : String,
    val ownerId : String,
    val dailyWorks : ArrayList<Goal>?,
    val generalProgress : Double,
)
