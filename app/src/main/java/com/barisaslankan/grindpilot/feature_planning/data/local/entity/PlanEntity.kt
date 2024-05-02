package com.barisaslankan.grindpilot.feature_planning.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.barisaslankan.grindpilot.core.util.PLANS_TABLE

@Entity(tableName = PLANS_TABLE)
data class PlanEntity(
    @PrimaryKey
    val id : String = "",
    val name : String = "",
    val goals : List<String> = arrayListOf(),
    val days : List<String> = arrayListOf(),
    val planDuration: Double = 0.0
)
