package com.barisaslankan.grindpilot.feature_planning.data.repository

import com.barisaslankan.grindpilot.core.util.NetworkHelper
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.feature_planning.data.local.entity.GoalEntity
import com.barisaslankan.grindpilot.feature_planning.data.local.entity.PlanEntity
import com.barisaslankan.grindpilot.feature_planning.data.util.mappers.DayMapper.mapStringToDay
import com.barisaslankan.grindpilot.feature_planning.data.util.mappers.toGoal
import com.barisaslankan.grindpilot.feature_planning.data.util.mappers.toGoalEntity
import com.barisaslankan.grindpilot.feature_planning.data.util.mappers.toPlanEntity
import com.barisaslankan.grindpilot.feature_planning.domain.model.Goal
import com.barisaslankan.grindpilot.feature_planning.domain.model.Plan
import com.barisaslankan.grindpilot.feature_planning.domain.model.ProgressType
import com.barisaslankan.grindpilot.feature_planning.domain.repository.PlanningLocalDataSource
import com.barisaslankan.grindpilot.feature_planning.domain.repository.PlanningRemoteDataSource
import com.barisaslankan.grindpilot.feature_planning.domain.repository.PlanningRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import java.util.UUID
import javax.inject.Inject

class PlanningRepositoryImpl @Inject constructor(
    private val remoteDataSource : PlanningRemoteDataSource,
    private val localDataSource : PlanningLocalDataSource,
    private val networkHelper: NetworkHelper
) : PlanningRepository{

    override fun getGoals(): Flow<Resource<List<Goal>>>  = flow{
        try {
            val goals = localDataSource.getAllGoals()
            emit(Resource.Success(goals.first().map { it.toGoal("") }))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong!"))
        }
    }

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
            emit(Resource.Success(plans.first().map {
                Plan(
                    it.id,
                    "",
                    it.name,
                    ArrayList(getGoalsByIds(it.goals).map { goalEntity -> goalEntity.toGoal("") }),
                    it.days.map { dayString -> mapStringToDay(dayString) }
            )
            }))
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

    override suspend fun createPlan(
        name: String,
        goals: ArrayList<Goal>
    ): Resource<Plan> {
        return remoteDataSource.createPlan(name, goals)
    }

    override suspend fun createGoal(
        name: String,
        progressType: ProgressType,
        tasks: ArrayList<String>?,
        workTime: String,
        totalWork: Double
    ): Resource<Goal> {
        val uuid = UUID.randomUUID().toString()
        return try {
            val goalEntity = GoalEntity(
                id = uuid,
                name = name,
                progressType = progressType,
                tasks = tasks,
                workTime = workTime,
                totalWork = totalWork
            )
            localDataSource.insertGoal(goalEntity)
            if(networkHelper.isNetworkConnected()){
                val result = remoteDataSource.createGoal(
                    id = uuid,
                    name = name,
                    progressType = progressType,
                    tasks = tasks,
                    workTime = workTime,
                    totalWork = totalWork
                )
                when(result){
                    is Resource.Success -> {
                        Resource.Success(goalEntity.toGoal(""))
                    }
                    is Resource.Error -> {
                        //Eski haline getir.
                        return Resource.Error(message = "Remote update failed")
                    }
                }
            }else {
                Resource.Error("Please check your connection")
            }
        }catch (e: Exception) {
            Resource.Error(message = e.localizedMessage ?: "Something went wrong!")
        }
    }

    override suspend fun updateGoalProgress(goalId: String, current: Double): Resource<Boolean> {
        return try {

            localDataSource.updateGoalProgress(goalId, current)

            if (networkHelper.isNetworkConnected()) {
                val updatedGoal = localDataSource.getGoalById(goalId)
                val result = remoteDataSource.updateGoal(updatedGoal.toGoal(""))

                when(result){
                    is Resource.Success -> {
                        Resource.Success(true)
                    }
                    is Resource.Error -> {
                        localDataSource.updateGoalProgress(goalId, current)
                        return Resource.Error(message = "Remote update failed")
                    }
                }
            } else {
                Resource.Error("Network error")
            }
        } catch (e: Exception) {
            Resource.Error(message = e.localizedMessage ?: "Something went wrong!")
        }
    }

    private suspend fun getGoalsByIds(goalIds: List<String>): List<GoalEntity> {
        return localDataSource.getGoalsByIds(goalIds)
    }
}