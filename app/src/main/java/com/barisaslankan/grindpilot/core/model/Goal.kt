package com.barisaslankan.grindpilot.core.model

data class Goal(
    val id : String = "",
    val ownerId : String = "",
    val name : String = "",
    val progressType : ProgressType = ProgressType.HOURS,
    val tasks : ArrayList<String>? = null,
    val progress : Double = 0.0,
    val workTime : String = "",
    val totalWork : Double = 0.0,
)
