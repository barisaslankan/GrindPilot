package com.barisaslankan.grindpilot.feature.planning.data.repository

import com.barisaslankan.grindpilot.core.model.Goal
import com.barisaslankan.grindpilot.core.model.Plan
import com.barisaslankan.grindpilot.core.util.NetworkHelper
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.feature.planning.data.local.entity.GoalEntity
import com.barisaslankan.grindpilot.feature.planning.data.local.entity.PlanEntity
import com.barisaslankan.grindpilot.feature.planning.data.util.mappers.toGoal
import com.barisaslankan.grindpilot.feature.planning.data.util.mappers.toGoalEntity
import com.barisaslankan.grindpilot.feature.planning.data.util.mappers.toPlanEntity
import com.barisaslankan.grindpilot.feature.planning.domain.repository.PlanningLocalDataSource
import com.barisaslankan.grindpilot.feature.planning.domain.repository.PlanningRemoteDataSource
import com.barisaslankan.grindpilot.feature.planning.domain.repository.PlanningRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

class PlanningRepositoryImpl @Inject constructor(
    private val remoteDataSource : PlanningRemoteDataSource,
    private val localDataSource : PlanningLocalDataSource,
    private val networkHelper: NetworkHelper
) : PlanningRepository{

    override fun getGoals(): Flow<Resource<List<Goal>>>  = flow{
        try {
            val goals = localDataSource.getAllGoals()
            emit(Resource.Success(goals.first().map { it.toGoal() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong!"))
        }
    }

    //bunu taşı
    override suspend fun updateGoals(): Resource<List<GoalEntity>> {
        if (networkHelper.isNetworkConnected()) {
            return try {
                val remoteGoals = remoteDataSource.fetchGoals().firstOrNull()

                if (remoteGoals is Resource.Success) {
                    localDataSource.clearGoals()
                    localDataSource.upsertGoals(remoteGoals.data!!.map { it.toGoalEntity() })
                    Resource.Success(localDataSource.getAllGoals().first())
                } else{
                    Resource.Error(message = remoteGoals?.message ?: "Error fetching goals")
                }

            } catch (e: Exception) {
                Resource.Error(e.localizedMessage ?: "Something went wrong!")
            }
        }else{
            return Resource.Error(message = "No connection")
        }
    }

    override fun getPlans(): Flow<Resource<List<Plan>>> = flow {
        try {
            val plans = localDataSource.getAllPlans()
            emit(Resource.Success(plans.first().map { Plan(
                it.id,
                it.ownerId,
                it.name,
                ArrayList(getGoalsByIds(it.goals).map { it.toGoal() })
            )}))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong!"))
        }
    }

    override suspend fun updatePlans(): Resource<List<PlanEntity>> {
        if (networkHelper.isNetworkConnected()) {
            return try {
                val remoteGoals = remoteDataSource.fetchPlans().firstOrNull()

                if (remoteGoals is Resource.Success) {
                    localDataSource.clearPlans()
                    localDataSource.upsertPlans(remoteGoals.data!!.map { it.toPlanEntity() })
                    Resource.Success(localDataSource.getAllPlans().first())
                } else{
                    Resource.Error(message = remoteGoals?.message ?: "Error fetching goals")
                }

            } catch (e: Exception) {
                Resource.Error(e.localizedMessage ?: "Something went wrong!")
            }
        }else{
            return Resource.Error(message = "No connection")
        }
    }

    override suspend fun addPlan(plan: Plan): Resource<Plan> {
        return try {
            remoteDataSource.uploadPlan(plan)
            Resource.Success(data = plan)
        }catch (e: Exception){
            Resource.Error(message = e.localizedMessage ?: "Something went wrong!")
        }
    }

    private suspend fun getGoalsByIds(goalIds: List<String>): List<GoalEntity> {
        return localDataSource.getGoalsByIds(goalIds)
    }
}