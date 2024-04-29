package com.barisaslankan.grindpilot.feature_planning.data.repository

import com.barisaslankan.grindpilot.core.util.FIRESTORE_GOALS
import com.barisaslankan.grindpilot.core.util.FIRESTORE_PLANS
import com.barisaslankan.grindpilot.core.util.FIRESTORE_USERS
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.core.util.await
import com.barisaslankan.grindpilot.feature_planning.domain.model.Goal
import com.barisaslankan.grindpilot.feature_planning.domain.model.Plan
import com.barisaslankan.grindpilot.feature_planning.domain.model.ProgressType
import com.barisaslankan.grindpilot.feature_planning.domain.repository.PlanningRemoteDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID
import javax.inject.Inject

class PlanningRemoteDataSourceImpl @Inject constructor(
    val auth : FirebaseAuth,
    val db : FirebaseFirestore
) : PlanningRemoteDataSource {

    val user = auth.currentUser

    override fun fetchGoals(): Flow<Resource<ArrayList<Goal>>> = flow {
        try {
           val result = db.collection(FIRESTORE_USERS).document(user!!.uid).collection(FIRESTORE_GOALS).get().await()

            val goals = result.documents.mapNotNull {
                it.toObject(Goal::class.java)
            }

            emit(Resource.Success(ArrayList(goals)))

        }catch (e : Exception){
            emit(Resource.Error(e.localizedMessage?.toString() ?: "Something went wrong!"))
        }
    }

    override fun fetchPlans(): Flow<Resource<ArrayList<Plan>>> = flow{
        try {
            val result = db.collection(FIRESTORE_USERS).document(user!!.uid).collection(FIRESTORE_PLANS).get().await()

            val plans = result.documents.mapNotNull {
                it.toObject(Plan::class.java)
            }

            emit(Resource.Success(ArrayList(plans)))

        }catch (e: Exception){
            emit(Resource.Error(e.localizedMessage?.toString() ?: "Something went wrong!"))
        }
    }

    override suspend fun createPlan(
        name: String,
        goals: ArrayList<Goal>
    ): Resource<Plan> {
        return try {
            val uuid = UUID.randomUUID().toString()
            val plan = Plan(id = uuid, ownerId = user!!.uid, name = name, goals = goals)
            db.collection(FIRESTORE_USERS).document(plan.ownerId).collection(FIRESTORE_PLANS).document(plan.id).set(plan).await()
            Resource.Success(plan)
        }catch (e: Exception){
            Resource.Error(e.localizedMessage?.toString() ?: "Something went wrong!")
        }
    }

    override suspend fun createGoal(
        id : String,
        name: String,
        progressType: ProgressType,
        tasks: ArrayList<String>?,
        workTime: String,
        totalWork: Double
    ): Resource<Goal> {
        return try{
            val goal = Goal(id = id, ownerId = user!!.uid, name = name, progressType = progressType, tasks = tasks, workTime = workTime, totalWork = totalWork)
            db.collection(FIRESTORE_USERS).document(goal.ownerId).collection(FIRESTORE_GOALS).document(goal.id).set(goal).await()
            Resource.Success(data = goal)
        }catch (e: Exception){
            Resource.Error(message = e.localizedMessage?.toString() ?: "Something went wrong!")
        }
    }

    override suspend fun updateGoal(goal: Goal) : Resource<Goal> {
        return try {
            val updatedCurrent = mapOf("current" to goal.current)
            db.collection(FIRESTORE_USERS).document(goal.ownerId).collection(FIRESTORE_GOALS).document(goal.id).update(updatedCurrent).await()
            Resource.Success(data = goal)
        }catch (e : Exception){
            Resource.Error(message = e.localizedMessage?.toString() ?: "Something went wrong!")
        }
    }
}