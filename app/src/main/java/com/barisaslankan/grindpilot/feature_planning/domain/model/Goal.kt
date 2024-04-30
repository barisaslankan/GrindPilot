package com.barisaslankan.grindpilot.feature_planning.domain.model

data class Goal(
    val id: String = "",
    val ownerId: String = "",
    val name: String = "",
    val progressType: ProgressType = ProgressType.HOURS,
    val tasks: List<Task>? = null,
    val current: Double = 0.0,
    val workTime: String = "",
    val totalWork: Double = 0.0
)

