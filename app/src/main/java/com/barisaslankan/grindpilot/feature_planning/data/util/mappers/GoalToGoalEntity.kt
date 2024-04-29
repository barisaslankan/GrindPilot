package com.barisaslankan.grindpilot.feature_planning.data.util.mappers

import com.barisaslankan.grindpilot.feature_planning.data.local.entity.GoalEntity
import com.barisaslankan.grindpilot.feature_planning.domain.model.Goal

fun Goal.toGoalEntity() : GoalEntity{
    return GoalEntity(
        id = id,
        name = name,
        progressType = progressType,
        tasks = tasks,
        current = current,
        workTime = workTime,
        totalWork = totalWork
    )
}