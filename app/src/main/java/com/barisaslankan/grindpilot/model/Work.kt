package com.barisaslankan.grindpilot.model

data class Work(
    val id : String,
    val ownerId : String,
    val goalName : String,
    val clock : Double,
    val workTime : Double
)
