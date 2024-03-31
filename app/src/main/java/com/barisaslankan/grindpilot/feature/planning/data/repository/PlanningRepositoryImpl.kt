package com.barisaslankan.grindpilot.feature.planning.data.repository

import com.barisaslankan.grindpilot.core.util.DB_GOALS
import com.barisaslankan.grindpilot.core.util.DB_PLANS
import com.barisaslankan.grindpilot.core.util.DB_USERS
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.core.util.await
import com.barisaslankan.grindpilot.feature.planning.domain.repository.PlanningRepository
import com.barisaslankan.grindpilot.model.Goal
import com.barisaslankan.grindpilot.model.Plan
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlanningRepositoryImpl @Inject constructor(
    val auth : FirebaseAuth,
    val db : FirebaseFirestore
) : PlanningRepository {

    val user = auth.currentUser

    override fun fetchGoals(): Flow<Resource<ArrayList<Goal>>> = flow {
        try {
           val result = db.collection(DB_USERS).document(user!!.uid).collection(DB_GOALS).get().await()

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
            val result = db.collection(DB_USERS).document(user!!.uid).collection(DB_PLANS).get().await()

            val plans = result.documents.mapNotNull {
                it.toObject(Plan::class.java)
            }

            emit(Resource.Success(ArrayList(plans)))

        }catch (e: Exception){
            emit(Resource.Error(e.localizedMessage?.toString() ?: "Something went wrong!"))
        }
    }

    override suspend fun uploadPlan(plan : Plan): Resource<Plan> {
        return try {
            db.collection(DB_USERS).document(user!!.uid).collection(DB_PLANS).document(plan.id).set(plan).await()
            Resource.Success(plan)
        }catch (e: Exception){
            Resource.Error(e.localizedMessage?.toString() ?: "Something went wrong!")
        }
    }
}