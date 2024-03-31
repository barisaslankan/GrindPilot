package com.barisaslankan.grindpilot.feature.set_goal.data.repository

import com.barisaslankan.grindpilot.core.util.DB_GOALS
import com.barisaslankan.grindpilot.core.util.DB_PLANS
import com.barisaslankan.grindpilot.core.util.DB_USERS
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.core.util.await
import com.barisaslankan.grindpilot.feature.set_goal.domain.repository.SetGoalRepository
import com.barisaslankan.grindpilot.model.Goal
import com.barisaslankan.grindpilot.model.Plan
import com.barisaslankan.grindpilot.model.ProgressType
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID
import javax.inject.Inject

class SetGoalRepositoryImpl @Inject constructor(
    val firebaseAuth: FirebaseAuth,
    val db : FirebaseFirestore
) : SetGoalRepository {

    val user = firebaseAuth.currentUser

    override suspend fun createPlan(name : String, goals: ArrayList<Goal>): Resource<Plan> {
        return try{
            val uuid = UUID.randomUUID().toString()
            val plan = Plan(id =uuid, ownerId = user!!.uid, goals =goals, name = name)

            db.collection(DB_USERS).document(user.uid).collection(DB_PLANS).add(plan).await()
            Resource.Success(data = plan)
        }catch (e: Exception){
            Resource.Error(message = e.localizedMessage?.toString() ?: "Something went wrong!")
        }
    }

    override suspend fun createGoal(
        name: String,
        progressType: ProgressType,
        tasks: ArrayList<String>?,
        workTime: String,
        totalWork: Double
    ): Resource<Goal> {
        return try{
            val uuid = UUID.randomUUID().toString()
            val goal = Goal(
                id = uuid,
                ownerId = user!!.uid,
                progressType = progressType,
                totalWork = totalWork,
                name = name,
                tasks = tasks,
                progress = 0.0,
                workTime = workTime
            )
            db.collection(DB_USERS).document(user.uid).collection(DB_GOALS).add(goal).await()
            Resource.Success(data = goal)
        }catch (e: Exception){
            Resource.Error(message = e.localizedMessage?.toString() ?: "Something went wrong!")
        }

    }
}