package com.barisaslankan.grindpilot.feature_planning.data.repository

import com.barisaslankan.grindpilot.feature_planning.data.local.PlanningDatabase
import com.barisaslankan.grindpilot.feature_planning.data.local.entity.GoalEntity
import com.barisaslankan.grindpilot.feature_planning.data.local.entity.PlanEntity
import com.barisaslankan.grindpilot.feature_planning.domain.repository.PlanningLocalDataSource
import kotlinx.coroutines.flow.Flow

class PlanningLocalDataSourceImpl(
    planningDatabase: PlanningDatabase
) : PlanningLocalDataSource {

    private val goalDao = planningDatabase.goalsDao()
    private val plansDao = planningDatabase.plansDao()

    override fun getAllGoals(): Flow<List<GoalEntity>>{
        return goalDao.getAllGoals()
    }

    override suspend fun upsertGoals(goals: List<GoalEntity>) {
        goalDao.addGoals(goals = goals)
    }

    override suspend fun clearGoals() {
        goalDao.clearGoals()
    }

    override fun getAllPlans(): Flow<List<PlanEntity>> {
        return plansDao.getAllPlans()
    }

    override suspend fun upsertPlans(plans: List<PlanEntity>) {
        plansDao.addPlans(plans)
    }

    override suspend fun clearPlans() {
        plansDao.deleteAllPlans()
    }

    override suspend fun getGoalsByIds(goalIds: List<String>): List<GoalEntity> {
        return goalDao.getGoalsByIds(goalIds)
    }

    override suspend fun updateGoalProgress(goadId: String, current: Double) {
        return goalDao.updateGoalProgress(goadId, current)
    }

    override suspend fun getGoalById(goalId: String): GoalEntity {
        return goalDao.getGoalById(goalId)
    }

    override suspend fun insertGoal(goal: GoalEntity) {
        return goalDao.insertGoal(goal = goal)
    }

    override suspend fun insertPlan(plan: PlanEntity) {
        return plansDao.insertPlan(plan)
    }

}