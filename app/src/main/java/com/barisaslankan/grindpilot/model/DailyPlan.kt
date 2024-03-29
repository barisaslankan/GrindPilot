package com.barisaslankan.grindpilot.model

data class DailyPlan(
    val id : String,
    val ownerId : String,
    val dailyWorks : ArrayList<Goal>?,
    val generalProgress : Double,
)
