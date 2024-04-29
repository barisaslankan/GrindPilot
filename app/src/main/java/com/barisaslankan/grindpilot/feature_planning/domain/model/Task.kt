package com.barisaslankan.grindpilot.feature_planning.domain.model

data class Task(
    val id : String = "",
    val ownerId : String = "",
    val goalName : String = "",
    val taskName : String = "",
    val hour : Double = 0.0,
    val duration : Double? = 0.0
)
