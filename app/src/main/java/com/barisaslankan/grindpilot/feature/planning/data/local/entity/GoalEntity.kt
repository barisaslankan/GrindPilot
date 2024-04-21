package com.barisaslankan.grindpilot.feature.planning.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.barisaslankan.grindpilot.core.util.GOALS_TABLE
import com.barisaslankan.grindpilot.core.model.ProgressType

@Entity(tableName = GOALS_TABLE)
data class GoalEntity(
    @PrimaryKey
    val id : String = "",
    val ownerId : String = "",
    val name : String = "",
    val progressType : ProgressType = ProgressType.HOURS,
    val tasks : List<String>? = null,
    val progress : Double = 0.0,
    val workTime : String = "",
    val totalWork : Double = 0.0,
)