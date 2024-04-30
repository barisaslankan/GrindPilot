package com.barisaslankan.grindpilot.feature_planning.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.barisaslankan.grindpilot.core.util.GOALS_TABLE
import com.barisaslankan.grindpilot.feature_planning.domain.model.ProgressType

@Entity(tableName = GOALS_TABLE)
data class GoalEntity(
    @PrimaryKey
    val id : String = "",
    val name : String = "",
    val progressType : ProgressType = ProgressType.HOURS,
    val tasks : String? = "",
    val current : Double = 0.0,
    val workTime : String = "",
    val totalWork : Double = 0.0,
)